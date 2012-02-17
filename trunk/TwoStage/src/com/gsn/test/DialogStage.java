package com.gsn.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.gsn.caro.asset.AssetOld;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.myplay.GsnStage;
import com.gsn.engine.template.GsnDialog;

public class DialogStage extends GsnStage {		
	
	ImageButton musicButton;
	ImageButton soundButton;
	ImageButton exitButton;
	GsnDialog dialog;
	GsnStage parent;
	String type;
	public DialogStage(float width, float height) {
		super(width, height);
		
		Image screenBG = new Image(ImageAsset.getInstance().greyBG);
		screenBG.width = this.width;
		screenBG.height = this.height;
		
		Image dialogBG = new Image(AssetOld.getInstance().dialogBg);
		ImageButton okBtn = new ImageButton(AssetOld.getInstance().okBtn,AssetOld.getInstance().okBtnDown);
		ImageButton cancelBtn = new ImageButton(AssetOld.getInstance().cancelBtn,AssetOld.getInstance().cancelBtnDown);
		
		

		okBtn.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				//DialogStage.this.parent.hideChild();
				if(type=="LOBBY")
				{
					
				}
				else
				{
					PlayStage p = (PlayStage)(DialogStage.this.parent);
					p.game.setLobbyStage();
				}
				dialog.remove();
			}
		});
		cancelBtn.setClickListener(new ClickListener() {

			@Override
			public void click(Actor actor, float x, float y) {
				//DialogStage.this.parent.hideChild();
				dialog.remove();
			}
		});
		
		dialog = new GsnDialog(dialogBG, okBtn, cancelBtn);
		ActorUtility.setCenter(dialog, this.width / 2, this.height / 2);
		musicButton = new ImageButton(ImageAsset.getInstance().musicOn, ImageAsset.getInstance().musicClick, ImageAsset.getInstance().musicOff);
		soundButton = new ImageButton(ImageAsset.getInstance().soundOn, ImageAsset.getInstance().soundClick, ImageAsset.getInstance().soundOff);
		exitButton = new ImageButton(ImageAsset.getInstance().exitNormal, ImageAsset.getInstance().exitClick);
		dialog.setContent("Bạn có thực sự muốn thoát khỏi \nphòng chơi không?");
		musicButton.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				// chuyen trang thai music
				//DialogStage.this.parent.hideDialog();
			}
		});
		
		soundButton.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				
				// TODO Auto-generated method stub
				// chuyen trang thai sound
				//DialogStage.this.parent.hideDialog();
			}
		});
		
		exitButton.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				
				// TODO Auto-generated method stub
				// chuyen trang thai sound
				DialogStage.this.addActor(dialog);
				//DialogStage.this.parent.hideDialog();
				//DialogStage.this.parent.game.setLobbyStage();
				
			}
		});
		
		this.addActor(screenBG);
		//this.addActor(dialog);
		this.addActor(musicButton);
		this.addActor(soundButton);
		
		//musicButton.x = this.width / 2;
		
		Table table = new Table();
		this.addActor(table);
		table.add(musicButton).width(String.valueOf((int)screenBG.width/4));
		table.add(soundButton).width(String.valueOf((int)screenBG.width/4));
		table.row();
		table.add(exitButton).colspan(8);
		
		ActorUtility.setCenter(table, this.width /2 , this.height / 2);
		//ActorUtility.setCenter(soundButton, this.width /2 + soundButton.width, this.height / 2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		if(!super.touchUp(x, y, pointer, button))
		{
			DialogStage.this.parent.hideChild();
		}
		return true;
	}

	@Override
	public void setInputListener() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(this);
	}
	
	public void setParent(GsnStage parent, String type)
	{
		this.parent = parent;
		this.type = type;
	}
}
