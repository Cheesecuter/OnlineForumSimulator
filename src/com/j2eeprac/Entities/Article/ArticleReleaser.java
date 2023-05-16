package com.j2eeprac.Entities.Article;

public class ArticleReleaser implements I_ArticleReleaser {

	private Article article;

	@Override
	public void releaseArticle(String aid, String aName, String author, String content) {
		this.article = new Article(aid, aName, author, content);
	}

	@Override
	public Article getArticle() {
		return this.article;
	}

}
