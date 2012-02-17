package com.gsn.test;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.gsn.caro.asset.ImageAsset;

public class InfoUserGroup extends Group {
	String name = "simo11";
	int gold;
	int xu;
	int point;
	Label labelName;
	Label labelGold;
	Label labelXu;
	Label labelPoint;
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
		avatarImage = new Image(ImageAsset.getInstance().avatarLobby);
		this.addActor(avatarImage);
		font = ImageAsset.getInstance().font;
		
		labelName = new Label("", new Label.LabelStyle(ImageAsset.getInstance().font, new Color(1, 1, 1, 1)));
		labelName.width = 80;
		labelName.setWrap(true);
		labelName.setText("Tên: " + name);
		labelName.setAlignment(0, Align.LEFT);
		
		labelGold = new Label("", new Label.LabelStyle(ImageAsset.getInstance().font, new Color(1, 1, 1, 1)));
		labelGold.width = 80;
		labelGold.setWrap(true);
		labelGold.setText("Gold: " + gold);
		labelGold.setAlignment(0, Align.LEFT);
		
		labelXu = new Label("", new Label.LabelStyle(ImageAsset.getInstance().font, new Color(1, 1, 1, 1)));
		labelXu.width = 80;
		labelXu.setWrap(true);
		labelXu.setText("Xu: " + xu);
		labelXu.setAlignment(0, Align.LEFT);
		
		labelPoint = new Label("", new Label.LabelStyle(ImageAsset.getInstance().font, new Color(0, 0, 0, 0)));
		labelPoint.width = 80;
		labelPoint.setWrap(true);
		labelPoint.setText("Điểm: " + point);
		labelPoint.setAlignment(0, Align.LEFT);
		
		this.addActor(labelPoint);
		this.addActor(labelName);
		this.addActor(labelXu);
		this.addActor(labelGold);
		
		labelPoint.x = this.x + 5 * avatarImage.width/9;
		labelGold.x = this.x + 5 * avatarImage.width/9;
		labelXu.x = this.x + 5 * avatarImage.width/9;
		
		labelPoint.y = this.y + 2.9f*avatarImage.height/4.5f;
		labelGold.y = this.y + 1.83f*avatarImage.height/4.5f;
		labelXu.y = this.y + 0.76f*avatarImage.height/4.5f;
		
		labelName.x = this.x + 5 * avatarImage.width/9;
		labelName.y = this.y + 4*avatarImage.height/4.5f;
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
