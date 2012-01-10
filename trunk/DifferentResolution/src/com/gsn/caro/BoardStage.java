package com.gsn.caro;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.GsnBoardStage;
import com.gsn.engine.layout.GsnRectangle;
import com.gsn.engine.layout.GsnRepeatingSprite;
import com.gsn.engine.layout.GsnSprite;
import com.gsn.engine.layout.GsnTableLayout;

public class BoardStage extends GsnBoardStage {
	ImageAsset asset = ImageAsset.getInstance();
	GsnRepeatingSprite menuBG;	
	ClockSprite meTimer;
	ClockSprite otherTimer;	
	GsnTableLayout menuLayout;
	
	Image board;
	int tmp = 50;
	public BoardStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		menuLayout = createMenuLayout(true, 0.2f);
		menuBG = new GsnRepeatingSprite(asset.menuBG, menuLayout.x, menuLayout.y, menuLayout.width, menuLayout.height);		
		//menuLayout.getBoundingRectangle().scaleAndPutSprite(menuBG);
		//System.out.println("menuBG : " + GsnRectangle.toString(menuBG));
		
		menuLayout.newRow(1f);
		menuLayout.addList(0.4f, 0.2f, 0.4f);		
		
		meTimer = new ClockSprite(menuLayout.list.get(0));
		meTimer.setTime(6);
		otherTimer = new ClockSprite(menuLayout.list.get(2));
	}
	@Override
	public void localDraw(SpriteBatch batcher) {
		// TODO Auto-generated method stub
		menuBG.draw(batcher);	
		meTimer.draw(batcher);
		otherTimer.draw(batcher);
	}
	@Override
	public boolean localTouchUp(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean globalTouchUp(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

}
