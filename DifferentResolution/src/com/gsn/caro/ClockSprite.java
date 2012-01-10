package com.gsn.caro;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.GdxUtility;
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
		numClock = GsnUtility.convertRegionsToSprites(ImageAsset.getInstance().numberTimerList);
		
		rect.scaleAndPutSprite(clockBG);
		for (Sprite sprite : numClock)
			GdxUtility.setCenter(sprite, rect.getCenter().x, rect.getCenter().y);
	}	
	
	@Override
	public void draw(SpriteBatch batcher) {
		// TODO Auto-generated method stub
		clockBG.draw(batcher);
		numClock.get(time).draw(batcher);
	}	
}
