"use strict";

 define(['jquery', 'ncc', 'NccLayout'], function($, ncc, NccLayout) {
    return $.extend(true, {}, NccLayout, {
        name: 'transactions',
        template: 'rv!layout/transactions',
        parent: 'wallet-pages',
        setupOnce: function() {
        	ncc.refreshTransactionList = function(account, silent) {
        		if (!account) account = ncc.get('activeAccount.address');
        		ncc.postRequest('account/transactions', { account: account }, function(data) {
	    			if (data.transactions) {
	    				ncc.set('transactionList', data.transactions);
	    			}
	    		}, {}, silent);
        	}
    	},
    	setupEverytime: function() {
    		var local = this.local;

    		local.listeners.push(ncc.observe('pendingTransactions transactionList', function() {
        		var activeAccount = ncc.get('activeAccount.address');
                var activePendingTransactions = this.get('pendingTransactions.' + activeAccount);
                var transactions = this.get('transactionList');
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
                this.set('allTransactions', result);
            }));

			local.listeners.push(ncc.observe('allTransactions transactionsPage.filter', function() {
				var filter = ncc.get('transactionsPage.filter');
				var keypath = 'filteredTransactions';
				var transactions = ncc.get('allTransactions');

				if (filter === 'all') {
					ncc.set(keypath, transactions);
				} else {
					var filtered = [];

					switch (filter) {
						case 'pending':
							for (var i = 0; i < transactions.length; i++) {
								var t = transactions[i];
								if (t.isPending) filtered.push(t);
							}
							break;
						case 'incoming':
							for (var i = 0; i < transactions.length; i++) {
								var t = transactions[i];
								if (!t.isPending && !t.isOutgoing) filtered.push(t);
							}
							break;
						case 'outgoing':
							for (var i = 0; i < transactions.length; i++) {
								var t = transactions[i];
								if (!t.isPending && t.isOutgoing) filtered.push(t);
							}
							break;
					}

					ncc.set(keypath, filtered);
				}
			}));

			local.listeners.push(ncc.observe('activeAccount.address', function(newValue, oldValue) {
				ncc.refreshTransactionList(newValue);
			}));

			local.listeners.push(ncc.on({
				refreshInfo: function() {
					this.refreshTransactionList();
				}
			}));

			local.autoRefresh = setInterval(ncc.refreshTransactionList.bind(null, null, true), local.autoRefreshInterval);
    	},
        leave: [function() {
            clearInterval(this.local.autoRefresh);
        }]
    });
});