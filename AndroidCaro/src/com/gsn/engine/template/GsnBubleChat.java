package com.gsn.engine.template;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.Remove;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

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
		TextBounds bounds =  label.getTextBounds();
		//System.out.println("text bound" + bounds.width + " " + bounds.height);		
		label.y = bounds.height / 2 + pad;
		label.x = (width + 2 * pad) / 2 - width / 2;
		chatBG.width = label.width + 2 * pad;
		chatBG.height = bounds.height + 3 * pad;
		this.height = chatBG.height;
		this.width = (int) chatBG.width;
	}

	public void removeFuture(float period) {
		this.action(Delay.$(Remove.$(), period));
	}	
}
