package com.gsn.test;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.caro.asset.AssetOld;
import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.GdxUtility;
import com.gsn.engine.GsnAnimation;
import com.gsn.engine.layout.GsnRectangle;
import com.gsn.engine.layout.GsnTableLayout;
import com.gsn.engine.template.GsnBubleChat;
import com.gsn.engine.template.GsnClockImage;

public class MenuStage extends Stage {
	ImageAsset asset;
	boolean inputBoard;	
	Image boardBorder;
	Image menuBG;
	Vector2 vector = new Vector2();
	GsnBubleChat bubleChatMe;
	GsnAnimation winAnimation;
	PlayStage parent;
	
	public MenuStage(PlayStage parent, float width, float height) {
		super(width, height, false);
		this.parent = parent;
		asset = ImageAsset.getInstance();

		boardBorder = new Image(new NinePatch(asset.boardBorder, 20, 20, 20, 20));
				
		bubleChatMe = new GsnBubleChat(new NinePatch(GdxUtility.convertListRegionToArray(AssetOld.getInstance().bbChat)), asset.font);
		bubleChatMe.x = 100;
		bubleChatMe.y = 100;
		winAnimation  = new GsnAnimation(0.2f, AssetOld.getInstance().winEffect);
				
		
		Image myClockBG = new GsnClockImage(asset.myClockBG);		
		//Image betBG = new Image(asset.betBG);
		Image betBG = new Image(asset.betBG);
		
		betBG.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				System.out.println("click Bet BG");
			}
		});
		
		final Image otherClockBG = new Image(asset.myClockBG);
		otherClockBG.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				otherClockBG.setRegion(asset.otherClockBG);
			}
		});
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
		
		ImageButton infoBtn = new ImageButton(asset.infoActiveBtn, asset.infoDeactiveBtn);
		
		float h = myClockBG.height + 20;
		GsnTableLayout table = new GsnTableLayout(0, 0, width, height);
			//new GsnTableLayout(0, height - h, width, h);
		table.newRow(false, this.height - h);
		table.add(1f);
		table.newRow(false, h);
		table.addList(0.4f, 0.2f, 0.4f);		
		
		
		table.list.get(1).putCenter(myClockBG);
		table.list.get(2).putTopCenter(betBG);
		table.list.get(2).putBottomCenter(infoBtn);
		table.list.get(3).putCenter(otherClockBG);
		table.list.get(0).putCenter(winAnimation);
		
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
		this.addActor(infoBtn);
		this.addActor(otherClockBG);
		this.addActor(backBtn);		
		this.addActor(boardBorder);			
	}
	
	public void chatMe(String text){
		bubleChatMe.setText(text);
		bubleChatMe.setWidthText(60);
		bubleChatMe.pack();
		bubleChatMe.removeFuture(2);
		this.addActor(bubleChatMe);
	}
	
	public void setInputBoard(boolean inputBoard) {
		this.inputBoard = inputBoard;
	}

	public void win() {
		// TODO Auto-generated method stub
		this.addActor(winAnimation);
	}	
}

