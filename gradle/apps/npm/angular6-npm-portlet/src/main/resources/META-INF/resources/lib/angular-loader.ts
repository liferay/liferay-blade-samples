// Import needed polyfills before application is launched
import 'core-js/es7/reflect';
import 'zone.js/dist/zone';

// Declare Liferay AMD loader
declare var Liferay: any;

// Launch application
export default function(rootId: string, mainRequire: string) {
	Liferay.Loader.require(mainRequire, (main: any) => {
		main.default(rootId);
	});
}
