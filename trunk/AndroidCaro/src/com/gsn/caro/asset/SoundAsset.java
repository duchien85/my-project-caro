package com.gsn.caro.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.gsn.caro.settings.CaroSettings;

public class SoundAsset {

	public static Music music;
	public static Sound hitSound;
	public static Sound soundWin;
	public static Sound soundLose;
	public static Sound soundClick;
	public static Sound soundTime;

	public static void playSound(Sound sound) {
		if (CaroSettings.soundEnabled)
			sound.play();
	}		 
	
	public static void load() {
		music = Gdx.audio.newMusic(Gdx.files.internal("data/sound/BgMusic.mp3"));
		music.setLooping(true);
		music.setVolume(0.5f);		
		hitSound = Gdx.audio.newSound(Gdx.files.internal("data/sound/DrawX.mp3"));
		soundWin = Gdx.audio.newSound(Gdx.files.internal("data/sound/Win.mp3"));
		soundLose = Gdx.audio.newSound(Gdx.files.internal("data/sound/Lose.mp3"));
		soundClick = Gdx.audio.newSound(Gdx.files.internal("data/sound/ButtonClick.mp3"));
		soundTime = Gdx.audio.newSound(Gdx.files.internal("data/sound/TimeCountDown.mp3"));
	}

	public static void playHitSound() 
	{
		
		playSound(hitSound);
	}
	
	public static void playSoundWin()
	{
		
		playSound(soundWin);
	}
	public static void playSoundLose()
	{
		
		playSound(soundLose);
	}
	public static void playSoundClick()
	{
	
		playSound(soundClick);
	}
	
	public static void playSoundTime()
	{
	
		playSound(soundTime);
	}
	
	public static void toogleMusic()
	{
		CaroSettings.musicEnabled = !CaroSettings.musicEnabled;
		if(CaroSettings.musicEnabled)
		{			
			music.play();
		}
		else
		{
			music.stop();
		}
			
	}
}
