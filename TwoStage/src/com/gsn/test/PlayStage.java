package com.gsn.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.myplay.GsnStage;
import com.gsn.engine.template.GSNQuickChat;

public class PlayStage extends GsnStage {
	CaroGame game;
	Stage stageLocal;
	public PlayStage(CaroGame game, float width, float height) {
		super(width, height, false);
		this.game = game;
		stageLocal = new Stage(width, height, false);
		DataProvider.getInstance().screenStage = stageLocal;
		stageLocal.getCamera().update();

		boardStage = new BoardStage(this, width, height);
		menuStage = new MenuStage(this, width, height);
		backgroundStage = new BackGroundStage(width, height);
	//	dialogStage = new DialogStage(this, width, height);
		quickChatStage = new GSNQuickChat(this, width, height);

		input = new GsnInputPlayStage(this);
		asset = ImageAsset.getInstance();

	}

	ImageAsset asset;
	BoardStage boardStage;
	BackGroundStage backgroundStage;
	DialogStage dialogStage;
	public MenuStage menuStage;
	GSNQuickChat quickChatStage;

	private final GsnInputPlayStage input;

	boolean isDialog = false;
	boolean isQuickChat = false;

	public void showDialog() {
		isDialog = true;
		dialogStage = game.settingStage;
		//this
		dialogStage.setInputListener();
		dialogStage.setParent(this, "PLAY");
	}
	
	public void showQuickChat() {
		isQuickChat = true;
		quickChatStage.setInputListener();
	}

	public void hideChild() {
		isDialog = false;
		this.setInputListener();
	}
	public void hideQuickChat() {
		isQuickChat = false;
		this.setInputListener();
	}
	
	public void chatMe(String s)
	{
		menuStage.chatMe(s);
	}
	public void chatOther(String s)
	{
		menuStage.chatOther(s);
	}
	public void clickEffect(float x, float y) {
		asset.clickEffect.startNow(stageLocal.getCamera(), x, y);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		boardStage.getCamera().update();
		menuStage.getCamera().update();

		backgroundStage.draw();

		float delta = Gdx.graphics.getDeltaTime();

		boardStage.act(delta);
		boardStage.draw();

		menuStage.act(delta);
		menuStage.draw();

		asset.clickEffect.update(delta);
		asset.clickEffect.drawNow();

		if (isDialog) {
			dialogStage.act(delta);
			dialogStage.draw();
		}
		if (isQuickChat)
		{
			quickChatStage.act(delta);
			quickChatStage.draw();
		}
	}

	public void dontTouchBoard() {
		input.touchBoard = false;
		boardStage.pinch.reset();
	}

	@Override
	public void setInputListener() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(input);
	}

}
