package com.gsn.engine.layout;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.gsn.engine.GsnUtility;


public class GsnAnimation {
	float duration;
	List<Sprite> frames;
	float time;
	private Sprite currentFrame;
	boolean isLoop;
	
	public GsnAnimation(float frameDuration, List<AtlasRegion> keyFrames, boolean looping, float centerX, float centerY) {
		// TODO Auto-generated constructor stub
		duration = frameDuration;		
		frames = GsnUtility.convertRegionsToSprites(keyFrames);
		time = 0;
		isLoop = looping;
		for (Sprite sprite : frames)
			GsnUtility.setCenterSprite(sprite, centerX, centerY);
	}		
	
	public void start(){
		time = 0;
		act(0f);
	}
	
	public boolean isFinish(){
		return currentFrame == null;
	}
	
	public Sprite act(float delta){
		time += delta;
		int no = (int) (time / duration);		
		if (no >= frames.size()){
			if (!isLoop)
				no = -1;
			else 
				no = no % frames.size();
		} 
		if (no < 0)
			currentFrame = null;
		else 
			currentFrame = frames.get(no);
		return currentFrame;
	}

	public Sprite getCurrentFrame() {
		return currentFrame;
	} 	
	
	public void draw(SpriteBatch batcher){
		if (currentFrame != null)
			currentFrame.draw(batcher);
	}
}
