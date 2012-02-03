package com.gsn.test;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.ActorUtility;

public class BetInfoGroup extends Group {
	public BetInfoGroup() {
		// TODO Auto-generated constructor stub
		ImageAsset asset = ImageAsset.getInstance();
		Image betBG = new Image(asset.betBG);
		Image iconGold = new Image(asset.iconGold);
		this.width = betBG.width;
		this.height = betBG.height;
				
		ActorUtility.setCenterHeight(iconGold, 5, betBG.height / 2);
		
		this.addActor(betBG);
		this.addActor(iconGold);
	}
}
