/**
 * Created by zhang_gd on 2017/5/12.
 */
setI18NText("");
define(function(require) {
    var Core = require('core/core');
    var Page = require('core/page');
    var Dialog = require('centit/centit.dialog');
    var osId = [
        {
            label:'aaa',
            value : '壹城平台'
        }
    ];

    // 角色信息列表\
    var planPush = Page.extend(function() {
        var table;
        this.selectOsId_Question;
        this.load = function (panel) {
            var vm = this;
            table = vm.table = panel.find('table');
            table.cdatagrid({
                // 必须要加此项!!
                controller: this,
            });
            //读取所有下拉框的ID
            this.selectOsId_Question = panel.find("#selectOsId_Question");
            this.selectOsId_Question.combobox('loadData',osId);
        }.bind(this);
    });

    return planPush;
});