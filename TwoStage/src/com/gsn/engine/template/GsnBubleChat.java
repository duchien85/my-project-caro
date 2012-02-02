package com.gsn.engine.template;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.Remove;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.gsn.caro.asset.ImageAsset;

public class GsnBubleChat extends Group {			
	float pad = 10;
	Image chatBG;
	Label label;

	public GsnBubleChat(NinePatch ground, BitmapFont font) {
		// TODO Auto-generated constructor stub
		super();
		chatBG = new Image(ground);		
		label = new Label("", new Label.LabelStyle(font, new Color(1, 1, 1, 1)));
		
		this.addActor(chatBG);
		this.addActor(label);		
	}
	
	public void setText(String text, int width){
		label.setText(text);
		label.width = width;		
		label.setWrap(true);
		label.setAlignment(0, Align.CENTER);		
		TextBounds bounds =  label.getTextBounds();
		//System.out.println("text bound" + bounds.width + " " + bounds.height);		
		label.y = bounds.height / 2 + pad;
		label.x = pad;
		chatBG.width = bounds.width + 2 * pad;
		chatBG.height = bounds.height + 2 * pad;
	}

	public void removeFuture(float period) {
		this.action(Delay.$(Remove.$(), period));
	}	
}
