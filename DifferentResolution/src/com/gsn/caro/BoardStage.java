package com.gsn.caro;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.GsnBoardStage;
import com.gsn.engine.GsnUtility;

public class BoardStage extends GsnBoardStage {
	ImageAsset asset = ImageAsset.getInstance();
	Sprite menuBG;	
	Sprite clockBG;
	Sprite pieceX;
	List numList;
	
	public BoardStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		menuBG = new Sprite(asset.menuBG);
		int tmp = 50;
		menuBG.setBounds(0, height - tmp, width, tmp);
		
		clockBG = new Sprite(asset.clockBG);
		GsnUtility.setCenterSprite(clockBG, 50, 100);		
		
		pieceX = new Sprite(asset.pieceX);
		pieceX.setPosition(50, 100);
		
		numList = asset.numberTimerList;
	}

	@Override
	public void localDraw(SpriteBatch batcher) {
		// TODO Auto-generated method stub		
		menuBG.draw(batcher);
		
		pieceX.draw(batcher);
		clockBG.draw(batcher);
		GsnUtility.drawTexture(batcher, (TextureRegion)numList.get(6), 50f, 100f);		
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
		
		return true;
	}

}
