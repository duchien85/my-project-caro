package com.gsn.caro;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.GsnBoardStage;
import com.gsn.engine.GsnUtility;
import com.gsn.engine.layout.GsnMenuLayout;

public class MenuTestStage extends GsnBoardStage {
	GsnMenuLayout layout;
	ImageAsset asset = ImageAsset.getInstance();
	Sprite meAvatar;
	Sprite youAvatar;
	
	public MenuTestStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		layout = new GsnMenuLayout(0, height - 200, width, 50, 1, 4);
		
		meAvatar = new Sprite(asset.avatar);
		GsnUtility.scaleSprite(meAvatar, layout.recArr[0][0]);
		GsnUtility.setCenterSprite(meAvatar, layout.recArr[0][0].getCenter());
		System.out.println(layout.recArr[0][0] + " : " + layout.recArr[0][0].getCenter());
		
		
		youAvatar = new Sprite(asset.avatar);
		youAvatar.setColor(0.2f, 1f, 1f, 1f);
		GsnUtility.setCenterSprite(youAvatar, layout.recArr[0][3].getCenter());
		System.out.println(layout.recArr[0][3] + " : " + layout.recArr[0][3].getCenter());		
	}

	@Override
	public void localDraw(SpriteBatch batcher) {
		// TODO Auto-generated method stub			
		meAvatar.draw(batcher);
		youAvatar.draw(batcher);
	}

	@Override
	public boolean localTouchUp(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean globalTouchUp(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

}
