'use strict';

angular.module('homeon')
  .controller('agendaCtrl', function($scope, $http, RestSrv){
    var url ="http://localhost:8080/api/public/agenda";

    $scope.agenda = {};
    $scope.agendas = [];
    $scope.permissions = [];
    $scope.showAddEditAgenda = false;

    $scope.show = function(){
      $scope.showAddEditAgenda = true;
    };
    $scope.hide = function(){
      $scope.showAddEditAgenda = false;
      $scope.agenda = {};
    };

      RestSrv.find(url, function(data){
        $scope.agendas = data;
      });

    $scope.editAgenda = function(agenda){
      $scope.agenda = angular.copy(agenda);
      $scope.show();
    };
    $scope.deleteAgenda = function(agenda) {
    RestSrv.delete('http://localhost:8080/api/public/agenda', agenda, function() {
    $scope.agendas.splice($scope.agendas.indexOf(agenda), 1);  
});
};
$scope.saveAgenda = function(agenda) {
      if (agenda.id) {
        RestSrv.edit(url, agenda, function() {
          delete agenda.password;

          for (var i = 0; i < $scope.agendas.length; i++) {
            if ($scope.agendas[i].id === agenda.id) {
              $scope.agendas[i] = agenda;
            }
          }

          $scope.hide();
          
        });
      } else {
        RestSrv.add(url, agenda, function(newAgenda) {
          $scope.agendas.push(newAgenda);
          $scope.hide();        
        });
      }
    };
      RestSrv.find(url, function(data) {
        $scope.agendas = data;
    });
  });
