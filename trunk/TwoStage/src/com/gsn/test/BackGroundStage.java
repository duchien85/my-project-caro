package com.gsn.test;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BackGroundStage extends Stage {
	ShapeRenderer shapeRender;
	Image background;

	public BackGroundStage(float width, float height) {
		super(width, height, false);
		shapeRender = new ShapeRenderer();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();
		shapeRender.begin(ShapeType.FilledRectangle);
		shapeRender.setColor(0.1f, 0.3f, 0.4f, 1f);
		shapeRender.filledRect(-1, -1, 2, 2);
		shapeRender.end();
	}
}
