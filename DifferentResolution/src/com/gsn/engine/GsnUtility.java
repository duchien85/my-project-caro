package com.gsn.engine;

import com.badlogic.gdx.math.Rectangle;

public class GsnUtility {

	public static boolean pointInRectangle(Rectangle r, float x, float y) {	
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}

}
