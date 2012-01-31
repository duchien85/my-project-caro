package com.gsn.engine.template;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GsnClockImage extends Image {
	BitmapFont font;
	public GsnClockImage(TextureRegion region) {
		// TODO Auto-generated constructor stub
		super(region);
		font = new BitmapFont(Gdx.files.internal("data/font/comic.fnt"), Gdx.files.internal("data/font/comic.png"), false);
		//font.scale(2);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
		String s = "4";
		TextBounds bounds =  font.getBounds(s);
		float tmpX = x + width / 2 - bounds.width / 2;
		float tmpY = y + height / 2 + bounds.height / 2;
		font.draw(batch, s, tmpX, tmpY);
	}
}
