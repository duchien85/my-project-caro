package com.gsn.test;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.GdxUtility;

public class GsnInputPlayStage implements InputProcessor {
	BoardStage boardStage;
	MenuStage menuStage;
	PlayStage playStage;
	boolean touchBoard = true;

	Vector2 vector = new Vector2();

	public GsnInputPlayStage(PlayStage stage) {
		this.playStage = stage;
		this.boardStage = stage.boardStage;
		this.menuStage = stage.menuStage;
	}

	private void checkCanTouchBoard(int x, int y) {
		menuStage.toStageCoordinates(x, y, vector);
		if (GdxUtility.inRectangle(vector.x, vector.y, menuStage.getRectangleBound())) {
			boardStage.resetPinchToZoom();
			touchBoard = false;
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		switch (keycode) {
		case Keys.F1:
			menuStage.chatMe("kasfjlasfjasdlgja asdfkk asf2w23 asdfas");
			break;
		case Keys.F2:
			menuStage.win();
			break;
		case Keys.F3:
			playStage.showDialog();
			break;
		case Keys.F4:
			playStage.hideDialog();
			break;
		default:
			break;
		}
		boardStage.keyDown(keycode);
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		boardStage.keyTyped(character);
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
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
		checkCanTouchBoard(x, y);
		if (touchBoard)
			boardStage.touchDown(x, y, pointer, button);
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		menuStage.touchDragged(x, y, pointer);
		checkCanTouchBoard(x, y);
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
		checkCanTouchBoard(x, y);
		if (touchBoard)
			boardStage.touchUp(x, y, pointer, button);
		touchBoard = true;
		return true;
	}

}
