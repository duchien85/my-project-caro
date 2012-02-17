package com.vng.caro;

import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gsn.caro.asset.DataProvider;
import com.gsn.engine.mercurry.MercuryClient.IMercuryListener;
import com.gsn.packet.PacketFactory;
import com.gsn.test.CaroGame;

public class CaroActivity extends AndroidApplication implements IMercuryListener {
	/** Called when the activity is first created. */
	public CaroGame game;
	CaroBinder binder;
	int myId;
	ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			//Toast.makeText(CaroActivity.this, "service DISconnected", Toast.LENGTH_LONG);
			Log.i("Caro Activity", "service disconnect");
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			//Toast.makeText(CaroActivity.this, "service connected", Toast.LENGTH_LONG).show();
			Log.i("Caro Activity", "service connect");
			binder = (CaroBinder) service;
			binder.activity = CaroActivity.this;

			binder.service.connect();
		}
	};
	
	public void sendPacket(String s) {
		binder.service.send(s);
	}

	@Override
	public void onBackPressed() {
		//super.onBackPressed();
	}

	public void sendPacket(JSONObject j) {
		binder.service.send(j);
	}		

	public void onExpireSession() {
		DataProvider.getInstance().loginStage.retryLogin();
	}

	Timer timer;

	public void sendLogin(String session) {
		JSONObject cmd = PacketFactory.createLogin(session);
		sendPacket(cmd);
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				onExpireSession();
			}
		}, 1000);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		DataProvider.getInstance().caroActivity = this;

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useWakelock = true;
		createWakeLock(config);
		game = new CaroGame();
		this.bindService(new Intent(this, CaroService.class), conn, Context.BIND_AUTO_CREATE);
		initialize(game, config);
		game.setCaroActivity(this);
	}

	@Override
	protected void onDestroy() {
		this.unbindService(conn);
		super.onDestroy();
	}

	@Override
	public void onConnected() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReceived(String s) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReceivedJson(JSONObject json) {

		Log.i("caro", "receive json : " + json);
		String gui = "{\"params\":null,\"_cmd\":\"GUI\",\"ext\":\"caro\"}";
		try {
			if (json.has("loginOK")) {
				if (json.getInt("loginOK") == 0) {
					binder.service.send(gui);
					timer.cancel();
				}
			} else {
				String s = "";
				JSONObject j1 = new JSONObject();
				s = json.getString("_cmd");
				if (s.equals("GUI")) {
					j1 = json.getJSONObject("params").getJSONObject("me");
					game.setLobbyStage();
					game.setInfoUser(true, j1.getString("username"), j1.getInt("exp"), j1.getInt("gold"), j1.getInt("xu"), j1.getInt("userId"), j1.getInt("numWin"), j1.getInt("numLose"), j1.getString("avatar"));
					myId = j1.getInt("userId");
				}

				else if (s.equals("JR")) {
					JSONArray arr;
					arr = json.getJSONObject("params").getJSONArray("users");
					Log.i("join room ", "receive json : " + json);
					for(int i = 0; i<arr.length(); i++)
					{
						j1 = arr.getJSONObject(i);
						if(j1.getInt("userId")!=myId)
							break;
					}
					game.setInfoUser(false, j1.getString("username"), j1.getInt("exp"), j1.getInt("gold"), j1.getInt("xu"), j1.getInt("userId"), j1.getInt("numWin"), j1.getInt("numLose"), j1.getString("avatar"));
					game.otherEnterRoom();
					// binder.service.send(PacketFactory.createReady());
					// binder.service.send(PacketFactory.createOutRoom());
				} else if (s.equals("GR")) {
					j1 = json.getJSONObject("params");

					game.playerReady(j1.getInt("userId"));
				} else if (s.equals("GS")) {
					j1 = json.getJSONObject("params");
					game.startGame(j1.getInt("whoseTurn"));

				} else if (s.equals("CM")) {
					j1 = json.getJSONObject("params");
					game.doStep(j1.getInt("whoseTurn"), j1.getInt("cell"));
				} else if (s.equals("OR")) {
					j1 = json.getJSONObject("params");
					game.outRoom(j1.getInt("userId"));
				} else if (s.equals("GSTOP")) {
					j1 = json.getJSONObject("params");
					if (j1.has("winner")) {
						game.stopGame(j1.getInt("winner"), j1.getInt("loser"), j1.getInt("canContinue"));
					} else {
						game.stopGame(-1, -1, 0);
					}
				} else if (s.equals("chat"))
				{
					j1 = json.getJSONObject("params");
					game.playStage.chatOther(j1.getString("message"));
				}
				
			}
		} catch (Exception e) {
			Log.e("Caro Activity", "Loi onReceive", e);
		}
	}

	public void disconect() {
		// TODO Auto-generated method stub
		binder.service.disconnect();
	}
}