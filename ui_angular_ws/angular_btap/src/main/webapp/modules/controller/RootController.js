(function() {
	'use strict';
	angular.module('angular_btap').controller('AppController', ['$scope', function AppController($scope) {
		$scop.userName = "Venkatesan";
	}]);
	angular.module('angular_btap').controller('RootController', ['$scope', function RootController($scope) {
		var $container = $('#lightbox');

		function animateAct(selector) {
			if ($container) {
				$container.isotope({
					filter: selector,
					animationOptions: {
						duration: 750,
						easing: 'linear',
						queue: false
					}
				});
			}

		}
		$scope.changeSelectedItems = function(selector, myThis) {
			$('.cat .active').removeClass('active');
			$(myThis).addClass('active');
			animateAct(selector);
			return false;
		};
		$scope.$on('$viewContentLoaded', function() {

			animateAct('*');
		});

	}]);
})(); // ends main function