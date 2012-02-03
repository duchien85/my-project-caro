package com.gsn.test;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.ImageAsset;

public class ScoreGroup extends Group {
	public ScoreGroup() {
		// TODO Auto-generated constructor stub
		ImageAsset asset = ImageAsset.getInstance();
		
		Image scoreBG = new Image(asset.scoreBG);
		this.addActor(scoreBG);
		this.height = scoreBG.height;
		this.width = scoreBG.width;
	}
}
