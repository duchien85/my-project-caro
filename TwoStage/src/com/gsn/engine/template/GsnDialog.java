package com.gsn.engine.template;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.ActorUtility;

public class GsnDialog extends Group {
	Label contentLabel;
	public GsnDialog(Image background, ImageButton okButton, ImageButton cancelButton) {
		super();
		
		this.width = background.width;
		this.height = background.height;
		this.addActor(background);
		if(cancelButton==null)
		{
			ActorUtility.setCenterBottom(okButton, this.width / 2, 0);
			this.addActor(okButton);
		}
		else
		{
			Table table = new Table();
			table.add(okButton).width(String.valueOf((int)this.width/2));
			table.add(cancelButton).width(String.valueOf((int)this.width/2));
			this.addActor(table);
			ActorUtility.setCenterBottom(table, this.width/2, (int)this.height/6);
		}
		
		//this.addActor(okButton);
		
		//BitmapFont font = ImageAsset.getInstance().font;
		contentLabel = new Label("", new Label.LabelStyle(ImageAsset.getInstance().font, new Color(1, 1, 1, 1)));
		contentLabel.setAlignment(Align.CENTER);
		this.addActor(contentLabel);
		contentLabel.x = this.width/2 - contentLabel.getTextBounds().width/2;
		contentLabel.y = this.height/1.5f;
		//ActorUtility.setCenter(contentLabel, this.width/2, this.height/2);
	}
	
	public void setContent(String text)
	{
		contentLabel.setText(text);
	}
	
}
