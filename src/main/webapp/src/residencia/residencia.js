'use strict';

angular.module('homeon')
  .controller('rsdCtrl', function($scope, ngNotify, $http, RestSrv, SERVICE_PATH){
    $scope.rsd = {};
    $scope.rsds = [];
    $scope.permissions = [];
    $scope.showAddEditRsd = false;

    $scope.show = function(){
      $scope.showAddEditRsd = true;
    };
    $scope.hide = function(){
      $scope.showAddEditRsd = false;
      $scope.rsd = {};
    };

     var permissionUrl = SERVICE_PATH.PRIVATE_PATH + '/permission';


    RestSrv.find(permissionUrl, function(data){
      $scope.permissions = data;
      RestSrv.find(RsdrUrl, function(data){
        $scope.rsds = data;
      });
    });

  var RsdrUrl = SERVICE_PATH.PRIVATE_PATH + '/residencia';

    $scope.editRsd = function(rsd){
      $scope.rsd = angular.copy(rsd);
      $scope.show();
    };
  $scope.deleteRsd = function(rsd) {
  RestSrv.delete(RsdrUrl, rsd, function() {
    $scope.rsds.splice($scope.rsds.indexOf(rsd), 1);
    //ngNotify.set('Package \'' + Rsd.name + '\' deleted.', 'success');
  });
};
$scope.saveRsd = function(rsd) {
      if (rsd.id) {
        RestSrv.edit(RsdrUrl, rsd, function() {
          delete rsd.password;

          for (var i = 0; i < $scope.rsds.length; i++) {
            if ($scope.rsds[i].id === rsd.id) {
              $scope.rsds[i] = rsd;
            }
          }

          $scope.hide();
      //    ngNotify.set('Rsd \'' + Rsd.name + '\' updated.', 'success');
        });
      } else {
        RestSrv.add(RsdrUrl, rsd, function(newRsd) {
          $scope.rsds.push(newRsd);
          $scope.hide();
        //  ngNotify.set('Rsd \'' + Rsd.name + '\' added.', 'success');
        });
      }
    };
    RestSrv.find(permissionUrl, function(data) {
      $scope.permissions = data;

      RestSrv.find(RsdrUrl, function(data) {
        $scope.rsds = data;
      //  ngNotify.set('Loaded users with success.', 'success');
      });
    });
  });
