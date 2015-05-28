/*Sans trop rentrer dans les détails du mode strict en Javascript (disponible depuis ECMAScript 5), 
 * sachez qu'il permet d'éviter les variables globales qui sont fortement déconseillées si l'on n'a 
 * pas une raison très précise de s'en servir (et qu'on les maîtrise parfaitement). 
 * Toujours dans cette idée, je vous conseille de toujours utiliser le mot-clé var lorsque vous 
 * déclarer une variable.*/
'use strict';
// Ici on définit l'application
// Cette application est monopage, chaque partie est liée en utilisant une URL relative. 
// On lie le module pour les membres avec l'application

var app = angular.module('emn-webapp', ['ngResource', 'ngRoute', 'ui.bootstrap','xeditable'])
	
	.config([ '$routeProvider','$httpProvider', function($routeProvider,$httpProvider) {
		
        $routeProvider
        // if URL fragment is /, then load the home partial, with the
        // MembersCtrl controller
        .when('/login', {
        	templateUrl : 'partials/login.html',
            controller : 'LoginCtrl'
        })
        .when('/', {
            templateUrl : 'partials/home.html',
            controller : 'MembersCtrl'
        })
        .when('/uv', {
        	templateUrl : 'partials/uv.html',
            controller : 'UvCtrl'
        })
        // Add a default route
        .otherwise({
            redirectTo : '/login'
        });
        
        $httpProvider.interceptors.push('authInterceptor');
    }]);

//Ajout du theme pour xeditable
app.run(['$rootScope', '$location', 'Auth','editableOptions', function ($rootScope, $location, Auth, editableOptions) {
	  editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
	  $rootScope.$on('$routeChangeStart', function (event) {

	        if ( !Auth.isLoggedIn() && !($location.path() == '/login') ) {
	            console.log('DENY');
	            event.preventDefault();
	            $location.path('/login');
	        }
	        else {
	            console.log('ALLOW');
	        }
	        
	   });
}]);


// Avant on utilisait l'ancienne méthode pour lier les controller, dans les nouvelles version d'angluar il faut faire ca :
//app.controller('MembersCtrl',[function() {
//  // ...
//}]);



app.controller('TabsCtrl',[function() {
	  // ...
}]);

//Cette partie est sensé rediriger vers la page d'accueil quand on est pas loggé
app.factory('authInterceptor', function($rootScope, $q, $window) {
	return {
		request : function(config) {
			config.headers = config.headers || {};
			if ($window.sessionStorage.token) {
				config.headers.Authorization = $window.sessionStorage.token;
			}
			return config;
		},
		response : function(response) {
			if (response.status === 401) {
				$location.path( "/" );
				$scope.$apply();
				console.log( "redirect 1 ")
			}
			return response || $q.when(response);
		}
	};
});