package com.gsn.test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.layout.GsnTableLayout;

public class MenuStage extends Stage {
	ImageAsset asset;
	boolean inputBoard;
	public boolean touchUpBoard;
	public boolean touchDownBoard;
	public boolean touchDragBoard;
	
	Vector2 vector = new Vector2();
	public void setInputBoard(boolean inputBoard) {
		this.inputBoard = inputBoard;
	}
	
	public MenuStage(float width, float height) {
		super(width, height, false);
		asset = ImageAsset.getInstance();

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
				touchUpBoard = false;
			}
		});
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

		Image tableImg = table.toImage();
		tableImg.color.set(1.0f, 1.0f, 1.0f, 1.0f);
		tableImg.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				System.out.println("click menu region");
				setInputBoard(false);
				touchUpBoard = false;
			}
		});
		this.addActor(tableImg);
		this.addActor(myClockBG);
		this.addActor(betBG);
		this.addActor(otherClockBG);
		this.addActor(backBtn);		
	}
}
