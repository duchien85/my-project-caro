package com.gsn.engine;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorUtility {
	public static void setCenter(Actor actor, float x, float y) {
		actor.x = x - actor.width / 2;
		actor.y = y - actor.height / 2;
	};

	public static void setTopRight(Actor actor, float x, float y) {
		actor.x = x - actor.width;
		actor.y = y - actor.height;
	}
}
