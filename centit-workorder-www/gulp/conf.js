/**
 *  This file contains the variables used in other gulp files
 *  which defines tasks
 *  By design, we only put there very generic config values
 *  which are used in several places to keep good readability
 *  of the tasks
 */

var gutil = require('gulp-util');

exports.paths = {
  src: 'src',
  dist: 'dist',
  tmp: '.tmp',
  e2e: 'e2e',
  localWebapp:'/home/zk/Workspaces/java/centit-workorder/centit-workorder-web/target/centit-workorder-web-0.0.1-SNAPSHOT',
  remoteWebapp: '/opt/websvr/tomcat8/webapps/framework/workorder',
  remoteWWW: '/data/www/framework/wk'
};

exports.remote = {
  // host: '106.15.39.61',
  host:'192.168.134.12',
  port: 22,
  username: 'root',
  password:'centit.1'
  // password: '1315.com'
};

/**
 *  Wiredep is the lib which inject bower dependencies in your project
 *  Mainly used to inject script tags in the index.html but also used
 *  to inject css preprocessor deps and js files in karma
 */
exports.wiredep = {
  exclude: [/\/bootstrap\.js$/],
  directory: 'bower_components'
};

/**
 *  Common implementation for an error handler of a Gulp plugin
 */
exports.errorHandler = function(title) {
  'use strict';

  return function(err) {
    gutil.log(gutil.colors.red('[' + title + ']'), err.toString());
    this.emit('end');
  };
};
