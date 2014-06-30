"use strict";

 define(['jquery', 'ncc', 'NccLayout'], function($, ncc, NccLayout) {
 	return $.extend(true, {}, NccLayout, {
 		name: 'landing',
		template: 'rv!layout/landing',
		local: {
			initialWaitTime: 600,
			closingGateTime: 1000,
			scrollHeader: 'active.scrollHeader'
		},
		initEverytime: function() {
			ncc.set('active.gateClosed', false);
			ncc.set('active.overlayIn', false);
			ncc.set('active.hexagon', false);
			ncc.set('active.landingRegion', '');
			ncc.set(this.local.scrollHeader, false);
		},
		setupEverytime: function() {
			var local = this.local;
			local.$html = $('html');
			local.$body = $('body');
			local.$doc = $(document);
	 		local.$leftForm = $('.left.region .form');
			local.$rightForm = $('.right.region .form');
			local.$staticHeader = $('#static-header');
			local.$createWalletNameInput = $('.left.region .custom-input').first().find('input');
			local.headerBottom = local.$staticHeader.offset().top + local.$staticHeader.outerHeight();
			local.$bgSlideshow = $('#bg-slideshow');
			local.bgSlideshowTop = local.$bgSlideshow.offset().top;

			// Intro
			$(function() {
				setTimeout(function() {
					ncc.set('active.gateClosed', true);
					setTimeout(function() {
						ncc.set('active.overlayIn', true);
						ncc.set('active.hexagon', true);
					}, local.closingGateTime);
				}, local.initialWaitTime);
			});

			ncc.set('status.listingWallets', true);
			ncc.getRequest('wallet/list', 
				function(data) {
					ncc.set('wallets', data.wallets);
				},
				{
					complete: function() {
						ncc.set('status.listingWallets', false);
					}
				}
			);

			require(['tinycarousel'], function() {
				$('#tips-carousel .container').tinycarousel({
			        interval: true,
			        intervalTime: 5000,
			        animationTime: 1000
			    });
			});

			$(window).on('scroll.landing', function(event) {
				var scrollTop = local.$doc.scrollTop();

				// scroll header
				if (!ncc.get(local.scrollHeader) && scrollTop >= local.headerBottom) {
					ncc.set(local.scrollHeader, true);
				}
				if (ncc.get(local.scrollHeader) && scrollTop < local.headerBottom) {
					ncc.set(local.scrollHeader, false);
				}

				// floating background
				var shifted = (scrollTop - local.bgSlideshowTop) * .8;
				local.$bgSlideshow.css('background-position', '50% calc(50% + ' + shifted + 'px)');
			});

			require(['expand'], function() {
				local.listeners.push(ncc.on({
					'switchLeft': function() {
						if (ncc.get('active.landingRegion') === 'right') {
							local.$rightForm.collapse();
						}
						ncc.set('active.landingRegion', 'left');
						ncc.set('active.hexagon', false);
						local.$leftForm.expand();
						local.$createWalletNameInput.focus();
					},
					'switchRight': function() {
						if (ncc.get('active.landingRegion') === 'left') {
							local.$leftForm.collapse();
						}
						ncc.set('active.landingRegion', 'right');
						ncc.set('active.hexagon', false);
						local.$rightForm.expand();
					}
				}));
			});

			local.listeners.push(ncc.on({
				openWallet: function(e, wallet) {
			    	var requestData = {
						wallet: wallet,
						password: ncc.get('inputPassword')[wallet]
					};
					ncc.set('status.openingWallet', true);
			    	ncc.postRequest('wallet/open', requestData,
			    		function(data) {
			    			// clear typed passwords before redirecting
							ncc.set('inputPassword', undefined);
			        		ncc.openWallet(data);
			        	},
						{
							complete: function() {
								ncc.set('status.openingWallet', false);
							}
						}
			    	);
			    },
				createWallet: function() {
					ncc.set('status.creatingWallet', true);
					var requestData = ncc.get('createdWallet');
			    	ncc.postRequest('wallet/create', requestData, function(data) {
		    			ncc.postRequest('wallet/open', requestData,
				    		function(data) {
				    			// clear form before redirecting
				    			ncc.set('createdWallet', {});
				        		ncc.openWallet(data);
				        	}
				    	);
		    		},
		    		{
		    			complete: function() {
		    				ncc.set('status.creatingWallet', false);
		    			}
		    		});
			    },
			    scrollTo: function(e, selector) {
			    	var $el = $(selector);
			    	if ($el[0]) {
			    		var offsetTop = $el.offset().top;
				        local.$html.add(local.$body).stop().animate({ // Chrome scrolls body, Firefox and IE scrolls html
				            scrollTop: offsetTop
				        }, 400);
			    	}
			    },
			    createWalletKeypress: function(e) {
			    	if (e.original.keyCode === 13) {
			    		ncc.fire('createWallet');
			    	}
			    },
			    passwordKeypress: function(e, wallet) {
			    	if (e.original.keyCode === 13) {
			    		ncc.fire('openWallet', {}, wallet);
			    	}
			    },
			    fileToImportSelected: function(e) {
			    	ncc.set('fileToImport.import_path', e.node.value);
			    },
			    importWallet: function() {
			    	var requestData = ncc.get('fileToImport');
			    	ncc.postRequest('wallet/import', requestData, function(data) {
			    		ncc.showMessage('Success', 'Wallet has been sucessfully imported!');
			    		ncc.set('fileToImport', {});
			    	});
			    },
			    importKeypress: function(e) {
			    	if (e.original.keyCode === 13) {
			    		ncc.fire('importWallet');
			    	}
			    },
			    walletSelected: function(e) {
			    	$(e.node).siblings('.password-prompt').find('input[type="password"]').focus();
			    }
			}));
		},
		leave: [function() {
			$(window).off('scroll.landing');
		}]
 	});
});