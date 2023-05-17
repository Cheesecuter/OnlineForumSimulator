<%@page import="com.j2eeprac.Entities.Article.Article"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<title>J2EE-PRAC</title>
<link rel="stylesheet"
	href="css/ArticlePage.css?v=<%=System.currentTimeMillis()%>"
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
	List<Article> homeArticleList;
	int authorFlag = 0;
	int adminFlag = 0;
	String linkAdminFlag = "0";
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
				<div class="contentBox-Container">
					<div class="contentBox">
						<div class="articleHeaderBox">
							<div class="articleHeader">
								<div class="articleTitleBox">
									<h1 id="articleTitle" class="articleTitle">
										<%
											articleAID = (String) session.getAttribute("articleAID");
										articleTitle = (String) session.getAttribute("articleTitle");
										if (articleTitle == null) {
											articleTitle = "";
										}
										%>
										<%=articleTitle%>
									</h1>
									<h2 id="articleAuthor" class="articleAuthor">
										<%
											articleAuthor = (String) session.getAttribute("articleAuthor");
										if (articleAuthor == null) {
											articleAuthor = "";
										}
										%>
										作者：<%=articleAuthor%>
									</h2>
								</div>
							</div>
						</div>
						<article>
							<div class="articleContent">
								<p id="articleContent">
									<%
										articleContent = (String) session.getAttribute("articleContent");
									if (articleContent == null) {
										articleContent = "";
									}
									articleContent = articleContent.replaceAll("\n", "</br>");
									articleContent = articleContent.replaceAll(" ", "&nbsp;");
									articleContent = articleContent.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
									%>
									<%=articleContent%>
								</p>
							</div>
						</article>
					</div>
				</div>
			</div>
			<%--占位符 --%>
			<aside class="mainBox-Aside">
				<div class="mainBox-Aside-Container">
					<div class="mainBox-Aside-Container-ArticleEditor">
						<ul>
							<li><a onclick="articleEditorForm.submit()">
									<div class="mainBox-Aside-Container-ArticleEditor-Menulist">
										<%
											adminFlag = (Integer) session.getAttribute("userAuthority");
										loginUserName = (String) session.getAttribute("loginUserName");
										articleAuthor = (String) session.getAttribute("articleAuthor");
										if (((loginUserName != null) && loginUserName.equals(articleAuthor) && (!loginUserName.equals("未登录")))
												|| adminFlag == 1) {
											authorFlag = 1;
											request.setAttribute("authorFlag", authorFlag);
										}
										%>
										<c:if test="${authorFlag == 0}">
										</c:if>
										<c:if test="${authorFlag == 1}">
											<div class="articleEditor">
												<form name="articleEditorForm" action="LinkArticleEditor"
													method="post">
													编辑文章 <input type="hidden" name="releaseFlag" value="false" />
													<%--<a href="LinkArticleEditor">编辑器</a> --%>
												</form>
											</div>
										</c:if>
									</div>
							</a></li>
						</ul>
					</div>
				</div>
				<div>
					<%
						homeArticleList = (List<Article>) session.getAttribute("homeArticleList");
					request.setAttribute("homeArticleList", homeArticleList);
					%>
					<div class="mainBox-Aside-ArticlesContainer">
						<c:if test="${homeArticleList!=null }">
							<c:forEach var="article" items="${homeArticleList }">
								<div class="mainBox-Aside-ArticleContainer">
									<div class="mainBox-Aside-ArticleBox">
										<form name="articleForm<c:out value='${article.getAID() }' />"
											class="articleForm" action="PageArticle" method="post">
											<a
												onclick="articleForm<c:out value='${article.getAID() }' />.submit()">
												<div class="articleAID" style="display: none;">
													<input name="articleAID"
														value="<c:out value='${article.getAID() }' />" />
												</div>
												<div class="mainBox-Aside-ArticleTitle">
													<c:out value="${article.getAname() }" />
												</div>
												<div class="mainBox-Aside-ArticleAuthor">
													作者：
													<c:out value="${article.getAuthor() }" />
												</div>
												<div class="mainBox-Aside-ArticleContent">
													<c:out value="${article.getContent() }" />
												</div>
											</a>
										</form>
									</div>
								</div>
							</c:forEach>
						</c:if>
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
	<script>
		function getSearchInput() {
			var searchInput = document.getElementById("searchInput");

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