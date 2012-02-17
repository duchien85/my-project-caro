package com.gsn.test;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;

public class SimpleClickListener implements ClickListener {
	String string;

	public SimpleClickListener(String s) {
		this.string = s;
	}

	@Override
	public void click(Actor actor, float x, float y) {
		System.out.println(string + " : " + x + " " + y);
	}

}
