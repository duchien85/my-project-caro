package com.gsn.test;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.myplay.GsnStage;
import com.vng.caro.CaroActivity;
import com.vng.caro.LoginDlg;
import com.vng.caro.LoginDlg.ILoginListener;
import com.vng.caro.R;

public class LoginStage extends GsnStage implements ILoginListener{

	ImageButton loginButton;
	CaroGame game;
	LoginDlg dialog;
	CaroActivity activity;
	public LoginStage(int width, int height, Boolean stretch, CaroGame game)
	{
		super(width, height, stretch);
		this.game = game;
		activity = DataProvider.getInstance().caroActivity;
		loginButton = new ImageButton(ImageAsset.getInstance().login, ImageAsset.getInstance().loginClick);
		this.addActor(loginButton);
		ActorUtility.setCenter(loginButton, width/2, height/2);
			
		
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				 dialog = new LoginDlg(activity, R.layout.dialog, LoginStage.this);
				 if (dialog.getSession() == null)
					 dialog.show();
				 else 
					 onSuccess(dialog.getSession());
			}
		});
		
		loginButton.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				showDialog();
			}
		});
	}
	
	
	public void showDialog(){
		activity.runOnUiThread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			 dialog.show();						 
		}
	});
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		switch (keycode) {	
		case Keys.BACK:
			Log.i("Gsn Input", "click Back Btn");
			Gdx.app.exit();
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	public void onSuccess(String session) {		
		//String s = "{\"params\":{\"username\":\"1F0189884C617423F554C4D8\"},\"_cmd\":\"login\",\"ext\":\"caro\"}";
		Log.i("login", "onSuccess : " + session);
		LoginStage.this.game.caroActivity.sendLogin(session);
	}

	@Override
	public void onFailure(int error) {
		System.out.println("login failure");		
	}


	public void retryLogin() {
		if (!dialog.saveinfo)
			showDialog();
		else {
			String session = dialog.retryLogin();
			if (session == null)
				showDialog();
			else
				onSuccess(session);
		}
		
	}	
}
