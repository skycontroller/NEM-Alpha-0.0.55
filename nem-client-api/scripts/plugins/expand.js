define(['jquery'], function($) {
    $.fn.expand = function (option) {
        return this.each(function () {
            var $this = $(this);
            var options = {};
            var $items;

            var DEFAULT_OPTIONS = {
                horizontally: false,
                contentSelector: '.content'
            };

            var mergeOptions = function (option) {
                return $.extend({}, DEFAULT_OPTIONS, $this.data(), option);
            };

            var init = function () {
                var $content = $this.find(options.contentSelector);
                if ($content[0]) {
                    if (options.horizontally) {
                        var width = $content.outerWidth();
                        $this.animate({ width: width }, {
                            complete: function() {
                                $this.width('auto');
                            }
                        });
                    } else {
                        var height = $content.outerHeight();
                        $this.animate({ height: height }, {
                            complete: function() {
                                $this.height('auto');
                            }
                        });
                    }
                }
            };

            options = mergeOptions(option);
            init();
        });
    };

    $.fn.collapse = function (option) {
        return this.each(function () {
            var $this = $(this);
            var options = {};
            var $items;

            var DEFAULT_OPTIONS = {
                horizontally: false,
                contentSelector: '.content'
            };

            var mergeOptions = function (option) {
                return $.extend({}, DEFAULT_OPTIONS, $this.data(), option);
            };

            var init = function () {
                var $content = $this.find(options.contentSelector);
                if ($content[0]) {
                    if (options.horizontally) {
                        var width = $content.outerWidth();
                        $this.width(width);
                        $this.animate({ width: 0 });
                    } else {
                        var height = $content.outerHeight();
                        $this.height(height);
                        $this.animate({ height: 0 });
                    }
                }
            };

            options = mergeOptions(option);
            init();
        });
    };
});