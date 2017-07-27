/* global JSON:false */
(function() {
  'use strict'

  angular.module('workorder')
    .run(testRun)

  /** @ngInject */
  function testRun($log, $httpBackend) {

    const listRegExp = /os\/\d+\/questions/
    const infoRegExp = /os\/\d+\/questions\/(\d+)/

    const questions = []

    $httpBackend.whenGET(/os\/\d+\/questions\/\d+\/top/)
      .respond(function(method, url) {

        const res = [
          '国民岳父黄磊公布的嫁女标准是什么？',
          '又现H7N9病人！西安将关闭所有活禽市场吗？',
          '波音737要告别中国了吗？',
          '日本出现沙尘暴，原因在于中国？',
          '中储粮如何回应万吨小麦变质？',
          '十年磨一剑，饱受争议的张柏芝将携最新作品回归，你期待吗？'
        ]

        $log.debug(method, url, res)

        return [200, res, {}]
      })

    // 新增
    $httpBackend.whenPOST(listRegExp)
      .respond(function(method, url, json) {

        let obj = JSON.parse(json)
        obj.createTime = new Date().getTime() + ""
        obj.questionId = obj.createTime
        obj.userName = obj.userCode
        obj.state = "0"

        questions.push(obj)

        $log.debug(method, url, obj)

        return [200, obj, {}]
      })

    // 查询单个
    $httpBackend.whenGET(infoRegExp)
      .respond(function(method, url) {

        let questionId = url.match(infoRegExp)[1]
        let obj = questions.filter(q => q.questionId === questionId)[0]

        $log.debug(method, url, obj)

        return [200, obj, {}]
      })

    // 查询列表
    $httpBackend.whenGET(listRegExp)
      .respond(function(method, url) {

        $log.debug(method, url, questions)

        return [200, questions, {}]
      })
  }


})();

