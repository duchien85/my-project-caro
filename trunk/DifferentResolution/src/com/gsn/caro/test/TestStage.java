package com.gsn.caro.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.GsnParticleEffect;

public class TestStage extends Stage {		
	
	GsnParticleEffect clickEffect = ImageAsset.getInstance().clickEffect;
	Vector2 vector = new Vector2();
	
	public TestStage(float width, float height, boolean stretch) {
		super(width, height, stretch);		
		batch.setProjectionMatrix(camera.combined);
		// TODO Auto-generated constructor stub		
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();
		
		clickEffect.update(Gdx.graphics.getDeltaTime());		
		clickEffect.drawNow();
		
		batch.begin();
		batch.draw(ImageAsset.getInstance().bet,100,100);
		batch.end();
	}
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		toStageCoordinates(x, y, vector);
		clickEffect.start();		
		clickEffect.setCamera(camera);
		Gdx.app.log("test", " click : " + vector.x + " " + vector.y);
		clickEffect.setPosition(vector.x, vector.y);
		return super.touchUp(x, y, pointer, button);
	}
}