package com.gsn.engine.template;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.engine.ActorUtility;
import com.gsn.test.BoardStage;

public class GsnClockImage extends Group {	
	List<Image> numbers = new ArrayList<Image>();
	Image clockImage;
	int time = 0;
	float countTime = 0;
	BoardStage board;
	boolean finish = true;
	Image currentTime;

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
		currentTime = numbers.get(time);
		this.addActor(currentTime);
	}	
	
	public int getTime() {
		return time;
	}
	public void setBoard(BoardStage board)
	{
		this.board = board;
	}
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
		this.removeActor(currentTime);
		currentTime = numbers.get(time);
		this.addActor(currentTime);
	}
	public void setTime(int time){
		this.time = time;
	}
	public void start()
	{
		finish = false;
	}
	public void stop()
	{
		finish = true;
		countTime = 0;
		time = 0;
	}
	public void act(float deltaTime)
	{
		if(!finish)
		{
			countTime = countTime + deltaTime;
			time = 9 - (int)countTime;
			
			if(time == 0)
			{
				countTime = 0;
				finish = true;
				board.randomStep();
			}
			
		}
	}
}
