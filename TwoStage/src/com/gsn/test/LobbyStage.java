package com.gsn.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.gsn.caro.asset.AssetOld;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.myplay.GsnStage;
import com.gsn.engine.template.GSNButton;

public class LobbyStage extends GsnStage {
	BetBtn[] betBtnArr = new BetBtn[5];
	ImageButton okBtn;
	ImageButton settingBtn;
	final CaroGame game;
	Table betTable;
	Image background;
	DialogStage dialogStage;
	Boolean isDialog = false;
	//boolean selected = false;
	int typeRoom;
	public int getRatioWidth(float ratio) {
		return (int) (ratio * width);
	}

	public int getRatioHeight(float ratio) {
		return (int) (ratio * height);
	}

	public LobbyStage(CaroGame game, float width, float height) {
		super(width, height);
		this.game = game;

		background = new Image(ImageAsset.getInstance().background);
		this.addActor(background);

		for (int i = 0; i < betBtnArr.length; i++) {
			switch (i) {
			case 0:
				betBtnArr[i] = new BetBtn(i, ImageAsset.getInstance().bet100_1, ImageAsset.getInstance().bet100_2, ImageAsset.getInstance().bet100_3);
				break;
			case 1:
				betBtnArr[i] = new BetBtn(i, ImageAsset.getInstance().bet500_1, ImageAsset.getInstance().bet500_2, ImageAsset.getInstance().bet500_3);
				break;
			case 2:
				betBtnArr[i] = new BetBtn(i, ImageAsset.getInstance().bet5000_1, ImageAsset.getInstance().bet5000_2, ImageAsset.getInstance().bet5000_3);
				break;
			case 3:
				betBtnArr[i] = new BetBtn(i, ImageAsset.getInstance().bet1G_1, ImageAsset.getInstance().bet1G_2, ImageAsset.getInstance().bet1G_3);
				break;
			case 4:
				betBtnArr[i] = new BetBtn(i, ImageAsset.getInstance().bet10G_1, ImageAsset.getInstance().bet10G_2, ImageAsset.getInstance().bet10G_3);
				break;
			default:
				break;
			}
			
			betBtnArr[i].setClickListener(new ClickListener() {

				@Override
				public void click(Actor actor, float x, float y) {
					// TODO Auto-generated method stub
					BetBtn bet = (BetBtn) actor;
					//selected = true;
					//okBtn.setEnable(true);
					typeRoom = bet.id + 1;
					for (int i = 0; i < 5; i++) {
						if (i != bet.id) {
							betBtnArr[i].setChecked(false);
						}
					}
				}
			});
		}
		
		betBtnArr[0].setChecked(true);
		typeRoom = betBtnArr[0].id + 1;
		okBtn = new ImageButton(new NinePatch(ImageAsset.getInstance().enterRoom), new NinePatch(ImageAsset.getInstance().enterRoomClick));

		okBtn.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				
				LobbyStage.this.game.setStage(LobbyStage.this.game.playStage);
				LobbyStage.this.game.playStage.menuStage.setTypeRoom(typeRoom);
				
			}
		});
		
		//okBtn.setEnable(false);
		//okBtn.setEnable(true);
		betTable = new Table();
		
		betTable.debug();
		for (int i = 0; i < 3; i++) {
			betTable.add(betBtnArr[i]).width(getRatioWidth(1f / 3.5f)).colspan(5).padBottom((int)width/15);
		}

		betTable.row();
		betTable.add(null).colspan(1);
		betTable.add(betBtnArr[3]).width(getRatioWidth(1f / 3.5f)).colspan(7);
		betTable.add(betBtnArr[4]).width(getRatioWidth(1f / 3.5f)).colspan(4);
		betTable.add(null).colspan(3);

		betTable.row();
		betTable.add(okBtn).colspan(15).padTop((int)width/10);
		betTable.pack();
		// betTable.y = 100;
		this.addActor(betTable);

		
		// add thanh phan ben tren: thong tin user va setting
		InfoUserGroup infoUser = new InfoUserGroup();
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
		settingBtn.x = width - settingBtn.width - width/30;
		settingBtn.y = height - settingBtn.height- height/40;
		if(okBtn.y < 0) okBtn.y = 0;
		betTable.y = height - infoUser.height - height/8f - betTable.height;
		betTable.x = (width - betTable.width)/2;
		background.width = width;
		background.height = height;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();
		if(isDialog)
		{
			dialogStage.act(Gdx.graphics.getDeltaTime());
			dialogStage.draw();
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
		//this
		dialogStage.setInputListener();
		dialogStage.setParent(this, "LOBBY");
	}

	public void hideChild() {
		isDialog = false;
		this.setInputListener();
	}
}
