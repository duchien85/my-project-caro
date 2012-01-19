package com.gsn.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gsn.caro.asset.ImageAsset;

public class TestGame extends Game {
	Stage menuStage;
	Stage boardStage;
	GsnInputPlayStage input;	
	ImageAsset asset;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		asset = ImageAsset.getInstance();
		asset.create();
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
		menuStage = new MenuStage(width, height);
		boardStage = new BoardStage(width, height);
		input = new GsnInputPlayStage(menuStage, boardStage);
		Gdx.input.setInputProcessor(input);
	}
	
	@Override
	public void render() {
		// TODO Auto-generated method stub
		super.render();		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		boardStage.act(Gdx.graphics.getDeltaTime());
		boardStage.draw();
		
		menuStage.act(Gdx.graphics.getDeltaTime());
		menuStage.draw();							
	}

}
