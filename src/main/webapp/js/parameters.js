/**
 * Created by sdc on 2018/3/3.
 */



function initPage() {
    $.ajax({
        url: "wtp",
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
                        findParameters(current);
                    }
                });
            } else {
                alert(data.msg);
            }
        }
    });
}





function deleteP(id) {
    $.ajax({
        url: "wdbi",
        data: {"id": id},
        type: "post",
        dataType: "json",

        success: function (data) {
            if (data.status == "success") {
                findParameters(1);
                initPage();
                alert(data.msg);
            } else {
                alert(data.msg);
            }
        }
    })
}

function findParameters(page) {
    $.ajax({
        url: "wfbp",
        data: {"page": page},
        type: "post",
        dataType: "json",
        success: function (data) {
            if (data.status == "success") {
                $(".usertable tr:gt(0)").remove();
                $.each(data.msg, function (index, obj) {
                    var element = "<tr>"
                        + "<td class='tab-user'>" + obj.weather + "</td>"
                        + "<td class='tab-user'>" + obj.color + "</td>"
                        + "<td class='tab-user'>" + obj.brightness + "</td>"
                        + "<td class='tab-user'>"
                        + "<img src='images/delete.jpg' class='deleteP' onclick='deleteP("+obj.id+")'/>"
                        + "</td>"
                        + "</tr>";
                    $(".usertable").append(element);

                })
            } else {
                alert(data.msg);
            }
        }
    })
}

$(function () {
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
        findParameters(1);
    }


    $(".btn").click(function(){
        var weather = $("#weather").val();
        var color = $("#lColor").val();
        var brightness = $("#lBright").val();
        if(weather == ""){
            alert("The weather cannot be empty!");
            return false;
        }
        if(color == ""){
            alert("Light color cannot be empty!");
            return false;
        }
        if(brightness == ""){
            alert("Light brightness cannot be empty");
            return false;
        }
        $.ajax({
            url:"weca",
            data:{"weather":weather,"color":color,"brightness":brightness},
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.status == "success"){
                    alert(data.msg);
                    initPage();
                    findParameters(1);
                }else{
                    alert(data.msg);
                }
            }
        })
    });
});
