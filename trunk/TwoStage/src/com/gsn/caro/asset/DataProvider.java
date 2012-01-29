package com.gsn.caro.asset;

import com.gsn.engine.gdx.GsnParticleEffect;

public class DataProvider {
	private DataProvider() {
	}

	private static DataProvider _instance;

	public static DataProvider getInstance() {
		if (_instance == null)
			_instance = new DataProvider();
		return _instance;
	}

	public GsnParticleEffect clickEffect;
}
