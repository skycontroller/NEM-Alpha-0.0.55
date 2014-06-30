"use strict";

 define(['jquery', 'ncc'], function($, ncc) {
    return function() {
        ncc.on({
            showClientInfo: function() {
                ncc.getRequest('info', 
                    function(data) {
                        if (data.appName) {
                            ncc.set('clientInfo', data);
                            ncc.showModal('clientInfo');
                        }
                    }
                );
                this.toggleOff('walletActions');
            }
        });

        require(['zeroClipboard'], function(ZeroClipboard) {
            var client = new ZeroClipboard($('.copyClipboard'));

            client.on('ready', function() {
                client.on('aftercopy', function() {
                    ncc.showTempMessage('Address has been copied to clipboard!');
                });
            });
        });

        /*$('[title]').tooltipster();
        var $accountId = $('.account-info .account-id');
        $('#global-zeroclipboard-html-bridge').hover(function() {
            $accountId.trigger('mouseover');
        }, function() {
            $accountId.trigger('mouseout');
        });*/

        require(['scroller'], function() {
            $('.scrollable').scroller();
        });

        require(['selecter'], function() {
            $('select').selecter();
        });

        /*var $notiArea = $('#wallet-page-header .account-area .vmiddle');
        $notiArea.dropit({
            action: 'click',
            submenuEl: '.notifications',
            triggerEl: 'a',
            triggerParentEl: '.noti-area'
        });
        // Close dropdown on clicking on notification box
        $notiArea.find('a.noti-box').click(function(e) {
            var $dropitOpen = $(this).closest('.dropit-open');
            if ($dropitOpen[0]) {
                $dropitOpen.removeClass('dropit-open');
                $('.notifications').hide();
                e.stopPropagation();
            }
        });*/

        require(['tinycarousel'], function() {
            $('.tile.news .news-preview').tinycarousel({
                axis: 'y',
                buttons: false,
                bullets: true,
                interval: true,
                intervalTime: 4000,
                animationTime: 700
            });
            $('.tile.messages .message-preview').tinycarousel({
                axis: 'y',
                buttons: false,
                interval: true,
                intervalTime: 4000,
                animationTime: 700
            });
        });
    }
});