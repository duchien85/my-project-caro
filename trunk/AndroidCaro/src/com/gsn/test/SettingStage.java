package com.gsn.test;

///import org.gsn.packet.PacketFactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.caro.asset.SoundAsset;
import com.gsn.caro.settings.CaroSettings;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.myplay.GsnStage;
import com.gsn.engine.template.GsnDialog;
import com.gsn.packet.PacketFactory;
import com.vng.caro.CaroActivity;

public class SettingStage extends GsnStage {
	
	ImageButton musicButton;
	ImageButton soundButton;
	ImageButton exitButton;
	GsnStage parent;
	String type;
	GsnDialog dialog;
	Boolean showDialog = false;
	public SettingStage(float width, float height) {
		super(width, height);
	
		Image screenBG = new Image(ImageAsset.getInstance().greyBG);
		screenBG.width = this.width;
		screenBG.height = this.height;

		Image dialogBG = new Image(ImageAsset.getInstance().dialogBg);
		ImageButton okBtn = new ImageButton(ImageAsset.getInstance().okBtn, ImageAsset.getInstance().okBtnDown);
		ImageButton cancelBtn = new ImageButton(ImageAsset.getInstance().cancelBtn, ImageAsset.getInstance().cancelBtnDown);

		okBtn.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				dialog.remove();
				SoundAsset.playSoundClick();
				if(type == "PLAY")
				{
					PlayStage p = (PlayStage) SettingStage.this.parent;
					p.hideChild();
					p.game.sendJson(PacketFactory.createOutRoom());
					//DialogStage.this.parent.hi
					p.game.setLobbyStage();
					p.boardStage.resetPointMatch();
					showDialog = false;
				}
				else
				{
					LobbyStage lobby = (LobbyStage) SettingStage.this.parent;
					lobby.hideChild();				
					lobby.game.setLoginStage();
					lobby.game.caroActivity.disconect();
					lobby.game.loginStage.showDialog();
				}
			}
		});
		cancelBtn.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				// DialogStage.this.parent.hideDialog();
				dialog.remove();
				showDialog = false;
				SoundAsset.playSoundClick();
			}
		});

		dialog = new GsnDialog(dialogBG, okBtn, cancelBtn);
		ActorUtility.setCenter(dialog, this.width / 2, this.height / 2);
		musicButton = new ImageButton(ImageAsset.getInstance().musicOn, ImageAsset.getInstance().musicClick, ImageAsset.getInstance().musicOff);
		soundButton = new ImageButton(ImageAsset.getInstance().soundOn, ImageAsset.getInstance().soundClick, ImageAsset.getInstance().soundOff);
		exitButton = new ImageButton(ImageAsset.getInstance().exitNormal, ImageAsset.getInstance().exitClick);
		dialog.setContent("Bạn có muốn thoát khỏi phòng chơi không?");
		musicButton.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				// chuyen trang thai music
				// DialogStage.this.parent.hideDialog();
				SoundAsset.playSoundClick();
				SoundAsset.toogleMusic();
			}
		});

		soundButton.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {

				// TODO Auto-generated method stub
				// chuyen trang thai sound
				// DialogStage.this.parent.hideDialog();
				CaroSettings.soundEnabled = !CaroSettings.soundEnabled;
				SoundAsset.playSoundClick();
			}
		});

		exitButton.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {

				// TODO Auto-generated method stub
				// chuyen trang thai sound
				SettingStage.this.addActor(dialog);
				// DialogStage.this.parent.hideDialog();
				// DialogStage.this.parent.game.setLobbyStage();
				SoundAsset.playSoundClick();
				showDialog = true;

			}
		});

		this.addActor(screenBG);
		// this.addActor(dialog);
		this.addActor(musicButton);
		this.addActor(soundButton);

		// musicButton.x = this.width / 2;

		Table table = new Table();
		this.addActor(table);
		table.add(musicButton).width(String.valueOf((int) screenBG.width / 4));
		table.add(soundButton).width(String.valueOf((int) screenBG.width / 4));
		table.row();
		table.add(exitButton).colspan(8);

		ActorUtility.setCenter(table, this.width / 2, this.height / 2);
		// ActorUtility.setCenter(soundButton, this.width /2 +
		// soundButton.width, this.height / 2);
		// TODO Auto-generated constructor stub
		setStateButton();
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		if (!super.touchUp(x, y, pointer, button) && !showDialog) {
			SettingStage.this.parent.hideChild();
		}
		return true;
	}

	@Override
	public void setInputListener() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(this);
	}
	public void setParent(GsnStage parent, String type)
	{
		this.parent = parent;
		this.type = type;
	}

	public void setStateButton() {
		// TODO Auto-generated method stub
		if(CaroSettings.musicEnabled)
			musicButton.setChecked(false);
		else musicButton.setChecked(true);
		
		if(CaroSettings.soundEnabled)
			soundButton.setChecked(false);
		else soundButton.setChecked(true);
	}
}
