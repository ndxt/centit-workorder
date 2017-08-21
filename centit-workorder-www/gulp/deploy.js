'use strict';

var gulp = require('gulp');
var sftp = require('gulp-sftp');
var GulpSSH = require('gulp-ssh');
var conf = require('./conf');

var gulpSSH = new GulpSSH({
  ignoreErrors: false,
  sshConfig: conf.remote
});

gulp.task('clean-before-build', ['clean', 'build']);

gulp.task('deploy', ['clean', 'clean-before-build'], function () {

  return gulp.src(['dist/**'])
    .pipe(sftp({
      host: conf.remote.host,
      user: conf.remote.username,
      pass: conf.remote.password,
      remotePath: conf.paths.remoteWWW
    }));
});

gulp.task('deploy:all', function () {
  return gulp.src([`${conf.paths.localWebapp}/**`])
    .pipe(sftp({
      host: conf.remote.host,
      user: conf.remote.username,
      pass: conf.remote.password,
      remotePath: `${conf.paths.remoteWebapp}`
    }));
});

gulp.task('deploy:war', function () {
  const war = 'D:/IdeaProjects/centit-workorder/centit-workorder-web/target/workorder.war'
  const war2 = '/home/zk/Workspaces/java/centit-workorder/centit-workorder-web/target/workorder.war'
  return gulp.src([war2])
    .pipe(sftp({
      host: conf.remote.host,
      user: conf.remote.username,
      pass: conf.remote.password,
      remotePath: '/opt/websvr/tomcat8/webapps'
    }));
});


gulp.task('deploy:class', function () {
  return gulp.src([`${conf.paths.localWebapp}/WEB-INF/classes/com/**`])
    .pipe(sftp({
      host: conf.remote.host,
      user: conf.remote.username,
      pass: conf.remote.password,
      remotePath: `${conf.paths.remoteWebapp}/WEB-INF/classes/com`
    }));
});

gulp.task('deploy:res', function () {
  return gulp.src(['/D/Projects/RunData/workorder_home/**'])
    .pipe(sftp({
      host: conf.remote.host,
      user: conf.remote.username,
      pass: conf.remote.password,
      remotePath: `/D/Projects/RunData/workorder_home`
    }));
});

gulp.task('deploy:reset', function() {
  return gulpSSH
    .shell([
      '/opt/reset.sh'
    ], {filePath: 'reset.log'})
    .pipe(gulp.dest('logs'));
});

gulp.task('deploy:tomcat', ['deploy:class'], function() {
  return gulpSSH
    .shell([
      '/opt/reset.sh'
    ], {filePath: 'reset.log'})
    .pipe(gulp.dest('logs'));
});


