package com.gsn.test;

import java.io.File;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.gsn.User.InfoUserGroup;
import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.caro.asset.SoundAsset;
import com.gsn.engine.ImageFactory;
import com.gsn.engine.myplay.GsnStage;
import com.gsn.engine.template.GSNButton;
import com.gsn.engine.template.GsnDialog;
import com.gsn.packet.PacketFactory;
import com.vng.caro.CaroActivity;

public class LobbyStage extends GsnStage {
	GSNButton[] betBtnArr = new GSNButton[5];
	// BetBtn[] betBtnArr = new BetBtn[5];
	GSNButton okBtn;
	ImageButton settingBtn;
	final CaroGame game;
	Table betTable;
	Image background;
	// boolean selected = false;
	InfoUserGroup infoUser;
	int typeRoom;
	SettingStage dialogStage;
	Boolean isDialog = false;
	CaroActivity activity;
	DialogStage quitDialog; 
	boolean loadAvatar = false;
	File fileAvatar = null;
	Boolean showQuitDialog = false;

	public int getRatioWidth(float ratio) {
		return (int) (ratio * width);
	}

	public int getRatioHeight(float ratio) {
		return (int) (ratio * height);
	}
	
	public LobbyStage(CaroGame game, float width, float height) {
		super(width, height);
		activity = DataProvider.getInstance().caroActivity;
		

		this.game = game;

		background = new Image(ImageAsset.getInstance().background);
		this.addActor(background);

		for (int i = 0; i < betBtnArr.length; i++) {
			switch (i) {
			case 0:
				betBtnArr[i] = new GSNButton(i, ImageAsset.getInstance().bet100_1, ImageAsset.getInstance().bet100_2, ImageAsset.getInstance().bet100_3, ImageAsset.getInstance().bet100_4);
			//	betBtnArr[i] = new GSNButton(i, ImageAsset.getInstance().oEffect, ImageAsset.getInstance().oEffect, ImageAsset.getInstance().oEffect, ImageAsset.getInstance().bet100_4);
				break;
			case 1:
				betBtnArr[i] = new GSNButton(i, ImageAsset.getInstance().bet500_1, ImageAsset.getInstance().bet500_2, ImageAsset.getInstance().bet500_3, ImageAsset.getInstance().bet500_4);
				break;
			case 2:
				betBtnArr[i] = new GSNButton(i, ImageAsset.getInstance().bet5000_1, ImageAsset.getInstance().bet5000_2, ImageAsset.getInstance().bet5000_3, ImageAsset.getInstance().bet5000_4);
				break;
			case 3:
				betBtnArr[i] = new GSNButton(i, ImageAsset.getInstance().bet1G_1, ImageAsset.getInstance().bet1G_2, ImageAsset.getInstance().bet1G_3, ImageAsset.getInstance().bet1G_4);
				break;
			case 4:
				betBtnArr[i] = new GSNButton(i, ImageAsset.getInstance().bet10G_1, ImageAsset.getInstance().bet10G_2, ImageAsset.getInstance().bet10G_3, ImageAsset.getInstance().bet10G_4);
				break;
			default:
				break;
			}

			betBtnArr[i].setAndSaveClickListener(new ClickListener() {

				@Override
				public void click(Actor actor, float x, float y) {
					Log.i("click ", "dfd");
					// TODO Auto-generated method stub
					GSNButton bet = (GSNButton) actor;
					SoundAsset.playSoundClick();
					// selected = true;
					// okBtn.setEnable(true);
					if (typeRoom != bet.id + 1) {
						typeRoom = bet.id + 1;
						for (int i = 0; i < 5; i++) {
							if (i != bet.id) {
								betBtnArr[i].setChecked(false);
							}
						}
					} else {
						betBtnArr[typeRoom - 1].setChecked(true);
					}
				}
			});
		}

		betBtnArr[0].setChecked(true);
		typeRoom = betBtnArr[0].id + 1;
		okBtn = new GSNButton(0, ImageAsset.getInstance().enterRoom, ImageAsset.getInstance().enterRoomClick, ImageAsset.getInstance().enterRoomDisable);

		okBtn.setAndSaveClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				SoundAsset.playSoundClick();
				LobbyStage.this.game.setStage(LobbyStage.this.game.playStage);
				LobbyStage.this.game.playStage.menuStage.setTypeRoom(typeRoom);
				LobbyStage.this.game.playStage.menuStage.inRoom(typeRoom);
				LobbyStage.this.game.playStage.menuStage.enterRoom();
			}
		});

		// okBtn.setEnable(false);
		// okBtn.setEnable(true);
		betTable = new Table();

		betTable.debug();
		for (int i = 0; i < 3; i++) {
			betTable.add(betBtnArr[i]).width(getRatioWidth(1f / 3.5f)).colspan(5).padBottom((int)width/15).height(ImageAsset.getInstance().bet100_1.getRegionHeight());
		}

		betTable.row();
		betTable.add(null).colspan(1);
		betTable.add(betBtnArr[3]).width(getRatioWidth(1f / 3.5f)).colspan(7).height(ImageAsset.getInstance().bet100_1.getRegionHeight());
		betTable.add(betBtnArr[4]).width(getRatioWidth(1f / 3.5f)).colspan(4).height(ImageAsset.getInstance().bet100_1.getRegionHeight());
		betTable.add(null).colspan(3);

		betTable.row();
		betTable.add(okBtn).colspan(15).padTop((int) width / 10);
		betTable.pack();
		// betTable.y = 100;
		this.addActor(betTable);

		// add thanh phan ben tren: thong tin user va setting
		infoUser = new InfoUserGroup();
		infoUser.x = width / 15;
		infoUser.y = height - infoUser.height - height / 11;
		this.addActor(infoUser);

		settingBtn = new ImageButton(ImageAsset.getInstance().menuBtn, ImageAsset.getInstance().menuBtnDown);
		this.addActor(settingBtn);
		settingBtn.setClickListener(new ClickListener() {

			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				// TODO Auto-generated method stub
				showDialog();
			}
		});
		settingBtn.x = width - settingBtn.width - width / 30;
		settingBtn.y = height - settingBtn.height - height / 40;
		betTable.y = height - infoUser.height - height / 8f - betTable.height;
		betTable.x = (width - betTable.width) / 2;
		
		
		Image dialogBG = new Image(ImageAsset.getInstance().dialogBg);
		ImageButton okBtn = new ImageButton(ImageAsset.getInstance().okBtn, ImageAsset.getInstance().okBtnDown);
		ImageButton cancelBtn = new ImageButton(ImageAsset.getInstance().cancelBtn, ImageAsset.getInstance().cancelBtnDown);
		 

		okBtn.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				
				SoundAsset.playSoundClick();
				showQuitDialog = false;
				Gdx.app.exit();
			}
		});
		cancelBtn.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				// DialogStage.this.parent.hideDialog();
				
				showQuitDialog = false;
				SoundAsset.playSoundClick();
				LobbyStage.this.setInputListener();
			}
		});

		GsnDialog dialog = new GsnDialog(dialogBG, okBtn, cancelBtn);
		dialog.setContent("Bạn có muốn thoát khỏi game luôn không?");
		quitDialog = new DialogStage(this.width, this.height);
		quitDialog.setDialog(dialog);
		
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		switch (keycode) {		
		case Keys.MENU:
			SoundAsset.playSoundClick();
			showDialog();			
			break;
		case Keys.BACK:
			showQuitDialog = true;
			quitDialog.setInputListener();
			break;
		default:
			break;
		}
		return super.keyDown(keycode);
	}
	
	public void setContentUser(String name, int point, int gold, int xu, String avatar) {
		infoUser.setContent(name, point, gold, xu);
		ImageFactory.saveBitmapToFileAsync(avatar, 64, 64, activity.getExternalCacheDir(), "me.png", new ImageFactory.IImageFactoryListener() {

			@Override
			public void onFinishLoading(File outFile) {
				// TODO Auto-generated method stub
				Log.i("load avatar", "done path : " + outFile.getAbsolutePath());
				loadAvatar = true;
				fileAvatar = outFile;								
			}

			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				Log.i("load avatar", " error ");
			}
		});
		setStateButton();
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();
		if (loadAvatar){
			loadAvatar = false;
			Texture text = new Texture(new FileHandle(fileAvatar));
			infoUser.setAvatar(text);
		}
		if (isDialog) {
			dialogStage.act(Gdx.graphics.getDeltaTime());
			dialogStage.draw();
		}
		if(showQuitDialog)
		{
			quitDialog.act(Gdx.graphics.getDeltaTime());
			quitDialog.draw();
		}
	}

	@Override
	public void setInputListener() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(this);
	}

	public void showDialog() {
		isDialog = true;
		dialogStage = game.settingStage;
		// this
		dialogStage.setInputListener();
		dialogStage.setParent(this, "LOBBY");
	}

	public void hideChild() {
		isDialog = false;
		this.setInputListener();
	}

	public void setStateButton() {
		if (infoUser.info.gold >= 100) {
			betBtnArr[0].setEnable(true);
			typeRoom = 1;
		} else {
			betBtnArr[0].setEnable(false);
			betBtnArr[0].setChecked(false);
			typeRoom = -1;
		}

		if (infoUser.info.gold >= 500)
			betBtnArr[1].setEnable(true);
		else
			betBtnArr[1].setEnable(false);

		if (infoUser.info.gold >= 5000)
			betBtnArr[2].setEnable(true);
		else
			betBtnArr[2].setEnable(false);

		if (infoUser.info.xu >= 1)
			betBtnArr[3].setEnable(true);
		else
			betBtnArr[3].setEnable(false);

		if (infoUser.info.xu >= 10)
			betBtnArr[4].setEnable(true);
		else
			betBtnArr[4].setEnable(false);
		if (typeRoom > 0) {
			okBtn.setEnable(true);
		} else {
			okBtn.setEnable(false);
		}
	}
	
}
