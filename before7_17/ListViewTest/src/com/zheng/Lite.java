package com.zheng;

public class Lite {
	
	private String name = "";
	private String information = "";
	private int image;
	
	public Lite(String name,String information,int image){
		this.name = name;
		this.information = information;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return getName() + " " + getInformation();
	}
}
