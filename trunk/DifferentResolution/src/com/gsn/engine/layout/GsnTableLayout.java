package com.gsn.engine.layout;

import java.util.ArrayList;
import java.util.List;

public class GsnTableLayout {
	private float tmpHeight, tmpWidth, tmpX, tmpY, oldHeight;	
	public float x, y, width, height;
	public List<GsnRectangle> list;
	public GsnTableLayout(float x, float y, float width, float height) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tmpX = x;
		this.tmpY = y;
		this.oldHeight = 0;
		list = new ArrayList<GsnRectangle>();
	}
	
	public void newRow(float rHeight){		
		this.tmpX = x;
		this.tmpY = this.tmpY + oldHeight;
		
		oldHeight = tmpHeight = rHeight * height;
	}
	
	public void add(float rWidth){
		tmpWidth = width * rWidth;
		list.add(new GsnRectangle(tmpX, tmpY, tmpWidth, tmpHeight));
		tmpX += tmpWidth;
		//tmpY += tmpHeight;
	}
}
