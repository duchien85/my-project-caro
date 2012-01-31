package com.gsn.test;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.gsn.engine.ActorUtility;

public class GsnInputPlayStage implements InputProcessor {
	BoardStage boardStage;
	MenuStage menuStage;
	boolean touchBoard = true;

	Vector2 vector = new Vector2();

	public GsnInputPlayStage(MenuStage menu, BoardStage board) {
		this.boardStage = board;
		this.menuStage = menu;
	}

	private void checkTouchBoard(int x, int y) {
		menuStage.toStageCoordinates(x, y, vector);
		if (ActorUtility.inActor(vector, menuStage.menuBG)) {
			boardStage.resetPinchToZoom();
			touchBoard = false;
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub

		menuStage.keyDown(keycode);
		boardStage.keyDown(keycode);
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		menuStage.keyTyped(character);
		boardStage.keyTyped(character);
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		menuStage.keyUp(keycode);
		boardStage.keyUp(keycode);
		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		menuStage.touchDown(x, y, pointer, button);
		checkTouchBoard(x, y);
		if (touchBoard)
			boardStage.touchDown(x, y, pointer, button);
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		menuStage.touchDragged(x, y, pointer);
		checkTouchBoard(x, y);
		if (touchBoard)
			boardStage.touchDragged(x, y, pointer);
		return true;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		menuStage.touchUp(x, y, pointer, button);
		checkTouchBoard(x, y);
		if (touchBoard)
			boardStage.touchUp(x, y, pointer, button);
		touchBoard = true;
		return true;
	}

}
