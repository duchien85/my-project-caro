package com.gsn.engine.myplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

abstract public class GsnStage extends Stage {
	private Vector3 vector3 = new Vector3();
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
	
	public void toScreenCoordinates(float x, float y, Vector2 out) {
		camera.project(vector3.set(x, y, 0));
		out.x = vector3.x;
		out.y = vector3.y;
	}
}
