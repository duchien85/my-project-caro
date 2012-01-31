package com.gsn.cotuong;

import com.badlogic.gdx.backends.jogl.JoglApplication;
import com.gsn.test.TestGame;

public class Desktop {
	
	public static void createGame(int gameMode){
		switch(gameMode){
		case 1 : 
			createGame(240, 320);
			break;
		case 2:
			createGame(480, 600);
			break;
		}
	}
	
	public static void createGame(int width, int height){
			new JoglApplication(new TestGame(), "my CoTuong", width, height, false);
	}
	
	public static void main(String[] agrs){
		createGame(1);
	}

}
