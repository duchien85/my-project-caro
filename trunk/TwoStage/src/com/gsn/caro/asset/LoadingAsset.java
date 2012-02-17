package com.gsn.caro.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class LoadingAsset {
	static AssetManager manager;
	public static AtlasRegion loadingBG;
	public static AtlasRegion loadingText;
	public static AtlasRegion dotText;
	
	public static void create(){
		Resolution[] resolutions = { new Resolution(240, 320, "240320"), new Resolution(480, 800, "480800")};
		ResolutionFileResolver resolver = new ResolutionFileResolver(new InternalFileHandleResolver(), resolutions);
		manager = new AssetManager(resolver);
		manager.setErrorListener(new AssetErrorListener() {
			@Override
			public void error(String fileName, Class type, Throwable throwable) {
				// TODO Auto-generated method stub
				Gdx.app.error("AssetManagerTest", "couldn't load asset '" + fileName + "'", (Exception) throwable);
			}
		});		
		Texture.setAssetManager(manager);
		manager.load("data/loading/pack", TextureAtlas.class);
		manager.finishLoading();
		TextureAtlas atlas = manager.get("data/loading/pack", TextureAtlas.class);
		loadingBG = atlas.findRegion("loading");
		loadingText = atlas.findRegion("chu loading");
		dotText = atlas.findRegion("dau cham");
	}
}
