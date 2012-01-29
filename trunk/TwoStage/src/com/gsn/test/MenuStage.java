package com.gsn.test;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.layout.GsnTableLayout;

public class MenuStage extends Stage {
	ImageAsset asset;

	public MenuStage(float width, float height) {
		super(width, height, false);
		asset = ImageAsset.getInstance();

		Image myClockBG = new Image(asset.myClockBG);
		Image betBG = new Image(asset.betBG);
		Image otherClockBG = new Image(asset.myClockBG);		
		ImageButton backBtn = new ImageButton(asset.backActiveBtn, asset.backDeactiveBtn);
		ActorUtility.setTopRight(backBtn, width, height);
		backBtn.setClickListener(new SimpleClickListener("click back btn"));
		// Image myClockBG2 = new Image(asset.myClockBG);
		// Image scoreBG2 = new Image(asset.scoreBG);
		// Image otherClockBG2 = new Image(asset.infoDeactiveBtn);

		float h = myClockBG.height + 20;
		GsnTableLayout table = new GsnTableLayout(0, height - h, width, h);
		table.newRow(1f);
		table.addList(0.4f, 0.2f, 0.4f);
		table.list.get(0).putCenter(myClockBG);
		table.list.get(1).putCenter(betBG);
		table.list.get(2).putCenter(otherClockBG);

		Image actTable = table.toImage();
		actTable.setClickListener(new SimpleClickListener("click menu region"));
		this.addActor(actTable);
		this.addActor(myClockBG);
		this.addActor(betBG);
		this.addActor(otherClockBG);
		this.addActor(backBtn);
	}	
}
