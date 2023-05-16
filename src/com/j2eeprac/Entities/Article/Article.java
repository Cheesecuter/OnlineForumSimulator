package com.j2eeprac.Entities.Article;

public class Article {
	private String aid;
	private String aName;
	private String author;
	private String content;

	public Article(String aid, String aName, String author, String content) {
		this.aid = aid;
		this.aName = aName;
		this.author = author;
		this.content = content;
	}
	
	public String getAID() {
		return this.aid;
	}

	public String getAname() {
		return this.aName;
	}

	public String getAuthor() {
		return this.author;
	}

	public String getContent() {
		return this.content;
	}

}
