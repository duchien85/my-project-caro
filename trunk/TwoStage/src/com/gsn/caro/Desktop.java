package com.gsn.caro;

import com.badlogic.gdx.backends.jogl.JoglApplication;
import com.gsn.test.CaroGame;

public class Desktop {

	public static void createGame(int mode) {
		switch (mode) {
		case 1:
			createGame(240, 320);
			break;
		case 2:
			createGame(480, 600);
			break;
		}
	}

	public static void createGame(int width, int height) {
		new JoglApplication(new CaroGame(), "My Caro", width, height, false);
	}

	public static void main(String[] args) {
		createGame(2);
	}
}
