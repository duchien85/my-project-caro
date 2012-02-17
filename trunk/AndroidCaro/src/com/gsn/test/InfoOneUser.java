package com.gsn.test;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.gsn.caro.asset.ImageAsset;

public class InfoOneUser extends Group {
	float pad = 20;
	
	String name = "simo11";
	int gold;
	int xu;
	int point;
	BitmapFont font;
	
	public InfoOneUser() {
		ImageAsset asset = ImageAsset.getInstance();
		Image avatar = new Image(asset.avatar);		
		
		this.addActor(avatar);
		
		font = ImageAsset.getInstance().font;
		Label label = new Label("talksdfj\n asdf an safkl nsdf", new Label.LabelStyle(ImageAsset.getInstance().font, new Color(1, 1, 1, 1)));
		//label.width = 80;
		//label.setWrap(true);
		label.setText("Tên: " + name + "\n" + "Gold: " + gold + "\n" + "Xu: " + xu + "\n" + "Điểm:" + point);
		label.setAlignment(0, Align.LEFT);

		// TextBounds bounds = a.getTextBounds();
		// System.out.println("text bound" + bounds.width + " " +
		// bounds.height);
		// a.pack();
		this.addActor(label);
		//label.x = this.x + label.width;
		label.y = label.height / 2 + pad;
		avatar.y = label.height + 3 * pad;
		this.width = avatar.width;
		this.height = avatar.height + 5*pad + label.height;
	}
}
