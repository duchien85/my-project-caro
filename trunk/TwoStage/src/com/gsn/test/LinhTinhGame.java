package com.gsn.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.jogl.JoglApplication;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gsn.caro.asset.DataProvider;

public class LinhTinhGame extends Game {
	ShapeRenderer shapeRenderer;
	Stage stage;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		shapeRenderer = new ShapeRenderer();
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
		stage = new Stage(width, height, false);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		// shapeRenderer.setProjectionMatrix(DataProvider.getInstance().screenStage.getCamera().combined);
		super.render();
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		shapeRenderer.begin(ShapeType.FilledRectangle);
		//stage.getCamera().update();
		stage.draw();
		
		shapeRenderer.setProjectionMatrix(stage.getCamera().combined);
		// shapeRenderer.identity();
		// shapeRenderer.translate(20, 12, 2);
		// shapeRenderer.rotate(0, 0, 1, 90);
		shapeRenderer.setColor(1.0f, 1.0f, 0, 1.0f);
		shapeRenderer.filledRect(100, 100, 100f, 105f);
		shapeRenderer.end();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JoglApplication(new LinhTinhGame(), "My Caro", 480, 800, false);
	}

}
