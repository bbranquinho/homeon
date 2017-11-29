'use strict';

angular.module('homeon')
  .controller('sensorCtrl', function($scope, ngNotify, $http, RestSrv, SERVICE_PATH){
    var SensorUrl = SERVICE_PATH.PRIVATE_PATH + '/sensor';

    $scope.sensor = {};
    $scope.sensores = [];
    $scope.showAddEditSensor = false;

    $scope.show = function(){
      $scope.showAddEditSensor = true;
    };
    $scope.hide = function(){
      $scope.showAddEditSensor = false;
      $scope.sensor = {};
    };

    $scope.editSensor = function(sensor){
      $scope.sensor = angular.copy(sensor);
      $scope.show();
    };
    $scope.deleteSensor = function(sensor) {
    RestSrv.delete(SensorUrl, sensor, function() {
    $scope.sensores.splice($scope.sensor.indexOf(sensor), 1);
  //  ngNotify.set('User \'' + user.name + '\' deleted.', 'success');
});
};
    $scope.saveSensor = function(sensor){
      if(sensor.id){
        RestSrv.edit(SensorUrl, sensor, function() {
          delete sensor.password;

          for (var i = 0; i < $scope.sensores.length; i++) {
            if ($scope.sensores[i].id === sensor.id) {
              $scope.sensores[i] = sensor;
            }
          }

          $scope.hide();
        //  ngNotify.set('User \'' + user.name + '\' updated.', 'success');
        });


      }else {
        RestSrv.add(SensorUrl, sensor, function(newSensor){
          $scope.sensores.push(newSensor);
          $scope.hide();
        });
      }
    };

      RestSrv.find(SensorUrl, function(data) {
        $scope.sensores = data;
      //  ngNotify.set('Loaded users with success.', 'success');
      });

  });
