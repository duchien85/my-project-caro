package com.gsn.caro.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gsn.caro.asset.ImageAsset;

public class TestGame extends Game {
	Stage one;
	Stage two;
		
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
		one = new MenuStage(width, height);
		two = new BoardStage(width, height);
		Gdx.input.setInputProcessor(new InputAdapter(){
			@Override
			public boolean touchDown(int x, int y, int pointer, int button) {
				// TODO Auto-generated method stub
				one.touchDown(x, y, pointer, button);
				two.touchDown(x, y, pointer, button);
				return super.touchDown(x, y, pointer, button);
			}
		});
	}
	
	@Override
	public void render() {
		// TODO Auto-generated method stub
		super.render();		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		one.act(Gdx.graphics.getDeltaTime());
		one.draw();		
		
		two.act(Gdx.graphics.getDeltaTime());
		two.draw();
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LwjglApplication(new TestGame(), "Test", 240, 320, false);
	}

}
