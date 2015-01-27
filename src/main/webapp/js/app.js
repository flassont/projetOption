/*Sans trop rentrer dans les détails du mode strict en Javascript (disponible depuis ECMAScript 5), 
 * sachez qu'il permet d'éviter les variables globales qui sont fortement déconseillées si l'on n'a 
 * pas une raison très précise de s'en servir (et qu'on les maîtrise parfaitement). 
 * Toujours dans cette idée, je vous conseille de toujours utiliser le mot-clé var lorsque vous 
 * déclarer une variable.*/
'use strict';
// Ici on définit l'application
// Cette application est monopage, chaque partie est liée en utilisant une URL relative. 
// On lie le module pour les membres avec l'application

var app = angular.module('emn-webapp', ['membersService',"xeditable"])
	
	.config([ '$routeProvider', function($routeProvider) {
        $routeProvider
        // if URL fragment is /, then load the home partial, with the
        // MembersCtrl controller
        .when('/', {
            templateUrl : 'partials/home.html',
            controller : MembersCtrl
        })
        .when('/dude', {
        	templateUrl : 'partials/aham.html',
            controller : MembersCtrl
        })
        // Add a default route
        .otherwise({
            redirectTo : '/'
        });
    }]);

//Ajout du theme pour xeditable
app.run(function(editableOptions) {
	  editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
	});

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
			}
			return response || $q.when(response);
		}
	};
});

app.config(function($httpProvider) {
	$httpProvider.interceptors.push('authInterceptor');
});
