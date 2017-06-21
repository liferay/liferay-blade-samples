AUI()
.use('aui-base','aui-delayed-task',	'liferay-poller',
		function(A) {

	Liferay.namespace('BladePoller');

	Liferay.BladePoller.Manager = {
			init: function() {

				var instance = this;

				instance._portletId = A.one("#pollerPortletId").val();

				instance._bladePollerContainer = A.one('#bladePollerContainer');

				console.log("Init: portletId:"+instance._portletId+
						", containerId:"+instance._bladePollerContainer);

				instance._updateTask = new A.debounce(instance._updateMessage,30000,
						instance);

				instance._updateTask.delay(0);

				Liferay.Poller.addListener(instance._portletId,instance._onPollerUpdate, instance);

				Liferay.on(
						'sessionExpired',
						function(event) {
							Liferay.Poller.removeListener(instance._portletId);
							instance._bladePollerContainer.hide();
						}
				);

			},

			send: function(options, id) {

				console.log("Options:" + options + ", id:" + id);

				var instance = this;

				Liferay.Poller.submitRequest(instance._portletId, options, id);

				instance._updateTask();
			},

			_updateMessage: function() {
				console.log("Update Message");
				var instance = this;
				instance.send(	{
					status : 'OK'
				});
			},

			_onPollerUpdate : function(response, chunkId) {

				console.log("updating...");

				var instance = this;

				instance._bladePollerContainer.text(response.content.message);

				instance._bladePollerContainer.show();

				instance.send(
						{
							status : 'OK'
						}
				);

			}

	};

	A.augment(Liferay.BladePoller.Manager, A.Attribute, true);

	Liferay.publish(
			'pollerPortletReady',
			{
				defaultFn: A.bind('init', Liferay.BladePoller.Manager),
				fireOnce: true
			}
	);

	A.on(
			'domready',
			function() {
				Liferay.fire('pollerPortletReady');
			}
	);

}

);