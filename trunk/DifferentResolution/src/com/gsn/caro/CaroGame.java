package com.gsn.caro;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gsn.caro.asset.ImageAsset;

public class CaroGame extends Game {
	ImageAsset asset;	
	Stage global;	
	//Stage test;
	
	@Override
	public void create() {
		Gdx.app.log("GAME", "CREATE");
		asset = ImageAsset.getInstance();
		asset.create();			
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		super.render();
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		global.act(Gdx.graphics.getDeltaTime());
		global.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		Gdx.app.log("GAME", "RESIZE");
		//global = new GlobalStage(width, height, false);		
		global = new BoardStage(width, height, false);
		Gdx.input.setInputProcessor(global);
	}	
	
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
		asset.finishLoading();
	}
}
