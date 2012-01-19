package com.gsn.test;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.gsn.caro.asset.ImageAsset;

public class BoardStage extends Stage {
	ImageAsset asset;
	public BoardStage(float width, float height) {
		super(width, height, false);
//		asset = ImageAsset.getInstance();
//		
//		ImageButton imgBtn = new ImageButton(asset.backActiveBtn, asset.backDeactiveBtn, asset.backActiveBtn);		
//		imgBtn.setClickListener(new SimpleClickListener("Board Stage Click"));
//		imgBtn.x =50;
//		imgBtn.y = 100;
//		
//		this.addActor(imgBtn);		
	}	
}
