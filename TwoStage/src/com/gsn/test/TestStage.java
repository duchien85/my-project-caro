package com.gsn.test;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.myplay.GsnStage;

public class TestStage extends GsnStage {

	public TestStage(float width, float height) {
		super(width, height);
		// TODO Auto-generated constructor stub
		Label a = new Label("talksdfj asdf a", new Label.LabelStyle(ImageAsset.getInstance().font, new Color(1, 0, 0, 1)));
		this.addActor(a);

	}

}
