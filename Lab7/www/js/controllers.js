angular.module('starter.controllers', [])

.controller('AppCtrl', function($scope, $ionicModal, $timeout, $http) {

  // With the new view caching in Ionic, Controllers are only called
  // when they are recreated or on app start, instead of every page change.
  // To listen for when this page is active (for example, to refresh data),
  // listen for the $ionicView.enter event:
  //$scope.$on('$ionicView.enter', function(e) {
  //});

  // Form data for the login modal
  $scope.loginData = {};
$scope.registerData = {};
    $scope.results = new Array();

  // Create the login modal that we will use later
  $ionicModal.fromTemplateUrl('templates/login.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.modal = modal;
  });

  // Triggered in the login modal to close it
  $scope.closeLogin = function() {
    $scope.modal.hide();
  };

  // Open the login modal
  $scope.login = function() {
    $scope.modal.show();
  };

  // Perform the login action when the user submits the login form
  $scope.doLogin = function() {
    console.log('Doing login', $scope.loginData);
  };
    $scope.doRegister = function() {
    console.log('Registering', $scope.loginData);
  };
    
    $scope.search = function(searchTerm){
        $scope.results = new Array();
        console.log(searchTerm);
        var service_url = 'https://kgsearch.googleapis.com/v1/entities:search';
          var params = {
            'query': searchTerm,
            'limit': 1,
            'indent': true,
            'key' : 'AIzaSyBairt1Z-mmE-7ywYdt17wfRhFSiM2DcZc',
          };
          $.getJSON(service_url + '?callback=?', params, function(response) {
              for(var i = 0; i < 2; i++){
                  $scope.results[i] = {
                        "name": response.itemListElement[0].result.name,
                        "descripion": response.itemListElement[0].result.description
                    };
            }
//            $.each(response.itemListElement, function(i, element) {
//              $('<div>', {text:element['result']['name']}).appendTo(list);
                console.log(response.itemListElement[0]);
              console.log(response.itemListElement[0].result.description);
//            });
          });
        }
    }
)
