var cavApp = angular.module("cavApp",["ngRoute","cavControllers","ngCookies"])

cavApp.config(['$routeProvider','$locationProvider', 
function($routeProvider,$locationProvider) {
	$routeProvider.when('/', {
		templateUrl: 'partials/main.html',
		controller : 'indexController'
	}),
	$routeProvider.when('/create', {
		templateUrl: 'partials/form.html',
		controller : 'createController'
	}),
	$routeProvider.when('/list', {
		templateUrl: 'partials/list.html',
		controller : 'listController'
	});
}]);