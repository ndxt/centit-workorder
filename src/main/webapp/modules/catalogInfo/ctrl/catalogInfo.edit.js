/**
 * Created by lu_hb on 2017/5/22.
 */


// function editQuestionCatalog(){
//     var row = $('#dg').datagrid('getSelected');
//     $('#catalogid').attr('value',row.catalogId);
//     if (row){
//         $('#dlg').dialog('open').dialog('center').dialog('setTitle','Edit QuestionCatalog');
//         $('#fm').form('load',row);
//         url = '/workorder/service/questioncatalog/update';
//     }
// }

define(function(require) {
    require('plugins/extend');

    var $ = require('jquery');
    var Page = require('core/page');
    var Config = require('config');
    // 编辑角色信息
    var EditCtrl = Page.extend(function() {
        // 加载页面时调用
        this.load = function(panel, data) {

            var form = panel.find('form');
            $('#catalogid').attr('value',data.catalogId);
            form.form('load',data);


        };

        // 提交表单时调用
        this.submit = function(panel, data, closeCallback) {

            var form = panel.find('form');

            var isValid = form.form('enableValidation').form('validate');
            if(isValid) {
                form.form('ajax', {
                    url: Config.ContextPath + 'service/questioncatalog/update',
                    method: 'post',
                    data: {name: 123}
                }).then(closeCallback);
            }
            return false;
        };


        this.onClose = function(table) {
            this.parent.table.datagrid('reload');// reload the user data
        };
    });
    return EditCtrl;
});