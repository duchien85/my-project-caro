package com.gsn.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;

public class GsnUtility {

	public static boolean pointInRectangle(Rectangle r, float x, float y) {
		
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}

	static public void setViewport (Camera camera, float width, float height, boolean stretch) {
		float thiswidth = 0f, thisheight = 0f;
		if (!stretch) {
			if (width > height && width / (float)Gdx.graphics.getWidth() <= height / (float)Gdx.graphics.getHeight()) {
				float toDeviceSpace = Gdx.graphics.getHeight() / height;
				float toViewportSpace = height / Gdx.graphics.getHeight();
	
				float deviceWidth = width * toDeviceSpace;
				thiswidth = width + (Gdx.graphics.getWidth() - deviceWidth) * toViewportSpace;
				thisheight = height;
			} else {
				float toDeviceSpace = Gdx.graphics.getWidth() / width;
				float toViewportSpace = width / Gdx.graphics.getWidth();
	
				float deviceHeight = height * toDeviceSpace;
				thisheight = height + (Gdx.graphics.getHeight() - deviceHeight) * toViewportSpace;
				thiswidth = width;
			}
		} else {
			thiswidth = width;
			thisheight = height;
		}
		
		float centerX = thiswidth / 2;
		float centerY = thisheight / 2;
	
		camera.position.set(centerX, centerY, 0);
		camera.viewportWidth = thiswidth;
		camera.viewportHeight = thisheight;
	}

}
