/**
 * Created by sdc on 2018/3/3.
 */
var clickFlag = true;
var birghtness = new Array(250, 215, 180, 155, 130, 105, 80, 55, 30, 0);
var freshen = true;
var color = new Array("S1P1", "S1P2", "S1P3", "S1P4", "", "S1P5", "S1P6", "S1P7", "S1P8");
//进度条数组
var arr = ["Progress0", "Progress1", "Progress2", "Progress3", "Progress4", "Progress5", "Progress6", "Progress7", "Progress8", "Progress9"]
//灯光状态数组
var arr2 = ["box-red", "box-yellow", "box-gray", "box-pink", "box-green", "box-orange", "box-purple", "box-cyan"]
//开关按钮绑定
var imgsrc = document.getElementById("openimg");
var currentAjax;
function giveInstruction(order, value) {
    if(freshen) {
        currentAjax.abort();
        clickFlag = false;
        $.ajax({
            url: "gi",
            data: {"order": order, "value": value},
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data.status == "success") {
                    clickFlag = true;
                } else {
                    clickFlag = true;
                    alert(data.msg);
                }
            }
        });
    }
}
//进度条事件
function brightness(a) {
    if(freshen) {
        if (imgsrc.getAttribute("src") == "images/o1.png") {
            var subscript
            //获取点击块的下标
            for (var i = 0; i < arr.length; i++) {
                if (arr[i] == a.id) {
                    subscript = i
                    break;
                }
            }

            giveInstruction("4", birghtness[subscript]);
            //点亮点击块之前的块
            for (var j = 0; j <= subscript; j++) {
                var obj = document.getElementById(arr[j]);
                obj.style.cssText = "background-color:#73c9e3;";
            }
            //熄灭点击块后面的块
            for (var k = subscript + 1; k < arr.length; k++) {
                var obj = document.getElementById(arr[k]);
                obj.style.cssText = "background-color:#fff;";
            }
        }
    }
}
//点击切换图片
function openclose() {
    if(freshen) {
        var imgobj = (imgsrc.getAttribute("src") == "images/o2.png");
        imgsrc.src = imgobj ? "images/o1.png" : "images/o2.png";
        if (imgsrc.getAttribute("src") == "images/o2.png") {
            giveInstruction("2", "");
            //关闭所有灯光状态
            for (var i = 0; i < arr2.length; i++) {
                if ($("#" + arr2[i]).hasClass("close")) {
                    $("#" + arr2[i]).removeClass("close")
                }
            }
            for (var i = 0; i < arr.length; i++) {
                //熄灭亮度管理
                var obj = document.getElementById(arr[i]);
                obj.style.cssText = "background-color:#fff;"
            }
        } else {
            giveInstruction("1", "");
        }
    }
}
//小方块点击阴影
function shadow(a, value) {
    if(freshen) {
        if (imgsrc.getAttribute("src") == "images/o1.png") {

            for (var i = 0; i < arr2.length; i++) {
                if ($("#" + arr2[i]).hasClass("close") && arr2[i] != a.id) {
                    $("#" + arr2[i]).removeClass("close")
                    break;
                }
            }
            giveInstruction("3", color[value]);
            if ($("#" + a.id).hasClass("close")) {
                $("#" + a.id).removeClass("close")
            } else {
                $("#" + a.id).addClass("close")
            }
        }
    }
}


//获取远程的灯光状态
function farLightStatus() {
    if(freshen) {
        currentAjax = $.ajax({
            url: "fls",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data.status == "success" && clickFlag) {
                    if (data.msg.onOff == "0") {
                        $("#openimg").attr("src", "images/o1.png");
                        //当开灯的时候才改变亮度与颜色
                        if (data.msg.color < 5) {
                            $(".box1").removeClass("close");
                            $(".box1:eq(" + (data.msg.color - 1) + ")").addClass("close");
                        } else if (data.msg.color >= 5) {
                            $(".box1").removeClass("close");
                            $(".box1:eq(" + (data.msg.color) + ")").addClass("close");
                        }
                        $(".boxcol").css("backgroundColor", "#fff");
                        if (data.msg.brightness == "0") {
                            $(".boxcol:lt(10)").css("backgroundColor", "#73c9e3");
                        } else if (data.msg.brightness == "30") {
                            $(".boxcol:lt(9)").css("backgroundColor", "#73c9e3");
                        } else if (data.msg.brightness == "55") {
                            $(".boxcol:lt(8)").css("backgroundColor", "#73c9e3");
                        } else if (data.msg.brightness == "80") {
                            $(".boxcol:lt(7)").css("backgroundColor", "#73c9e3");
                        } else if (data.msg.brightness == "105") {
                            $(".boxcol:lt(6)").css("backgroundColor", "#73c9e3");
                        } else if (data.msg.brightness == "130") {
                            $(".boxcol:lt(5)").css("backgroundColor", "#73c9e3");
                        } else if (data.msg.brightness == "155") {
                            $(".boxcol:lt(4)").css("backgroundColor", "#73c9e3");
                        } else if (data.msg.brightness == "180") {
                            $(".boxcol:lt(3)").css("backgroundColor", "#73c9e3");
                        } else if (data.msg.brightness == "215") {
                            $(".boxcol:lt(2)").css("backgroundColor", "#73c9e3");
                        } else if (data.msg.brightness == "250") {
                            $(".boxcol:lt(1)").css("backgroundColor", "#73c9e3");
                        }
                    } else if (data.msg.onOff == "1") {
                        //当关灯时所有的图片不高亮亮度变为零
                        $(".box1").removeClass("close");
                        $(".boxcol").css("backgroundColor", "#fff");
                        $("#openimg").attr("src", "images/o2.png");
                    }
                } else if (data.status == "faild") {
                    alert(data.msg);
                    freshen = false;
                }
            }
        });
    }
}

$(function () {
    setInterval("farLightStatus()", 2000);
})