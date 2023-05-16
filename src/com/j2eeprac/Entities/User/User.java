package com.j2eeprac.Entities.User;

import com.j2eeprac.Entities.Article.ArticleReleaserProxy;

public class User {

	private String uid;
	private String uKey;
	private String uName;
	private String uSex;
	private int uAge;
	private int authority;
	private ArticleReleaserProxy articleReleaserProxy;

	public User(String uid, String uKey, String uName, String uSex, int uAge, int authority) {
		this.uid=uid;
		this.uKey=uKey;
		this.uName = uName;
		this.uSex = uSex;
		this.uAge = uAge;
		this.authority = authority;
	}
	
	public void setUserInfo(String uid, String uKey, String uName, String uSex, int uAge, int authority) {
		this.uid = uid;
		this.uKey=uKey;
		this.uName = uName;
		this.uSex = uSex;
		this.uAge = uAge;
		this.authority = authority;
	}

	public void setArticleReleaser(ArticleReleaserProxy articleReleaserProxy) {
		this.articleReleaserProxy = articleReleaserProxy;
	}

	public ArticleReleaserProxy getArticleReleaser() {
		return this.articleReleaserProxy;
	}

	public String getUID() {
		return this.uid;
	}
	
	public String getUkey() {
		return this.uKey;
	}

	public String getUname() {
		return this.uName;
	}

	public String getUsex() {
		return this.uSex;
	}

	public int getUage() {
		return this.uAge;
	}

	public int getAuthority() {
		return this.authority;
	}

}
