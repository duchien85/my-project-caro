package com.gsn;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.gsn.caro.CaroGame;

public class Desktop {

	/**
	 * @param args
	 */
	public static void createGame(int mode){		
		switch (mode){
		case 1:			
			createGame(240, 320);
			break;
		case 2:
			createGame(480, 800);
			break;
		}		
	}
	
	public static void createGame(int width, int height){
		new LwjglApplication(new CaroGame(), "My Caro", width, height, false);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		createGame(2);
		//new LwjglApplication(new CaroGame(), "My Caro", 480, 800, false);
		//new LwjglApplication(new CaroGame(), "My Caro", 240, 320, false);
		//new LwjglApplication(new CaroGame(), "My Caro", 240, 320, false);		
	}
}
