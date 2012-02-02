package com.gsn.engine.template;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.gdx.GsnAnimation;

public class GsnClockImage extends Group {	
	List<Image> numbers = new ArrayList<Image>();
	Image clockImage;
	int time = 1;

	public GsnClockImage(TextureRegion region, List<AtlasRegion> timeNumbers) {
		// TODO Auto-generated constructor stub
		super();
		this.clockImage = new Image(region);
		this.height = clockImage.height;
		this.width = clockImage.width;
		
		int i = 0;
		for (AtlasRegion tmp : timeNumbers ){
			numbers.add(new Image(tmp));
			ActorUtility.setCenter(numbers.get(i), width / 2, height / 2);
			i++;
		}			
		
		this.addActor(clockImage);
		this.addActor( numbers.get(time));
	}	
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);		
	}
	public void setTime(int time){
		this.time = time;
	}
}
