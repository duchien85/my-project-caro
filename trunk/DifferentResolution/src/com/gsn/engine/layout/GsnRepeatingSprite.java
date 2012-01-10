package com.gsn.engine.layout;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GsnRepeatingSprite extends GsnSprite {
	TextureRegion texture;
	float x, y, width, height;
	public GsnRepeatingSprite(TextureRegion region, float x, float y, float width, float height){
		this.texture = region;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bound.set(x, y, width, height);
	}
	
	private int div(float a, float b){		
		return (int)(a / b);
	}

	private float mod(float a, float b){
		float div = div(a, b);
		return a - b * div;
	}
	
	@Override
	public void draw(SpriteBatch batcher) {
		// TODO Auto-generated method stub
		int divX = div(width, texture.getRegionWidth());
		int divY = div(height, texture.getRegionHeight());
		for (int i = 0; i < divX; i++)
			for (int j = 0; j < divY; j++){
				float tmpX = x + i * texture.getRegionWidth();
				float tmpY = y + j * texture.getRegionHeight();
				batcher.draw(texture, tmpX, tmpY);				
			}			
	}
}
