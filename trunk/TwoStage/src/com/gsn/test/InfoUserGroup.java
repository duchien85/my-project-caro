package com.gsn.test;

import org.omg.CORBA.Bounds;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
		TextBounds bounds = font.getMultiLineBounds("tên : " + name);
		font.drawMultiLine(batch, "tên : " + name + "\n vàng: " + gold + "\n xu: " + xu + "\n điểm: " + point, this.x + avatarImage.height, this.y + avatarImage.height - bounds.height);
	}
}
