// Register an AngularJS Portlet
(function(Liferay, angular) {

if (angular.portlet)
	return;

angular.portlet = {};
var angularPortlets = {};

angular.portlet.add = function(portletName, angularFunction) {
	angularPortlets[portletName] = angularFunction;
};

	Liferay.Portlet.ready(function(portletInstanceId, node) {
	var idx = portletInstanceId.indexOf('_INSTANCE_');

	var portletName = portletInstanceId.substring(0, idx);

	if (angularPortlets[portletName]) {
	  var portletId = portletInstanceId.substring(idx).replace('_INSTANCE_', '');

	  angular.bootstrap(node.getDOMNode(), angularPortlets[portletName](
				  portletId, node.getDOMNode()));
		}
	});
})(Liferay, angular);

// Shared service
angular.module('sharedService', []).factory('SharedService', function($window, $rootScope) {

var sharedObject = {};
$window.rootScopes = $window.rootScopes || [];
$window.rootScopes.push($rootScope);

if (!!$window.sharedService) {
	return $window.sharedService;
}

$window.sharedService = {
	change: function(name, value) {
	  sharedObject[name] = value
	  angular.forEach($window.rootScopes, function(scope) {
		if (!scope.$$phase) {
			scope.$apply();
		}
	  });
	},
	get: function() {
	  return sharedObject;
	}
}

return $window.sharedService;
});