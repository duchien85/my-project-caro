package com.gsn.cotuong.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.gsn.engine.gdx.GsnParticleEffect;

public class ImageAsset {
	private static ImageAsset _instance;
	static AssetManager manager;
	
	public static ImageAsset getInstance(){
		if(_instance ==null){
			_instance = new ImageAsset();
		}
		return _instance;
	}
	
	final static String tag = ImageAsset.class.getSimpleName();
	public BitmapFont font;
	public GsnParticleEffect clickEffect = new GsnParticleEffect();
	
	private AtlasRegion btnLogin;
	
	
	public ImageAsset(){
		
	}
	
	private void assignContent(){
		clickEffect.load(Gdx.files.internal("data/particle/click.p"), Gdx.files.internal("data/particle"));
		TextureAtlas alas = manager.get("data/content/pack", TextureAtlas.class);
		
	}
	
	public void create(){
		manager = new AssetManager();
		loadAll();
		Texture.setAssetManager(manager);
		font = new BitmapFont();
	}
	
	
	public void finnishLoading(){
		manager.finishLoading();
	}
	
	private void loadAll(){
		manager.load("gdx.pack",TextureAtlas.class);
		finnishLoading();
		assignContent();
		
		
	}

}
