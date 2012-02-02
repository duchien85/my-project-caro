package com.gsn.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.AssetOld;
import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.myplay.GsnStage;

public class PlayStage extends GsnStage {
	public PlayStage(float width, float height) {
		super(width, height, false);
		Stage tmp = new Stage(width, height, false);
		DataProvider.getInstance().screenStage = tmp;
		tmp.getCamera().update();

		menuStage = new MenuStage(this, width, height);
		boardStage = new BoardStage(this, width, height);
		backgroundStage = new BackGroundStage(width, height);
		dialogStage = new DialogStage(this, width, height);

		input = new GsnInputPlayStage(this);
		asset = ImageAsset.getInstance();

	}

	ImageAsset asset;
	BoardStage boardStage;
	BackGroundStage backgroundStage;
	DialogStage dialogStage;
	MenuStage menuStage;

	public final GsnInputPlayStage input;

	boolean isDialog = false;

	public void showDialog() {
		isDialog = true;
		dialogStage.setInputListener();
	}

	public void hideDialog() {
		isDialog = false;
		this.setInputListener();
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		boardStage.getCamera().update();
		menuStage.getCamera().update();

		backgroundStage.draw();

		float delta = Gdx.graphics.getDeltaTime();

		boardStage.act(delta);
		boardStage.draw();

		menuStage.act(delta);
		menuStage.draw();

		asset.clickEffect.update(delta);
		asset.clickEffect.drawNow();

		if (isDialog) {
			dialogStage.act(delta);
			dialogStage.draw();
		}
	}

	@Override
	public void setInputListener() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(input);
	}

}
