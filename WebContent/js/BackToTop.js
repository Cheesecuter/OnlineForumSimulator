/**
 * 
 */
$(function() {
	$(window).scroll(
		function() {
			var scrollt = document.documentElement.scrollTop
				+ document.body.scrollTop;
			if (scrollt > 1) {
				$("#backToTop").fadeIn(400);
			} else {
				$("#backToTop").stop().fadeOut(200);
			}
		});
	$("#backToTop").click(function() {
		$("html,body").animate({
			scrollTop: "0px"
		}, 200);
	});
});