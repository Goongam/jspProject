package com.dm.common;

import java.sql.Timestamp;

public class QuestionDTO {
 	private int question_id;
 	private String question_title;
 	private String question_contnet;
 	private String memeber_id;
 	private boolean anonymous;
 	private String category;
 	private int views;	
 	private Timestamp edit_time;
 	private String member_nickname;
 	
 	
 	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public Timestamp getEdit_time() {
		return edit_time;
	}
	public void setEdit_time(Timestamp edit_time) {
		this.edit_time = edit_time;
	}
	public String getMemeber_id() {
		return memeber_id;
	}
	public void setMemeber_id(String memeber_id) {
		this.memeber_id = memeber_id;
	}
	public String getQuestion_title() {
		return question_title;
	}
	public void setQuestion_title(String question_title) {
		this.question_title = question_title;
	}
	public String getQuestion_contnet() {
		return question_contnet;
	}
	public void setQuestion_contnet(String question_contnet) {
		this.question_contnet = question_contnet;
	}
	public boolean getAnonymous() {
		return anonymous;
	}
	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}
	public String getcategory() {
		return category;
	}
	public void setcategory(String category) {
		this.category = category;
	}
	public String getMember_nickname() {
		return member_nickname;
	}
	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}
	
	/*
 `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `content` TEXT NOT NULL,
  `member_id` VARCHAR(45),
  `anonymous` VARCHAR(45) NULL,
  `category` INT NULL,
  `views` VARCHAR(45) NOT NULL,
  `edit_time` TIMESTAMP NOT NULL,
  
  */
}
