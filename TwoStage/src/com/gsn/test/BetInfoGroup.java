package com.gsn.test;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.ActorUtility;

public class BetInfoGroup extends Group {
	Image type;
	Image number;
	Image betBG;
	public BetInfoGroup() {
		// TODO Auto-generated constructor stub
		ImageAsset asset = ImageAsset.getInstance();
		betBG = new Image(asset.betBG);
		type = new Image(asset.iconGold);
		number = new Image(asset.iconGold);
	//	type.setRegion(asset.iconGold);		
		this.width = betBG.width;
		this.height = betBG.height;
				
		ActorUtility.setLeftCenter(type, number.x, betBG.height / 2);
		ActorUtility.setLeftCenter(number, (betBG.width - number.width - type.width - type.width/5)/2, betBG.height / 2);
		
		this.addActor(betBG);
		this.addActor(type);
		
	}
	public void setType(TextureRegion _type, TextureRegion _number)
	{
		type.remove();
		type = new Image(_type);
		number.remove();
		number = new Image(_number);
		this.addActor(type);
		this.addActor(number);
		ActorUtility.setLeftCenter(number, (betBG.width - number.width - type.width - type.width/5)/2, betBG.height / 2);
		ActorUtility.setLeftCenter(type, number.x + number.width + type.width / 5, betBG.height / 2);
		
	}
}
