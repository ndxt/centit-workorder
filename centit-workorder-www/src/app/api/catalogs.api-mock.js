(function() {
  'use strict'

  angular.module('workorder')
    .run(testRun)

  /** @ngInject */
  function testRun($log, $httpBackend) {

    const catalogs = [
      { catalogId: '1', title: '科学百科', description: '携手中国科协<br>打造权威科普平台', logo: 'glyphicon glyphicon-home' },
      { catalogId: '2', title: '非遗百科', description: '触摸千年华夏文脉<br>聚焦今朝文化传承', logo: 'glyphicon glyphicon-inbox' },
      { catalogId: '3', title: '数字博物馆', description: '东北民族民俗博物馆<br>探迹别样东北风情', logo: 'glyphicon glyphicon-qrcode' },
      { catalogId: '4', title: '数字方志馆', description: '陕西馆全新上线<br>带你走进习大大故乡', logo: 'glyphicon glyphicon-camera' },
      { catalogId: '5', title: '明星百科', description: '最权威明星资料库<br>已上线307位明星', logo: 'glyphicon glyphicon-map-marker' },
      { catalogId: '6', title: '城市百科', description: '已上线368个城市<br>收录38017个词条', logo: 'glyphicon glyphicon-picture' },
      { catalogId: '7', title: '秒懂星课堂', description: '不一样的知识体验<br>发现偶像的另一面<strong>123</strong>', logo: 'glyphicon glyphicon-facetime-video' },
      { catalogId: '8', title: '艺术百科', description: '关注艺术前沿动态<br>遇见真 发现美', logo: 'glyphicon glyphicon-print' },
      { catalogId: '9', title: '特色词条', description: '由百科精英用户编写<br>集趣味与严谨于一身', logo: 'glyphicon glyphicon-tree-conifer' }
    ]

    const infoRegExp = /os\/\d+\/catalogs\/(\d+)/
    const listRegExp = /os\/\d+\/catalogs/

    $httpBackend.whenGET(infoRegExp)
      .respond(function(method, url) {

        let catalogId = url.match(infoRegExp)[1];
        let res = Object.assign({}, catalogs.filter(o => o.catalogId === catalogId)[0]);
        res.description = res.description.replace('<br>', ' ');

        $log.debug(method, url, res)
        return [200, res, {}]
      })

    $httpBackend.whenGET(listRegExp)
      .respond(function(method, url) {
        $log.debug(method, url, catalogs)
        return [200, catalogs, {}]
      })
  }
})();

