package com.gsn.test;

import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.caro.asset.SoundAsset;
import com.gsn.engine.myplay.GsnStage;
import com.gsn.engine.template.GSNQuickChat;
import com.gsn.engine.template.GsnDialog;

public class PlayStage extends GsnStage {
	CaroGame game;
	Stage stageLocal;
	public PlayStage(CaroGame game, float width, float height) {
		super(width, height, false);
		this.game = game;
		stageLocal = new Stage(width, height, false);
		DataProvider.getInstance().screenStage = stageLocal;
		stageLocal.getCamera().update();

		boardStage = new BoardStage(this, width, height, ImageAsset.getInstance().menuBg.getRegionHeight());
		menuStage = new MenuStage(this, width, height);
		backgroundStage = new BackGroundStage(width, height);
		dialogStage = new SettingStage(width, height);
		quickChatStage = new GSNQuickChat(this, width, height);
		outRoom = new DialogStage(width, height);
		input = new GsnInputPlayStage(this);
		asset = ImageAsset.getInstance();
		
		Image dialogBG1 = new Image(ImageAsset.getInstance().dialogBg);
		ImageButton okBtn1 = new ImageButton(ImageAsset.getInstance().okBtn, ImageAsset.getInstance().okBtnDown);
		
		okBtn1.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				hideChild();
				PlayStage.this.game.setLobbyStage();
				
			}
		});
		GsnDialog dialog = new GsnDialog(dialogBG1, okBtn1, null);
		dialog.setContent("Đối thủ của bạn vừa thoát khỏi phòng chơi");
		outRoom.setDialog(dialog);
		
		Image dialogBG = new Image(ImageAsset.getInstance().dialogBg);
		ImageButton okBtn = new ImageButton(ImageAsset.getInstance().okBtn, ImageAsset.getInstance().okBtnDown);
		ImageButton cancelBtn = new ImageButton(ImageAsset.getInstance().cancelBtn, ImageAsset.getInstance().cancelBtnDown);
		 

		okBtn.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				
				SoundAsset.playSoundClick();
				hideChild();
				PlayStage.this.game.setLobbyStage();
			//	Gdx.app.exit();
			}
		});
		cancelBtn.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				// DialogStage.this.parent.hideDialog();

				hideChild();
				SoundAsset.playSoundClick();
				PlayStage.this.setInputListener();
			}
		});

		GsnDialog dialog1 = new GsnDialog(dialogBG, okBtn, cancelBtn);
		dialog1.setContent("Bạn có muốn thoát khỏi phòng chơi không?");
		quitRoom = new DialogStage(this.width, this.height);
		quitRoom.setDialog(dialog1);
		
	}

	ImageAsset asset;
	BoardStage boardStage;
	BackGroundStage backgroundStage;
	SettingStage dialogStage;
	public MenuStage menuStage;
	GSNQuickChat quickChatStage;
	
	DialogStage outRoom;
	DialogStage quitRoom;
	Boolean showOutRoom = false;
	
	public final GsnInputPlayStage input;

	boolean isDialog = false;
	boolean isQuickChat = false;
	boolean isQuitRoom = false;

	public void showSetting() {
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

	public void showQuitRoom() {
		isQuitRoom = true;
		quitRoom.setInputListener();
	}
	
	
	public void showOutRoomDialog()
	{
		showOutRoom = true;
		outRoom.setInputListener();
	//	outRoom.s
	}

	public void hideChild() {
		isDialog = false;
		showOutRoom = false;
		isQuitRoom = false;
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
		if(showOutRoom)
		{
			outRoom.act(delta);
			outRoom.draw();
		}
		if(isQuitRoom)
		{
			quitRoom.act(delta);
			quitRoom.draw();
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
	public void sendJson(JSONObject json)
	{
		game.sendJson(json);
	}
	
}
