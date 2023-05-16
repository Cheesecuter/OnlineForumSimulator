<%@page import="java.util.Date"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<title>J2EE-PRAC</title>
<link rel="stylesheet"
	href="css/ArticleEditor.css?v=<%=System.currentTimeMillis()%>"
	type="text/css" />
<link rel="stylesheet"
	href="css/LoginFrame.css?v=<%=System.currentTimeMillis()%>"
	type="text/css" />
</head>

<body>
	<%
		String searchInput;
	String loginUserName;
	String msg;
	String articleAID;
	String articleContent;
	String articleTitle;
	String articleAuthor;
	int authorFlag = 0;
	int adminFlag = 0;
	String linkAdminFlag = "0";
	String releaseFlag = "false";
	%>
	<div class="mainPage">
		<div class="toolBar">
			<div class="toolBar-Container">
				<div class="toolBar-Container-Left">
					<div class="toolBar-Container-Left-Logo">
						<a href="LinkHome"> <img title="J2EE-PRAC"
							src="<%="WebContent/images/icon.png"%>${backurl}?t=<%=new Date()%>"
							width="80px">
						</a>
					</div>
					<%
						linkAdminFlag = (String) session.getAttribute("adminFlag");
					if (linkAdminFlag == null || linkAdminFlag.equals("0")) {
						linkAdminFlag = "0";
						request.setAttribute("adminFlag", linkAdminFlag);
					} else {
						linkAdminFlag = "1";
						request.setAttribute("adminFlag", linkAdminFlag);
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
					if (session.getAttribute("releaseFlag") != null) {
					releaseFlag = (String) session.getAttribute("releaseFlag");
				}
				%>
				<div class="contentBox-Container">
					<div class="contentBox">
						<form id="articleEditorForm" name="articleEditorForm"
							action="ArticleEditor" method="post">
							<input type="hidden" name="releaseFlag" value="<%=releaseFlag %>" />
							<div class="articleHeaderBox">
								<div class="articleHeader">
									<div class="articleTitleBox">
										<%
											articleAID = (String) session.getAttribute("articleAID");
										articleTitle = (String) session.getAttribute("articleTitle");
										if (articleTitle == null) {
											articleTitle = "";
										}
										%>
										<textarea id="articleTitle" name="articleTitle"
											class="textareaArticleTitle" placeholder="请输入文章标题"
											required=true><%=articleTitle%></textarea>
									</div>
								</div>
							</div>
							<div>
								<div class="articleContent">
									<%
										articleContent = (String) session.getAttribute("articleContent");
									if (articleContent == null) {
										articleContent = "";
									}
									articleContent = articleContent.replaceAll("</br>", "\n");
									articleContent = articleContent.replaceAll("&nbsp;", " ");
									articleContent = articleContent.replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;", "\t");
									%>
									<textarea id="articleContent" name="articleContent"
										class="textareaArticleContent"><%=articleContent%></textarea>
								</div>
							</div>
							<input type="hidden" id="articleEditorDeleteFlag"
								name="articleEditorDeleteFlag" value=0 />
						</form>
					</div>
				</div>
				<div class="articleEditorContainer">
					<%
						adminFlag = (Integer) session.getAttribute("userAuthority");
					loginUserName = (String) session.getAttribute("loginUserName");
					articleAuthor = (String) session.getAttribute("articleAuthor");
					if ((loginUserName != null) && (loginUserName.equals(articleAuthor) && (!loginUserName.equals("未登录")))
							|| adminFlag == 1) {
						authorFlag = 1;
					}
					%>
					<div class="articleEditorButtonContainer">
						<button type="button" onclick="articleEditorFormSubmit()"
							class="articleSubmitButton">
							<span style="font-weight: border;">提交</span>
						</button>
						<button type="button" id="articleDeleteButton"
							class="articleDeleteButton" onclick="editArticleDelete()"
							style="display: block;">
							<span style="font-weight: border;">删除</span>
						</button>
						<button type="button" id="articleDeleteButtonCheck"
							class="articleDeleteButtonCheck"
							onclick="editArticleDeleteCheck()" style="display: none;">
							<span style="font-weight: border; color: #ff5555">确认删除</span>
						</button>
					</div>
				</div>
			</div>
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
								10100020002&nbsp;&nbsp;</span> <span>Email: j2eeprac@mail.com</span>
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
	<script>
		function editArticleDelete() {
			var articleDeleteButton = document
					.getElementById("articleDeleteButton");
			var articleDeleteButtonCheck = document
					.getElementById("articleDeleteButtonCheck");
			articleDeleteButton.style.display = "none";
			articleDeleteButtonCheck.style.display = "block";
		}
		function editArticleDeleteCheck() {
			var articleEditorDeleteFlag = document
					.getElementById("articleEditorDeleteFlag");
			articleEditorDeleteFlag.value = 1;
			document.getElementById("articleEditorForm").submit();
		}
		function articleEditorFormSubmit(){
			var articleTitle = document.getElementById("articleTitle").value;
			if(articleTitle.length==0){
				alert("标题不能为空");
			}
			else{
				document.getElementById("articleEditorForm").submit();
			}
		}
	</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/UpdateUser.js?v=<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/LoginFrame.js?v=<%=System.currentTimeMillis()%>"></script>
	<%--
	<script src="http://cdn.staticfile.org/jquery/1.11.1-rc2/jquery.min.js"></script>
	 --%>
	 <script type="text/javascript"
		src="${pageContext.request.contextPath }/js/jquery.min.js?v=<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/BackToTop.js?v=<%=System.currentTimeMillis()%>"></script>
</body>

</html>