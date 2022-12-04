package com.dm.common;

import java.sql.Timestamp;

public class AnswerDTO {
	private int id;
	private String title;
	private String content;
	private String member_id;
	private boolean anonymous;
	private int vote;
	private int question_id;
	private Timestamp edit_time;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public boolean isAnonymous() {
		return anonymous;
	}
	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public Timestamp getEdit_time() {
		return edit_time;
	}
	public void setEdit_time(Timestamp edit_time) {
		this.edit_time = edit_time;
	}
	
	
}