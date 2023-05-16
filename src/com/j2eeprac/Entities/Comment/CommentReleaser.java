package com.j2eeprac.Entities.Comment;

public class CommentReleaser implements I_CommentReleaser {

	private Comment comment;

	@Override
	public void releaseComment(String cid, String uid, String aid, String uName, String aName, String comment) {
		this.comment = new Comment(cid, uid, aid, uName, aName, comment);
	}

	@Override
	public Comment getComment() {
		return this.comment;
	}

}
