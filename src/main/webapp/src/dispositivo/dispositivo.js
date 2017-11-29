'use strict';

angular.module('homeon')
  .controller('dsptvCtrl', function($scope, ngNotify, $http, RestSrv, SERVICE_PATH){
    var SensorUrl = SERVICE_PATH.PRIVATE_PATH + '/dispositivo';

    $scope.dsptv = {};
    $scope.dsptvs = [];
    $scope.showAddEditDsptv = false;

    $scope.show = function(){
      $scope.showAddEditDsptv = true;
    };
    $scope.hide = function(){
      $scope.showAddEditDsptv = false;
      $scope.dsptv = {};
    };

    $scope.editDsptv = function(dsptv){
      $scope.dsptv = angular.copy(dsptv);
      $scope.show();
    };
    $scope.deleteDsptv = function(dsptv) {
    RestSrv.delete(SensorUrl, dsptv, function() {
    $scope.dsptvs.splice($scope.dsptvs.indexOf(dsptv), 1);
  //  ngNotify.set('User \'' + user.name + '\' deleted.', 'success');
});
};
$scope.saveDsptv = function(dsptv) {
      if (dsptv.id) {
        RestSrv.edit(SensorUrl, dsptv, function() {
          delete dsptv.password;

          for (var i = 0; i < $scope.dsptvs.length; i++) {
            if ($scope.dsptvs[i].id === dsptv.id) {
              $scope.dsptvs[i] = dsptv;
            }
          }

          $scope.hide();
          //ngNotify.set('User \'' + dsptv.name + '\' updated.', 'success');
        });
      } else {
        RestSrv.add(SensorUrl, dsptv, function(newDsptv) {
          $scope.dsptvs.push(newDsptv);
          $scope.hide();
        //  ngNotify.set('User \'' + dsptv.name + '\' added.', 'success');
        });
      }
    };

      RestSrv.find(SensorUrl, function(data) {
        $scope.dsptvs = data;
      //  ngNotify.set('Loaded users with success.', 'success');
      });
  });
