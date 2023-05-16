package com.j2eeprac.Entities.Article;

public interface I_ArticleReleaser {
	
	public void releaseArticle(String aid, String aName, String author, String content);
	
	public Article getArticle();
	
}
