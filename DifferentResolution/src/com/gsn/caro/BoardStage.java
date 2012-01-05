package com.gsn.caro;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.GsnBoardStage;
import com.gsn.engine.GsnUtility;

public class BoardStage extends GsnBoardStage {
	ImageAsset asset = ImageAsset.getInstance();
	Sprite menuBG;	
	Sprite clockBG;
	Sprite pieceX;
	List numList;
	
	Image board;
	int tmp = 50;
	public BoardStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		menuBG = new Sprite(asset.menuBG);
		
		menuBG.setBounds(0, height - tmp, width, tmp);
		
		clockBG = new Sprite(asset.clockBG);
		GsnUtility.setCenterSprite(clockBG, 50, height - tmp /2 );		
		
		pieceX = new Sprite(asset.pieceX);
		pieceX.setPosition(50, 100);
		
		numList = asset.numberTimerList;
		
		this.clickEffect = asset.clickEffect;
		board = new Image(asset.board);
		
		this.addActor(board);
		globalCam.translate(-50, -100, 0);
	}

	@Override
	public void localDraw(SpriteBatch batcher) {
		// TODO Auto-generated method stub		
		menuBG.draw(batcher);
		
		pieceX.draw(batcher);
		clockBG.draw(batcher);				
		GsnUtility.drawCenterTexture(batcher, (TextureRegion)numList.get(6), 50, height - tmp /2);		
	}

	@Override
	public boolean localTouchUp(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		if (GsnUtility.pointInRectangle(clockBG.getBoundingRectangle(), x, y)){
			Gdx.app.log(tag, "click Clock");
			return false;
		}
		return true;
	}

	@Override
	public boolean globalTouchUp(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub				
		clickEffect.startNow(globalCam, x, y);
		Gdx.app.log(tag, "click global : " + x + " " + y);
		return true; 
	}

}
