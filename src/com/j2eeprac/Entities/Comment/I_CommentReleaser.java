package com.j2eeprac.Entities.Comment;

public interface I_CommentReleaser {
	public void releaseComment(String cid, String uid, String aid, String uName, String aName, String comment);

	public Comment getComment();
}
