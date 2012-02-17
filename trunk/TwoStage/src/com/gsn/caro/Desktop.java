package com.gsn.caro;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.gsn.engine.myplay.GsnGame;
import com.gsn.test.CaroGame;

public class Desktop {

	public static void createGame(int mode) {
		switch (mode) {
		case 1:
			createGame(240, 320);
			break;
		case 2:
			createGame(320, 480);
			break;
		case 3:
			createGame(540, 960);
			break;
		case 4:
			createGame(480, 800);
			break;
		}
	}
	

	public static void createGame(int width, int height) {
		//new JoglApplication(new CaroGame(), "My Caro", width, height, false);
		GsnGame game = new CaroGame();		
		new LwjglApplication(game, "My Caro", width, height, false);		
		
	}

	public static void main(String[] args) {
		createGame(1);
	}
}
