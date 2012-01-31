package com.gsn.engine.layout;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GsnNinePath extends Image {
	NinePatch ninePath;
	public GsnNinePath(TextureRegion region, int left, int right, int top, int bottom){
		super(region);
		ninePath = new NinePatch(region, left, right, top, bottom);		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		ninePath.draw(batch, this.x, this.y, this.width * this.scaleX, this.height * this.scaleY);
	}
}
