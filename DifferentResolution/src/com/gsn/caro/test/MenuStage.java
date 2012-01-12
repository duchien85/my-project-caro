package com.gsn.caro.test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.ImageAsset;

public class MenuStage extends Stage {
	Vector2 vector = new Vector2();
	Image avatar;
		
	public MenuStage(float width, float height) {
		super(width, height, false);
		// TODO Auto-generated constructor stub		
		avatar = new Image(ImageAsset.getInstance().avatar);
		addActor(avatar);
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub;		
		this.toStageCoordinates(x, y, vector);
		Actor actor = this.hit(vector.x, vector.y);
		if (actor == avatar){
			System.out.println("click AVATAR");
		}
		
		return super.touchDown(x, y, pointer, button);
	}

}
