// On définit le service REST sur le module pour pouvoir l'utiliser, c'est ici qu'on fait le lien
// entre le haut niveau JEE7 et angularJS
angular.module('membersService', ['ngResource']).factory('Members', function($resource){
  return $resource('rest/members:memberId', {});
});