"use strict";

 define(['jquery', 'ncc'], function($, ncc) {
 	return {
 		local: {
            listeners: [],
            autoRefreshInterval: 30000
        },
        leave: [
	        function() {},
	        function() {
	        	$.each(this.local.listeners, function() {
					this.cancel();
				});
				this.local.listeners = [];
        	}
        ]
 	};
 });