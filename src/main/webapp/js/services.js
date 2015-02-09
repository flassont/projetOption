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

services.factory('Auth', function(){
	var user;
	if ( sessionStorage.getItem("user") ) {
		user = {"email" : sessionStorage.getItem("user")};
	}

	return{
	    setUser : function(aUser){
	    	sessionStorage.setItem("user",aUser.email);
	        user = aUser;
	    },
	    reSet :  function() {
	    	user = null;
	    	sessionStorage.removeItem("user");
	    },
	    isLoggedIn : function(){
	        return(user)? user : false;
	    },
	    getUser : function() {
	    	return this.user;
	    }
	  }
});



// services.factory('Member', function($resource){
// return $resource('rest/members:memberId', {});
// });
