package com.gsn.caro.settings;

import android.content.SharedPreferences;

import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.SoundAsset;

public class CaroSettings {
	public final static String PREFS_NAME = "myPlay Caro";

	public final static String MUSIC_ENABLE = "musicEnabled";
	public final static String SOUND_ENABLE = "soundEnabled";
	
	public static boolean musicEnabled = true;
	public static boolean soundEnabled = true;
	public static final int PING_DELAY = 2000;
	public static final int PING_TIMEOUT = 3000;

	public static void load() {
		SharedPreferences settings = DataProvider.getInstance().caroActivity.getSharedPreferences(PREFS_NAME, 0);		
		musicEnabled = settings.getBoolean(MUSIC_ENABLE, true);					
		soundEnabled = settings.getBoolean(SOUND_ENABLE, true);
		SoundAsset.toogleMusic();
		SoundAsset.toogleMusic();
		
	}

	public static void save() {
		SharedPreferences settings = DataProvider.getInstance().caroActivity.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean(MUSIC_ENABLE, musicEnabled);
		editor.putBoolean(SOUND_ENABLE, soundEnabled);
		editor.commit();
	}
	
		
}
