package com.gsn.test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.layout.GsnNinePath;
import com.gsn.engine.layout.GsnRectangle;
import com.gsn.engine.layout.GsnTableLayout;

public class MenuStage extends Stage {
	ImageAsset asset;
	boolean inputBoard;	
	GsnNinePath boardBorder;
	Image menuBG;
	Vector2 vector = new Vector2();
	
	public MenuStage(float width, float height) {
		super(width, height, false);
		asset = ImageAsset.getInstance();

		boardBorder = new GsnNinePath(asset.boardBorder, 20, 20, 20, 20);
				
		
		Image myClockBG = new Image(asset.myClockBG);
		Image betBG = new Image(asset.betBG);
		
		betBG.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				System.out.println("click Bet BG");
			}
		});
		
		Image otherClockBG = new Image(asset.myClockBG);
		ImageButton backBtn = new ImageButton(asset.backActiveBtn, asset.backDeactiveBtn);		
		ActorUtility.setTopRight(backBtn, width, height);
		backBtn.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				float rX = actor.x + x;
				float rY = actor.y + y;				
				DataProvider.getInstance().clickEffect.startNow(MenuStage.this.getCamera(), rX, rY);				
			}
		});	
		float h = myClockBG.height + 20;
		GsnTableLayout table = new GsnTableLayout(0, 0, width, height);
			//new GsnTableLayout(0, height - h, width, h);
		table.newRow(false, this.height - h);
		table.add(1f);
		table.newRow(false, h);
		table.addList(0.4f, 0.2f, 0.4f);		
		
		
		table.list.get(1).putCenter(myClockBG);
		table.list.get(2).putCenter(betBG);
		table.list.get(3).putCenter(otherClockBG);
		GsnRectangle tmp = table.list.get(0);
		boardBorder.x = tmp.x;
		boardBorder.y = tmp.y;
		boardBorder.width = tmp.width;
		boardBorder.height = tmp.height + 5;
		
		this.menuBG = new GsnRectangle(table.list.get(1).x, table.list.get(1).y, width, table.list.get(1).height).toFilledRectangle(1, 1, 0, 1);
		menuBG.color.set(1.0f, 1.0f, 1.0f, 1.0f);
		menuBG.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				System.out.println("click menu region");
				setInputBoard(false);				
			}
		});
		this.addActor(menuBG);
		this.addActor(myClockBG);
		this.addActor(betBG);
		this.addActor(otherClockBG);
		this.addActor(backBtn);		
		this.addActor(boardBorder);
	}
	
	public void setInputBoard(boolean inputBoard) {
		this.inputBoard = inputBoard;
	}
}
