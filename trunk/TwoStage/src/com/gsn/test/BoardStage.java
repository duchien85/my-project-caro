package com.gsn.test;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.ImageAsset;

public class BoardStage extends Stage {
	ImageAsset asset;
	public BoardStage(float width, float height) {
		super(width, height, false);
		asset = ImageAsset.getInstance();
		Image board = new Image(asset.board);
		board.setClickListener(new SimpleClickListener("click Board"));
		this.addActor(board);
	}	
}
