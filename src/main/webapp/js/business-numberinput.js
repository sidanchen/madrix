var oldValue = 0;//存储上一个正确的值
//文档加载后执行
jQuery(document).ready(function () {
    /*使用键盘弹起事件判断文本框是否有改变*/
    jQuery("#numberInput").keyup(function () {
        numberInputIsNaN();
    });

    /*判断文本框的值发生改变之后是否合法*/
    jQuery("#numberInput").blur(function () {
        numberInputIsNaN();
        var value = jQuery("#numberInput").val();//获取文本框的值
        if (value % 100 != 0) {//如果这个值不是100的整数倍
            var newValue = parseInt(value / 100) * 100;
            jQuery("#numberInput").val(newValue);
        }
    });

    /*判断文本框的内容是否合法，如果不合法替换为之前的值*/
    function numberInputIsNaN() {
        var value = jQuery("#numberInput").val();//获取文本框的值
        var re = /^[0-9]+jQuery/;//正则表达式，判断是否是数字
        if (!re.test(value)) {//判断不是一个数字,
            jQuery("#numberInput").val(oldValue);//如果这一次输入的不是一个数字，则将当前值替换成上一个正确的值
        } else {//如果这一次输入的是正确的值，则将值存放到oldValue变量中
            oldValue = value;
        }

        if (value < 0) {//如果值小于0 则将值替换为0
            value = 0;
            jQuery("#numberInput").val(value);//将新的值放入到input中
        }
    }

    /*为sub绑定click事件*/
    jQuery("#sub").click(function () {
        var value = jQuery("#numberInput").val();//获取文本框的值
        var newValue = value - 100;
        if (newValue < 0) {//如果值小于0 则将值替换为0
            newValue = 0;
        }
        jQuery("#numberInput").val(newValue);//将新的值放入到input中
    });

    /*为add绑定click事件*/
    jQuery("#add").click(function () {
        var value = jQuery("#numberInput").val();//获取文本框的值
        var newValue = parseInt(value) + 100;//value会被当成字符串处理，需要先转换为int类型
        jQuery("#numberInput").val(newValue);//将新的值放入到input中
    });
});