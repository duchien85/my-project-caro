/**
 * 
 */
package com.gsn.test;

import com.gsn.caro.asset.AssetOld;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.caro.asset.LoadingAsset;
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
	DialogStage settingStage;
	@Override
	public void create() {
		// TODO Auto-generated method stub
		ImageAsset.getInstance().create();
		AssetOld.getInstance().finishLoadingAll();
		
		LoadingAsset.create();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		//width = 240;
		//height = 320;
		super.resize(width, height);
		playStage = new PlayStage(this, width, height);
		lobbyStage = new LobbyStage(this, width, height);
		settingStage = new DialogStage(width, height);
		setLobbyStage();
		setPlayStage();
		//setStage(playStage);
		//setStage(lobbyStage);
		//setStage(new LoadingStage(width, height));
	}
	
	public void setLobbyStage(){
		this.setStage(lobbyStage);
	}
	
	public void setPlayStage(){		
		this.setStage(playStage);
	}
	public void showSettingStage()
	{
		
	}
}
