'use strict';

angular.module('homeon')
  .controller('comodoCtrl', function($scope, $http, RestSrv){
    var ComodoUrl ="http://localhost:8080/api/private/comodo";

    $scope.comodo = {};
    $scope.comodos = [];
    $scope.permissions = [];
    $scope.showAddEditComodo = false;

    $scope.show = function(){
      $scope.showAddEditComodo = true;
    };
    $scope.hide = function(){
      $scope.showAddEditComodo = false;
      $scope.comodo = {};
    };

    var permissionUrl = 'http://localhost:8080/api/private/permission';

    RestSrv.find(permissionUrl, function(data){
      $scope.permissions = data;
      RestSrv.find(ComodoUrl, function(data){
        $scope.comodos = data;
      });
    });

    $scope.editComodo = function(comodo){
      $scope.comodo = angular.copy(comodo);
      $scope.show();
    };
    $scope.deleteComodo = function(comodo) {
    RestSrv.delete(ComodoUrl, comodo, function() {
    $scope.comodos.splice($scope.comodos.indexOf(comodo), 1);
  //  ngNotify.set('User \'' + user.name + '\' deleted.', 'success');
});
};
$scope.saveComodo = function(comodo) {
      if (comodo.id) {
        RestSrv.edit(ComodoUrl, comodo, function() {
          delete comodo.password;

          for (var i = 0; i < $scope.comodos.length; i++) {
            if ($scope.comodos[i].id === comodo.id) {
              $scope.comodos[i] = comodo;
            }
          }

          $scope.hide();
          //ngNotify.set('User \'' + comodo.name + '\' updated.', 'success');
        });
      } else {
        RestSrv.add(ComodoUrl, comodo, function(newSolo) {
          $scope.comodos.push(newComodo);
          $scope.hide();
        //  ngNotify.set('User \'' + comodo.name + '\' added.', 'success');
        });
      }
    };
    //RestSrv.find(permissionUrl, function(data) {
      //$scope.permissions = data;

      RestSrv.find(ComodoUrl, function(data) {
        $scope.comodos = data;
      //  ngNotify.set('Loaded users with success.', 'success');
      });
    //});
  });
