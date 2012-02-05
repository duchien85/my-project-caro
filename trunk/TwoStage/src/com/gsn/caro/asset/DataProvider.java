package com.gsn.caro.asset;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gsn.engine.gdx.GsnParticleEffect;

public class DataProvider {
	private static DataProvider _instance;

	public static DataProvider getInstance() {
		if (_instance == null)
			_instance = new DataProvider();
		return _instance;
	}	

	public Stage screenStage;

	private DataProvider() {
	}
}
