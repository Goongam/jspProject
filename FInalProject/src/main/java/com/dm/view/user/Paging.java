package com.dm.view.user;

public class Paging {
	private int all_question_count;
	private int current_page;
	private int start_page;
	private int last_page;
	private int list_num;
	private int display_page;
	private int total_page;

	public int getAll_question_count() {
		return all_question_count;
	}

	public void setAll_question_count(int all_question_count) {
		this.all_question_count = all_question_count;
	}

	public int getCurrent_page() {
		return current_page;
	}

	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}

	public int getStart_page() {
		return start_page;
	}

	public void setStart_page(int start_page) {
		this.start_page = start_page;
	}

	public int getLast_page() {
		return last_page;
	}

	public void setLast_page(int last_page) {
		this.last_page = last_page;
	}

	public int getList_num() {
		return list_num;
	}

	public void setList_num(int list_num) {
		this.list_num = list_num;
	}

	public int getDisplay_page() {
		return display_page;
	}

	public void setDisplay_page(int display_page) {
		this.display_page = display_page;
	}

	public int getTotal_page() {
		return total_page;
	}

	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}

}
