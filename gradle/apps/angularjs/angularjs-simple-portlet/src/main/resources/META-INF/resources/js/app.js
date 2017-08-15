(function(Liferay, angular) {

  var portletName = "com_liferay_blade_samples_portlet_angularjs_simple_AngularJSSimplePortlet";
	
  angular.portlet.add(portletName,
    function(portletId) {

      var mymodule = angular.module(portletId + '.submodule', ['ui.router', 'sharedService']);

      mymodule
        .config(function ($stateProvider) {

          $stateProvider.   
              state('home', {
                  url: '',
                  templateUrl: '/o/angularjs-simple-portlet/partial/home.html',
                  controller: 'HomeCtrl'
              });        
        })
        .controller('HomeCtrl', function($scope, SharedService) {
          $scope.portletId = portletId;
          $scope.sharedModel = function (value) {
            if (angular.isDefined(value)) {
              SharedService.change('sharedModel', value)
            } else {        
              return SharedService.get().sharedModel
            }
          }
        });   
        

      return [ mymodule.name ];
    });  

})(Liferay, angular);        