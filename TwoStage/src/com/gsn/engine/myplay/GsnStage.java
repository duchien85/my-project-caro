package com.gsn.engine.myplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

abstract public class GsnStage extends Stage {

	public GsnStage(float width, float height) {
		super(width, height, false);
	}

	public GsnStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		// TODO Auto-generated constructor stub
	}

	public void setInputListener() {
		Gdx.input.setInputProcessor(this);
	}
}
