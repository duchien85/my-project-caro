package com.gsn.test;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.User.InfoGameGroup;
import com.gsn.User.UserData;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.caro.asset.SoundAsset;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.GdxUtility;
import com.gsn.engine.gdx.GsnAnimation;
import com.gsn.engine.gdx.GsnAnimation.IFinishEffectListener;
import com.gsn.engine.layout.GsnRectangle;
import com.gsn.engine.layout.GsnTableLayout;
import com.gsn.engine.template.GsnBubleChat;
import com.gsn.engine.template.GsnClockImage;
import com.gsn.packet.Constant;
import com.gsn.packet.PacketFactory;

public class MenuStage extends Stage implements IFinishEffectListener {
	ImageAsset asset;
	boolean inputBoard;
	Image waitOther;
	Image boardBorder;
	Vector2 vector = new Vector2();
	GsnBubleChat bubleChatMe;
	GsnBubleChat bubleChatOther;
	GsnAnimation winAnimation;
	GsnAnimation loseAnimation;
	GsnAnimation drawAnimation;
	GsnAnimation startAnimation;
	PlayStage parent;
	GsnClockImage myClockBG;
	GsnClockImage otherClockBG;
	
	GsnAnimation animation;

	ImageButton menuBtn;
	ImageButton chatBtn;
	ImageButton showInfoBtn;
	ImageButton hideInfoBtn;
	InfoGameGroup infoGame;
	ImageButton readyButton;

	Image menuBG;
	GsnRectangle rectBound;
	GsnRectangle rectBoundHideInfo;
	GsnRectangle rectBoundShowInfo;
	Image iconMe;
	Image iconOther;
	Image readyItem;
	Image waitOpponentReady;
	Image waitEnterRoom;
	BitmapFont font;
	Group mePoint;
	Group otherPoint;
	float heightMenu;
	BetInfoGroup betInfo ;
	ImageButton scoreInfo;
	
	
	static final int GOLD100 = 1;
	static final int GOLD500 = 2;
	static final int GOLD5000 = 3;
	static final int XU1 = 4;
	static final int XU10 = 5;
	public boolean isPlaying = false; 
	JSONObject roomJson;
	int canContinue;

	public MenuStage(PlayStage parent, float width, float height) {
		super(width, height, false);
		this.parent = parent;
		asset = ImageAsset.getInstance();
		menuBtn = new ImageButton(asset.menuBtn, asset.menuBtnDown);
		
		chatBtn = new ImageButton(asset.chatBtn, asset.chatBtnDown);
		showInfoBtn = new ImageButton(asset.showInfoBtn, asset.showInfoBtnDown);
		hideInfoBtn = new ImageButton(asset.hideInfoBtn, asset.hideInfoBtnDown);
		infoGame = new InfoGameGroup(width);
		readyButton = new ImageButton(asset.readyButton, asset.readyButtonClick);

		iconMe = new Image(asset.iconMe);
		iconOther = new Image(asset.iconOther);
		waitOther = new Image(ImageAsset.getInstance().waitOther);
		readyItem = new Image(ImageAsset.getInstance().readyItem);
		waitEnterRoom = new Image(ImageAsset.getInstance().waitOpponent);
		waitOpponentReady = new Image(ImageAsset.getInstance().waitOpponentReady);
		bubleChatMe = new GsnBubleChat(new NinePatch(GdxUtility.convertListRegionToArray(asset.bubleChatOther9Path)), asset.font);
		
		bubleChatOther = new GsnBubleChat(new NinePatch(GdxUtility.convertListRegionToArray(asset.bubleChatMe9Path)), asset.font);
		
		winAnimation = new GsnAnimation(0.2f, ImageAsset.getInstance().winEffect);
		loseAnimation = new GsnAnimation(0.2f, ImageAsset.getInstance().loseEffect);
		drawAnimation = new GsnAnimation(0.2f, ImageAsset.getInstance().drawEffect);
		startAnimation = new GsnAnimation(0.2f, ImageAsset.getInstance().startEffect);
		
		winAnimation.setListener(this);
		loseAnimation.setListener(this);
		drawAnimation.setListener(this);
		

		myClockBG = new GsnClockImage(asset.clockBG, ImageAsset.getInstance().timeNumbers);
		myClockBG.setBoard(parent.boardStage);
		myClockBG.start();
		// Image betBG = new Image(asset.betBG);
		betInfo = new BetInfoGroup();
		scoreInfo = new ImageButton(asset.scoreBG);
		scoreInfo.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				showInfoUser();
				SoundAsset.playSoundClick();		
			}
		});

		otherClockBG = new GsnClockImage(asset.clockBG, ImageAsset.getInstance().timeNumbers);
		otherClockBG.setBoard(parent.boardStage);
		ImageButton backBtn = new ImageButton(asset.backActiveBtn, asset.backDeactiveBtn);
		ActorUtility.setTopRight(backBtn, width, height);

		menuBtn.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				MenuStage.this.parent.showSetting();
				SoundAsset.playSoundClick();
			}
		});
		
		chatBtn.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				MenuStage.this.parent.showQuickChat();
				SoundAsset.playSoundClick();
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
				SoundAsset.playSoundClick();
			}
		});
		readyButton.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				// TODO Auto-generated method stub
				MenuStage.this.parent.sendJson(PacketFactory.createReady());
				MenuStage.this.parent.input.dontTouchBoard();
				SoundAsset.playSoundClick();
				if(!readyItem.visible)
				{
					waitOpponentReady.visible = true;
					waitOther.visible = true;
				}
			}
		});

		showInfoBtn = new ImageButton(asset.showInfoBtn, asset.showInfoBtnDown);
		showInfoBtn.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				showInfoUser();
				SoundAsset.playSoundClick();
			}
		});

		hideInfoBtn.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				hideInfoUser();
				SoundAsset.playSoundClick();
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
		table.list.get(3).putCenter(waitOther);
		table.list.get(3).putCenter(readyItem);
		table.list.get(0).putCenter(winAnimation);
		table.list.get(0).putCenter(loseAnimation);
		table.list.get(0).putCenter(drawAnimation);
		table.list.get(0).putCenter(startAnimation);
		table.list.get(0).putCenter(waitEnterRoom);
		table.list.get(0).putCenter(waitOpponentReady);

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
		this.addActor(waitOther);
		this.addActor(readyItem);
		this.addActor(chatBtn);
		this.addActor(menuBtn);
		this.addActor(showInfoBtn);

		boardBorder = new Image(new NinePatch(GdxUtility.convertListRegionToArray(asset.board9Path)));
		GsnRectangle tmp = table.list.get(0);
		boardBorder.x = tmp.x;
		boardBorder.y = tmp.y;
		boardBorder.width = tmp.width;
		boardBorder.height = tmp.height;

		this.addActor(boardBorder);
		this.addActor(showInfoBtn);
		
		rectBoundHideInfo = new GsnRectangle(0, height - heightMenu, width, heightMenu);
		rectBoundShowInfo = new GsnRectangle(0, height - heightMenu - infoGame.height, width, heightMenu + infoGame.height);
		rectBound = rectBoundHideInfo;
		
		setTypeRoom(3);
		setPointMatch(0, 0);
		
		//waitOther = new Image();
		this.addActor(readyButton);
		ActorUtility.setCenter(readyButton, width/2, height/2);
		this.addActor(waitEnterRoom);
		waitEnterRoom.visible = true;
		this.addActor(waitOpponentReady);
		
	//	outRoom = new GsnDialog(dialogBG1, okBtn1, null);
		enterRoom();
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
		
		bubleChatMe.x = iconMe.x + iconMe.width / 2;
		bubleChatMe.y = iconMe.y - 10 - bubleChatMe.height;
		
	}
	
	public void chatOther(String text)
	{
		bubleChatOther.setText(text, 80);
		bubleChatOther.removeFuture(2);
		this.addActor(bubleChatOther);
		
		bubleChatOther.x = iconOther.x + iconOther.width / 2 - bubleChatOther.width;
		bubleChatOther.y = iconOther.y - 10 - bubleChatOther.height;
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
		myClockBG.visible = myClockBG.visible = false;
		myClockBG.visible = true;;
		otherClockBG.visible = false;
		iconOther.visible = true;
		iconMe.visible = false;
	}

	public void otherStep() {
		otherClockBG.start();
		myClockBG.stop();
		myClockBG.visible = false;
		otherClockBG.visible = true;
		iconOther.visible = false;
		iconMe.visible = true;
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
	public void inRoom(int type)
	{
		
		
		switch (type) {
		case GOLD100:
			roomJson = PacketFactory.createQuickPlay(100, Constant.BET_GOLD);
			break;
		case GOLD500:
			roomJson = PacketFactory.createQuickPlay(500, Constant.BET_GOLD);
			break;
		case GOLD5000:
			roomJson = PacketFactory.createQuickPlay(5000, Constant.BET_GOLD);
			break;
		case XU1:
			roomJson = PacketFactory.createQuickPlay(1, Constant.BET_COIN);
			break;
		case XU10:
			roomJson = PacketFactory.createQuickPlay(10, Constant.BET_COIN);
			break;
		default:
			break;
		}
		parent.game.caroActivity.sendPacket(roomJson);
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
	
	public void removeClock()
	{
		myClockBG.visible = false;
		otherClockBG.visible = false;
	}
	
	public void addClock()
	{
		myClockBG.visible = true;
		otherClockBG.visible = true;
	}
	
	public void setUserInfo(Boolean me, String name, int gold, int xu, int point, int id, int numWin, int numLose, String avatar)
	{
		if(me)
		{
			infoGame.setMeInfo(name, gold, xu, point, id, numWin, numLose, avatar);
			parent.boardStage.setIdPlayer(true, id);
		}
		else
		{
			infoGame.setOtherInfo(name, gold, xu, point, id, numWin, numLose, avatar);
			parent.boardStage.setIdPlayer(false, id);
		}
	}
	

	public void otherReady()
	{
		waitOther.visible = false;
		readyItem.visible = true;
		waitOpponentReady.visible = false;
		
	}
	public void meReady()
	{
		readyButton.visible = false;
		
	}

	public void startGame(int idStart) {
		// TODO Auto-generated method stub
		addClock();
		readyItem.visible = false;
		waitOpponentReady.visible = false;
		parent.boardStage.initGame();
		if(infoGame.checkMeStart(idStart))
		{
			myClockBG.start();
			otherClockBG.stop();
			parent.boardStage.setXStep(true);
			myStep();
		}
		else
		{
			myClockBG.stop();
			otherClockBG.start();
			parent.boardStage.setXStep(false);
			otherStep();
		}
		isPlaying = true;
		this.addActor(startAnimation);
	}

	public void playerReady(int id) {
		// TODO Auto-generated method stub
		if(infoGame.checkMeStart(id))
		{
			meReady();
		}
		else
			otherReady();
	}
	public void enterRoom()
	{
		removeClock();
		waitOther.visible = false;
		readyItem.visible = false;
		readyButton.visible = false;
		waitOpponentReady.visible = false;
		waitEnterRoom.visible = true;
		
	}
	public void otherEnterRoom()
	{
		
		waitEnterRoom.visible = false;
		readyButton.visible = true;
		
	}

	public void outRoom(int id) {
		// TODO Auto-generated method stub
		parent.boardStage.resetData();
		if(infoGame.checkMeStart(id))
		{
			if(isPlaying)
				parent.game.setLobbyStage();
			
		}
		else
		{
			if(isPlaying)
			{
				parent.showOutRoomDialog();
				
			}
			else
			{
				enterRoom();
			}
		}
	}

	public void stopGame(int winner, int loser, int canContinue) throws JSONException {
		// TODO Auto-generated method stub
		
		Log.i("room Json : ", roomJson.toString());
		
		if(roomJson.toString().contains("gold"))
		{
			updateWinUser(winner, roomJson.getJSONObject("params").getInt("betLv"), Constant.BET_GOLD);
		}
		else
		{
			updateWinUser(winner, roomJson.getJSONObject("params").getInt("betLv"), Constant.BET_COIN);
		}
		if(winner < 0)
		{
			this.addActor(drawAnimation);
			parent.boardStage.finishGame("DRAW");
		}
		else if(infoGame.checkMeStart(winner))
		{
			this.addActor(winAnimation);
			parent.boardStage.finishGame("WIN");
		}
		else
		{
			this.addActor(loseAnimation);
			parent.boardStage.finishGame("LOSE");
		}
		this.canContinue = canContinue;
	}
	
	public void updateWinUser(int userWin, int betLevel, int betType)
	{		
		UserData data = new UserData();
		data.setMyInfo(infoGame.getMeInfo());
		data.setOtherInfo(infoGame.getOtherInfo());
		data.solveWin(userWin, betLevel, betType);
		infoGame.setContentUser();
	}
	public void onFinish()
	{
		parent.boardStage.initGame();
		if(canContinue == 0)
		{
			enterRoom();
			otherEnterRoom();
		}
		else if(canContinue == 1)
		{
		//	parent.game.setLobbyStage();
		}
		else 
		{
			parent.game.setLobbyStage();
		}
	}
}
