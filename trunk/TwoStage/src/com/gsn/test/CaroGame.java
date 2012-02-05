/**
 * 
 */
package com.gsn.test;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.gsn.caro.asset.AssetOld;
import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.myplay.GsnGame;

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
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
		playStage = new PlayStage(this, width, height);
		lobbyStage = new LobbyStage(this, width, height);
		setPlayStage();
		//setStage(playStage);
		//setStage(lobbyStage);
		//setStage(new TestStage(width, height));
	}
	
	public void setLobbyStage(){
		this.setStage(lobbyStage);
	}
	
	public void setPlayStage(){		
		this.setStage(playStage);
	}
}
