package com.gsn.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.caro.asset.AssetOld;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.myplay.GsnStage;
import com.gsn.engine.template.GsnDialog;

public class DialogStage extends GsnStage {		
	final PlayStage parent;

	public DialogStage(PlayStage parent, float width, float height) {
		super(width, height);
		this.parent = parent;
		Image screenBG = new Image(ImageAsset.getInstance().greyBG);
		screenBG.width = this.width;
		screenBG.height = this.height;
		
		Image dialogBG = new Image(AssetOld.getInstance().dialogBg);
		ImageButton okBtn = new ImageButton(AssetOld.getInstance().okBtn,AssetOld.getInstance().okBtnDown);
		GsnDialog dialog = new GsnDialog(dialogBG, okBtn);
		
		ActorUtility.setCenter(dialog, this.width / 2, this.height / 2);

		okBtn.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				DialogStage.this.parent.hideDialog();
			}
		});
		
		this.addActor(screenBG);
		this.addActor(dialog);		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setInputListener() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(this);
	}
}
