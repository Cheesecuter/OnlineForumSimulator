/**
 * 
 */
var LoginFrameOpenModel = document.querySelector(".model-btn");
var LoginFrameLoginModel = document.querySelector(".loginButton");
var LoginFrameCloseModel = document.querySelector(".loginFrameCloseButton");
var LoginFrame = document.querySelector(".loginFrame");

LoginFrameOpenModel.onclick = function() {
	$(LoginFrame).fadeIn(200);
	document.documentElement.style.overflow = "hidden";
}
LoginFrameLoginModel.onclick = function() {
	$(LoginFrame).stop().fadeOut(100);
	document.documentElement.style.overflow = "scroll";
}
LoginFrameCloseModel.onclick = function() {
	$(LoginFrame).stop().fadeOut(100);
	document.documentElement.style.overflow = "scroll";
}
function getScroll() {
	return {
		scrollTop: document.documentElement.scrollTop
			|| document.body.scrollTop,
		scrollLeft: document.documentElement.scrollLeft
			|| document.body.scrollLeft
	}
}
function getPage(e) {
	return {
		pageX: e.clientX + getScroll().scrollLeft,
		pageY: e.clientY + getScroll().scrollTop,
	}
}
var box = document.querySelector(".loginFrameBox");
var boxH = document.querySelector(".loginFrameTitle");
var body = document.querySelector(".loginFrame");
boxH.onmousedown = function(e) {
	e = e || event;
	var x = e.pageX - box.offsetLeft;
	var y = e.pageY - box.offsetTop;
	document.onmouseover = function(e) {
		var boxX = e.pageX - x;
		var boxY = e.pageY - y;
		var docW = window.innerWidth;
		var docH = window.innerHeight;
		var loginW = box.offsetWidth;
		var loginH = box.offsetWidth;
		var indocW = docW - loginW;
		var indocH = docH - loginH;

		boxX = boxX < 0 ? 0 : boxX;
		boxY = boxY < 0 ? 0 : boxY;
		boxX = boxX > indocW ? indocW : boxX;
		boxY = boxY > indocH ? indocH : boxY;

		box.style.left = boxX + 'px';
		box.style.top = boxY + 'px';
		box.style.marginLeft = 0;
		box.style.marginTop = 0;
	}
}
document.onmouseup = function() {
	document.onmouseover = null;
}