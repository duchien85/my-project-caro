package com.gsn.test;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.GdxUtility;
import com.gsn.engine.layout.GsnTableLayout;

public class InfoGameGroup extends Group {
	public InfoGameGroup(float width) {
		ImageAsset asset = ImageAsset.getInstance();
		Image infoBG = new Image(new NinePatch(GdxUtility.convertListRegionToArray(asset.infoUserBG9Path)));
		InfoOneUser userMe = new InfoOneUser();
		InfoOneUser userOther = new InfoOneUser();
		
		infoBG.width = width;
		infoBG.height = userMe.height;				
		
		this.width = infoBG.width;
		this.height = infoBG.height;
		
		GsnTableLayout table = new GsnTableLayout(0, 0, width, height);
		table.newRow(true, 1);
		table.add(0.5f).putCenter(userMe);
		table.add(0.5f).putCenter(userOther);
		
		this.addActor(infoBG);
		this.addActor(userMe);				
		this.addActor(userOther);
	}
}
