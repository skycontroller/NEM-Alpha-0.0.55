// TODO: Convert to ractive 
define(['jquery'], function($) {
	return function() {
		var $staticHeader = $('#static-header');
		var $scrollHeader = $('#scroll-header');
		var inClass = 'in';
		var scrollHeaderIn = $scrollHeader.hasClass(inClass);
		var $body = $('body');

		$(window).scroll(function(event) {
			var headerBottom = $staticHeader.offset().top + $staticHeader.outerHeight();
			var scrollTop = $body.scrollTop();
			if (!scrollHeaderIn && scrollTop >= headerBottom) {
				$scrollHeader.addClass(inClass);
				scrollHeaderIn = true;
			}
			if (scrollHeaderIn && scrollTop < headerBottom) {
				$scrollHeader.removeClass(inClass);
				scrollHeaderIn = false;
			}
		});

		// Intro
		var initialWaitTime = 600;
		var closingGateTime = 1000;
		var $main = $('main');

		$(function() {
			setTimeout(function() {
				$main.addClass('closed');
				setTimeout(function() {
					$main.addClass('in').addClass('hex');
				}, closingGateTime);
			}, initialWaitTime);
		});

		// Region switching
		var $leftRegion = $('.left.region');
		var $leftForm = $leftRegion.find('.form');
		var $rightRegion = $('.right.region');
		var $rightForm = $rightRegion.find('.form');

		$('#btn-create-wallet').click(function() {
			if ($rightRegion.hasClass('active')) {
				$rightRegion.removeClass('active');
				$rightForm.collapse();
				$main.removeClass('right');
			}
			$leftRegion.addClass('active');
			$leftForm.expand();
			$main.removeClass('hex').addClass('left');
		});
		$('#btn-import-wallet').click(function() {
			if ($leftRegion.hasClass('active')) {
				$leftRegion.removeClass('active');
				$leftForm.collapse();
				$main.removeClass('left');
			}
			$rightRegion.addClass('active');
			$rightForm.expand();
			$main.removeClass('hex').addClass('right');
		});

		require(['tinycarousel'], function() {
			$('#tips-carousel .container').tinycarousel({
		        interval: true,
		        intervalTime: 5000,
		        animationTime: 1000
		    });
		});
	};
});
