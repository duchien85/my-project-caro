package com.gsn.test;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.esotericsoftware.tablelayout.BaseTableLayout;
import com.gsn.caro.asset.ImageAsset;

public class MenuStage extends Stage{
	ImageAsset asset;
	
	public MenuStage(float width, float height) {
		super(width, height, false);
		asset = ImageAsset.getInstance();
		
		Table table = new Table();		
		
		Image myClockBG = new Image(asset.myClockBG);
		Image scoreBG = new Image(asset.scoreBG);
		Image otherClockBG = new Image(asset.myClockBG);
		
		
		Image myClockBG2 = new Image(asset.myClockBG);
		Image scoreBG2 = new Image(asset.scoreBG);
		Image otherClockBG2 = new Image(asset.infoDeactiveBtn);
				
		table.debug("all");
		table.row();
		table.add(myClockBG);		
		table.add(scoreBG);
		table.add(otherClockBG);
				
//		table.row();
//		table.add(scoreBG2).align(GsnBaseTableLayout.RIGHT);
//		table.add(myClockBG2).align(GsnBaseTableLayout.RIGHT);
//		table.add(otherClockBG2).right();
//		table.width(480);
		table.pack();		
		table.x = 0;
		table.y = this.height - table.height;
		
		this.addActor(table);
	}	
}
