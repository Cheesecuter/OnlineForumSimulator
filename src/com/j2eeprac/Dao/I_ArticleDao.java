package com.j2eeprac.Dao;

import java.util.List;
import com.j2eeprac.Entities.Article.Article;

public interface I_ArticleDao {
	public int insert(Article article);

	public int delete(String AID);

	public int update(Article article);

	public int countAll();
	
	public List<Article> selectAll();
	
	public List<Article> selectAllByAuthor(String author);

	public Article findByArticleID(String AID);

	public Article findByArticleName(String aName);
	
	public List<Article> findArticles(String keyword);
}
