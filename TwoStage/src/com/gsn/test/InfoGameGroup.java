package com.gsn.test;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.GdxUtility;

public class InfoGameGroup extends Group {
	public InfoGameGroup(float width, float height) {
		ImageAsset asset = ImageAsset.getInstance();
		Image infoBG = new Image(new NinePatch(GdxUtility.convertListRegionToArray(asset.infoUserBG9Path)));		
		infoBG.width = width;
		infoBG.height = height;
		
		this.addActor(infoBG);
		
		this.width = width;
		this.height = height;
	}
}
