// On définit le service REST sur le module pour pouvoir l'utiliser, c'est ici qu'on fait le lien
// entre le haut niveau JEE7 et angularJS
var services = angular.module('emn-webapp');

services.factory('Members', function($resource) {
	return $resource('rest/members/:memberId', {}, {
		'update' : {
			method : 'PUT'
		}
	// delete: { method: 'DELETE', params: {memberId: '@memberId'}}
	});
});

services.factory('CategIntervenants', function($resource) {
	return $resource('rest/categoriesIntervenant',
		{}, {});
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

services.factory('Uv', function() {
	return function() {
		this.name ='';
		this.nbCours = 0;
	};
});

services.provider('Menu', function () {
	var self = this;

	this.defaults = {
		items: [{
			template: 'UVs',
			route: '#/uv'
		}]
	};

	this.$get = ['$rootScope', function($rootScope) {
		var _items = angular.isArray(self.defaults.items) ? self.defaults.items  : [self.defaults.items];
		return {
			get items() {
				return angular.copy(_items);
			},
			add: function (item) {
				_items.push(item);
				$rootScope.$broadcast('menuChanged');
			}
		};
	}];
});

// services.factory('Member', function($resource){
// return $resource('rest/members:memberId', {});
// });
