jQuery(document).ready(function ($) {
    var scroll_top_duration = 250;
    var $back_to_top = $('.cd-top');

    $back_to_top.on('click', function (event) {
        event.preventDefault();
        $('body, html').animate({
                scrollTop: 0
            }, scroll_top_duration
        );
    });

});
