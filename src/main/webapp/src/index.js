'use strict';

var BASE_URL;
if (window.location.port === "8081" ) {
    BASE_URL = window.location.origin + ':8081/api';
} else {
    BASE_URL = window.location.origin + '/api';
}

angular.module('homeon', ['checklist-model', 'ngNotify', 'ngRoute', 'ngCookies', 'ngStorage'])
  .constant('SERVICE_PATH', {
    'ROOT_PATH': BASE_URL,
    'PUBLIC_PATH': BASE_URL + '/public',
    'PRIVATE_PATH': BASE_URL + '/private'
  })
  .config(function($routeProvider){
    $routeProvider
    .when('/', {
      templateUrl: 'src/home/home.html',
      controller: 'homeCtrl'
    })
     .when('/login', {
     templateUrl: 'src/login/login.html',
     controller: 'loginCtrl'
    })
    .when('/user', {
      templateUrl: 'src/user/user.html',
      controller: 'userCtrl'
    })
    .when('/residencia', {
      templateUrl: 'src/residencia/residencia.html',
      controller: 'rsdCtrl'
    })
    .when('/comodo', {
      templateUrl: 'src/comodo/comodo.html',
      controller: 'comodoCtrl'
    })
    .when('/sensor', {
      templateUrl: 'src/sensor/sensor.html',
      controller: 'sensorCtrl'
    })
    .when('/dispositivo', {
      templateUrl: 'src/dispositivo/dispositivo.html',
      controller: 'dsptvCtrl'
    })
    .when('/abrirChamado', {
      templateUrl: 'src/abrirChamado/abrirChamado.html',
      controller: 'chamadoCtrl'
    })
    .otherwise({
      redirectTo: '/login'
    });    
  })
  .config(function($httpProvider) {
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    $httpProvider.defaults.withCredentials = true;
    $httpProvider.interceptors.push('httpRequestInterceptor');
  })
  .run(function($rootScope, ngNotify, LoginLogoutSrv) {
    $rootScope.authDetails = { name: '', authenticated: false, permissions: [] };

    ngNotify.config({
      theme: 'pastel'
    });

    LoginLogoutSrv.verifyAuth();
  });

  
