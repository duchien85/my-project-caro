/**
 * 
 */
package com.gsn.test;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.caro.asset.LoadingAsset;
import com.gsn.caro.asset.SoundAsset;
import com.gsn.caro.settings.CaroSettings;
import com.gsn.engine.myplay.GsnGame;
import com.vng.caro.CaroActivity;

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
	String tag = "Caro Game";
	public PlayStage playStage;
	public LobbyStage lobbyStage;
	public CaroActivity caroActivity;
	public LoginStage loginStage;	
	int width;
	int height;
	public SettingStage settingStage;	
	@Override
	public void create() {
		Gdx.app.log(tag, "create");
		SoundAsset.load();
		CaroSettings.load();		
		Log.i(tag, "sound : " + CaroSettings.soundEnabled);
		ImageAsset.getInstance().create();
		ImageAsset.getInstance().loadTexture();
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		LoadingAsset.create();
		setStage(new LoadingStage(this, width, height));
		//setLobbyStage();
	//	setPlayStage();
		//setStage(playStage);
		//setStage(lobbyStage);
		//setStage(new TestStage(width, height));
		//setLoginStage();
	}
	
	@Override
	public void dispose() {				
		CaroSettings.save();
		super.dispose();
	}
	
	@Override
	public void pause() {
		Gdx.app.log(tag, "pause");
		super.pause();
	}
	
	@Override
	public void resume() {
		Gdx.app.log(tag, "resume");
		super.resume();		
		ImageAsset.getInstance().finishLoading();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		Gdx.app.log(tag, "resize");
	}
	
	public void setLobbyStage(){
		Log.i(tag, "set Lobby Stage");
		this.setStage(lobbyStage);
	}
	
	public void setPlayStage(){		
		this.setStage(playStage);
	}
	public void setLoginStage(){
		Log.i(tag, "set Login Stage");
		this.setStage(loginStage);
	}
	public void setInfoUser(Boolean me, String name, int point, int gold, int xu, int id, int numWin, int numLose, String avatar)
	{
		if(me)
		{
			lobbyStage.setContentUser(name, point, gold, xu, avatar);
			playStage.menuStage.setUserInfo(me, name, gold, xu,point, id, numWin, numLose, avatar);
		}
		else
		{
			playStage.menuStage.setUserInfo(me, name, gold, xu, point, id, numWin, numLose, avatar);
		}
		
	}
	public void setCaroActivity(CaroActivity activity)
	{
		caroActivity = activity;
	}
	public void playerReady(int id)
	{
		playStage.menuStage.playerReady(id);
	}
	public void startGame(int id)
	{
		playStage.menuStage.startGame(id);
	}
	public void enterRoom()
	{
		playStage.menuStage.enterRoom();
	}
	public void otherEnterRoom()
	{
		playStage.menuStage.otherEnterRoom();
	}
	public void sendJson(JSONObject json)
	{
		caroActivity.sendPacket(json);
	}
	public void doStep(int id, int cell)
	{
		int i = cell / 15;
		int j = cell % 15;
		playStage.boardStage.doStep(i, j, id);
	}

	public void outRoom(int id) {
		// TODO Auto-generated method stub
		playStage.menuStage.outRoom(id);
	}

	public void stopGame(int winner, int loser, int canContinue) throws JSONException {
		// TODO Auto-generated method stub
		playStage.menuStage.stopGame(winner, loser, canContinue);
		
	}

	public void onFinishLoading() {
		// TODO Auto-generated method stub
		playStage = new PlayStage(this, width, height);
		lobbyStage = new LobbyStage(this, width, height);
		loginStage = new LoginStage(width, height, false, this);
		settingStage = new SettingStage(width, height);
		
		DataProvider.getInstance().loginStage = loginStage;
		
		setLoginStage();
		LoadingAsset.unload();
	}
}
