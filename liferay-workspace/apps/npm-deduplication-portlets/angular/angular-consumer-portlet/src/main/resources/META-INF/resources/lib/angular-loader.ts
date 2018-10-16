// Bootstrap angular provider before using any of its modules
import 'angular-provider';

// Declare Liferay AMD loader
declare var Liferay: any;

// Launch application
export default function(rootId: string) {
	Liferay.Loader.require(
		'angular-consumer-portlet@1.0.0/lib/main',
		(main: any) => {
			main.default(rootId);
		},
	);
}
