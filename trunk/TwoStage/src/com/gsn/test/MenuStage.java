package com.gsn.test;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.gsn.caro.asset.AssetOld;
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
	GsnBubleChat bubleChatOther;
	GsnAnimation winAnimation;
	PlayStage parent;
	GsnClockImage myClockBG;
	GsnClockImage otherClockBG;
	GsnAnimation animation;

	ImageButton menuBtn;
	ImageButton chatBtn;
	ImageButton showInfoBtn;
	ImageButton hideInfoBtn;
	InfoGameGroup infoGame;

	Image menuBG;
	GsnRectangle rectBound;
	GsnRectangle rectBoundHideInfo;
	GsnRectangle rectBoundShowInfo;
	Image iconMe;
	Image iconOther;

	BitmapFont font;
	Group mePoint;
	Group otherPoint;
	float heightMenu;
	BetInfoGroup betInfo ;
	ScoreGroup scoreInfo;
	
	static final int GOLD100 = 1;
	static final int GOLD500 = 2;
	static final int GOLD5000 = 3;
	static final int XU1 = 4;
	static final int XU10 = 5;

	public MenuStage(PlayStage parent, float width, float height) {
		super(width, height, false);
		this.parent = parent;
		asset = ImageAsset.getInstance();
		menuBtn = new ImageButton(asset.menuBtn, asset.menuBtnDown);
		
		chatBtn = new ImageButton(asset.chatBtn, asset.chatBtnDown);
		showInfoBtn = new ImageButton(asset.showInfoBtn, asset.showInfoBtnDown);
		hideInfoBtn = new ImageButton(asset.hideInfoBtn, asset.hideInfoBtnDown);
		infoGame = new InfoGameGroup(width);

		iconMe = new Image(asset.iconMe);
		iconOther = new Image(asset.iconOther);

		bubleChatMe = new GsnBubleChat(new NinePatch(GdxUtility.convertListRegionToArray(asset.bubleChatOther9Path)), asset.font);
		bubleChatMe.x = 100;
		bubleChatMe.y = 100;
		
		bubleChatOther = new GsnBubleChat(new NinePatch(GdxUtility.convertListRegionToArray(asset.bubleChatMe9Path)), asset.font);
		bubleChatOther.x = 10;
		bubleChatOther.y = 100;
		
		winAnimation = new GsnAnimation(0.2f, AssetOld.getInstance().winEffect);

		myClockBG = new GsnClockImage(asset.clockBG, ImageAsset.getInstance().time);
		myClockBG.setBoard(parent.boardStage);
		myClockBG.start();
		// Image betBG = new Image(asset.betBG);
		betInfo = new BetInfoGroup();
		scoreInfo = new ScoreGroup();

		otherClockBG = new GsnClockImage(asset.clockBG, ImageAsset.getInstance().time);
		otherClockBG.setBoard(parent.boardStage);
		ImageButton backBtn = new ImageButton(asset.backActiveBtn, asset.backDeactiveBtn);
		ActorUtility.setTopRight(backBtn, width, height);

		menuBtn.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				MenuStage.this.parent.showDialog();
			}
		});
		
		chatBtn.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				MenuStage.this.parent.showQuickChat();
			}
		});
		backBtn.setClickListener(new ClickListener() {
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				float rX = actor.x + x;
				float rY = actor.y + y;
				MenuStage.this.parent.game.setLobbyStage();
				MenuStage.this.parent.clickEffect(rX, rY);
			}
		});

		showInfoBtn = new ImageButton(asset.showInfoBtn, asset.showInfoBtnDown);
		showInfoBtn.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				showInfoUser();
			}
		});

		hideInfoBtn.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				hideInfoUser();
			}
		});

		heightMenu = myClockBG.height + menuBtn.height;
		GsnTableLayout table = new GsnTableLayout(0, 0, width, height);

		table.newRow(false, this.height - heightMenu);
		table.add(1f).putTopCenter(showInfoBtn).putTopLeft(infoGame);

		table.newRow(false, myClockBG.height);
		table.addList(1f / 3, 1f / 3, 1f / 3);

		table.newRow(false, menuBtn.height);
		table.add(1).putTopCenter(betInfo);
		

		table.list.get(1).putCenter(myClockBG).putCenter(iconMe);
		GsnRectangle cell = table.list.get(2);
		cell.putCenter(scoreInfo);

		font = ImageAsset.getInstance().font;
		

		table.list.get(3).putCenter(otherClockBG).putCenter(iconOther);
		table.list.get(0).putCenter(winAnimation);

		ActorUtility.setTopRight(menuBtn, this.width, this.height);
		ActorUtility.setBottomRight(chatBtn, menuBtn.x, menuBtn.y);

		hideInfoBtn.x = showInfoBtn.x;
		hideInfoBtn.y = showInfoBtn.y - infoGame.height;

		menuBG = new Image(asset.boardBG);
		menuBG.width = this.width;
		menuBG.height = this.heightMenu;

		ActorUtility.setTopLeft(menuBG, 0, this.height);

		this.addActor(menuBG);
		this.addActor(iconMe);
		this.addActor(myClockBG);
		this.addActor(betInfo);
		
		this.addActor(scoreInfo);
		

		this.addActor(iconOther);
		this.addActor(otherClockBG);
		this.addActor(backBtn);

		this.addActor(chatBtn);
		this.addActor(menuBtn);
		this.addActor(showInfoBtn);

		boardBorder = new Image(new NinePatch(GdxUtility.convertListRegionToArray(asset.board9Path)));
		GsnRectangle tmp = table.list.get(0);
		boardBorder.x = tmp.x;
		boardBorder.y = tmp.y;
		boardBorder.width = tmp.width;
		boardBorder.height = tmp.height + 5;

		this.addActor(boardBorder);
		this.addActor(showInfoBtn);
		
		rectBoundHideInfo = new GsnRectangle(0, height - heightMenu, width, heightMenu);
		rectBoundShowInfo = new GsnRectangle(0, height - heightMenu - infoGame.height, width, heightMenu + infoGame.height);
		rectBound = rectBoundHideInfo;
		
		setTypeRoom(3);
		setPointMatch(0, 0);
	}

	public void showInfoUser() {
		MenuStage.this.parent.dontTouchBoard();
		this.addActor(infoGame);
		showInfoBtn.remove();
		this.addActor(hideInfoBtn);
		rectBound = rectBoundShowInfo;
	}

	public void hideInfoUser() {
		MenuStage.this.parent.dontTouchBoard();
		infoGame.remove();
		hideInfoBtn.remove();
		this.addActor(showInfoBtn);
		rectBound = rectBoundHideInfo;
	}

	public void chatMe(String text) {
		bubleChatMe.setText(text, 80);
		bubleChatMe.removeFuture(2);
		this.addActor(bubleChatMe);
	}

	public void chatOther(String text)
	{
		bubleChatOther.setText(text, 80);
		bubleChatOther.removeFuture(2);
		this.addActor(bubleChatOther);
	}
	public void setInputBoard(boolean inputBoard) {
		this.inputBoard = inputBoard;
	}

	public void win() {
		// TODO Auto-generated method stub
		this.addActor(winAnimation);
	}

	public void myStep() {
		myClockBG.start();
		otherClockBG.stop();
	}

	public void otherStep() {
		otherClockBG.start();
		myClockBG.stop();
	}

	public GsnRectangle getRectangleBound() {
		return rectBound;
	}

	public void setPointMatch(int me, int other) {
		if(mePoint!=null)
			mePoint.remove();
		mePoint = convertNumber(me, "BLUE");
		if(otherPoint!=null)
			otherPoint.remove();
		otherPoint = convertNumber(other, "RED");
		this.addActor(mePoint);
		this.addActor(otherPoint);
		mePoint.x = scoreInfo.x + (scoreInfo.width / 2 - mePoint.width)/2;
		mePoint.y = scoreInfo.y + (scoreInfo.height - mePoint.height)/2;
		
		otherPoint.x = scoreInfo.x + scoreInfo.width / 2 + (scoreInfo.width / 2 - mePoint.width)/2;
		otherPoint.y = scoreInfo.y + (scoreInfo.height - mePoint.height)/2;
	}
	public void setTypeRoom(int type)
	{
		switch (type) {
		case GOLD100:
			betInfo.setType(ImageAsset.getInstance().iconGold, ImageAsset.getInstance().num100Gold);
			break;
		case GOLD500:
			betInfo.setType(ImageAsset.getInstance().iconGold, ImageAsset.getInstance().num500Gold);
			break;
		case GOLD5000:
			betInfo.setType(ImageAsset.getInstance().iconGold, ImageAsset.getInstance().num5000Gold);
			break;
		case XU1:
			betInfo.setType(ImageAsset.getInstance().iconXu, ImageAsset.getInstance().num1G);
			break;
		case XU10:
			betInfo.setType(ImageAsset.getInstance().iconXu, ImageAsset.getInstance().num10G);
			break;
		default:
			break;
		}
	}
	
	public Group convertNumber(int number, String type)
	{
		Group result = new Group();
	
		int array [] = new int [10];
		int count = 0;
		while (number >= 0) {
			array[count] = number % 10;
			number = number / 10;
			if(number == 0) number = -1;
			count++;
		}
		Image image;
		float fixX = 0;
		for (int i = 0; i< count; i++)
		{
			if(type.equals("BLUE"))
				image = new Image(ImageAsset.getInstance().numberPointBlue.get(array[i]));
			else
				image = new Image(ImageAsset.getInstance().numberPointRed.get(array[i]));
			result.addActor(image);
			image.x = fixX;
			fixX = image.width + image.width / 5;
			result.width = fixX - image.width / 5;
			result.height = image.height;
		}
		return result;
	}
}
