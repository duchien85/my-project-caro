package com.gsn.caro;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.caro.asset.ImageAsset;

public class BoardStage extends Stage {
	Vector2 vector = new Vector2();
	Image avatar;
	public BoardStage(float width, float height) {
		super(width, height, false);
		ImageButton imgBtn = new ImageButton(ImageAsset.getInstance().clock);
		
		imgBtn.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				System.out.println("KAKAKAKA");
			}
		});
		
		this.addActor(imgBtn);
	
	}	
}
