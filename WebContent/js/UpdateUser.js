/**
 * 
 */
function getUser() {
	var displayUserAvatar = document
		.querySelector(".toolBar-Container-Right-Btns-Avatar");
	var displayUserName = document.getElementById("loginUserName");
	var userName = document.getElementById("userName");
	displayUserName.innerHTML = "登入用户: " + userName.value;
	displayUserAvatar.style.display = "block";
}