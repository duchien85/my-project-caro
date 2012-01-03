package com.gsn.caro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.GsnStage;
import com.gsn.engine.GsnUtility;


public class GlobalStage extends GsnStage {
	public String tag = GlobalStage.class.getSimpleName();
	Vector2 point = new Vector2();		
	ImageAsset asset;
	Image avatar;					
	Sprite pieceO;		
	
	public GlobalStage(float width, float height, boolean stretch) {		
		super(width, height, stretch);
		GsnUtility.setViewport(localCam, width, height, stretch);				
//		Gdx.app.log(tag, "kt " + width + " " + height + " ");
//		Gdx.app.log(tag, "center " + centerX + " " + centerY + " ");		
		asset = ImageAsset.getInstance();
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
		//camera.translate(-200, -100, 0);		
		pieceO = new Sprite(asset.pieceO);		
	}		

	@Override
	public void localDraw(SpriteBatch batcher) {
		// TODO Auto-generated method stub
		pieceO.draw(batcher);		
	}

	@Override
	public boolean localTouchUp(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		Rectangle r = pieceO.getBoundingRectangle();
//		Gdx.app.log(tag, "rec : " + r);
//		Gdx.app.log(tag, "pos : " + x + " " + y);
		if (GsnUtility.pointInRectangle(r, x, y)){
			Gdx.app.log(tag, "click LOCAL!!!!");			
		}
		return true;
	}
}
