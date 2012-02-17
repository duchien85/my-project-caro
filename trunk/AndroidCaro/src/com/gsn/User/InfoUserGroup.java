package com.gsn.User;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.GdxUtility;

public class InfoUserGroup extends Group {
	public UserInfo info;
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
	Image avatar;
	public InfoUserGroup() {
		// TODO Auto-generated constructor stub
		avatarImage = new Image(ImageAsset.getInstance().avatarLobby);
		this.addActor(avatarImage);
		info = new UserInfo();
		font = ImageAsset.getInstance().font;
		
		labelName = new Label("", new Label.LabelStyle(ImageAsset.getInstance().font, new Color(0, 1, 0, 1)));
		//labelName.width = 80;
		//labelName.setWrap(true);
		labelName.setText(info.name);
		labelName.setAlignment(0, Align.LEFT);
		
		labelGold = new Label("", new Label.LabelStyle(ImageAsset.getInstance().font, new Color(0, 1, 0, 1)));
		//labelGold.width = 80;
		//labelGold.setWrap(true);
		labelGold.setText("" + info.gold);
		labelGold.setAlignment(0, Align.LEFT);
		
		labelXu = new Label("", new Label.LabelStyle(ImageAsset.getInstance().font, new Color(0, 1, 0, 1)));
		labelXu.setText("" + info.xu);
		labelXu.setAlignment(0, Align.LEFT);
		
		labelPoint = new Label("", new Label.LabelStyle(ImageAsset.getInstance().font, new Color(0, 1, 0, 1)));
		labelPoint.setText("" + info.exp);
		labelPoint.setAlignment(0, Align.LEFT);
		
		
		
		this.addActor(labelPoint);
		this.addActor(labelName);
		this.addActor(labelXu);
		this.addActor(labelGold);
		
		labelPoint.y =  + 2.9f*avatarImage.height/4.5f;
		labelGold.y = + 1.83f*avatarImage.height/4.5f;
		labelXu.y = 0.76f*avatarImage.height/4.5f;
		
		labelName.x =  5.2f * avatarImage.width/9;
		labelName.y = 4*avatarImage.height/4.5f;
	}

	public void setContent(String name, int point, int gold, int xu) {
		info.name = GdxUtility.getShortName(name);
		info.exp = point;
		info.gold = gold;
		info.xu = xu;
		labelGold.setText(GdxUtility.formatGold(gold));
		labelPoint.setText(GdxUtility.formatNumber(point));
		labelXu.setText(GdxUtility.formatNumber(xu));
		labelName.setText(info.name);
		labelName.x =  5.2f * avatarImage.width/9 + labelName.getTextBounds().width / 2;
		labelPoint.x =  5.2f * avatarImage.width/9 + labelPoint.getTextBounds().width / 2;
		labelGold.x = 5.2f * avatarImage.width/9  + labelGold.getTextBounds().width / 2;
		labelXu.x = 5.2f * avatarImage.width/9  + labelXu.getTextBounds().width / 2;
	}
	
	public void setAvatar(Texture ava)
	{
		if(avatar!=null)
			avatar.remove();
		avatar = new Image(ava);
		this.addActor(avatar);
		avatar.width = avatarImage.height;
		avatar.height = avatarImage.height;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
		// new Label("trung", style)
		// TextBounds bounds = font.getMultiLineBounds("tÃƒÆ’Ã‚Âªn : " + name);
		// font.drawMultiLine(batch, "tÃƒÆ’Ã‚Âªn : " + name + "\n vÃƒÆ’Ã‚Â ng: " + gold +
		// "\n xu: " + xu + "\n Ãƒâ€žÃ¢â‚¬ËœiÃƒÂ¡Ã‚Â»Ã†â€™m: " + point, this.x + avatarImage.height,
		// this.y + avatarImage.height - bounds.height);
	}
}
