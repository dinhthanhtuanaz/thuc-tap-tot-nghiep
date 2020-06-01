$(document).ready(function() {
	$('.treeview > a.treeview-not-link').click(function(event) {
		event.preventDefault();
		$(this).next('.treeview-menu').slideToggle(200);
		$(this).parent().siblings().find('.treeview-menu').slideUp(200);
		$(this).find('.toggle-rotate').toggleClass('toggle-rotate--active');
		$(this).parent().siblings().find('.toggle-rotate').removeClass('toggle-rotate--active');
    });

    $('.counter').counterUp({
        delay: 10,
        time: 500
    });
});
