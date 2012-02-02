package com.gsn.test;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.gsn.caro.asset.AssetOld;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.myplay.GsnStage;

public class LobbyStage extends GsnStage {
	BetBtn[] betBtnArr = new BetBtn[5];
	ImageButton okBtn;
	ImageButton settingBtn;
	final CaroGame game;
	Table betTable;
	Image background;

	public int getRatioWidth(float ratio) {
		return (int) (ratio * width);
	}

	public int getRatioHeight(float ratio) {
		return (int) (ratio * height);
	}

	public LobbyStage(CaroGame game, float width, float height) {
		super(width, height);
		this.game = game;

		background = new Image(AssetOld.getInstance().background);
		//this.addActor(background);

		for (int i = 0; i < betBtnArr.length; i++) {
			betBtnArr[i] = new BetBtn(i, AssetOld.getInstance().bet100_1, AssetOld.getInstance().bet100_2, AssetOld.getInstance().bet100_3);
			betBtnArr[i].setClickListener(new ClickListener() {

				@Override
				public void click(Actor actor, float x, float y) {
					// TODO Auto-generated method stub
					BetBtn bet = (BetBtn) actor;

					for (int i = 0; i < 5; i++) {
						if (i != bet.id) {
							betBtnArr[i].setChecked(false);
						}
					}
				}
			});
		}

		okBtn = new ImageButton(AssetOld.getInstance().bet100_1, AssetOld.getInstance().bet100_2);
		okBtn.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				LobbyStage.this.game.setStage(LobbyStage.this.game.playStage);
			}
		});

		betTable = new Table();
		betTable.y = getRatioHeight(1f / 3);
		betTable.debug();
		for (int i = 0; i < 3; i++) {
			betTable.add(betBtnArr[i]).width(getRatioWidth(1f / 3)).colspan(5).padBottom(30);
		}

		betTable.row();
		betTable.add(null).colspan(1);
		betTable.add(betBtnArr[3]).width(getRatioWidth(1f / 3)).colspan(7);
		betTable.add(betBtnArr[4]).width(getRatioWidth(1f / 3)).colspan(4);
		betTable.add(null).colspan(3);

		betTable.row();
		betTable.add(okBtn).colspan(15);
		betTable.pack();
		// betTable.y = 100;
		this.addActor(betTable);

		InfoUserGroup infoUser = new InfoUserGroup();
		infoUser.x = 10;
		infoUser.y = height - infoUser.height - 10;

		this.addActor(infoUser);

		settingBtn = new ImageButton(AssetOld.getInstance().menuButton);
		this.addActor(settingBtn);
		
		Label a = new Label("talksdfj asdf a", new Label.LabelStyle(ImageAsset.getInstance().font, new Color(1, 1, 1, 1)));
		this.addActor(a);
		
		settingBtn.x = width - settingBtn.width - 10;
		settingBtn.y = height - settingBtn.height - 10;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();
		betTable.drawDebug(this);
	}

}
