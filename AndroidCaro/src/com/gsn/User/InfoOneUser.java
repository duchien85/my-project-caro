package com.gsn.User;

import java.io.File;

import android.util.Log;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.GdxUtility;
import com.gsn.engine.ImageFactory;

public class InfoOneUser extends Group {
	float pad = 20;
	
	UserInfo info;
	BitmapFont font;
	Label labelName;
	Label labelGold;
	Label labelXu;
	Label labelPoint;
	Label labelWin;
	Label labelLose;
	Image background;
	Image avatar;
	Image avatarUser;
	Boolean loadAvatar = false;
	File fileAvatar;		
	public InfoOneUser(BitmapFont fontVoVan) {
		ImageAsset asset = ImageAsset.getInstance();
		avatar = new Image(asset.avatar);		
		info = new UserInfo();
		this.addActor(avatar);
		background = new Image(ImageAsset.getInstance().infoBg);
		//font = font;
		font = fontVoVan;
		labelName = new Label("", new Label.LabelStyle(font, new Color(0, 0, 0, 1)));
		//labelName.width = 80;
		//labelName.setWrap(true);
		labelName.setText(info.name);
		//labelName.setAlignment(0, Align.LEFT);
		
		labelGold = new Label("", new Label.LabelStyle(font, new Color(1, 1, 1, 1)));
		//labelGold.width = 80;
		//labelGold.setWrap(true);
		labelGold.setText("" + info.gold);
		//labelGold.setAlignment(0, Align.LEFT);
		
		labelXu = new Label("", new Label.LabelStyle(font, new Color(1, 1, 1, 1)));
		//labelXu.width = 80;
		//labelXu.setWrap(true);
		labelXu.setText("" + info.xu);
		//labelXu.setAlignment(0, Align.LEFT);
		
		labelPoint = new Label("", new Label.LabelStyle(font, new Color(1, 1, 1, 1)));
		//labelPoint.width = 80;
		//labelPoint.setWrap(true);
	//	labelPoint.setText("" + point);
		//labelPoint.setAlignment(0, Align.LEFT);
		
		labelWin = new Label("", new Label.LabelStyle(font, new Color(1, 1, 1, 1)));
		labelWin.setText("" + info.numWin);
	//	labelWin.setAlignment(0, Align.LEFT);
		
		labelLose = new Label("", new Label.LabelStyle(font, new Color(1, 1, 1, 1)));
		labelLose.setText("" + info.numLose);
		//labelLose.setAlignment(0, Align.LEFT);
		
		this.addActor(background);
		this.addActor(labelPoint);
		this.addActor(labelName);
		this.addActor(labelXu);
		this.addActor(labelGold);
		this.addActor(labelLose);
		this.addActor(labelWin);
		
		labelPoint.y = 4.3f*background.height/5f;
		labelGold.y = 3.4f*background.height/5f;
		labelXu.y = 2.5f*background.height/5f;
		
		labelLose.y = 1.8f*background.height/15f;
		labelWin.y = 3.9f*background.height/15f;
		
		labelName.x =  10;
		labelName.y = background.height + avatar.height - avatar.height / 10;
		
		avatar.y = background.height;
		this.width = avatar.width;
		this.height = avatar.height + background.height;
		
	}
	
	public void setInfoUser(String name, int gold, int xu, int point, int id, int numWin, int numLose, String avatarString)
	{
		info.name = GdxUtility.getShortName(name);
		info.gold = gold;
		info.xu = xu;
		info.exp = point;
		info.uid = id;
		info.numLose = info.numLose;
		info.numWin = info.numWin;
		
		ImageFactory.saveBitmapToFileAsync(avatarString, 64, 64, DataProvider.getInstance().caroActivity.getExternalCacheDir(), "me.png", new ImageFactory.IImageFactoryListener() {

			@Override
			public void onFinishLoading(File outFile) {
				// TODO Auto-generated method stub
				Log.i("load avatar", "done path : " + outFile.getAbsolutePath());
				loadAvatar = true;
				fileAvatar = outFile;								
			}

			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				Log.i("load avatar", " error ");
			}
		});
		
		setContentUser();
	}
	public void setContentUser()
	{
		labelGold.setText(GdxUtility.formatGold(info.gold)) ;
		labelXu.setText(GdxUtility.formatNumber(info.xu));
		labelPoint.setText(GdxUtility.formatNumber(info.exp));
		labelName.setText(info.name);
		labelWin.setText(GdxUtility.formatNumber(info.numWin));
		labelLose.setText(GdxUtility.formatNumber(info.numLose));
		
		
		labelPoint.x =  background.width / 3.5f;// + labelPoint.width / 2;
		labelGold.x = background.width / 3.5f;// + labelGold.width / 2;
		labelXu.x = background.width / 3.5f;// + labelXu.width / 2;
		labelName.x =  avatar.width / 2 - labelName.getTextBounds().width / 2;
		labelWin.x =  background.width / 1.6f;// + labelWin.width / 2;
		labelLose.x =  background.width / 2.0f;// + labelLose.width / 2;
		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
		if(loadAvatar)
		{
			loadAvatar = false;
			Texture text = new Texture(new FileHandle(fileAvatar));
			if(avatarUser!=null)
				avatarUser.remove();
			avatarUser = new Image(text);
		//	avatarUser.x
			this.addActor(avatarUser);
			avatarUser.x = 2*avatar.width/20;
			avatarUser.y = height - 17 * avatar.height / 20 ;
			avatar.width = 19 * avatar.height / 20;
			avatar.height = 19 * avatar.height / 20;
		}
	}
}
