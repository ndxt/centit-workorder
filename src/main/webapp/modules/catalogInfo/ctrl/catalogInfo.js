/**
 * Created by zhang_gd on 2017/5/12.
 */
setI18NText("");
define(function(require) {
//	var Config = require('config');
    var Core = require('core/core');
    var Page = require('core/page');

    var Dialog = require('centit/centit.dialog');
    var state = [
        {
            label:0,
            value : '待发送'
        },
        {
            label:4,
            value : '已取消发送'
        }
    ];
    var osId = [
        {
            label:'YCXMGLPT',
            value : '壹城平台'
        }
    ];

    // var fileInformation = require('../ctrl/fileAccessLog.fileInformation');
    // 角色信息列表\
    var planPush = Page.extend(function() {
        var table;
        var SimData = [];
        var i;
        var osIdUrl = 'system/msgdlvry/getOsIds';
        var optIdUrl = 'system/msgdlvry/getOptIds';
        this.selectOsId;
        this.seletOptId;
        this.selectPushState;
        var fileIn;
        // this.injecte([
        // 	fileIn =  new fileInformation('fileInformation'),
        // ]);
        this.load = function (panel) {
            var vm = this;
            table = vm.table = panel.find('table');
            table.cdatagrid({
                // 必须要加此项!!
                controller: this,
            });
            //读取所有下拉框的ID
            this.selectPushState  = panel.find("#selectPushState");
            this.selectOsId = panel.find("#selectOsId");
            this.seletOptId = panel.find("#selectOptId");
            this.selectOsId.combobox('loadData',osId);
            this.selectPushState.combobox('loadData',state);
            this.seletOptId.combobox('loadData',optId);
        }.bind(this);
    });

    return planPush;
});