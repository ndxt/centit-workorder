/**
 * Created by lu_hb on 2017/5/19.
 */

define(function(require) {
    var Page = require('core/page');

    // 编辑角色信息
    var QuestionInfoEdit = Page.extend(function() {
        // 加载页面时调用
        this.load = function(panel, data) {
            var form = panel.find('form');
            form.form('load',data)
                .form('disableValidation')
                .form('focus');

    };

        // 提交表单时调用
        this.submit = function(panel, data, closeCallback) {

            var form = panel.find('form');

            form.form('submit',{
                url: '/workorder/service/questioninfo/updateoperator/'+data.questionId,
                onSubmit: function(){
                    return form.form('validate');
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                        $('#dg').datagrid('reload');
                        panel.dialog('close');        // close the dialog
                           // reload the user data
                    }
                }
            });
        }
        // 窗口关闭时调用
        this.onClose = function() {

        };
    });
    return QuestionInfoEdit;
});