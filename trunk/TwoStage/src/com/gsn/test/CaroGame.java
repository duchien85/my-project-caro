/**
 * 
 */
package com.gsn.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.gsn.caro.asset.AssetOld;
import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.myplay.GsnGame;
import com.gsn.engine.myplay.GsnStage;

/**
 * @author trungdv2
 * 
 */
public class CaroGame extends GsnGame {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.ApplicationListener#create()
	 */
	PlayStage playStage;
	LobbyStage lobbyStage;

	@Override
	public void create() {
		// TODO Auto-generated method stub
		ImageAsset.getInstance().create();
		AssetOld.getInstance().finishLoadingAll();
		DataProvider.getInstance().clickEffect = ImageAsset.getInstance().clickEffect;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
		playStage = new PlayStage(width, height);
		lobbyStage = new LobbyStage(this, width, height);
		setStage(playStage);
		//setStage(lobbyStage);
		//setStage(new TestStage(width, height));
	}
}
