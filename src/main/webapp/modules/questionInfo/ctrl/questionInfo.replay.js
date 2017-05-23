/**
 * Created by lu_hb on 2017/5/19.
 */
// function replayQuestion(){
//     $('#dlg_replay').dialog({//dlg_replay是工单回复dialog的容器
//         onClose:function(){//设置的onClose方法
//             debugger;//这里的debugger是什么鬼
//             $("#ui_replay").html('');//将ui_replay清空
//             $('#dlg_replay').dialog('close');//关闭dialog
//             $('#fm_replay').form('clear');//清除dialog中的表单数据
//         }
//     });
//     var row = $('#dg').datagrid('getSelected');//获取选中可以省略,在centitUI中将选中的数据已经封装传入
//     if (row){
//         $('#dlg_replay').dialog('open').dialog('center').dialog('setTitle','工单回复');
//         $('#fm_replay').form('load',row);
//     }//打开dialog加载数据
//     $("#askTime").html(row.createTime);
//     $("#userName").html(row.userName);
//     $("#askTitle").html(row.questionTitle);
//     $("#askContent").html(row.questionContent);//因为这些数据都不是正常的input label形式，所以需要手动加载
//     var questionId = row.questionId;//获取questionId设置url等值
//     $.ajax({//获取数据给ui_replay添加数据
//         url: '/workorder/service/questioninfo/selectquestionround/' + questionId,
//         type: 'GET',
//         success: function(result) {
//             var questionRoundArray = [];
//             questionRoundArray = result.data;
//             $($('#Q_title_replay>span>input')[0]).attr('disabled','disabled');
//             $($('#Q_content_replay>span>textarea')[0]).attr('disabled','disabled');
//             for (var i = 0;i<questionRoundArray.length;i++){
//                 var time = questionRoundArray[i].createTime;
//                 var username = questionRoundArray[i].userName;
//                 var orA = questionRoundArray[i].orA;
//                 if (orA=="A"){
//                     orA="回复";
//                 }else if(orA=="Q"){
//                     orA="追问";
//                 }
//                 var content = questionRoundArray[i].roundContent;
//                 $("#ui_replay").append("<li style='margin-bottom: 20px'><p><small><i>"+time+" "+"</i><span>"+username+"："+orA+"</span></small></p><p>"+content+"</p></li>");
//             }
//         }
//     });
// }
//
//
// function  close_replay() {
//     $("#ui_replay").html('');
//     $('#dlg_replay').dialog('close');
//     $('#fm_replay').form('clear');
// }
//
// function saveReplay(){
//     $('#fm_replay').form('submit',{
//         url:  '/workorder/service/questioninfo/replayquestion',
//         onSubmit: function(){
//             return $(this).form('validate');
//         },
//         success: function(result){
//             var result = eval('('+result+')');
//             if (result.errorMsg){
//                 $.messager.show({
//                     title: 'Error',
//                     msg: result.errorMsg
//                 });
//             } else {
//                 $('#dlg_replay').dialog('close');// close the dialog
//                 $('#fm_replay').form('clear');
//                 $('#dg').datagrid('reload');    // reload the user data
//             }
//         }
//     });
// }

define(function (require) {
    require('plugins/extend');

    var Core = require('core/core');
    var $ = require('jquery');
    var Page = require('core/page');


    var ReplayCtrl = Page.extend(function() {

        this.load = function(panel, data) {
            var form = panel.find('form');
            form.form('clear');

            //如何设置这种形式的值？

            $("#askTime").html(data.createTime);
            $("#userName").html(data.userName);
            $("#askTitle").html(data.questionTitle);
            $("#askContent").html(data.questionContent);//因为这些数据都不是正常的input label形式，所以需要手动加载
            var questionId = data.questionId;//获取questionId设置url等值
            $.ajax({//获取数据给ui_replay添加数据
                url: '/workorder/service/questioninfo/selectquestionround/' + questionId,
                type: 'GET',
                success: function(result) {
                    var questionRoundArray = [];
                    questionRoundArray = result.data;
                    $($('#Q_title_replay>span>input')[0]).attr('disabled','disabled');
                    $($('#Q_content_replay>span>textarea')[0]).attr('disabled','disabled');
                    for (var i = 0;i<questionRoundArray.length;i++){
                        var time = questionRoundArray[i].createTime;
                        var username = questionRoundArray[i].userName;
                        var orA = questionRoundArray[i].orA;
                        if (orA=="A"){
                            orA="回复";
                        }else if(orA=="Q"){
                            orA="追问";
                        }
                        var content = questionRoundArray[i].roundContent;
                        $("#ui_replay").append("<li style='margin-bottom: 20px'><p><small><i>"+time+" "+"</i><span>"+username+"："+orA+"</span></small></p><p>"+content+"</p></li>");
                    }
                }
            });
        };

        this.submit = function(panel, data, closeCallback) {
            //原先没有表单校验就不添加
            var form = panel.find('form');
            form.form('submit',{
                url:  '/workorder/service/questioninfo/replayquestion',
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                        $('#dlg_replay').dialog('close');// close the dialog
                        $('#fm_replay').form('clear');
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                }
            });
        };

        this.onClose = function(table) {
            $("#ui_replay").html('');
            $('#dlg_replay').dialog('close');
            $('#fm_replay').form('clear');
        };

    });

    return ReplayCtrl;
})