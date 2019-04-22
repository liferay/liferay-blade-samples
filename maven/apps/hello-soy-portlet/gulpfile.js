const { fork } = require('child_process');
const gulp = require('gulp');

const resourcesDir = `${__dirname}/target/classes/META-INF/resources`;

const defaults = {
	cwd: resourcesDir,
	encoding: 'utf8',
	env: null,
	killSignal: 'SIGTERM',
	maxBuffer: 200 * 1024,
	stdio: 'inherit',
	timeout: 0
};

function callNode(args, callback) {
	process.chdir(resourcesDir);
	fork(args.shift(), args, defaults)
	.on('close', callback);
}

gulp.task(
	'config-js-modules',
	function(done) {
		callNode([
			`${__dirname}/node_modules/liferay-module-config-generator/bin/index.js`,
			 '--config',
			 '',
			 '--extension',
			 '--filePattern',
			 '**/resources/*.js',
			 '--format',
			 '/_/g,-',
			 '--ignorePath',
			 'true',
			 '--moduleConfig',
			 `${__dirname}/package.json`,
			 '--namespace',
			 'Liferay.Loader',
			 '--output',
			 `${__dirname}/target/classes/META-INF/config.json`,
			 '--moduleRoot',
			 resourcesDir,
			 `${__dirname}/target/classes/META-INF`
		], done);
	});

gulp.task(
	'transpile-js',
	function(done) {
		callNode([
			`${__dirname}/node_modules/metal-cli/index.js`,
			'build',
			'--bundleFileName',
			`--dest`,
			resourcesDir,
			'--format',
			'amd',
			'--globalName',
			'--moduleName',
			'--soyDeps',
			`${__dirname}/node_modules/lexicon*/src/**/*.soy`,
			`${__dirname}/node_modules/metal*/src/**/*.soy`,
			'--soyDest',
			resourcesDir,
			'--soySrc',
			'**/*.soy',
			'--src',
			'**/*.es.js',
			'**/*.soy.js'
		], done);
	});