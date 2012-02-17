package com.gsn.test;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.caro.asset.LoadingAsset;
import com.gsn.engine.myplay.GsnStage;

public class LoadingStage extends GsnStage {
	float time;
	Image[] dotText = new Image[3];
	boolean added;
	CaroGame game;
	ImageAsset asset;

	public LoadingStage(CaroGame game, float width, float height) {
		super(width, height);
		this.game = game;
		asset = ImageAsset.getInstance();
		time = 0;
		Image background = new Image(LoadingAsset.loadingBG);
		this.addActor(background);

		Image loadingText = new Image(LoadingAsset.loadingText);

		loadingText.x = width - loadingText.width - getRatioWidth(0.1f);
		loadingText.y = getRatioHeight(0f);
		System.out.println("fkdj");
		this.addActor(loadingText);
		added = true;
		for (int i = 0; i < 3; i++) {
			dotText[i] = new Image(LoadingAsset.dotText);
			dotText[i].x = loadingText.x + loadingText.width + dotText[i].width * (2 * i + 1);
			dotText[i].y = loadingText.y + loadingText.height / 3;
			this.addActor(dotText[i]);
		}
	}	
	
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		if (asset.update()){
			asset.assignContent();
			game.onFinishLoading();
		}
		
		time += delta;
		if (time > 0.3f){
			time = 0;
			added = !added;
			if (added){
				addActor(dotText[2]);
			} else{
				removeActor(dotText[2]);
			}
		}
	}
}
