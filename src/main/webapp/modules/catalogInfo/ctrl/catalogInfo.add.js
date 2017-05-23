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
            this.data = null;

            var form = panel.find('form');

            $('#fm').form('clear');
             var osid =  $('#osid').attr('value','aa');
            // url = '/workorder/service/questioncatalog/create';
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