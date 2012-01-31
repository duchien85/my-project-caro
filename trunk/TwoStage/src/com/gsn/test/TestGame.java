package com.gsn.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.ImageAsset;

public class TestGame extends Game {
	ImageAsset asset;
	BoardStage boardStage;
	GsnInputPlayStage input;
	MenuStage menuStage;


	@Override
	public void create() {
		// TODO Auto-generated method stub
		asset = ImageAsset.getInstance();
		asset.create();
		DataProvider.getInstance().clickEffect = asset.clickEffect;	
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		boardStage.getCamera().update();
		menuStage.getCamera().update();
		boardStage.act(Gdx.graphics.getDeltaTime());
		boardStage.draw();

		menuStage.act(Gdx.graphics.getDeltaTime());
		menuStage.draw();

		asset.clickEffect.update(Gdx.graphics.getDeltaTime());
		asset.clickEffect.drawNow();		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
		Stage tmp = new Stage(width, height, false);
		DataProvider.getInstance().screenStage = tmp;
		tmp.getCamera().update();
		
		menuStage = new MenuStage(width, height);
		boardStage = new BoardStage(width, height);
		input = new GsnInputPlayStage(menuStage, boardStage);
		Gdx.input.setInputProcessor(input);
	}

}
