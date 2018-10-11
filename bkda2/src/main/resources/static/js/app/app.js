var app = angular.module('app', ['ui.router', 'ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/bkda2',
    USER_SERVICE_API : 'http://localhost:8080/bkda2/api/user/'
});

app.config(['$stateProvider', '$urlRouterProvider',
	function($stateProvider, $urlRouterProvider) {
		$stateProvider
			.state('home', {
				url: '/',
				templateUrl: 'partials/list',
				controller: 'UserController',
				controllerAs: 'ctrl',
				resolver: {
					users: function ($q, UserService) {
						console.log();
						var deferred = $q.defer();
						UserService.loadAllUsers().then(deferred.resolve, deferred.resolve);
						
					}
				}
			})
			.state('login', {
				url: '/login',
				templateUrl: 'partials/login',
				controller: 'LoginController',
				controllerAs: 'loginctrl',
				resolver: {
					
				}
			});
		$urlRouterProvider.otherwise('/');
	}]);