package com.gsn.caro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gsn.caro.asset.GsnParticleEffect;
import com.gsn.caro.asset.ImageAsset;

public class TestStage extends Stage {		
	
	GsnParticleEffect clickEffect = ImageAsset.getInstance().clickEffect;
	public TestStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		clickEffect.setSpriteBatch(batch);
		// TODO Auto-generated constructor stub		
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();
		batch.begin();
		
		clickEffect.update(Gdx.graphics.getDeltaTime());
		clickEffect.draw();
		
		batch.draw(ImageAsset.getInstance().bet,100,100);
		batch.end();
	}
	
	Vector2 vector = new Vector2();
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		toStageCoordinates(x, y, vector);
		clickEffect.start();
		Gdx.app.log("test", " click : " + vector.x + " " + vector.y);
		clickEffect.setPosition(vector.x, vector.y);
		return super.touchUp(x, y, pointer, button);
	}
}
