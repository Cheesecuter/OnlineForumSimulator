<%@page import="com.j2eeprac.Entities.Article.Article"%>
<%@page import="com.j2eeprac.Entities.User.User"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>J2EE-PRAC</title>
<link rel="stylesheet"
	href="css/Home-Admin.css?v=<%=System.currentTimeMillis()%>"
	type="text/css" />
<link rel="stylesheet"
	href="css/LoginFrame.css?v=<%=System.currentTimeMillis()%>"
	type="text/css" />
</head>
<body>
	<%
		String searchInput;
	String loginUserAvatar;
	String loginUserName;
	String msg;
	int loggedFlag = 0;
	User userProfile;
	List<Article> userReleases;
	List<User> userList;
	List<Article> articleList;
	String adminFlag = "0";
	%>
	<div class="mainPage">
		<div class="toolBar">
			<div class="toolBar-Container">
				<div class="toolBar-Container-Left">
					<div class="toolBar-Container-Left-Logo">
						<a href="LinkHome"> <img title="J2EE-PRAC"
							src="WebContent/images/icon.png?v=<%=System.currentTimeMillis()%>"
							width="80px">
						</a>
					</div>
					<%
						adminFlag = (String) session.getAttribute("adminFlag");
					if (adminFlag == null || adminFlag.equals("0")) {
						adminFlag = "0";
						request.setAttribute("adminFlag", adminFlag);
					} else {
						adminFlag = "1";
						request.setAttribute("adminFlag", adminFlag);
					}
					%>
					<ul class="toolBar-Container-Left-Menus" id="toolBar-Menu">
						<li class="" title="主页"><a href="LinkHome">主页</a></li>
						<li class="" title="下载"><a href="LinkHome">下载</a></li>
						<li class="" title="社区"><a href="LinkHome">社区</a></li>
						<c:if test="${adminFlag=='0' }">
							<li class="" title="个人中心"><a href="LinkUserHome">个人中心</a></li>
						</c:if>
						<c:if test="${adminFlag=='1' }">
							<li class="" title="个人中心"><a href="LinkAdminHome">个人中心</a></li>
						</c:if>
					</ul>
				</div>
				<div class="toolBar-Container-Middle">
					<div class="toolBar-Container-Middle-Search">
						<form class="toolBar-Container-Middle-Search-Container"
							action="QueryArticleServlet" method="post">
							<input id="searchInput" maxlength="1000" autocomplete="off"
								name="searchInput" type="text"
								<%searchInput = (String) session.getAttribute("searchInput");
if (searchInput == null) {
	searchInput = "";
}%>
								value="<%=searchInput%>" style="text-indent: 12px;">
							<button id="searchButton" type="submit">
								<span>搜索</span>
							</button>
						</form>
					</div>
				</div>
				<div class="toolBar-Container-Right">
					<div class="toolBar-Container-Right-UserName">
						<%
							loginUserName = (String) session.getAttribute("loginUserName");
						msg = (String) session.getAttribute("msg");
						if (loginUserName == null || loginUserName.equals("未登录")) {
							loginUserName = "未登录";
						}
						if (msg == null) {
							msg = "";
						}
						%>
						<span id="loginUserName"> <%="登入用户: " + loginUserName%></span> <br />
						<span> <%=msg%></span>
					</div>
					<div class="toolBar-Container-Right-Btns">
						<div class="toolBar-Container-Right-Btns-Login">
							<button id="loginUserAvatar" type="button" class="model-btn">
								<img class="toolBar-Container-Right-Btns-Avatar" title="登录"
									src="WebContent/images/defaultAvatar.png?v=<%=System.currentTimeMillis()%>"
									width="36px">
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="mainBox-Container">
			<div class="mainBox">
				<%
					if (loginUserName == null || loginUserName.equals("未登录")) {
					loggedFlag = 0;
					request.setAttribute("loggedFlag", loggedFlag);
				} else {
					loggedFlag = 1;
					request.setAttribute("loggedFlag", loggedFlag);
					userProfile = (User) session.getAttribute("userProfile");
					userReleases = (List<Article>) session.getAttribute("userReleases");
					userList = (List<User>) session.getAttribute("userList");
					articleList = (List<Article>) session.getAttribute("articleList");
					request.setAttribute("userList", userList);
					request.setAttribute("articleList", articleList);
				}
				%>
				<c:if test="${loggedFlag == 0}">
					<div class="mainBox-UserBox">
						<span>未登录用户</span>
					</div>
				</c:if>
				<c:if test="${loggedFlag == 1}">
					<%--普通用户操作面板 --%>
					<c:if test="${userProfile!=null }">
						<div class="mainBox-UserProfileBoxContainer">
							<div class="mainBox-UserProfileBox">
								<div class="mainBox-UserProfileBox-Title">编辑个人信息</div>
								<form name="userProfileForm" action="UserProfileEditor"
									method="post">
									<div class="userProfileUID">
										<span class="userProfileStarTXT">&nbsp;</span>
										<div class="userProfileUIDTXT">&nbsp;UID</div>
										<input value="<c:out value='${userProfile.getUID() }'/>"
											disabled="true" /> <input type="hidden"
											name="userProfileIptUID"
											value="<c:out value='${userProfile.getUID() }'/>" />
									</div>
									<div class="userProfileUname">
										<span class="userProfileStarTXT">*</span>
										<div class="userProfileUnameTXT">&nbsp;昵称</div>
										<input name="userProfileIptUname"
											value="<c:out value='${userProfile.getUname() }'/>" />
									</div>
									<div class="userProfileUkey">
										<span class="userProfileStarTXT">*</span>
										<div class="userProfileUkeyTXT">&nbsp;密码</div>
										<input name="userProfileIptUkey" type="password"
											id="userProfileIptUkey" required=true onkeyup="pw_check()"
											oninvalid="setCustomValidity('密码不能为空');"
											oninput="setCustomValidity('');" />
									</div>
									<div class="userProfileUkeyCheck">
										<span class="userProfileStarTXT">*</span>
										<div class="userProfileUkeyCheckTXT">&nbsp;确认密码</div>
										<input name="userProfileIptUkeyCheck" type="password"
											id="userProfileIptUkeyCheck" required=true
											onkeyup="pw_check()" />
										<div class="userProfileErrorPW" id="userProfileErrorPW"></div>
									</div>
									<div class="userProfileUsex">
										<span class="userProfileStarTXT">&nbsp;</span>
										<div class="userProfileUsexTXT">&nbsp;性别</div>
										<input name="userProfileIptUsex"
											value="<c:out value='${userProfile.getUsex() }'/>" />
									</div>
									<div class="userProfileUage">
										<span class="userProfileStarTXT">&nbsp;</span>
										<div class="userProfileUageTXT">&nbsp;年龄</div>
										<input name="userProfileIptUage"
											value="<c:out value='${userProfile.getUage() }'/>" />
									</div>
									<div class="userProfileSubmitBtn">
										<button type="submit" id="userProfileFormSubmitBtn"
											class="userProfileFormSubmitBtn">
											<span style="font-weight: border;">修改</span>
										</button>
									</div>
								</form>
							</div>
						</div>
					</c:if>
					<c:if test="${userReleases!=null}">
						<div class="mainBox-UserReleasesBox">
							<table class="articleListTable">
								<c:if test="${userReleases!=null}">
									<tr class="articleListTab">
										<td class="articleTabAID"><c:out value="AID" /></td>
										<td class="articleTabAname"><c:out value="名称" /></td>
										<td class="articleTabAuthor"><c:out value="作者" /></td>
										<td class="articleTabEditBtn"></td>
									</tr>
								</c:if>
								<c:forEach var="article" items="${userReleases}">
									<tr class="articleListTab">
										<form
											name="editArticleForm<c:out value='${article.getAID() }' />"
											action="UserArticleEditor" method="post">
											<input type="hidden" name="editArticleAID"
												value="<c:out value='${article.getAID() }' />" />
											<td class="articleTabAID"><c:out
													value="${article.getAID()}" /></td>
											<td class="articleTabAname">&nbsp;<c:out
													value="${article.getAname()}" /></td>
											<td class="articleTabAuthor"><c:out
													value="${article.getAuthor()}" /></td>
											<td class="articleTabEditBtn"><a
												onclick="editArticleForm<c:out value='${article.getAID() }' />.submit()">
													<div class="articleTabEditButton">编辑</div>
											</a></td>
										</form>
									</tr>
								</c:forEach>
							</table>
						</div>
					</c:if>
				</c:if>
			</div>
			<aside class="mainBox-Aside">
				<div class="mainBox-Aside-Container">
					<div class="mainBox-Aside-Container-AdminEditor">
						<ul>
							<li><a onclick="userProfile.submit()">
									<div class="mainBox-Aside-Container-AdminEditor-Menulist">
										<form name="userProfile" action="UserProfileBtn" method="post">
											个人信息 <input name="userProfileName" type="hidden"
												value="<%=loginUserName%>" />
										</form>
									</div>
							</a></li>
							<li><a onclick="userReleases.submit()">
									<div class="mainBox-Aside-Container-AdminEditor-Menulist">
										<form name="userReleases" action="UserReleasesBtn"
											method="post">
											内容管理<input name="userProfileName" type="hidden"
												value="<%=loginUserName%>" />
										</form>
									</div>
							</a></li>
							<li><a onclick="articleRelease.submit()">
									<div class="mainBox-Aside-Container-AdminEditor-Menulist">
										<form name="articleRelease" action="UserArticleReleaser"
											method="post">
											作品发布<input name="userProfileName" type="hidden"
												value="<%=loginUserName%>" />
										</form>
									</div>
							</a></li>
						</ul>
					</div>
				</div>
			</aside>
		</div>
		<div class="footer">
			<div class="footerContainer">
				<div class="footerList">
					<h4 class="footerTitle">
						<span>相关链接</span>
					</h4>
					<ul class="footerUl">
						<li><a
							href="https://github.com/Cheesecuter/OnlineForumSimulator"
							style="color: #999;">Github</a></li>
					</ul>
				</div>
				<div class="footerBottom">
					<section class="footerBottomLinks">
						<section class="footerBottomLink">
							<a href="LinkHome" style="color: #999;">关于网址</a> <a
								href="LinkHome" style="color: #999;">联系方式</a> <a href="LinkHome"
								style="color: #999;">帮助中心</a> <a href="LinkHome"
								style="color: #999;">资源下载</a> <br /> <span>Tel:
								10100020002&nbsp;&nbsp;</span> <span>Email: OnlineForum@mail.com</span>
						</section>
						<section class="footerBottomLink">
							<span>Copyright 2023 HRBUST-2004010525.All Rights Reserved</span>
						</section>
					</section>
				</div>
			</div>
		</div>
		<div class="loginFrame" id="loginFrame">
			<div class="loginFrameBox">
				<form class="loginBox" action="LoginServlet" method="post">
					<div class="loginFrameInputBox">
						<div class="loginFrameTitle">登入</div>
					</div>
					<div class="loginFrameInputBox">
						<label for="userName">用户名：</label> <input type="text"
							id="userName" name="userName">
					</div>
					<div class="loginFrameInputBox">
						<label for="userKey">密码：</label> <input type="password"
							id="userKey" name="userKey">
					</div>
					<div class="loginFrameButtonBox">
						<button type="button" class="registerButton"
							onclick="registerOpenForm.submit()">
							<span style="font-weight: border;">注册</span>
						</button>
						<button type="submit" class="loginButton">
							<span style="font-weight: border;">登入</span>
						</button>
					</div>
					<button type="button" class="loginFrameCloseButton">
						<span>X</span>
					</button>
				</form>
				<form name="registerOpenForm" action="LinkRegisterPage"
					method="post"></form>
			</div>
		</div>
		<div id="backToTop">
			<div class="arrow"></div>
			<div class="stick"></div>
		</div>
	</div>
	<!-- get user -->
	<script>
		function editUserDelete(){
			var userEditorDeleteBtn = document.getElementById("btn-userEditorDelete");
			var userEditorDeleteCheckBtn = document.getElementById("btn-userEditorDeleteCheck");
			userEditorDeleteBtn.style.display = "none";
			userEditorDeleteCheckBtn.style.display = "block";
		}
		function editUserDeleteCheck(){
			var userEditorDeleteFlag = document.getElementById("userEditorDeleteFlag");
			userEditorDeleteFlag.value=1;
			document.getElementById("userInfoBox").submit();
		}
		function pw_check() {
			var pw1 = document.getElementById("userProfileIptUkey").value;
			var pw2 = document.getElementById("userProfileIptUkeyCheck").value;
			if (pw1 == pw2) {
				document.getElementById("userProfileFormSubmitBtn").disabled = false;
				document.getElementById("userProfileErrorPW").innerHTML = ""
			} else {
				document.getElementById("userProfileFormSubmitBtn").disabled = true;
				document.getElementById("userProfileErrorPW").innerHTML = "密码不同"
			}
		}
	</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/UpdateUser.js?v=<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/LoginFrame.js?v=<%=System.currentTimeMillis()%>"></script>
	 <script type="text/javascript"
		src="${pageContext.request.contextPath }/js/jquery.min.js?v=<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/BackToTop.js?v=<%=System.currentTimeMillis()%>"></script>
</body>
</html>