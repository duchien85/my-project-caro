package com.gsn.engine.layout;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gsn.engine.GsnUtility;

public abstract class GsnSprite extends InputAdapter{
	protected Rectangle bound = new Rectangle();

	abstract public void draw(SpriteBatch batcher);

	public float getHeight() {
		return bound.getHeight();
	}

	public float getWidth() {
		return bound.getWidth();
	}

	public float getX() {
		return bound.x;
	}

	public float getY() {
		return bound.y;
	}
	
	public float getMiddleX() {
		return bound.x + bound.getWidth()/2;
	}

	public float getMiddleY() {
		return bound.y + bound.getHeight()/2;
	}	

	public static Vector2 getCenter(GsnSprite sprite) {
		return new Vector2(sprite.getMiddleX(), sprite.getMiddleY());
	}

	private static void _setCenter(GsnSprite sprite, float x, float y) {
		Vector2 cen = getCenter(sprite);
		sprite.setPosition(x - cen.x, y - cen.y);
	}

	public void setCenter(float x, float y) {
		_setCenter(this, x, y);
	}

	public void setPosition(float x, float y) {
		bound.setX(x);
		bound.setY(y);
	}
	
	public void setCenter(GsnPoint center){
		setCenter(center.x, center.y);
	}
	public Rectangle getBound() {
		return bound;
	}

	public boolean isTouchIn(float x, float y) {
		return GsnUtility.pointInRectangle(bound, x, y);
	}
}
