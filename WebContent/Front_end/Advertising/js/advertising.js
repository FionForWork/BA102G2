$(function() {
 /** 
 * 根據螢幕寬度的變化決定輪播圖片應該展示什麼 
 */ 
    function resize (){
     // 獲取螢幕寬度 
        var windowWidth = $(window).width;
     // 判斷螢幕屬於大還是小 
        var isSmallScreen = windowWidth < 768; 
     // 根據大小為介面上的每一張輪播圖設置背景 
        $('#main_ad > .carousel-inner > .item').each(function(i, item) {
            var $item = $(item); 
     //var imgSrc = $item.data(isSmallScreen ?'image-xs' : 'image-lg');
            var imgSrc = isSmallScreen ?$item.data('image-xs') : $item.data('image-lg'); 
            // console.log("imgSrc"+imgSrc);
     // 設置背景圖片
            $item.css('backgroundImage', 'url("' + imgSrc + '")'); 
     // 
     //因為我們需要小圖時 尺寸等比例變化，所以小圖時我們使用img方式 
            if (isSmallScreen) {
                $item.html('<img src="' + imgSrc + '" alt="" />');
            } 
            else { 
                $item.empty; 
            }

        });
 } 
 // // 讓window對象立即觸發一下resize，初始化div的背景圖 
 $(window).trigger('resize'); 
  $(window).on('resize',resize).trigger('resize'); 
 });