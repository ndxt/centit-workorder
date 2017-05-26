//改写newQuestion
// function newQuestion(){
//     $('#dlg_new').dialog('open').dialog('center').dialog('setTitle','新建工单');
//     $('#fm_new').form('clear');
//     var osId =  $('#osid').attr('value','aa');
//     var catalog = [];
//     $.ajax({
//         url: '/workorder/service/questioncatalog/getall/' + osId,
//         type: 'GET',
//         success: function(result) {
//             var log = result.data.objList;
//             for(var i=0;i<log.length;i++){
//                 catalog[i] = {};
//                 catalog[i].value=log[i].catalogName;
//                 catalog[i].label=log[i].catalogId;
//             }
//             this.selectCatalogId = $("#selectCatalogId");
//             this.selectCatalogId.combobox('loadData',catalog);
//         }
//     });
// }

define(function(require) {
    require('plugins/extend');

    var Core = require('core/core');
    var $ = require('jquery');
    var Page = require('core/page');


    var AddCtrl = Page.extend(function() {

        this.load = function(panel, data) {
            var form = panel.find('form');
            form.form('clear');

            var catalog = [];
            $.ajax({
                url: '/workorder/service/questioncatalog/getalllist',
                type: 'GET',
                success: function(result) {
                    console.log(result);
                    var log = result.data.objList;
                    for(var i=0;i<log.length;i++){
                        catalog[i] = {};
                        catalog[i].value=log[i].catalogName;
                        catalog[i].label=log[i].catalogId;
                    }
                    this.selectCatalogId = $("#selectCatalogId");//panel怎么去获取？
                    this.selectCatalogId.combobox('loadData',catalog);
                }
            });
        };

        this.submit = function(panel, data, closeCallback) {


            var form = panel.find('form');

            var isValid = form.form('enableValidation').form('validate');
            if(isValid) {
                form.form('ajax', {
                    url: '/workorder/service/questioninfo/createquestion',
                    method: 'post',
                    data: {name: 123}
                }).then(closeCallback);
            }
            return false;

        };

        this.onClose = function(table) {
            table.datagrid('reload');
        };

    });

    return AddCtrl;
});