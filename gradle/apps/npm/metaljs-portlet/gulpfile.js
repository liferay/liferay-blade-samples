'use strict';

var gulp = require('gulp');

// We need to use gulp to compile soy files because metal-cli compiles for a
// different target version (of the `goog` modules).
require('gulp-metal').registerTasks({
	taskPrefix: 'metal:',
	soySrc: './src/main/resources/META-INF/resources/**/*.soy',
	soyDest: './src/main/resources/META-INF/resources',
});

gulp.task('create-soy-files', ['metal:soy'], function() {
	return gulp
		.src('./src/main/resources/META-INF/resources/**/*.soy.js')
		.pipe(gulp.dest('./src/main/resources/META-INF/resources'));
});

gulp.task('default', ['create-soy-files']);