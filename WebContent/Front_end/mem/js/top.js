 $(function(){
    $("#back-to-top").click(function(){
        $("html,body").animate({
            scrollTop:0
        },1000);
    });
    $(window).scroll(function() {
        if ( $(this).scrollTop() > 100){
            $('#back-to-top').fadeIn("fast");
        } else {
            $('#back-to-top').stop().fadeOut("fast");
        }
    });
});