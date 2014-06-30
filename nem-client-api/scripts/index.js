"use strict";

require.config({
    baseUrl: 'scripts',
    paths: {
        'layout': '..',
        'jquery': 'plugins/jquery-2.1.0.min',
        'expand': 'plugins/expand',
        'modernizr': 'plugins/modernizr.custom.00846',
        'ractive': 'plugins/ractive.min',
        'tinycarousel': 'plugins/jquery.tinycarousel.min',
        'scroller': 'plugins/jquery.fs.scroller.min',
        'selecter': 'plugins/jquery.fs.selecter.min',
        'stepper': 'plugins/jquery.fs.stepper.min',
        'gridster': 'plugins/jquery.gridster.min',
        'maskedinput': 'plugins/jquery.maskedinput.min',
        'dropit': 'plugins/dropit.min',
        'zeroClipboard': 'plugins/ZeroClipboard.min',
        'tooltipster': 'plugins/jquery.tooltipster.min',
        'deployJava': 'plugins/deployJava.min'
    },
    shim: {
        'scroller': { deps: ['jquery'] },
        'selecter': { deps: ['jquery'] },
        'stepper': { deps: ['jquery'] },
        'gridster': { deps: ['jquery'] },
        'maskedinput': { deps: ['jquery'] },
        'dropit': { deps: ['jquery'] },
        'tooltipster': { deps: ['jquery'] }
    }
});

define(['ncc'], function(ncc) {
    ncc.loadPage('landing');
});