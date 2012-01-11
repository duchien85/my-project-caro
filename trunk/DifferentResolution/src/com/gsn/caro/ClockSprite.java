package com.gsn.caro;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.GsnUtility;
import com.gsn.engine.layout.GsnRectangle;
import com.gsn.engine.layout.GsnSprite;

public class ClockSprite extends GsnSprite {
	Sprite clockBG;
	List<Sprite> numClock;
	int time;
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public ClockSprite(GsnRectangle rect) {
		// TODO Auto-generated constructor stub
		bound = rect.toRectangle();
		clockBG = new Sprite(ImageAsset.getInstance().clockBG);
		rect.scaleAndPutSprite(clockBG);
		
		numClock = GsnUtility.convertRegionsToSprites(ImageAsset.getInstance().numberTimerList);		
		GsnRectangle tmp =  rect.setMargin(true, 0.2f, 0.3f);
		for (Sprite sprite : numClock){
			//GdxUtility.setCenter(sprite, rect.getCenter().x, rect.getCenter().y);
			tmp.scaleAndPutSprite(sprite);
		}
	}	
	
	@Override
	public void draw(SpriteBatch batcher) {
		// TODO Auto-generated method stub
		clockBG.draw(batcher);
		numClock.get(time).draw(batcher);
	}	
}
