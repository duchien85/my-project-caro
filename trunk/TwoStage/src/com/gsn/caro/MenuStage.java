package com.gsn.caro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.GdxUtility;

public class MenuStage extends Stage {
	Vector2 vector = new Vector2();
	Image avatar;
	Image pieceO;
	TextButton tmp;

	public MenuStage(float width, float height) {
		super(width, height, false);

		Skin skin = new Skin(Gdx.files.internal("data/skin/uiskin.json"), Gdx.files.internal("data/skin/uiskin.png"));		

		final TextButton btn = new TextButton("Text Button", skin) {
			@Override
			public void touchUp(float x, float y, int pointer) {
				// TODO Auto-generated method stub
				if (GdxUtility.canHit(x, y, width, height)) {
					System.out.println("click Button TEXT");
				}
				super.touchUp(x, y, pointer);
			}
		};

		ImageButton imgBtn = new ImageButton(ImageAsset.getInstance().clock);
		
		imgBtn.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				System.out.println("haaaaaaaa");
			}
		});
		
		Table myTable = new Table();
		myTable.x = 100;
		myTable.y = 200;
		this.addActor(myTable);
		myTable.debug();
		myTable.add(imgBtn);
		myTable.row();
		myTable.add(btn);
	}
}
