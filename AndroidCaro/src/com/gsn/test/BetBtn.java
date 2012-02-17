package com.gsn.test;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

public class BetBtn extends ImageButton {
	public int id;

	public BetBtn(int id, TextureRegion regionUp, TextureRegion regionDown, TextureRegion regionChecked) {
		super(regionUp, regionDown, regionChecked);
		this.id = id;
	}
}
