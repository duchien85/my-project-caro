package com.gsn.test;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.ImageAsset;

public class BackGroundStage extends Stage {	
	Image background;

	public BackGroundStage(float width, float height) {
		super(width, height, false);		
		background = new Image(ImageAsset.getInstance().boardBG);
		background.width = this.width;
		background.height = this.height;
		addActor(background);
	}	
}
