package com.dm.common;

public class CategoryDTO {
	private String category_name;
	private String img_url;

	public String getCategory_name() {
		return category_name;
	}

	public String getCategory_name_url() {
		return category_name.replace("&", "%26").replace("+", "%2B").replace("#", "%23");
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
}
