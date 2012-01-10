package com.gsn.caro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.GsnBoardStage;
import com.gsn.engine.layout.GsnAnimation;
import com.gsn.engine.layout.GsnRepeatingSprite;
import com.gsn.engine.layout.GsnTableLayout;

public class BoardStage extends GsnBoardStage {
	ImageAsset asset = ImageAsset.getInstance();
	GsnRepeatingSprite menuBG;	
	ClockSprite meTimer;
	ClockSprite otherTimer;	
	GsnTableLayout menuLayout;
	GsnAnimation animation;
	
	Image board;
	int tmp = 50;
	public BoardStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		menuLayout = createMenuLayout(true, 0.2f);
		menuBG = new GsnRepeatingSprite(asset.menuBG, menuLayout.x, menuLayout.y, menuLayout.width, menuLayout.height);
		
		menuLayout.newRow(1f);
		menuLayout.addList(0.4f, 0.2f, 0.4f);		
		
		meTimer = new ClockSprite(menuLayout.list.get(0));
		meTimer.setTime(6);
		otherTimer = new ClockSprite(menuLayout.list.get(2));
		
		animation = new GsnAnimation(0.2f, asset.winAni, false, 100, 100);
		animation.start();
	}
	
	float time; 
	
	@Override
	public void localDraw(SpriteBatch batcher) {
		// TODO Auto-generated method stub
		time += Gdx.graphics.getDeltaTime();
		menuBG.draw(batcher);	
		meTimer.draw(batcher);
		otherTimer.draw(batcher);
		
		animation.act(Gdx.graphics.getDeltaTime());
		animation.draw(batcher);
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
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		switch (keycode){
		case Input.Keys.F1:
			animation.start();
			break;
		}
			
		return super.keyDown(keycode);
	}

}
