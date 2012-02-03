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
import com.gsn.engine.gdx.GsnAnimation;
import com.gsn.engine.layout.GsnRectangle;
import com.gsn.engine.layout.GsnTableLayout;
import com.gsn.engine.template.GsnBubleChat;
import com.gsn.engine.template.GsnClockImage;

public class MenuStage extends Stage {
	ImageAsset asset;
	boolean inputBoard;
	Image boardBorder;	
	Vector2 vector = new Vector2();
	GsnBubleChat bubleChatMe;
	GsnAnimation winAnimation;
	PlayStage parent;
	GsnClockImage myClockBG;
	GsnClockImage otherClockBG;
	GsnAnimation animation;	
	ImageButton menuBtn;
	ImageButton chatBtn;
	
	Image menuBG;
	GsnRectangle rectBound;
	Image iconMe;
	Image iconOther;

	public MenuStage(PlayStage parent, float width, float height) {
		super(width, height, false);
		this.parent = parent;
		asset = ImageAsset.getInstance();					
		menuBtn = new ImageButton(asset.menuBtn, asset.menuBtnDown);
		chatBtn = new ImageButton(asset.menuBtn, asset.menuBtnDown);
		
		iconMe = new Image(asset.iconMe);
		iconOther = new Image(asset.iconOther);
		
		bubleChatMe = new GsnBubleChat(new NinePatch(GdxUtility.convertListRegionToArray(AssetOld.getInstance().bbChat)), asset.font);
		bubleChatMe.x = 100;
		bubleChatMe.y = 100;
		winAnimation = new GsnAnimation(0.2f, AssetOld.getInstance().winEffect);

		myClockBG = new GsnClockImage(asset.clockBG, AssetOld.getInstance().timeNumbers);
		myClockBG.setBoard(parent.boardStage);
		myClockBG.start();
		// Image betBG = new Image(asset.betBG);
		BetInfoGroup betInfo = new BetInfoGroup();
		ScoreGroup scoreInfo = new ScoreGroup();
		
		otherClockBG = new GsnClockImage(asset.clockBG, AssetOld.getInstance().timeNumbers);
		otherClockBG.setBoard(parent.boardStage);
		ImageButton backBtn = new ImageButton(asset.backActiveBtn, asset.backDeactiveBtn);
		ActorUtility.setTopRight(backBtn, width, height);
		backBtn.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				float rX = actor.x + x;
				float rY = actor.y + y;
				MenuStage.this.parent.game.setLobbyStage();
				DataProvider.getInstance().clickEffect.startNow(MenuStage.this.getCamera(), rX, rY);
			}
		});

		ImageButton infoBtn = new ImageButton(asset.infoActiveBtn, asset.infoDeactiveBtn);
		infoBtn.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				showInfoUser();
			}
		});

		float heightMenu = myClockBG.height + menuBtn.height;
		GsnTableLayout table = new GsnTableLayout(0, 0, width, height);
		table.newRow(false, this.height - heightMenu);
		table.add(1f);
		
		table.newRow(false, myClockBG.height);
		table.addList(1f/3, 1f/3, 1f/3);
		
		table.newRow(false, menuBtn.height);
		table.add(1).putTopCenter(betInfo);
		
		table.list.get(1).putCenter(myClockBG).putCenter(iconMe);		
		GsnRectangle cell = table.list.get(2);
		cell.putCenter(scoreInfo);
		table.list.get(3).putCenter(otherClockBG).putCenter(iconOther);
		table.list.get(0).putCenter(winAnimation);
		
		ActorUtility.setTopRight(menuBtn, this.width, this.height);
		ActorUtility.setBottomRight(chatBtn, menuBtn.x, menuBtn.y);
		
		this.menuBG = new GsnRectangle(table.list.get(1).x, table.list.get(1).y, width, table.list.get(1).height).toFilledRectangle(1, 1, 0, 1);		
		
		this.addActor(iconMe);
		this.addActor(myClockBG);
		this.addActor(betInfo);
		this.addActor(infoBtn);
		this.addActor(scoreInfo);
		this.addActor(iconOther);
		this.addActor(otherClockBG);
		this.addActor(backBtn);
		
		this.addActor(chatBtn);
		this.addActor(menuBtn);	
		
		boardBorder = new Image(new NinePatch(GdxUtility.convertListRegionToArray(asset.board9Path)));
		GsnRectangle tmp = table.list.get(0);
		boardBorder.x = tmp.x;
		boardBorder.y = tmp.y;
		boardBorder.width = tmp.width;
		boardBorder.height = tmp.height + 5;
		
		this.addActor(boardBorder);		
		rectBound = new GsnRectangle(0, height - heightMenu, width, heightMenu);
	}
	
	public void showInfoUser(){
		
		
	}

	public void chatMe(String text) {
		bubleChatMe.setText(text, 80);
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
	
	public void myStep()
	{
		myClockBG.start();
		otherClockBG.stop();
	}
	public void otherStep()
	{
		otherClockBG.start();
		myClockBG.stop();
	}
	
	
	public GsnRectangle getRectangleBound(){
		return rectBound;
	}
}
