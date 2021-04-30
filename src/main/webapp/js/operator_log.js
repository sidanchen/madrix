/**
 * Created by sdc on 2018/3/3.
 */

function initPage(){
    $.ajax({
        url: "oltp",
        type: "get",
        dataType: "json",

        success: function (data) {
            if (data.status == "success") {
                $(".page").pagination({
                    currentPage: 1,// 当前页数
                    totalPage: data.msg,// 总页数
                    isShow: false,// 是否显示首尾页
                    count: 0,// 显示个数
                    prevPageText: "Pre",// 上一页文本
                    nextPageText: "Next",// 下一页文本
                    callback: function (current) {
                        findLog(current);
                    }
                });
            } else {
                alert(data.msg);
            }
        }
    });
}

function findLog(page) {
    $.ajax({
        url: "olbp",
        data: {"page": page},
        type: "post",
        dataType: "json",
        success: function (data) {
            if (data.status == "success") {
                //删除子类元素
                $(".usertable tr:gt(0)").remove();
                $.each(data.msg, function (index, obj) {
                    var element = "<tr class='col-md-12 column'>"
                        + "<td class='tab-user'>" + obj.id + "</td>"
                        + "<td class='tab-user'>" + obj.operator + "</td>"
                        + "<td class='tab-user'>" + new Date(obj.operatingTime.time).Format("yyyy-MM-dd hh:mm") + "</td>"
                        + "<td class='tab-user'>" + obj.order + "</td>"
                        + "<td class='tab-user'>" + obj.value + "</td>"
                        + "</tr>";
                    $(".usertable").append(element);
                })
            } else {
                alert(data.msg);
            }
        }
    })
}

$(function(){
    var flag = true;
    $.ajax({
        url:"il",
        async:false,
        dataType:"json",
        success:function(data){
            if(data.status == "success"){

            }else{
                alert(data.msg);
                flag = false;
            }
        }
    })
    if(flag) {
        initPage();
        findLog(1);
    }
})
