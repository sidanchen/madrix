/**
 * Created by sdc on 2018/3/3.
 */



function initPage() {
    $.ajax({
        url: "openSchedule/query/1",
        type: "get",
        dataType: "json",

        success: function (data) {
            if (data.status == "success") {
                $("#beforeSunrise").val(data.msg.beforeSunrise);
                $("#afterSunrise").val(data.msg.afterSunrise);
                $("#beforeSunset").val(data.msg.beforeSunset);
                $("#afterSunset").val(data.msg.afterSunset);
            } else {
                alert(data.msg);
            }
        }
    });
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
    $("#beforeSunrise").change(function () {
        var beforeSunrise = $("#beforeSunrise").val();
        if(beforeSunrise === "" || beforeSunrise ==null){
            $("#beforeSunrise").val("0");
        }
        if(isNaN(beforeSunrise)) {
            //对于空数组和只有一个数值成员的数组或全是数字组成的字符串，isNaN返回false，例如：'123'、[]、[2]、['123'],isNaN返回false,
            //所以如果不需要val包含这些特殊情况，则这个判断改写为if(!isNaN(val) && typeof val === 'number' )
            $("#beforeSunrise").val("0");
        }
        $("#afterSunrise").val("0");
    });
    $("#afterSunrise").change(function () {
        var beforeSunrise = $("#afterSunrise").val();
        if(beforeSunrise === "" || beforeSunrise ==null){
            $("#afterSunrise").val("0");
        }
        if(isNaN(beforeSunrise)) {
            //对于空数组和只有一个数值成员的数组或全是数字组成的字符串，isNaN返回false，例如：'123'、[]、[2]、['123'],isNaN返回false,
            //所以如果不需要val包含这些特殊情况，则这个判断改写为if(!isNaN(val) && typeof val === 'number' )
            $("#afterSunrise").val("0");
        }
        $("#beforeSunrise").val("0");
    });

    $("#beforeSunset").change(function () {
        var beforeSunrise = $("#beforeSunset").val();
        if(beforeSunrise === "" || beforeSunrise ==null){
            $("#beforeSunset").val("0");
        }
        if(isNaN(beforeSunrise)) {
            //对于空数组和只有一个数值成员的数组或全是数字组成的字符串，isNaN返回false，例如：'123'、[]、[2]、['123'],isNaN返回false,
            //所以如果不需要val包含这些特殊情况，则这个判断改写为if(!isNaN(val) && typeof val === 'number' )
            $("#beforeSunset").val("0");
        }
        $("#afterSunset").val("0");
    });

    $("#afterSunset").change(function () {
        var beforeSunrise = $("#afterSunset").val();
        if(beforeSunrise === "" || beforeSunrise ==null){
            $("#afterSunset").val("0");
        }
        if(isNaN(beforeSunrise)) {
            //对于空数组和只有一个数值成员的数组或全是数字组成的字符串，isNaN返回false，例如：'123'、[]、[2]、['123'],isNaN返回false,
            //所以如果不需要val包含这些特殊情况，则这个判断改写为if(!isNaN(val) && typeof val === 'number' )
            $("#afterSunset").val("0");
        }
        $("#beforeSunset").val("0");
    });
    
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
    $("#update").click(function(){
        var beforeSunrise = $("#beforeSunrise").val();
        var afterSunrise = $("#afterSunrise").val();
        var beforeSunset = $("#beforeSunset").val();
        var afterSunset = $("#afterSunset").val();
        var id = "1";
        $.ajax({
            url:"openSchedule/update",
            data:{"beforeSunrise":beforeSunrise,"afterSunrise":afterSunrise,"beforeSunset":beforeSunset,"afterSunset":afterSunset,"id":id},
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.status == "success"){
                    alert(data.msg);
                }else{
                    alert(data.msg);
                }
            }
        })
    });
});
