/**
 * Created by lu_hb on 2017/5/18.
 */
define(function(require) {
    require('plugins/extend');

    var $ = require('jquery');
    var Page = require('core/page');
    var Config = require('config');


    var AddCtrl = Page.extend(function() {

        this.load = function(panel) {

            var form = panel.find('form');
            form.form('clear');
            var catalog = [];
            $.ajax({
                url: Config.ContextPath + 'service/questioncatalog/getallosid',
                type: 'GET',
                success: function(result) {
                    console.log(result);
                    var log = result.data;
                    var logItem;
                    for(var i=0;i<log.length;i++){
                        logItem = {};

                        logItem.value=log[i].osId;
                        logItem.label=log[i].osName;
                        catalog.push(logItem);
                    }
                    console.log(catalog);



                    $('#osId').combobox({data:catalog});

                }
            });
        };

        this.submit = function(panel, data, closeCallback) {

            var form = panel.find('form');

            // 开启校验
            var isValid = form.form('enableValidation').form('validate');

            if (isValid) {
                form.form('ajax', {
                    url: Config.ContextPath + 'service/questioncatalog/create',
                    method: 'post'
                }).then(closeCallback);
            }

            return false;
        };

        this.onClose = function(table) {
            this.parent.table.datagrid('reload');
        };

    });

    return AddCtrl;
});