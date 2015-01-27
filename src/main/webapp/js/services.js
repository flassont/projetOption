// On définit le service REST sur le module pour pouvoir l'utiliser, c'est ici qu'on fait le lien
// entre le haut niveau JEE7 et angularJS
var services = angular.module('membersService', [ 'ngResource' ]);

services.factory('Members', function($resource) {
	return $resource('rest/members/:memberId', {}, {
		'update' : {
			method : 'PUT'
		}
	// delete: { method: 'DELETE', params: {memberId: '@memberId'}}
	});
});

services.factory('Responsabilites', function($resource) {
	return $resource('rest/responsabilites/:responsabilite/:responsabiliteId',
			{}, {});
});

services.factory('Relations', function($resource) {
	return $resource('rest/relations/:relationId', {}, {});
});



// services.factory('Member', function($resource){
// return $resource('rest/members:memberId', {});
// });
