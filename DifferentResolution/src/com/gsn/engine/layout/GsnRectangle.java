package com.gsn.engine.layout;

public class GsnRectangle{
	public float x, y, width, height;
	public GsnRectangle(float x, float y, float width, float height) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;			
	}
	
	public GsnPoint getCenter(){
		GsnPoint tmp = new GsnPoint(x + width / 2, y + height / 2);
		return tmp;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + x + " " + y + " " + width + " " + height + ")";
	}

	public void setMargin(float marginX, float marginY) {
		// TODO Auto-generated method stub
		x = x + marginX;
		y = y + marginY;
		width = width - marginX;
		height = height - marginY;
	}
}