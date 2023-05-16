package com.j2eeprac.Entities.Article;

public class ArticleReleaserProxy implements I_ArticleReleaser {

	private ArticleReleaser articleReleaser;

	public ArticleReleaserProxy(ArticleReleaser articleReleaser) {
		this.articleReleaser = articleReleaser;
	}

	@Override
	public void releaseArticle(String aid, String aName, String author, String content) {
		this.articleReleaser.releaseArticle(aid, aName, author, content);
	}

	@Override
	public Article getArticle() {
		return this.articleReleaser.getArticle();
	}

}
