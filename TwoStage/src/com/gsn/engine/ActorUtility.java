package com.gsn.engine;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorUtility {
	public static boolean inActor(Vector2 v, Actor actor) {
		return inRange(v.x, actor.x, actor.x + actor.width) && inRange(v.y, actor.y, actor.y + actor.height);
	}

	private static boolean inRange(float x, float a, float b) {
		return (a <= x) && (x <= b);
	};

	public static void setCenter(Actor actor, float x, float y) {
		actor.x = x - actor.width / 2;
		actor.y = y - actor.height / 2;
	}

	public static void setTopRight(Actor actor, float x, float y) {
		actor.x = x - actor.width;
		actor.y = y - actor.height;
	}
}
