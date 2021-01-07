import jquery from 'jQuery'
// _parsedata,
import {_getContextPath, _getTimestamp, _parsedata} from './util'
import MODE from './chatMode.js'
import Mustache from 'mustache'

const $ = jquery
class IM {
  constructor(mine) {
    this.im = window.layui.layim
    this.messageHandler = '';
    this.mine = mine
    this.config = $.extend({}, {}, {layer: window.layui.layer})

    // this.layer = config.layer;
    // this.tree = config.tree;
    this.config.mode = MODE.MODE_SERVICE;// 因为在发送消息时会判断是否为'askForService',最好改为在UserIM中重写方法

    this.services = {
      id: '0',
      groupname: '同事',
      list: []
    }
    // 路径
    this.contextPath = _getContextPath()

    // 获取当前用户信息
    this.getMineInfo()

    this.beforeInit()
      .then(function () {
        // 初始化IM
        this.initIM()

        this.im.on('sendMessage', this.onIMMessage.bind(this))

        // 创建WS链接
        this.createWSConnection()

        // 创建完后做一些处理
        this.afterInit()
      }.bind(this))
  }

  /**
         * IM发送信息事件
         * @param res
         */
  onIMMessage(res) {
    let mine = res.mine
    let to = res.to

    // 用户时修改发送id
    to.id = this.to ? this.to.id : to.id

    // TODO 后期会判断不同的消息类别：文字、图片

    this.sendChatMessage({mine, to})
  }

  /**
         * 发送聊天信息
         * @param mine
         * @param to
         */
  sendChatMessage({mine, to}) {
    let data = {
      type: MODE.MSG_TYPE_CHAT,
      contentType: MODE.CONTENT_TYPE_TEXT,
      content: {
        msg: mine.content || mine
      },
      sender: mine.id,
      senderName: mine.username,
      receiver: to.id,
      sendTime: _getTimestamp()
    }
    let mode = this.config.mode;
    if (mode === 'askForService') {
      this.sendWSMessage(data);
    }
    // 机器人功能
    // // //现在先写成这样，等后台写好再修改。
    // if (mode == 'askRobot') {
    //   this.sendQuestionRequest({question: (data.content.msg || '').replace(/\n/, '')});
    // }
    // 评价功能
    // if (this.onAfterSendChatMessage) {
    //   this.onAfterSendChatMessage.call(this, data, mode)
    // }
  }

  sendWSMessage(data) {
    if (this.socket.readyState === 3) {
      window.location.reload();
      this.showSystemMessage({
        id: '0',
        content: Mustache.render('您已掉线，请<a onclick="window.location.reload();" style="color: RGB(98, 158, 229)">刷新</a>重新连接')
      })
    } else if (this.socket.readyState === 1) {
      this.socket.send(JSON.stringify(data))
    }
  }

  /**
         * 完善当前用户信息
         * @returns {*|{id: *, name: string, avatar: string}}
         */
  getMineInfo() {
    // if (typeof this.mine === 'string') {
    //   // TODO 从后台获取用户信息
    //   this.mine = {
    //     id: this.mine,
    //     osId: this.config.osId || 'centit',
    //     username: '我',
    //     avatar: ctx + USER_AVATAR
    //   }
    // }

    let mine = this.mine
    mine.osId = 'centit'
    mine.optId = 'centit'
    mine.id = mine.id || mine.userCode // 后台需要的名字是userCode
    mine.username = mine.username || mine.userName // 后台需要的名字是userName
    mine.avatar = MODE.DEFAULT_AVATAR
    mine.userType = mine.userType || 'S'
    return mine
  }

  /**
         * 创建WS链接
         */
  createWSConnection() {
    let id = this.mine.id
    let wsHost

    wsHost = `ws://localhost:10085/im/im/${id}`

    let socket = this.socket = new WebSocket(wsHost)

    socket.onopen = this.onWSOpen.bind(this)

    socket.onmessage = this.onWSMessage.bind(this)

    socket.onclose = this.onWSClose.bind(this)
  }

  /**
   * 建立连接初始化之后
   */
  afterInit() {
    // 更改好友状态
    let services = this.services.list
    let arr = []
    arr.concat(services).forEach(d => {
      if (d.userState === 'F') {
        this.im.setFriendStatus(d.userCode, 'offline')
      }
    })

    this.im.on('tool(quickReply)', function () {
      $.get(`${this.contextPath}/json/reply.txt`, function (res) {
        if ($('div.layui-show .selectContainer').html()) { // 判断Id = selectContainer的元素是否存在
          $('div.layui-show .serviceList').css('display', 'block');
          return;
        }
        var replys = [
          '您的智商余额已不足请及时充值！',
          '您好，有什么我能帮到你的吗？',
          '对不起，我暂时忙，可能服务不及时，请谅解。',
          '来不及解释了，快上车！'
        ]
        var jsonReply = {};
        var replyArr = [];
        for (var i = replys.length - 1; i + 1; i--) {
          let relyObj = {'reply': replys[i]};
          replyArr.push(relyObj);
        }
        jsonReply.replys = replyArr;

        var render = Mustache.render('{{#replys}} <option class={{generateClass}}>{{reply}}</option>{{/replys}}', jsonReply);

        var form = $('<div class="layui-form" style="display: inline-block;font-size: 16px;"></div>')
        var selectContainer = $('<div  class="layui-form-item selectContainer"></div>');
        var str = '<div class="layui-input-block" style="margin-left: 0;margin-top: 6px;">' +
                '</div>';
        var selectOption = '<select class="serviceList" name="service" lay-verify="required" style="display: block;width:150px;">' +
                '<option value="">请选择回复</option>' +
                render +
                '</select>';
        selectContainer.html(str);
        form.append(selectContainer);

        $('div.layui-show .layui-unselect.layim-chat-tool').append(form);
        $('div.layui-show .selectContainer div.layui-input-block').html(selectOption);
      })
    }.bind(this));
  }

  /**
         * WebSocket关闭打开事件
         */
  onWSClose() { // CF
    window.location.reload();
    window.layui.use('layer', function () {
      var layer = window.layui.layer;

      layer.open({
        title: '系统通知',

        content: Mustache.render('您已掉线，请<a onclick="window.location.reload();" style="color: RGB(98, 158, 229);cursor: pointer">刷新</a>重新连接')
      });
    });
  }

  /**
         * 显示收到的聊天信息
         * @param id
         * @param content
         * @param senderName
         * @param system
         * @param timestamp
         */
  showChatMessage({id, content, timestamp, senderName, system = false}) {
    this.im.getMessage({
      type: 'friend',
      system,
      username: senderName,
      id,
      content,
      timestamp: timestamp || _getTimestamp(),
      avatar: MODE.SERVICE_AVATAR
    })
  }

  /**
         * WebSocket通道收到信息事件
         * @param res
         */
  onWSMessage(res) { // CF
    let data = res.data
    if (!this.messageHandler) {
      clearTimeout(this.messageHandler);
    }
    try {
      data = JSON.parse(res.data)
      console.log(res)
    } catch (e) {
      // console.info(e)
    }
    if (data.contentType === 'offline' && this.mine.userType === 'S') {
      window.layui.layer.use('layer', function () {
        var layer = window.layui.layer;

        layer.open({
          title: '下线通知',
          content: data.content.msg
        });
      });
    }
    switch (data.type) {
      case MODE.MSG_TYPE_CHAT:
        this.showChatMessage($.extend({id: data.sender}, data, {content: data.content.msg}))
        break
      case MODE.MSG_TYPE_SYSTEM:
        this.showSystemMessage($.extend({id: '0'}, data, {
          content: data.content.msg,
          id: data.content.id,
          data: data.content
        }))
        break
      case MODE.MSG_TYPE_COMMAND:
        this.onCommandMessage(data, data.content)
        break;
      case MODE.MSG_TYPE_QUESTION:
        this.createProblemList(data.content, data);
        break;
      case MODE.MSG_TYPE_BROADCAST:
        // this.onBroadcastMessage(data);
        break
      default:
        console.warn(`未知的数据类型：${data.type}`)
    }
  }

  /**
         * WebSocket通道打开事件
         */
  onWSOpen() { // CF
    this.sendRegisterCommand()

    // if (this.mine.userType ==== MODE.TYPE_USER) {
    //   // 确保注册完成之后执行
    //   setTimeout(function () {
    //     let mode = this.config.mode
    //     if (mode ==== MODE.MODE_SERVICE) {
    //       // 申请客服
    //       this.sendAsk4ServiceCommand()
    //     } else if (mode ==== MODE.MODE_QUESTION) {
    //       this.sendAsk4QuestionCommand()
    //     }
    //   }.bind(this), 1000)
    // }
    console.log('WebSocket connection is opened.')
  }

  /**
         * 发送注册（上线）指令
         */
  sendRegisterCommand() {
    let contentType = MODE.CONTENT_TYPE_REGISTER
    let content = this.mine
    let receiver = this.mine.id
    console.log('receiver is', receiver)
    this.sendCommandMessage({contentType, content, receiver})
  }

  /**
         * 发送指令信息
         * @param contentType
         * @param content
         * @param receiver
         */
  sendCommandMessage({contentType, content, receiver}) { // CF
    let data = {
      type: MODE.MSG_TYPE_COMMAND,
      contentType,
      content,
      receiver,
      sender: this.mine.id,
      sendTime: _getTimestamp()
    }
    this.sendWSMessage(data)
  }
  queryService() {
    // let ctx = this.contextPath

    return fetch(`/api/message/webimcust/services`)
      .then(res => res.json())
      .then(res => res.data)
    // return new Promise(resolve => resolve())
  }

  initIM() {
    this.mine.avatar = MODE.DEFAULT_AVATAR
    let config = {
      init: {
        mine: this.mine,
        friend: [
          this.services
        ]
      },
      title: '南大先腾IM',
      uploadImage: {
        url: `/api/message/file/upload` // （返回的数据格式见下文）
        // 默认post
      },
      uploadFile: {
        url: `/api/message/file/upload` // （返回的数据格式见下文）
        // 默认post
      },
      tool: [],
      // {
      //   alias: 'quickReply',
      //   title: '快速回复',
      //   icon: '&#xe611;'
      // }],
      isgroup: false,
      copyright: true,
      // chatLog: window.layui.cache.dir + '../chatlog.html'
    };
    this.im.config(config)
  }

  beforeInit() {
    return Promise.all([
    //   this.queryUsers(),
      this.queryService()
    ]).then(res => {
      this.services.list = _parsedata(res[0].filter(d =>
        d.userCode !== this.mine.id
      )
      )
    })
  }
}

export default IM
