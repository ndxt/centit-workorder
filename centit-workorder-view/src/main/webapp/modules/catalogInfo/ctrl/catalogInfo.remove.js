/**
 * Created by lu_hb on 2017/5/22.
 */
// function destroyQuestionCatalog(){
//     var row = $('#dg').datagrid('getSelected');
//     if (row){
//         $.messager.confirm('Confirm','Are you sure you want to destroy this question catalog?',function(r){
//             if (r){
//                 $.ajax({
//                     url: '/workorder/service/questioncatalog/delete/' + row.catalogId,
//                     type: 'DELETE',
//                     success: function(result) {
//                         if (result.message == 'OK'){
//                             $('#dg').datagrid('reload');    // reload the user data
//                         } else {
//                             $.messager.show({    // show error message
//                                 title: 'Error',
//                                 msg: result.errorMsg
//                             });
//                         }
//                     }
//                 });
//             }
//         });
//     }
// }

/**
 * Created by lu_hb on 2017/5/19.
 */
define(function(require) {
    var Config = require('config');
    var Core = require('core/core');
    var Page = require('core/page');

    // 删除角色
    var CatalogInfoDelete = Page.extend(function() {
        // @override
        this.submit = function(table, data) {
            if (data){
                $.messager.confirm('Confirm','Are you sure you want to destroy this question catalog?',function(r){
                    if (r){
                        $.ajax({
                            url: '/workorder/service/questioncatalog/delete/' + data.catalogId,
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
                    }
                });
            }
        };
    });
    return CatalogInfoDelete;
});
