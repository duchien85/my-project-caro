package com.gsn.test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.GsnPinchToZoom;

public class BoardStage extends Stage {
	ImageAsset asset;
	GsnPinchToZoom pinch;
	
	private Vector3 vector = new Vector3();
	public void toScreenCoordinates (float x, float y, Vector2 out) {
		camera.project(vector.set(x, y, 0));
		out.x = vector.x;
		out.y = vector.y;		
	}
	
	public BoardStage(float width, float height) {
		super(width, height, false);
		pinch = new GsnPinchToZoom(this);
		asset = ImageAsset.getInstance();
		Image board = new Image(asset.board);
		board.x = -board.width / 2;
		board.y = -board.height / 2;
		board.setClickListener(new ClickListener() {
			Vector2 vector = new Vector2();
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				float rX = actor.x + x;
				float rY = actor.y + y;
				toScreenCoordinates(rX, rY, vector);				
				DataProvider.getInstance().clickEffect.startNow(DataProvider.getInstance().screenStage.getCamera(), vector.x, vector.y);
			}
		});
		this.addActor(board);
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		pinch.keyUp(keycode);
		return super.keyUp(keycode);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		pinch.keyDown(keycode);
		return super.keyDown(keycode);
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		// System.out.println("touch down ne : " + pointer);
		pinch.touchDown(x, y, pointer, button);
		return super.touchDown(x, y, pointer, button);
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		pinch.touchUp(x, y, pointer, button);
		return super.touchUp(x, y, pointer, button);
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		pinch.touchDragged(x, y, pointer);
		return super.touchDragged(x, y, pointer);
	}
}
