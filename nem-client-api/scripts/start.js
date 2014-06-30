"use strict";

 define(['jquery', 'ncc'], function($, ncc) {
 	return {
 		name: 'start',
		template: 'rv!layout/start',
		setupEverytime: function() {
			require(['deployJava'], function() {
				ncc.set('startPage.nccButton', deployJava.createWebStartLaunchButton('nem-client.jnlp', '1.8.0'));
				ncc.set('startPage.standAloneButton', deployJava.createWebStartLaunchButton('nem-server.jnlp', '1.8.0'));
			});
			ncc.getRequest('shutdown', null, {
				error: function(jqXHR, textStatus, errorThrown) {
					if (jqXHR.status !== 200) {
						ncc.showError(textStatus, errorThrown);
					}
		        }
			});
		}
 	};
});