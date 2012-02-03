package com.gsn.test;

import org.omg.CORBA.Bounds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.gsn.caro.asset.AssetOld;
import com.gsn.caro.asset.ImageAsset;

public class InfoUserGroup extends Group {
	String name = "simo11";
	int gold;
	int xu;
	int point;
	BitmapFont font;

	@Override
	public void addActor(Actor actor) {
		// TODO Auto-generated method stub
		super.addActor(actor);
		this.height = Math.max(height, actor.height);
	}

	Image avatarImage;

	public InfoUserGroup() {
		// TODO Auto-generated constructor stub
		avatarImage = new Image(AssetOld.getInstance().avatarLobby);
		this.addActor(avatarImage);
		font = ImageAsset.getInstance().font;
		Label label = new Label("talksdfj\n asdf an safkl nsdf", new Label.LabelStyle(ImageAsset.getInstance().font, new Color(1, 1, 1, 1)));
		label.width = 80;
		label.setWrap(true);
		label.setText("Tên: " + name + "\n" + "Gold: " + gold + "\n" + "Xu: " + xu + "\n" + "Điểm: " + point);
		label.setAlignment(0, Align.LEFT);

		// TextBounds bounds = a.getTextBounds();
		// System.out.println("text bound" + bounds.width + " " +
		// bounds.height);
		// a.pack();
		this.addActor(label);
		label.x = this.x + label.width;
		label.y = this.y + label.height;

	}

	public void setContent(String name, int point, int gold, int xu) {
		this.name = name;
		this.point = point;
		this.gold = gold;
		this.xu = xu;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
		// new Label("trung", style)
		// TextBounds bounds = font.getMultiLineBounds("tÃªn : " + name);
		// font.drawMultiLine(batch, "tÃªn : " + name + "\n vÃ ng: " + gold +
		// "\n xu: " + xu + "\n Ä‘iá»ƒm: " + point, this.x + avatarImage.height,
		// this.y + avatarImage.height - bounds.height);
	}
}
