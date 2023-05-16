package com.j2eeprac.Dao;

import java.util.List;
import com.j2eeprac.Entities.Comment.Comment;

public interface I_CommentDao {
	public int insert(Comment comment);

	public int delete(String CID);

	public int update(Comment comment);

	public int countAll();
	
	public List<Comment> selectAll();

	public Comment findByCommentID(String CID);
	
	public Comment findByUserID(String UID);

	public Comment findByUserName(String uName);
}
