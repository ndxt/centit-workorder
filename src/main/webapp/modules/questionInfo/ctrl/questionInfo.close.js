/**
 * Created by lu_hb on 2017/5/19.
 */
define(function(require) {
    var Config = require('config');
    var Core = require('core/core');
    var Page = require('core/page');

    // 关闭问题
    var QuestionInfoClose = Page.extend(function() {
        // @override
        this.submit = function(table, data) {
            $.ajax({
                url: '/workorder/service/questioninfo/closequestion/' + data.questionId,
                type: 'PUT',
                success: function(result) {
                    if (result.message == 'OK'){
                        table.datagrid('reload');    // reload the user data
                    } else {
                        $.messager.show({    // show error message
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    }
                }
            });
        };
    });
    return QuestionInfoClose;
});