(function() {
  'use strict'

  angular.module('workorder')
    .run(testRun)

  /** @ngInject */
  function testRun($log, $httpBackend) {

    const documents = [
      { documentId: '1', title: '音爆', children:[{ documentId: '1', title: '音爆',children:[
        { documentId: '2', title: '狂怒', description: '英国“狂怒1”是Hawker公司在接近大战结束时设计的一种战斗机，使用“暴风”的机...'},
        { documentId: '2', title: '狂怒', description: '英国“狂怒2”是Hawker公司在接近大战结束时设计的一种战斗机，使用“暴风”的机...'},
        { documentId: '2', title: '狂怒', description: '英国“狂怒3”是Hawker公司在接近大战结束时设计的一种战斗机，使用“暴风”的机...'},
        { documentId: '2', title: '狂怒', description: '英国“狂怒4”是Hawker公司在接近大战结束时设计的一种战斗机，使用“暴风”的机...'},
        { documentId: '2', title: '狂怒', description: '英国“狂怒5”是Hawker公司在接近大战结束时设计的一种战斗机，使用“暴风”的机...'},
        { documentId: '2', title: '狂怒', description: '英国“狂怒6”是Hawker公司在接近大战结束时设计的一种战斗机，使用“暴风”的机...'},
        { documentId: '2', title: '狂怒', description: '英国“狂怒7”是Hawker公司在接近大战结束时设计的一种战斗机，使用“暴风”的机...'},
        { documentId: '2', title: '狂怒', description: '英国“狂怒8”是Hawker公司在接近大战结束时设计的一种战斗机，使用“暴风”的机...'}
      ]}]},
      { documentId: '1', title: '音爆', description: '物体运行速度接近音速时，会有一股强大的阻力，使物体产生强烈的振荡，速度衰减。这一...', logo: 'glyphicon glyphicon-home', url: 'http://baike.baidu.com/item/%E9%9F%B3%E7%88%86/5641561' },
      { documentId: '2', title: '狂怒', description: '英国“狂怒”是Hawker公司在接近大战结束时设计的一种战斗机，使用“暴风”的机...', logo: 'glyphicon glyphicon-inbox', url: 'http://baike.baidu.com/item/%E7%8B%82%E6%80%92/6717697' },
      { documentId: '3', title: '太空船一号', description: '“太空船一号”是一架装有火箭发动机的有翼飞行器（名为“飞船”，实为飞机），由鲁坦...', logo: 'glyphicon glyphicon-qrcode', url: 'http://baike.baidu.com/item/%E5%A4%AA%E7%A9%BA%E8%88%B9%E4%B8%80%E5%8F%B7/8061588' },
      { documentId: '4', title: '多级火箭', description: '多级火箭是由数级火箭组合而成的运载工具。每一级都装有发动机与燃料，目的是为了提高...', logo: 'glyphicon glyphicon-camera', url: 'http://baike.baidu.com/item/%E5%A4%9A%E7%BA%A7%E7%81%AB%E7%AE%AD/8074383' },
      { documentId: '5', title: '小翼', description: '机小翼又称“端翼”或润扩散器，是一种装在机翼翼梢处、近似垂直于机翼的小翼面。可...', logo: 'glyphicon glyphicon-map-marker', url: 'http://baike.baidu.com/item/%E5%B0%8F%E7%BF%BC/8087800' },
      { documentId: '6', title: '陀螺地平仪', description: '陀螺地平仪（gyroscopic horizon），是指利用陀螺特性测量飞机俯仰...', logo: 'glyphicon glyphicon-picture', url: 'http://baike.baidu.com/item/%E9%99%80%E8%9E%BA%E5%9C%B0%E5%B9%B3%E4%BB%AA/8170253' },
      { documentId: '7', title: '环境系列卫星', description: '环境系列卫星是中国专门用于环境和灾害监测的对地观测卫星系统。系统由2颗光学卫星（...', logo: 'glyphicon glyphicon-facetime-video', url: 'http://baike.baidu.com/item/%E7%8E%AF%E5%A2%83%E7%B3%BB%E5%88%97%E5%8D%AB%E6%98%9F/8178348' },
      { documentId: '8', title: '伊尔-96', description: '伊尔-96（Ил-96）是由前苏联伊留<h1>hiahiahia</h1><br><strong>run a test</strong>申设计局（Ilyushin，苏联解体后改为...', logo: 'glyphicon glyphicon-print', url: 'http://baike.baidu.com/item/%E4%BC%8A%E5%B0%94-96/8214742' },
      { documentId: '9', title: '航空器适航', description: '民用航空器的适航性是指该航空器各部件及子系统的整体性能和操纵特性在预期运行环境和...', logo: 'glyphicon glyphicon-tree-conifer', url: 'http://baike.baidu.com/item/%E8%88%AA%E7%A9%BA%E5%99%A8%E9%80%82%E8%88%AA/8226548' }
    ]
    const documentsCopy = [
      { documentId: '1', title: '音爆', description: '物体运行速度接近音速时，会有一股强大的阻力，使物体产生强烈的振荡，速度衰减。这一...', logo: 'glyphicon glyphicon-home', url: 'http://baike.baidu.com/item/%E9%9F%B3%E7%88%86/5641561' },
      { documentId: '2', title: '狂怒', description: '英国“狂怒”是Hawker公司在接近大战结束时设计的一种战斗机，使用“暴风”的机...', logo: 'glyphicon glyphicon-inbox', url: 'http://baike.baidu.com/item/%E7%8B%82%E6%80%92/6717697' },
      { documentId: '3', title: '太空船一号', description: '“太空船一号”是一架装有火箭发动机的有翼飞行器（名为“飞船”，实为飞机），由鲁坦...', logo: 'glyphicon glyphicon-qrcode', url: 'http://baike.baidu.com/item/%E5%A4%AA%E7%A9%BA%E8%88%B9%E4%B8%80%E5%8F%B7/8061588' },
      { documentId: '4', title: '多级火箭', description: '多级火箭是由数级火箭组合而成的运载工具。每一级都装有发动机与燃料，目的是为了提高...', logo: 'glyphicon glyphicon-camera', url: 'http://baike.baidu.com/item/%E5%A4%9A%E7%BA%A7%E7%81%AB%E7%AE%AD/8074383' },
      { documentId: '5', title: '小翼', description: '机小翼又称“端翼”或润扩散器，是一种装在机翼翼梢处、近似垂直于机翼的小翼面。可...', logo: 'glyphicon glyphicon-map-marker', url: 'http://baike.baidu.com/item/%E5%B0%8F%E7%BF%BC/8087800' },
      { documentId: '6', title: '陀螺地平仪', description: '陀螺地平仪（gyroscopic horizon），是指利用陀螺特性测量飞机俯仰...', logo: 'glyphicon glyphicon-picture', url: 'http://baike.baidu.com/item/%E9%99%80%E8%9E%BA%E5%9C%B0%E5%B9%B3%E4%BB%AA/8170253' },
      { documentId: '7', title: '环境系列卫星', description: '环境系列卫星是中国专门用于环境和灾害监测的对地观测卫星系统。系统由2颗光学卫星（...', logo: 'glyphicon glyphicon-facetime-video', url: 'http://baike.baidu.com/item/%E7%8E%AF%E5%A2%83%E7%B3%BB%E5%88%97%E5%8D%AB%E6%98%9F/8178348' },
      { documentId: '8', title: '伊尔-96', description: '伊尔-96（Ил-96）是由前苏联伊留<h1>hiahiahia</h1><br><strong>run a test</strong>申设计局（Ilyushin，苏联解体后改为...', logo: 'glyphicon glyphicon-print', url: 'http://baike.baidu.com/item/%E4%BC%8A%E5%B0%94-96/8214742' },
      { documentId: '9', title: '航空器适航', description: '民用航空器的适航性是指该航空器各部件及子系统的整体性能和操纵特性在预期运行环境和...', logo: 'glyphicon glyphicon-tree-conifer', url: 'http://baike.baidu.com/item/%E8%88%AA%E7%A9%BA%E5%99%A8%E9%80%82%E8%88%AA/8226548' }
    ]

    const infoRegExp = /os\/\d+\/documents\/(\d+)/
    const listRegExp = /os\/\d+\/documents/

    $httpBackend.whenGET(infoRegExp)
      .respond(function(method, url) {

        let catalogId = url.match(infoRegExp)[1]
        let res = Object.assign({}, documents.filter(o => o.catalogId === catalogId)[0])
        res.description = res.description.replace('<br>', ' ')

        $log.debug(method, url, arguments)
        return [200, res, {}]
      })

    $httpBackend.whenGET(listRegExp)
      .respond(function(method, url) {
        $log.debug(method, url, documents)
        return [200, documents, {}]
      })
  }
})();

