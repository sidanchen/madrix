/**
 * Created by sdc on 2018/3/4.
 */
$(function(){
    setInterval(function isLogin(){
        $.ajax({
            url:"il",
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.status == "faild"){
                    window.location.href = "login.html";
                }
            }
        })
    },1000)
})