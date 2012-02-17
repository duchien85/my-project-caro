package com.vng.caro;

import java.util.List;

import org.json.JSONObject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.gsn.engine.mercurry.GsnServerInfo;
import com.gsn.engine.mercurry.MercuryClient;

public class CaroService extends Service{
	CaroBinder binder;
	MercuryClient client;
	final static CaroService instance = null;
	
	public static CaroService getInstance()
	{
		if(instance == null)
		{
			return new CaroService();
		}
		else
		{
			return instance;
		}
	}
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return binder;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		binder = new CaroBinder();
		binder.service = this;		
	}
	
	public void connect(){
		try{
		//List<GsnServerInfo> serverList = GsnServerInfo.getServerInfo("http://myplay.apps.zing.vn/caro/service.php?act=getServerInfo");
		//GsnServerInfo server = serverList.get(0);
		//client = new MercuryClient(server.getIp(), server.getPort(), binder.activity);
		
		client = new MercuryClient("120.138.65.103", 443, binder.activity);
		client.connect();
		} catch (Exception e){
			Log.e("service", "connect", e);
		}
		
	}
	
	public void disconnect() {
		client.disconnect();

	}
	public void onDestroy(){	
		super.onDestroy();
	}
	public void send(String s){
		Log.i("send : " , s);
		client.send(s);
	}

	public void send(JSONObject json){
		client.send(json);
	}
}
