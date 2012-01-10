package com.gsn.caro.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.GsnBoardStage;
import com.gsn.engine.GsnUtility;

public class GlobalStage extends GsnBoardStage {
	ImageAsset asset;
	Image avatar;			
	Sprite pieceO;					
	Sprite menuBG;
	Vector2 point = new Vector2();		
	public String tag = GlobalStage.class.getSimpleName();
	
	public GlobalStage(float width, float height, boolean stretch) {		
		super(width, height, stretch);	
//		Gdx.app.log(tag, "kt " + width + " " + height + " ");
//		Gdx.app.log(tag, "center " + centerX + " " + centerY + " ");				
		asset = ImageAsset.getInstance();
		clickEffect = asset.clickEffect;
		avatar = new Image(asset.avatar);
		avatar.x = 50;
		avatar.y = 0;
		avatar.setClickListener(new ClickListener() {			
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				Gdx.app.log(tag, "click GLOBAL AAAAAAAAAAA!!!");
			}
		});		
		//addActor(new Image(asset.background));		
		addActor(avatar);		
		camera.translate(-200, -100, 0);		
		pieceO = new Sprite(asset.pieceO);				
		menuBG = new Sprite(asset.menuBG);
		int tmp = 50;
		menuBG.setBounds(0, this.height - tmp, this.width, tmp);
	}		

	@Override
	public void localDraw(SpriteBatch batcher) {
		// TODO Auto-generated method stub
		menuBG.draw(batcher);
		batcher.draw(asset.menuBG, 100, 100);
		batcher.draw((TextureRegion) asset.numberTimerList.get(5), 20, 20);
		menuBG.draw(batcher);
		pieceO.draw(batcher);				
	}

	@Override
	public boolean localTouchUp(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		Rectangle r = pieceO.getBoundingRectangle();
		Gdx.app.log(tag, "click local : " + x + " " + y);		
		clickEffect.startNow(localCam, x, y);
		if (GsnUtility.pointInRectangle(r, x, y)){
			Gdx.app.log(tag, "click LOCAL!!!!");			
		}
		return true;
	}

	@Override
	public boolean globalTouchUp(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		Gdx.app.log(tag, "click GLOBAL : " + x + " " + y);
		return false;
	}
}
