package com.gsn.test;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.GsnPinchToZoom;

public class BoardStage extends Stage {
	ImageAsset asset;
	GsnPinchToZoom pinch;

	public BoardStage(float width, float height) {
		super(width, height, false);
		pinch = new GsnPinchToZoom(this);
		asset = ImageAsset.getInstance();
		Image board = new Image(asset.board);
		board.x = -board.width / 2;
		board.y = -board.height / 2;
		// board.setClickListener(new SimpleClickListener("click Board"));
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
