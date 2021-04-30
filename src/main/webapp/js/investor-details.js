$(document).ready(function () {
    function centerModals() {
        $('.modal').each(function (i) {
            var $clone = $(this).clone().css('display', 'block').appendTo('body');
            var top = Math.round(($clone.height() - $clone.find('.modal-content').height()) / 2);
            top = top > 50 ? top : 0;
            $clone.remove();
            $(this).find('.modal-content').css("margin-top", top - 50);
        });
    }
    // 在模态框出现的时候调用垂直居中函数
    $('.modal').on('show.bs.modal', centerModals);
    // 在窗口大小改变的时候调用垂直居中函数
    $(window).on('resize', centerModals);
});