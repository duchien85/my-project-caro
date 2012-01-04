package com.gsn.engine;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class GsnUtility {

	public static boolean pointInRectangle(Rectangle r, float x, float y) {	
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}
	public static void setCenterSprite(Sprite sprite, float x, float y){
		float cX = x - sprite.getWidth() / 2;
		float cY = y - sprite.getHeight() / 2;
		sprite.setPosition(cX, cY);
	}
	
	public static void drawTexture(SpriteBatch batcher, TextureRegion texture, float x, float y){
		float cX = x - texture.getRegionWidth() / 2;
		float cY = y - texture.getRegionHeight() / 2;
		batcher.draw(texture, cX, cY);
	}
	
	public static List<Sprite> convertRegionsToSprites(){ 
		return null;
		
	}
}
