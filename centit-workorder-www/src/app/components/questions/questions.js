(function() {
  'use strict';

  angular.module('workorder')
    .directive('centitQuestions', centitQuestions);

  var DEFAULTS = {
    typeSpeed: 200,
    deleteSpeed: 40,
    pauseDelay: 2000,
    loop: true,
    postfix: ' '
  };

  /** @ngInject **/
  function centitQuestions(malarkey) {
    var directive = {
      restrict: 'EA',
      scope: {
        values: '=',
        options: '=centitQuestions'
      },
      template: '&nbsp;',
      link: linkFunc
    };

    return directive;

    function linkFunc(scope, el) {
      var watcher;
      var typist = malarkey(el[0], Object.assign({}, DEFAULTS, scope.options));
      el.addClass('acme-malarkey');

      if (scope.values)
      scope.values.forEach(function(value) {
        typist.type(value).pause().delete()
      })

      watcher = scope.$watch('values', function(values) {
        if (!values) return;

        typist.clear();
        values.forEach(function(value) {
          typist.type(value).pause().delete();
        })
      });

      scope.$on('$destroy', function () {
        watcher();
      });
    }
  }

})();
