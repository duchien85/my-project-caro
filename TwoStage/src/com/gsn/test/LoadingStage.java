package com.gsn.test;

import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.FadeIn;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;
import com.badlogic.gdx.scenes.scene2d.actions.Forever;
import com.badlogic.gdx.scenes.scene2d.actions.Remove;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.LoadingAsset;
import com.gsn.engine.myplay.GsnStage;

public class LoadingStage extends GsnStage {
	float time;
	Image[] dotText = new Image[3];
	boolean added;

	public LoadingStage(float width, float height) {
		super(width, height);
		time = 0;
		Image background = new Image(LoadingAsset.loadingBG);
		this.addActor(background);

		Image loadingText = new Image(LoadingAsset.loadingText);

		loadingText.x = width - loadingText.width - getRatioWidth(0.1f);
		loadingText.y = getRatioHeight(0f);

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
