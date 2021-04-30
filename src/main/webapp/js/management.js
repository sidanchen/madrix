/**
 * Created by sdc on 2018/3/3.
 */
//验证用户名的有效性
function testUsername() {
    // 获取用户输入用户名
    var username = $("#username").val();
    // 用户名正则表达式
    var usernameRex = /^[A-Za-z0-9]{4,8}$/;
    // 提示用户用户名不能为空
    if (username == "") {
        alert("User name cannot be empty!");
        return false;
    }
    /*
     * if (!usernameRex.test(username)) { alert("User names can only be numbers
     * from 4 to 8 digits or letters!"); return false; }
     */
    return true;
}
// 验证用户的密码有效性
function testPassword() {
    // 获取用户输入的密码
    var password = $("#password").val();
    // 获取用户输入的确认密码
    var confirmPassword = $("#confirmPassword").val();
    // 密码的正则表达式
    var passwordRex = /^[a-zA-Z][a-zA-Z0-9]{5,20}$/;
    if (password == "") {
        alert("Password cannot be empty ");
        return false;
    }
    /*
     * if (!passwordRex.test(password)) { alert("An initial letter; a numeric
     * combination!"); return false; }
     */
    return true;

}
function testEmail(email) {
    if (email.indexOf("@") == -1) {
        alert("Please enter the correct mailbox format");
        return false;
    }
    return true;
}
function findUser(page) {
    $.ajax({
        url: "fubp",
        data: {"page": page},
        type: "post",
        dataType: "json",

        success: function (data) {
            if (data.status == "success") {
                //删除子类元素
                $(".usertable").children().remove();
                $.each(data.msg, function (index, obj) {
                    var element = "<tr class='col-md-12 column'>"
                        + "<td class='tab-user'>" + obj.loginName + "</td>"
                        + "<td class='tab-user'>" + obj.email + "</td>"
                        + "<td class='tab-user'>"
                        + "<img src='images/delete.jpg' class='deleteU'onclick='deleteU("+obj.id+")'/>" +
                        "</td>"
                        + "</tr>";
                    $(".usertable").append(element);
                })
            } else {
                alert(data.msg);
            }
        }
    })
}

function initPage(){
    $.ajax({
        url: "totalpages",
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
                        findUser(current);
                    }
                });
            } else {
                alert(data.msg);
            }
        }
    });
}

function deleteU(id){
    $.ajax({
        url:"delete",
        data:{"id":id},
        type:"post",
        dataType:"json",

        success:function(data){
            if(data.status == "success"){
                findUser(1);
                initPage();
                alert(data.msg);
            }else{
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
        findUser(1);
        initPage();
    }
    $(".btn").click(function () {
        var userName = $("#username").val();
        var passwrd = $("#pwd").val();
        var email = $("#email").val();
        if (!testUsername(userName)) {
            return false;
        }
        if (!testPassword(passwrd)) {
            return false;
        }
        if (!testEmail(email)) {
            return false;
        }
        $.ajax({
            url: "add",
            data: {"userName": userName, "password": passwrd, "email": email},
            type: "post",
            dataType: "json",

            success: function (data) {
                if (data.status == "success") {
                    findUser(1);
                    initPage();
                    alert(data.msg);
                } else {
                    alert(data.msg);
                }
            }
        })
        return false;
    })
})