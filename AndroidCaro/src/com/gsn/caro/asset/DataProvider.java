package com.gsn.caro.asset;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gsn.test.LoginStage;
import com.vng.caro.CaroActivity;

public class DataProvider {
	private static DataProvider _instance;

	public static DataProvider getInstance() {
		if (_instance == null)
			_instance = new DataProvider();
		return _instance;
	}	

	public Stage screenStage;
	public CaroActivity caroActivity;
	public LoginStage loginStage;
	private DataProvider() {
	}
}
