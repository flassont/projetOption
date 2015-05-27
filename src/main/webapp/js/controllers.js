// On en a besoin pour définir les routes
var appControllers = angular.module('appControllers', []);

appControllers.controller('TabsCtrl', ['$scope','$rootScope', '$location', function TabsCtrl($scope, $rootScope, $location) {
	
	
}]);

appControllers.controller('LoginCtrl', ['$scope', '$rootScope', '$http','$window', '$routeParams','$location', 'Auth', 'CategIntervenants', function MembersCtrl($scope,$rootScope, $http, $window, $routeParams, $location, Auth, CategIntervenants ) {

    $scope.categs = CategIntervenants.query();

    $scope.formActiveTab = 'connexion';


		$scope.auth = function (val) {
			return Auth.isLoggedIn();
		};
	
	// submit
	  $scope.login = function () {
	    // Ask to the server, do your job and THEN set the user

	    Auth.setUser(user); // Update the state of the user in the app
	  };
	  
	  $scope.reset = function() {
	        // clear input fields
	        $scope.newMember = {};
	        $scope.user = {};
	  };
	  
	  $scope.isActive = function (viewLocation) { 
	        return viewLocation === $location.path();
	  };
	  
	  $scope.submit = function () {
	        $http({
	            method: 'POST',
	            url: 'rest/auth/login',
	            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
	            transformRequest: function(obj) {
	                var str = [];
	                for(var p in obj)
	                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	                return str.join("&");
	            },
	            data: $scope.user
	        })
	          .success(function (data, status, headers, config) {
	            $window.sessionStorage.token = data.token;
	            $scope.message = 'Bienvenue';
	            console.log( $scope.message );
	            Auth.setUser($scope.user);
	            $scope.auth = true;
	            // Clear the form
	 	       	$scope.reset();
	 	       
	 	       	$location.path('/');

	          })
	          .error(function (data, status, headers, config) {
	            // Erase the token if the user fails to log in
	            delete $window.sessionStorage.token;

	            // Handle login errors here
	            $scope.message = 'Error: Invalid user or password';
	            console.log( $scope.message );
	            // Clear the form
	 	       	$scope.reset();
	 	       	// $scope.$apply()
	          });
	      };
	      
	      $scope.logout = function () {
	    	  $http({
		            method: 'POST',
		            url: 'rest/auth/logout',
		            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		            transformRequest: function(obj) {
		                var str = [];
		                for(var p in obj)
		                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		                return str.join("&");
		            },
		            data: {"email" : $window.sessionStorage.user, "token":$window.sessionStorage.token}
		        }).success(function (data, status, headers, config) {
			    	  delete $window.sessionStorage.token;
			    	  delete $window.sessionStorage.user;
			    	  Auth.reSet();
				      $scope.message = 'Au revoir';
				      console.log( $scope.message );
				      event.preventDefault();
				 	  $location.path('/login');
		        })
	      }
}]);

appControllers.controller('MembersCtrl',['$scope', '$rootScope', '$http','$window', '$routeParams', 'Members', 'Responsabilites', 'Relations', function MembersCtrl($scope,$rootScope, $http, $window, $routeParams, Members, Responsabilites, Relations) {
	
    // Define a refresh function, that updates the data from the REST service
    $scope.refresh = function() {
        $scope.members = Members.query();
        $scope.modules = Responsabilites.query({ responsabilite: 'modules' });
    };

    // Define a reset function, that clears the prototype newMember object, and
    // consequently, the form
    $scope.reset = function() {
        // clear input fields
        $scope.newMember = {};
        $scope.user = {};
    };

    $scope.creerRelation = function(annee, emailIntervenant, idResponsabilite, etatInitial) {
        $scope.successMessages = '';
        $scope.errorMessages = '';
        $scope.errors = {};

        $scope.relationPredecessor = {};

        $scope.relationPredecessor.annee = annee;
        $scope.relationPredecessor.emailIntervenant = emailIntervenant;
        $scope.relationPredecessor.idResponsabilite = idResponsabilite;
        $scope.relationPredecessor.etatInitial = etatInitial;

        Relations.save($scope.relationPredecessor, function(data) {

            $scope.successMessages = [ 'Relation Created' ];

            $scope.refresh();

            $scope.reset();
        }, function(result) {
            if ((result.status == 409) || (result.status == 400)) {
                $scope.errors = result.data;
            } else {
                $scope.errorMessages = [ 'Unknown  server error' ];
            }
            $scope.$apply();
        });

    };

    // Define a register function, which adds the member using the REST service,
    // and displays any error messages
    $scope.register = function() {
        $scope.successMessages = '';
        $scope.errorMessages = '';
        $scope.errors = {};
        
       Members.save($scope.newMember, function(data) {

            // mark success on the registration form
            $scope.successMessages = [ 'Member Registered' ];

            // Update the list of members
            $scope.refresh();

            // Clear the form
            $scope.reset();
        }, function(result) {
            if ((result.status == 409) || (result.status == 400)) {
                $scope.errors = result.data;
            } else {
                $scope.errorMessages = [ 'Unknown  server error' ];
            }
            $scope.$apply();
        });

    };
    
    $scope.removeMember = function(member) {
    	
    	$scope.newMember = {};
    	
    	$scope.errors = {};
        
// $scope.newMember.name = "Nicolas";
//        
//        
// $scope.email = member.email;
    	
// alert( member.email );
        
    	Members.remove( {memberId: member.email}, function(data) {
    		// mark success on the registration form
            $scope.successMessages = [ 'Member Removed' ];

            // Update the list of members
            $scope.refresh();

    	}, function(result) {
    		if ((result.status == 409) || (result.status == 400)) {
                $scope.errors = result.data;
            } else {
                $scope.errorMessages = [ 'Unknown  server error' ];
            }
            $scope.$apply();
    	} )
    }
    
    $scope.updateMemberName = function(data, member) {
    	
   	 	$scope.errors = {};

    	member.name = data;
    	
    	
    	Members.save( member, function(data) {
    		// mark success on the registration form
            $scope.successMessages = [ 'Member Updated' ];

            // Update the list of members
            $scope.refresh();

    	}, function(result) {
    		if ((result.status == 409) || (result.status == 400)) {
                $scope.errors = result.data;
            } else {
                $scope.errorMessages = [ 'Unknown  server error' ];
            }
            $scope.$apply();
    	} )
    }
    
    $scope.updateMemberSurname = function(data, member) {
    	
   	 	$scope.errors = {};
    	
    	member.surname = data;
    	
    	
    	Members.save( member, function(data) {
    		// mark success on the registration form
            $scope.successMessages = [ 'Member Updated' ];

            // Update the list of members
            $scope.refresh();

    	}, function(result) {
    		if ((result.status == 409) || (result.status == 400)) {
                $scope.errors = result.data;
            } else {
                $scope.errorMessages = [ 'Unknown  server error' ];
            }
            $scope.$apply();
    	} )
    }
    
    $scope.updateMemberEmail = function(data, member) {
    	
   	 	$scope.errors = {};
        
    	member.email = data;
    	
    	Members.save( member, function(data) {
    		// mark success on the registration form
            $scope.successMessages = [ 'Member Updated' ];

            // Update the list of members
            $scope.refresh();

    	}, function(result) {
    		if ((result.status == 409) || (result.status == 400)) {
                $scope.errors = result.data;
            } else {
                $scope.errorMessages = [ 'Unknown  server error' ];
            }
            $scope.$apply();
    	} )
    }
    
    $scope.updateMemberPassword = function(data, member) {
    	
   	 	$scope.errors = {};
        
    	member.password = data;
    	
    	Members.save( member, function(data) {
    		// mark success on the registration form
            $scope.successMessages = [ 'Member Updated' ];

            // Update the list of members
            $scope.refresh();

    	}, function(result) {
    		if ((result.status == 409) || (result.status == 400)) {
                $scope.errors = result.data;
            } else {
                $scope.errorMessages = [ 'Unknown  server error' ];
            }
            $scope.$apply();
    	} )
    }

    // Call the refresh() function, to populate the list of members2
    $scope.refresh();

    // Initialize newMember here to prevent Angular from sending a request
    // without a proper Content-Type.
    $scope.reset();

    // Set the default orderBy to the name property
    $scope.orderBy = 'name';
    
    // Initialisation de l'onglet actif connexion/inscription
    $scope.formActiveTab = 'connexion';

    $scope.modalMemberEmail = '';

    $scope.setModalMemberEmail = function (email) {
        $scope.modalMemberEmail = email;
    }
}]);

appControllers.controller('UVsModulesCtrl',['$scope', '$http', 'Responsabilites', function UVsModulesCtrl($scope, $http, Responsabilites) {

    $scope.uvmodal = {};

    $scope.setUvModal = function(uv) {
        $scope.uvmodal = uv;
    }

    $scope.reset = function() {
        // clear input fields
        $scope.newUV = {};
        $scope.newModule = {};
    };
    
    $scope.refresh = function() {
        $scope.uvs = Responsabilites.query({ responsabilite: 'uvs' });
        $scope.modules = Responsabilites.query({ responsabilite: 'modules' });
    };
	
	$scope.registeruv = function() {
        $scope.successMessages = '';
        $scope.errorMessages = '';
        $scope.errors = {};
        
       Responsabilites.save({ responsabilite: 'uvs' }, $scope.newUV, function(data) {

            // mark success on the registration form
            $scope.successMessages = [ 'UV Registered' ];

            // Update the list of members
            $scope.refresh();

            // Clear the form
            $scope.reset();
        }, function(result) {
            if ((result.status == 409) || (result.status == 400)) {
                $scope.errors = result.data;
            } else {
                $scope.errorMessages = [ 'Unknown  server error' ];
            }
        });

    };
    
    $scope.registermodule = function() {
        $scope.successMessages = '';
        $scope.errorMessages = '';
        $scope.errors = {};
        
       Responsabilites.save({ responsabilite: 'modules' }, $scope.newModule, function(data) {

            // mark success on the registration form
            $scope.successMessages = [ 'Module Registered' ];

            // Update the list of members
            $scope.refresh();

            // Clear the form
            $scope.reset();
        }, function(result) {
            if ((result.status == 409) || (result.status == 400)) {
                $scope.errors = result.data;
            } else {
                $scope.errorMessages = [ 'Unknown  server error' ];
            }
            $scope.$apply();
        });

    };

    $scope.addModuleToUV = function(module) {
        $scope.successMessages = '';
        $scope.errorMessages = '';
        $scope.errors = {};

        Responsabilites.save({ responsabilite: 'addmoduletouv', moduleId: module.id, UVId: $scope.uvmodal.id }, $scope.newModule, function(data) {

            // mark success on the registration form
            $scope.successMessages = [ 'Module Added To UV' ];

            // Update the list of members
            $scope.refresh();

            // Clear the form
            $scope.reset();
        }, function(result) {
            if ((result.status == 409) || (result.status == 400)) {
                $scope.errors = result.data;
            } else {
                $scope.errorMessages = [ 'Unknown  server error' ];
            }
            $scope.$apply();
        });
    }

    
    $scope.refresh();
    
    $scope.reset();
    
}]);


appControllers.controller('MenuCtrl', ['$scope', '$location', 'Menu', function($scope, $location, Menu) {
    'use strict';

    // Mock for user data
    // TODO Replace user mock with content from service
    var user = {
        lastName: 'DUPONT',
        firstName: 'Paul',
        role: 'Reponsable'
    };
    $scope.user = user;

    // Menu items
    // Update from service when adding menu item
    $scope.menu = {};
    $scope.menu.items = Menu.items;
    $scope.$on('menuChanged', onMenuUpdate);

    $scope.isActive = function(link) {
        return '#' + $location.path() === link;
    }

    function onMenuUpdate() {
        $scope.menu.items = Menu.items;
    };
}]);