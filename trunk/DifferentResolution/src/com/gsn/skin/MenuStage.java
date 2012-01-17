package com.gsn.skin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
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

		Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"), Gdx.files.internal("data/uiskin.png"));
		Table myTable = new Table();
		myTable.x = 0;
		myTable.y = 100;
		this.addActor(myTable);

		final TextButton btn = new TextButton("Text Button", skin) {
			@Override
			public void touchUp(float x, float y, int pointer) {
				// TODO Auto-generated method stub
				if (GdxUtility.canHit(x, y, width, height)) {
					System.out.println("click Button");
				} else {
					System.out.println("DONT click Button");
				}
				super.touchUp(x, y, pointer);
			}
		};

		ImageButton imgBtn = new ImageButton(ImageAsset.getInstance().avatar) {
			@Override
			public void touchUp(float x, float y, int pointer) {
				// TODO Auto-generated method stub
				if (GdxUtility.canHit(x, y, width, height)) {
					System.out.println("click Button");
				} else {
					System.out.println("DONT click Button");
				}
				super.touchUp(x, y, pointer);
			}
		};

		myTable.debug();
		myTable.add(new Label("meow", skin)).colspan(6);
		myTable.row();
		myTable.add(btn);
		// myTable.row();
		myTable.add(new TextButton("Toggle Button", skin.getStyle("toggle", TextButtonStyle.class)));
		// myTable.row();
		myTable.add(imgBtn);
		myTable.row();
		myTable.add(new CheckBox("meow", skin));
		myTable.pack();

	}

}
