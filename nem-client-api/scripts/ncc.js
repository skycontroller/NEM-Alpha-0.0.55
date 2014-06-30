"use strict";

define(['jquery', 'ractive', 'tooltipster'], function($, Ractive) {
    var NccModal = Ractive.extend({
        template: '#modal-template',
        open: function() {
            this.set('isActive', true);
            $('.active.modal-container').focus();
        },
        close: function() {
            this.set('isActive', false);
        },
        init: function() {
            this.on({
                closeModal: function() {
                    this.close();
                },
                modalContainerClick: function(e) {
                    if (e.node === e.original.target) { //clicked outside modal
                        this.fire('closeModal');
                    }
                },
                modalContainerKeyup: function(e) {
                    if (e.original.keyCode === 27 || e.original.keyCode === 13) {
                        this.fire('closeModal');
                    }
                },
                confirm: function(e, action) {
                    var callbacks = this.get('callbacks');
                    var result;
                    if (callbacks && callbacks[action]) {
                        result = callbacks[action]();
                    }
                    if (result !== false) {
                        this.close();
                    }
                },
                submit: function() {
                    var submit = this.get('submit');
                    var values = this.get('values');
                    var result;
                    if (submit) {
                        result = submit(values, this.close.bind(this));
                    }
                    if (result !== false) {
                        this.close();
                    }
                },
                inputKeypressed: function(e) {
                    if (e.original.keyCode === 13) {
                        this.fire('submit');
                    }
                }
            });
        }
    });

    var NccRactive = Ractive.extend({
        el: document.body,
        template: '#template',
        faults: {
            101: 'File not found',
            102: 'Wallet has not been created',
            103: 'Wallet file is corrupted',
            104: 'Wrong password',
            105: 'Wallet storage location has not been set',
            106: 'Wallet is not opened',
            107: 'Wallet does not contain this account',
            200: 'Minimum fee is not met',
            201: 'Due hours for a transaction must less than one day',
            202: 'No public key',
            300: 'HTTP client has not been started',
            301: 'HTTP I/O exception',
            302: 'HTTP status 400',
            303: 'HTTP status unknown',
            304: 'URL malformed',
            305: 'NEM Infrastructure Server is not available',
            306: 'Unexpected error occurred, sorry for this',
            400: 'Some parameter is missing',
            401: 'No long value',
            402: 'No integer value',
            403: 'No big integer value'
        },
        settings: {
            fractionalDigits: 2,
            decimalMark: '.'
        },
        getPageParam: (function() {
            var qStr = location.search.substring(1, location.search.length);
            var queries = qStr.split('&');
            var params = [];
            var temp, key, value;
            for (var i = 0; i < queries.length; i++) {
                temp = queries[i].split('=');
                if (temp[0] && temp[1]) {
                    key = decodeURIComponent(temp[0]);
                    value = decodeURIComponent(temp[1]);
                    params[key] = value;
                }
            }
            return function(key) {
                return params[key];
            };
        })(),
        components: {
            errorModal: NccModal,
            messageModal: NccModal,
            confirmModal: NccModal,
            inputModal: NccModal,
            sendNemModal: NccModal,
            clientInfoModal: NccModal,
            transactionDetailsModal: NccModal
        },
        computed: {
            allAccounts: 'this.prepend([${wallet.primaryAccount}], ${wallet.additionalAddresses})'
        },
        ajaxError: function(jqXHR, textStatus, errorThrown) {
            this.showError(textStatus, errorThrown);
        },
        apiPath: function(api) {
            return '../api/' + api;
        },
        checkSuccess: function(data, silent, settings) {
            if (data.fault) {
                if (!silent) {
                    this.showError(data.fault['fault-id']);
                }
                if (settings && settings.altFailCb) {
                    settings.altFailCb(data.fault['fault-id']);
                }
                return false;
            } else if (data.error) {
                if (!silent) {
                    this.showError(data.status, data.error);
                }
                if (settings && settings.altFailCb) {
                    settings.altFailCb(data.status, data.error);
                }
                return false;
            }
            return true;
        },
        getRequest: function(api, successCb, settings, silent) {
            var self = this;
            var s = {
                dataType: 'json',
                type: 'get',
                error: silent ? [] : $.proxy(this.ajaxError, self),
                success: function(data) {
                    if (self.checkSuccess(data, silent, settings) && successCb) {
                        successCb(data);
                    }
                }
            };
            $.extend(s, settings);
            $.ajax(this.apiPath(api), s);
        },
        postRequest: function(api, requestData, successCb, settings, silent) {
            var self = this;
            var s = {
                contentType: 'application/json',
                dataType: 'json',
                type: 'post',
                data: JSON.stringify(requestData),
                error: silent ? [] : this.ajaxError.bind(self),
                success: function(data) {
                    if (self.checkSuccess(data, silent, settings) && successCb) {
                        successCb(data);
                    }
                }
            };
            $.extend(s, settings);
            $.ajax(this.apiPath(api), s); 
        },
        syncRequest: function(url) {
            return $.ajax(url, {
                async: false,
                type: 'GET',
                error: this.ajaxError
            }).responseText;
        },
        formatAddress: function(address) {
            if (typeof address === 'string') {
                return address.match(/.{1,6}/g).join('-');
            } else {
                return address;
            }
        },
        restoreAddress: function(address) {
            return address.replace(/\-/g, '');
        },
        addThousandSeparators: function(num) {
            return num.toString(10).replace(/\B(?=(\d{3})+(?!\d))/g, '&thinsp;');
        },
        minDigits: function(num, digits) {
            num = num.toString(10);
            while (num.length < digits) {
                num = '0' + num;
            }
            return num;
        },
        toNem: function(mNem) {
            return mNem / 1000000;
        },
        toMNem: function(nem) {
            return nem * 1000000;
        },
        formatCurrency: function(amount, dimTrailings) {
            var nem = this.addThousandSeparators(Math.floor(this.toNem(amount)));
            var mNem = this.minDigits(amount % 1000000, 6).substring(0, this.settings.fractionalDigits);

            if (dimTrailings) {
                var cutPos = mNem.length - 1;
                while (mNem.charAt(cutPos) === '0') {
                    cutPos--;
                }
                cutPos++;

                var clearPart = mNem.substring(0, cutPos);
                var dimmedPart = mNem.substring(cutPos, mNem.length);
                if (dimmedPart) {
                    if (clearPart) {
                        return nem + this.settings.decimalMark + clearPart + '<span class="dimmed">' + dimmedPart + '</span>';
                    } else {
                        return nem + '<span class="dimmed">' + this.settings.decimalMark + dimmedPart + '</span>';
                    }
                }
            }

            return nem + this.settings.decimalMark + mNem;
        },
        toDate: function(ms) {
            return new Date(ms);
        },
        formatDate: (function() {
            var shortMonths = {
                1: 'Jan',
                2: 'Feb',
                3: 'Mar',
                4: 'Apr',
                5: 'May',
                6: 'Jun',
                7: 'Jul',
                8: 'Aug',
                9: 'Sep',
                10: 'Oct',
                11: 'Nov',
                12: 'Dec',
            };
            return function(date, format) {
                if (typeof date === 'number') {
                    date = this.toDate(date);
                }
                var month = date.getMonth() + 1;
                var day = date.getDate();
                var year = date.getFullYear();
                var hour = date.getHours();
                var min = date.getMinutes();
                var sec = date.getSeconds();

                switch (format) {
                case 'MM/dd/yy hh:mm:ss':
                    month = this.minDigits(month, 2);
                    day = this.minDigits(day, 2);
                    year = year.toString(10);
                    year = year.substring(year.length - 2, year.length);
                    hour = this.minDigits(hour, 2);
                    min = this.minDigits(min, 2);
                    sec = this.minDigits(sec, 2);
                    return month + '/' + day + '/' + year + ' ' + hour + ':' + min + ':' + sec;
                case 'M dd, yyyy':
                    month = shortMonths[month];
                    day = this.minDigits(day, 2);
                    return month + ' ' + day + ', ' + year;
                case 'M dd, yyyy hh:mm:ss':
                    month = shortMonths[month];
                    day = this.minDigits(day, 2);
                    hour = this.minDigits(hour, 2);
                    min = this.minDigits(min, 2);
                    sec = this.minDigits(sec, 2);
                    return month + ' ' + day + ', ' + year + ' ' + hour + ':' + min + ':' + sec;
                }
            };
        })(),
        daysPassed: function(begin) {
            var now = new Date().getTime();
            var timespan = now - begin;
            var day = 1000*60*60*24;
            var days = timespan / day;
            var roundedDays = Math.round(days);
            return {
                days: days,
                roundedDays: roundedDays
            };
        },
        sortByTimeNewest: function(a, b) {
            if (a && b && a.timeStamp && b.timeStamp) {
                return b.timeStamp - a.timeStamp;
            } else {
                return 0;
            }
        },
        getModal: function(modalName) {
            return this.findComponent(modalName + 'Modal');
        },
        showModal: function(modalName) {
            this.getModal(modalName).open();
        },
        showError: function(errorId, message) {
            var modal = this.getModal('error');
            if (!message && typeof errorId === 'number') {
                message = this.faults[errorId];
            }
            modal.set({
                errorId: errorId,
                message: message || 'Unknown error'
            });
            modal.open();
        },
        showMessage: function(title, message, closeCallback) {
            var modal = this.getModal('message');
            modal.set({
                modalTitle: title,
                message: message
            });
            modal.open();
            if (closeCallback) {
                var ob = modal.observe('isActive', function(newValue, oldValue) {
                    if (newValue !== oldValue) {
                        closeCallback();
                        ob.cancel();
                    }
                });
            }
        },
        showConfirmation: function(title, message, callbacks, actions) {
            var modal = this.getModal('confirm');
            modal.set({
                modalTitle: title,
                message: message,
                callbacks: callbacks,
                actions: actions || [
                    {
                        action: 'no',
                        label: 'No',
                        cssClass: 'secondary'
                    },
                    {
                        action: 'yes',
                        label: 'Yes',
                        cssClass: 'primary'
                    }
                ]
            });
            modal.open();
        },
        showInputForm: function(title, message, fields, initValues, submit, submitLabel) {
            var modal = this.getModal('input');
            modal.set({
                fields: null,
                values: null
            });

            modal.set({
                modalTitle: title,
                message: message,
                fields: fields,
                values: initValues,
                submit: submit,
                submitLabel: submitLabel
            });
            modal.open();
        },
        toggleOn: function(id) {
            var keypath = 'active.' + id;
            if (!this.get(keypath)) {
                this.set(keypath, true);
                var firstTime = true;
                var self = this;
                $(document).on('click.' + id, function(ev) {
                    if (firstTime) {
                        firstTime = false;
                        return;
                    }
                    //if (self.nodes[id] !== ev.target && !$.contains(self.nodes[id], ev.target)) {
                    self.toggleOff(id);
                    //}
                });
            }
        },
        toggleOff: function(id) {
            this.set('active.' + id, false);
            $(document).off('click.' + id);
        },
        openWallet: function(walletData) {
            var address = walletData.primaryAccount && walletData.primaryAccount.address;
            if (address) {
                var self = this;
                this.postRequest('wallet/account/info', { wallet: walletData.name, account: address }, function(data) {
                    self.set('wallet', walletData)
                    self.set('activeAccount', data);
                    self.loadPage('dashboard');
                });
            } else {
                this.showError();
            }
        },
        prepend: function(args, array) {
            var a = array.slice(0);
            a.unshift.apply(a, args);
            return a;
        },
        loadPage: function(page, level, childInit) {
            var self = this;
            var isInnermost = false;
            if (!level && level !== 0) {
                level = 0;
                isInnermost = true;
            }

            require([page], function(layout) {
                var init = function() {
                    var keypath = 'layout.' + level;
                    var currentLayout = self.get(keypath);
                    if (isInnermost || (currentLayout && currentLayout.name) !== layout.name) {
                        var requires = layout.dependencies ? [layout.template].concat(layout.dependencies) : [layout.template];
                        require(requires, function(template) {
                            if (currentLayout && currentLayout.leave) {
                                $.each(currentLayout.leave, function() {
                                    this.apply(currentLayout);
                                });
                            }

                            // Init
                            if (!layout.alreadyInit && layout.initOnce) {
                                layout.initOnce();
                                layout.alreadyInit = true;
                            }
                            if (layout.initEverytime) {
                                layout.initEverytime();
                            }

                            self.set(keypath, null);
                            self.partials[level] = template;
                            self.set(keypath, layout);

                            // Setup
                            if (!layout.alreadySetup && layout.setupOnce) {
                                layout.setupOnce();
                                layout.alreadySetup = true;
                            }
                            if (layout.setupEverytime) {
                                layout.setupEverytime();
                            }

                            if (childInit) childInit();
                        });
                    } else {
                        if (childInit) childInit();
                    }
                };

                if (layout.parent) {
                    level = self.loadPage(layout.parent, level, init);
                } else {
                    init();
                }

                if (isInnermost) {
                    // Clear the old layout names
                    var nccLayouts = self.get('layout');
                    if (nccLayouts) {
                        for (var i = level + 1; i < nccLayouts.length; i++) {
                            self.set('layout.' + i, null);
                        }
                    }
                }
            });

            return level + 1;
        },
        init: function(options) {
            this.on({
                redirect: function(e, page) {
                    this.loadPage(page);
                },
                toggleOn: function(e, id) {
                    this.toggleOn(id);
                },
                toggleOff: function(e, id) {
                    this.toggleOff(id);
                },
                openModal: function(e, id) {
                    this.showModal(id);
                },
                shutdown: function() {
                    var self = this;
                    this.showConfirmation('Close program', 'Are you sure you want to close NEM Community Client?', {
                        yes: function() {
                            self.loadPage('start');
                        }
                    });
                },
                selectFile: function(e, id) {
                    var input = this.nodes[id];
                    var evt = document.createEvent("MouseEvents");
                    evt.initEvent('click', true, false);
                    input.dispatchEvent(evt);
                },
                registerToolTip: function(e) {
                    if (!$(e.node).data('tooltipster-ns')) {
                        var $node = $(e.node);
                        $node.tooltipster({
                            position: 'bottom',
                            delay: 50,
                            functionBefore: function($origin, continueTooltip) {
                                var title = $origin.attr('title');
                                if (title) {
                                    $origin.tooltipster('content', title);
                                    $origin.removeAttr('title');
                                }
                                continueTooltip();
                            }
                        });
                        $node.tooltipster('show');
                    }
                }
            });

            this.set('formatAddress', this.formatAddress);
            this.set('formatCurrency', this.formatCurrency);
            this.set('formatDate', this.formatDate);
            this.set('toDate', this.toDate);
            this.set('daysPassed', this.daysPassed);
            this.set('toNem', this.toNem);
        }
    });

    window.ncc = new NccRactive();
    return ncc;
});