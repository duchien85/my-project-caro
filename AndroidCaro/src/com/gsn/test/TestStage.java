package com.gsn.test;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.GdxUtility;
import com.gsn.engine.myplay.GsnStage;
import com.vng.caro.CaroActivity;

public class TestStage extends GsnStage {
	AlertDialog alert;

	public TestStage(float width, float height) {
		super(width, height);
		// TODO Auto-generated constructor stub
		final CaroActivity caro = DataProvider.getInstance().caroActivity;
		final AlertDialog.Builder builder = new AlertDialog.Builder(caro);
		builder.setMessage("Are you sure you want to exit?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				caro.finish();
			}
		}).setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});

		caro.runOnUiThread(new Runnable() {
			public void run() {
				alert = builder.create();
			}
		});

		Label a = new Label("talksdfj asdf a", new Label.LabelStyle(ImageAsset.getInstance().font, new Color(0, 0, 1, 1)));

		// (BitmapFont font, Color fontColor, BitmapFont messageFont, Color
		// messageFontColor, NinePatch cursor,
		// TextureRegion selection, NinePatch background)
		ImageAsset asset = ImageAsset.getInstance();
		BitmapFont font = ImageAsset.getInstance().font;
		NinePatch cursor = new NinePatch(GdxUtility.convertListRegionToArray(asset.bubleChatOther9Path));
		NinePatch bg = new NinePatch(GdxUtility.convertListRegionToArray(asset.board9Path));
		TextureRegion region = asset.infoUserBG9Path.get(4);
		TextFieldStyle style = new TextFieldStyle(font, new Color(1, 0, 0, 1), font, new Color(0, 1, 0, 1), cursor, region, bg);

		final TextField text = new TextField("aaa", style);

		text.x = 100;
		text.y = 50;
		text.width = 300;
		text.height = 300;
		this.addActor(text);

		Button btn = new Button(asset.bet100_1);
		btn.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				System.out.println("text : " + text.getText());
				caro.runOnUiThread(new Runnable() {
					public void run() {
						alert.show();
					}
				});
			}
		});
		this.addActor(btn);
	}

}
