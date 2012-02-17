package com.gsn.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.myplay.GsnStage;
import com.gsn.engine.template.GsnDialog;

public class DialogStage extends GsnStage{

	GsnDialog dialog;
	
	public DialogStage(float width, float height)
	{
		super(width, height, false);
		Image screenBG = new Image(ImageAsset.getInstance().greyBG);
		screenBG.width = this.width;
		screenBG.height = this.height;
		this.addActor(screenBG);
	}
	
	public void setDialog(GsnDialog dialog)
	{
		if(dialog != null)
			dialog.remove();
		this.dialog = dialog;
		addActor(dialog);
		ActorUtility.setCenter(dialog, width/2, height/2);
	}
	
	@Override
	public void setInputListener() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(this);
	}
}
