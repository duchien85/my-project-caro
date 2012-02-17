package com.gsn.engine.myplay;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

abstract public class GsnGame implements ApplicationListener {
	GsnStage currentStage;	
	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stu
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if (currentStage != null){
			currentStage.act(Gdx.graphics.getDeltaTime());
			currentStage.draw();			
		}
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void setStage(GsnStage stage) {
		this.currentStage = stage;
		stage.setInputListener();
	};
	
}
