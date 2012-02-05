package com.gsn.test;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.ImageAsset;

public class InfoOneUser extends Group {
	float pad = 20;
	public InfoOneUser() {
		ImageAsset asset = ImageAsset.getInstance();
		Image avatar = new Image(asset.avatar);		
		
		this.width = avatar.width;
		this.height = avatar.height + pad;
		
		this.addActor(avatar);
	}
}
