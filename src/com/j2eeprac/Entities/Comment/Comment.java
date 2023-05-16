package com.j2eeprac.Entities.Comment;

public class Comment {
	private String cid;
	private String uid;
	private String aid;
	private String uName;
	private String aName;
	private String comment;

	public Comment(String cid, String uid, String aid, String uName, String aName, String comment) {
		this.cid = cid;
		this.uid = uid;
		this.aid = aid;
		this.uName = uName;
		this.aName = aName;
		this.comment = comment;
	}

	public String getCID() {
		return this.cid;
	}

	public String getUID() {
		return this.uid;
	}

	public String getAID() {
		return this.aid;
	}

	public String getUname() {
		return this.uName;
	}

	public String getAname() {
		return this.aName;
	}

	public String getComment() {
		return this.comment;
	}
}
