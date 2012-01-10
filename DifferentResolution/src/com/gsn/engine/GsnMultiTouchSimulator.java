package com.gsn.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

public class GsnMultiTouchSimulator {
	private final int POINTER = 2;
	private final int BUTTON = 0;
	private boolean pressAlt = false;
	private InputProcessor input = new InputAdapter();

	public GsnMultiTouchSimulator(InputProcessor input) {
		this.input = input;
	}

	public void keyUp(int keycode) {
		switch (keycode) {
		case Input.Keys.ALT_LEFT:
			int x = Gdx.graphics.getWidth() / 2;
			int y = Gdx.graphics.getHeight() / 2;
			pressAlt = false;
			input.touchUp(x, y, POINTER, BUTTON);
			break;
		}
	}

	public void keyDown(int keycode) {
		switch (keycode) {
		case Input.Keys.ALT_LEFT:
			if (!pressAlt) {
				int x = Gdx.graphics.getWidth() / 2;
				int y = Gdx.graphics.getHeight() / 2;
				pressAlt = true;
				input.touchDown(x, y, POINTER, BUTTON);
			}
			break;
		}
	}
}
