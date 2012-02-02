package com.gsn.engine.template;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.Remove;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.ImageAsset;

public class GsnBubleChat extends Image {
	BitmapFont font;
	String text;
	float widthText = 50;
	float pad = 10;

	public GsnBubleChat(NinePatch ground, BitmapFont font) {
		// TODO Auto-generated constructor stub
		super(ground);
		this.font = font;
		setText("chưa đặt text");
		setWidthText(widthText);
	}

	public void setWidthText(float widthText) {
		this.widthText = widthText;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setPad(float pad) {
		this.pad = pad;
	}

	public void pack() {
		TextBounds bound = font.getWrappedBounds(text, widthText);
		this.width = bound.width + pad * 2;
		this.height = bound.height + pad * 2;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
		font.drawWrapped(batch, text, this.x + pad, this.y + this.height - pad, widthText, HAlignment.CENTER);
	}

	public void removeFuture(float period) {
		this.action(Delay.$(Remove.$(), period));
	}

	float time;

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		time += delta;
		if (time > 1)
			this.setRegion(ImageAsset.getInstance().backActiveBtn);
		// System.out.println("act buble chat... " + time);
	}
}
