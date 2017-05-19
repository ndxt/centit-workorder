/**
 * Created by zhang_gd on 2017/5/12.
 */
setI18NText("");
define(function(require) {
    var Core = require('core/core');
    var Page = require('core/page');
    var osId = [
        {
            label:'aaa',
            value : '壹城平台'
        }
    ];

    // 角色信息列表\
    var planPush = Page.extend(function() {
        var table;
        var osIdUrl = 'system/msgdlvry/getOsIds';
        this.selectOsId_Catalog;
        this.load = function (panel) {
            var vm = this;
            debugger;
            table = vm.table = panel.find('table');
            // var tableUrl = table.attributes.url.nodeValue;
            table.cdatagrid({
                // 必须要加此项!!
                controller: this,
            });
            //读取所有下拉框的ID
            debugger;
            this.selectOsId_Catalog = panel.find("#selectOsId_Catalog");
            this.selectOsId_Catalog.combobox('loadData',osId);
        }.bind(this);
    });

    return planPush;
});