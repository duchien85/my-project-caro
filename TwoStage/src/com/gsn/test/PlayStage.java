package com.gsn.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gsn.caro.asset.AssetOld;
import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.ImageAsset;

public class PlayStage extends Stage {
	public PlayStage(float width, float height) {
		super(width, height, false);
		Stage tmp = new Stage(width, height, false);
		DataProvider.getInstance().screenStage = tmp;
		tmp.getCamera().update();
		
		menuStage = new MenuStage(this, width, height);
		boardStage = new BoardStage(this,width, height);
		backgroundStage = new BackGroundStage(width, height);
		
		input = new GsnInputPlayStage(this);
		
		asset = ImageAsset.getInstance();
	}

	ImageAsset asset;
	BoardStage boardStage;	
	BackGroundStage backgroundStage;
	public final GsnInputPlayStage input;
	MenuStage menuStage;

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		boardStage.getCamera().update();
		menuStage.getCamera().update();
		
		backgroundStage.draw();
		
		boardStage.act(Gdx.graphics.getDeltaTime());
		boardStage.draw();

		menuStage.act(Gdx.graphics.getDeltaTime());
		menuStage.draw();

		asset.clickEffect.update(Gdx.graphics.getDeltaTime());
		asset.clickEffect.drawNow();		
	}

}
