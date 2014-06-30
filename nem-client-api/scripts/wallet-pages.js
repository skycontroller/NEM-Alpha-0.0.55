"use strict";

define(['jquery', 'ncc', 'NccLayout'], function($, ncc, NccLayout) {
    return $.extend(true, {}, NccLayout, {
        name: 'wallet',
        template: 'rv!layout/wallet-pages',
        setupOnce: function() {
            var local = this.local;

            ncc.refreshWallet = function(wallet, silent) {
                if (!wallet) wallet = ncc.get('wallet.name');

                ncc.postRequest('wallet/info', { wallet: wallet }, function(data) {
                    ncc.set('wallet', data);
                }, null, silent);
            };

            ncc.refreshAccount = function(wallet, account, silent) {
                if (!wallet) wallet = ncc.get('wallet.name');
                if (!account) account = ncc.get('activeAccount.address');

                var success = false;
                ncc.postRequest('wallet/account/info', { wallet: wallet, account: account }, function(data) {
                    success = true;
                    ncc.set('activeAccount', data);
                    ncc.set('status.lostConnection', false);
                }, {
                    complete: function() {
                        if (!success) {
                            ncc.set('status.lostConnection', true);
                        }
                    }
                }, silent);
            };

            ncc.refreshInfo = function(wallet, account, silent) {
                ncc.refreshWallet(wallet, silent);
                ncc.refreshAccount(wallet, account, silent);
            };

            ncc.showTempMessage = function(message, duration) {
                if (!duration) {
                    duration = 2000;
                }
                this.set('tempMessage.message', message);
                this.set('tempMessage.visible', true);
                var self = this;
                window.setTimeout(function() {
                    self.set('tempMessage.visible', false);
                }, duration);
            };
        },
        setupEverytime: function() {
            var local = this.local;

            require(['zeroClipboard'], function(ZeroClipboard) {
                local.client = new ZeroClipboard($('.copyClipboard'));

                local.client.on('ready', function() {
                    local.client.on('aftercopy', function() {
                        ncc.showTempMessage('Address has been copied to clipboard!');
                    });
                });

                require(['tooltipster'], function() {
                    $('#global-zeroclipboard-flash-bridge').tooltipster({
                        content: 'Click to copy address to clipboard',
                        position: 'bottom',
                        delay: 50
                    });
                });
            });

            local.listeners.push(ncc.observe('activeAccount.transactions.* transactionList.*', function(newValue, oldValue, keypath) {
                var activeAccount = ncc.get('activeAccount.address');
                if (newValue.sender === activeAccount) {
                    ncc.set(keypath + '.isOutgoing', true);
                } else {
                    ncc.set(keypath + '.isOutgoing', false);
                }
            }));

            local.listeners.push(ncc.observe('pendingTransactions activeAccount.transactions', function() {
                var activeAccount = ncc.get('activeAccount.address');
                var activePendingTransactions = this.get('pendingTransactions.' + activeAccount);
                var transactions = this.get('activeAccount.transactions');
                var pendings = [];

                if (activePendingTransactions && transactions) {
                    for (var i = 0; i < activePendingTransactions.length; i++) {
                        var id = activePendingTransactions[i].id;
                        var dup = false;
                        for (var j = 0; j < transactions.length; j++) {
                            if (id === transactions[j].id) {
                                dup = true;
                                break;
                            }
                        }

                        if (!dup) {
                            pendings.push(activePendingTransactions[i]);
                        }
                    }
                }

                var result = this.prepend(pendings, transactions);
                result.sort(ncc.sortByTimeNewest);
                this.set('allRecentTransactions', result);
            }));

            local.listeners.push(ncc.on({
                showClientInfo: function() {
                    ncc.getRequest('info', 
                        function(data) {
                            if (data.metadata) {
                                var modal = ncc.getModal('clientInfo');
                                modal.set(data);
                                modal.open();
                            } else {
                                ncc.showError();
                            }
                        }
                    );
                },
                closeWallet: function() {
                    ncc.showConfirmation('Close wallet', 'Are you sure you want to close your wallet and return to landing page?', {
                        yes: function() {
                            ncc.postRequest('wallet/close', { wallet: ncc.get('wallet.name') }, function(data) {
                                if (data.ok) {
                                    ncc.loadPage('landing');
                                } else {
                                    ncc.showError();
                                }
                            });
                        }
                    });
                },
                switchAccount: function(e, address) {
                    var wallet = ncc.get('wallet.name');
                    ncc.postRequest('wallet/account/info', { wallet: wallet, account: address }, function(data) {
                        ncc.set('activeAccount', data);
                    });
                },
                refreshInfo: function() {
                    this.refreshInfo();
                },
                createNewAccount: function() {
                    var wallet = ncc.get('wallet.name');
                    ncc.showInputForm('Create new account', '',
                        [
                            {
                                name: 'label',
                                type: 'text',
                                label: {
                                    content: 'Private Label'
                                }
                            },
                            {
                                name: 'wallet',
                                type: 'text',
                                readonly: true,
                                label: {
                                    content: 'Wallet'
                                }
                            },
                            {
                                name: 'password', 
                                type: 'password',
                                label: {
                                    content: "Wallet's password"
                                }
                            }
                        ],
                        {
                            wallet: wallet
                        },
                        function(values, closeModal) {
                            ncc.postRequest('wallet/account/new', values, function(data) {
                                if (data.address) {
                                    var label = data.label && data.label.privateLabel;
                                    ncc.showMessage('Success', 'Account ' + ncc.formatAddress(data.address) + (label? ' (' + label + ')' : '') + ' has been created!', function() {
                                        ncc.refreshInfo();
                                    });
                                    closeModal();
                                } else {
                                    ncc.showError();
                                }
                            });
                            return false;
                        }, 'Create'
                    );
                },
                addAccount: function() {
                    var wallet = ncc.get('wallet.name');
                    ncc.showInputForm('Add an Existing Account', '',
                        [
                            {
                                name: 'account_key',
                                type: 'text',
                                label: {
                                    content: "Account's Private Key"
                                }
                            },
                            {
                                name: 'wallet',
                                type: 'text',
                                readonly: true,
                                label: {
                                    content: 'Wallet'
                                }
                            },
                            {
                                name: 'password', 
                                type: 'password',
                                label: {
                                    content: "Wallet's password"
                                }
                            }
                        ],
                        {
                            wallet: wallet
                        },
                        function(values, closeModal) {
                            ncc.postRequest('wallet/account/add', values, function(data) {
                                if (data.address) {
                                    var label = data.label && data.label.privateLabel;
                                    ncc.showMessage('Success', 'Account ' + ncc.formatAddress(data.address) + (label? ' (' + label + ')' : '') + ' has been added to wallet!', function() {
                                        ncc.refreshInfo();
                                    });
                                    closeModal();
                                } else {
                                    ncc.showError();
                                }
                            });
                            return false;
                        }, 'Add'
                    );
                },
                setCurrentAccountAsPrimary: function() {
                    var account = ncc.get('activeAccount.address');
                    var accountLabel = ncc.get('activeAccount.label.privateLabel');
                    var wallet = ncc.get('wallet.name');
                    ncc.showInputForm('Set primary account', '',
                        [
                            {
                                name: 'account',
                                type: 'text',
                                readonly: true,
                                label: {
                                    content: 'Account to be set as Primary'
                                },
                                sublabel: accountLabel?
                                    {
                                        content: accountLabel
                                    } :
                                    {
                                        isHtml: true,
                                        content: '<span class="null">&lt;No label&gt;</span>'
                                    }
                            }, 
                            {
                                name: 'wallet',
                                type: 'text',
                                readonly: true,
                                label: {
                                    content: 'Wallet'
                                }
                            }, 
                            {
                                name: 'password', 
                                type: 'password',
                                label: {
                                    content: "Wallet's password"
                                }
                            }
                        ],
                        {
                            account: ncc.formatAddress(account),
                            wallet: wallet
                        },
                        function(values, closeModal) {
                            values.account = account;
                            ncc.postRequest('wallet/account/primary', values, function(data) {
                                ncc.showMessage('Success', 'Account ' + ncc.formatAddress(account) + (accountLabel? ' (' + accountLabel + ')' : '') + ' has been set as primary!', function() {
                                    ncc.refreshInfo();
                                });
                                closeModal();
                            });
                            return false;
                        }, 'Set as primary'
                    );
                },
                bootLocalNode: function() {
                    var account = ncc.get('activeAccount.address');
                    var accountLabel = ncc.get('activeAccount.label.privateLabel');
                    var wallet = ncc.get('wallet.name');
                    ncc.showInputForm('Boot local node', '',
                        [
                            {
                                name: 'account',
                                type: 'text',
                                readonly: true,
                                label: {
                                    content: 'Account to boot local node'
                                },
                                sublabel: accountLabel?
                                    {
                                        content: accountLabel
                                    } :
                                    {
                                        isHtml: true,
                                        content: '<span class="null">&lt;No label&gt;</span>'
                                    }
                            }, 
                            {
                                name: 'wallet',
                                type: 'text',
                                readonly: true,
                                label: {
                                    content: 'Wallet'
                                }
                            }, 
                            {
                                name: 'node_name', 
                                type: 'text',
                                label: {
                                    content: 'Node name'
                                }
                            }
                        ],
                        {
                            account: ncc.formatAddress(account),
                            wallet: wallet
                        },
                        function(values, closeModal) {
                            values.account = account;
                            ncc.postRequest('node/boot', values, function(data) {
                                if (data.ok) {
                                    closeModal();
                                } else {
                                    ncc.showError();
                                }
                            });
                            return false;
                        }, 'Boot'
                    );
                },
                viewTransaction: (function() {
                    var modal = ncc.getModal('transactionDetails');
                    modal.set('formatDate', ncc.get('formatDate').bind(ncc));
                    modal.set('formatAddress', ncc.get('formatAddress').bind(ncc));
                    modal.set('toNem', ncc.get('toNem').bind(ncc));
                    return function(e, transaction) {
                        modal.set('transaction', transaction);
                        modal.open();
                    }
                })()
            }));

            var modal = ncc.getModal('sendNem');

            var resetFee = (function() {
                var requestData = {
                    wallet: ncc.get('wallet.name'),
                    account: ncc.get('activeAccount.address'),
                    recipient: 'A'
                };
                return function(forceReset, silent) {
                    requestData.amount = ncc.toMNem(modal.get('amount'));
                    requestData.message = modal.get('message') && modal.get('message').toString();
                    requestData.encrypt = modal.get('encrypted')? 1 : 0;
                    
                    ncc.postRequest('wallet/account/transaction/fee', requestData, function(data) {
                            var currentFee = modal.get('fee');
                            var newFee = data.fee / 1000000;
                            if (newFee || newFee === 0) {
                                if (forceReset || !currentFee || currentFee < newFee) {
                                    modal.set('fee', newFee);
                                    modal.set('isFeeAutofilled', true);
                                }
                            }
                        }, null, silent
                    );
                };
            })();

            modal.observe('fee', function(newValue, oldValue, keypath) {
                modal.set('isFeeAutofilled', false);
            });

            modal.observe('amount message encrypted', (function() {
                var t;
                return function() {
                    if (t) clearTimeout(t);
                    t = setTimeout(function() {
                        resetFee(modal.get('isFeeAutofilled'), true);
                    }, 500);
                }
            })(), {
                init: false
            });

            modal.on({
                resetFee: function() {
                    resetFee(true, false );
                },
                sendTransaction: function() {
                    var activeAccount = ncc.get('activeAccount.address');
                    var requestData = {
                        wallet: ncc.get('wallet.name'),
                        password: modal.get('password'),
                        account: activeAccount,
                        recipient: ncc.restoreAddress($('.recipient.form-control').val()),
                        amount: ncc.toMNem(modal.get('amount')),
                        message: modal.get('message') && modal.get('message').toString(),
                        fee: ncc.toMNem(modal.get('fee')),
                        hours_due: modal.get('dueBy'),
                        encrypt: modal.get('encrypted')? 1 : 0
                    };
                    ncc.postRequest('wallet/account/transaction/send', requestData, function(data) {
                        if (data.id) {
                            ncc.showMessage('Success', 'Transaction has been sent successfully!', function() {
                                ncc.refreshInfo();
                            });

                            modal.close();

                            var keypath = 'pendingTransactions.' + activeAccount;
                            var activePendingTransactions = ncc.get(keypath) || [];

                            var now = new Date().getTime();
                            activePendingTransactions.push({
                                isPending: true,
                                isOutgoing: true,
                                id: data.id,
                                amount: requestData.amount,
                                fee: requestData.fee,
                                message: requestData.message,
                                sender: activeAccount,
                                recipient: requestData.recipient.toUpperCase(),
                                timeStamp: now
                            });
                            ncc.set(keypath, activePendingTransactions);
                        } else {
                            ncc.showError();
                        }
                    },
                    {
                        complete: function() {
                            modal.set('password', '');
                        }
                    });
                },
                sendFormKeypress: function(e) {
                    if (e.original.keyCode === 13) {
                        ncc.fire('sendTransaction');
                    }
                },
                queryRecipient: function() {
                    var address = ncc.restoreAddress($('.recipient.form-control').val());
                    ncc.postRequest('account/find', { account: address }, function(data) {
                            if (data.address) {
                                modal.set('recipientLabel', (data.label && data.label.privateLabel) || '');
                            } else {
                                modal.set('recipientLabel', null);
                            }
                        }, 
                        {
                            error: function() {
                                modal.set('recipientLabel', null);
                            },
                            altFailCb: function() {
                                modal.set('recipientLabel', null);
                            }
                        },
                    true);
                }
            });

            require(['maskedinput'], function() {
                var $recipient = $('.form-control.recipient');
                // on-change doesn't work with maskedInput
                $recipient.on('change', function() {
                    modal.fire('queryRecipient');
                });
                $recipient.mask('******-******-******-******-******-******-****');
            });

            require(['stepper'], function() {
                $('.form-control.due-by input').stepper({
                    labels: {
                        up: '',
                        down: ''
                    }
                });
            });

            modal.set({
                isFeeAutofilled: true,
                dueBy: 12
            });

            local.autoRefresh = setInterval(ncc.refreshAccount.bind(null, null, null, true), local.autoRefreshInterval);
        },
        leave: [function() {
            clearInterval(this.local.autoRefresh);
        }]
    });
});