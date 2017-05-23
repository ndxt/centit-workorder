/**
 * Created by lu_hb on 2017/5/19.
 */
define(function(require) {
    var Config = require('config');
    var Core = require('core/core');
    var Page = require('core/page');

    // 删除角色
    var QuestionInfoDelete = Page.extend(function() {
        // @override
        this.submit = function(table, data) {
            $.ajax({
                url: '/workorder/service/questioninfo/deletequestion/' + data.questionId,
                type: 'DELETE',
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
    return QuestionInfoDelete;
});
