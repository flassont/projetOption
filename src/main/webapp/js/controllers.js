
function TabsCtrl($scope, $location) {
	$scope.isActive = function (viewLocation) { 
        return viewLocation === $location.path();
    };
}



function MembersCtrl($scope, $http, Members, Responsabilites) {

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
        
//        $scope.newMember.name = "Nicolas";
//        
//        
//        $scope.email = member.email;
    	
//        alert( member.email );
        
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

    // Call the refresh() function, to populate the list of members
    $scope.refresh();

    // Initialize newMember here to prevent Angular from sending a request
    // without a proper Content-Type.
    $scope.reset();

    // Set the default orderBy to the name property
    $scope.orderBy = 'name';
    
    //Initialisation de l'onglet actif connexion/inscription
    $scope.formActiveTab = 'connexion';
}

function UVsModulesCtrl($scope, $http, Responsabilites) {
	
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
            $scope.$apply();
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
    
    $scope.refresh();
    
    $scope.reset();
    
}
