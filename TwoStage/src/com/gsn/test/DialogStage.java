package com.gsn.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.AssetOld;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.myplay.GsnStage;

public class DialogStage extends GsnStage {
	ShapeRenderer shapeRender = new ShapeRenderer();
	Image dialogBG;
	final PlayStage parent;

	public DialogStage(PlayStage parent, float width, float height) {
		super(width, height);
		this.parent = parent;

		dialogBG = new Image(AssetOld.getInstance().dialogBg);
		ActorUtility.setCenter(dialogBG, width / 2, height / 2);

		dialogBG.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				DialogStage.this.parent.hideDialog();
			}
		});
		Image background = new Image(AssetOld.getInstance().bbChat.get(4));
		background.width = this.width;
		background.height = this.height;
		this.addActor(background);
		this.addActor(dialogBG);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setInputListener() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();
	}
}
