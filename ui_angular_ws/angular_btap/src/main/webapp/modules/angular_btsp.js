(function() {
	'use strict';

	var angular_btap = angular.module('angular_btap', ['ngRoute', 'ngResource']);

	/*Application RouteProvider Config */
	/************************************************/
	angular_btap.config(['$routeProvider', function($routeProvider) {
		$routeProvider.
		when('/', {
			controller: 'RootController',
			templateUrl: 'views/homepage.html'
		}).
		when('/home', {
			controller: 'RootController',
			templateUrl: 'views/homepage.html'
		}).
		when('/about', {
			controller: 'RootController',
			templateUrl: 'views/aboutpage.html'
		}).
		when('/services', {
			controller: 'RootController',
			templateUrl: 'views/services.html'
		}).
		when('/portfolio', {
			controller: 'RootController',
			templateUrl: 'views/portfolio.html'
		}).
		when('/myteam', {
			controller: 'RootController',
			templateUrl: 'views/team.html'
		}).
		when('/contact', {
			controller: 'RootController',
			templateUrl: 'views/contact.html'
		});

	}]);


})(); // ends main function