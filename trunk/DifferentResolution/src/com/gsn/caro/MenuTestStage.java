package com.gsn.caro;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.GsnBoardStage;
import com.gsn.engine.layout.GsnRectangle;
import com.gsn.engine.layout.GsnTableLayout;

public class MenuTestStage extends GsnBoardStage {
	GsnTableLayout layout;
	ImageAsset asset = ImageAsset.getInstance();
	List<Sprite> list = new ArrayList<Sprite>();
	
	public MenuTestStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		layout = new GsnTableLayout(0, 0, this.width, 200);
		layout.newRow(0.3f);
		layout.add(0.3f);
		layout.add(0.7f);
		layout.newRow(0.7f);
		layout.add(0.2f);
		layout.add(0.4f);
		layout.add(0.4f);
		
		for (GsnRectangle rect : layout.list){
			Sprite sprite = new Sprite(asset.avatar);
			rect.scaleAndPutSprite(sprite);
			list.add(sprite);
			System.out.println("rect   : " + rect);
			System.out.println("sprite : " + GsnRectangle.toString(sprite));
			System.out.println("------------------------");
		}
		
		for (Sprite sprite : list){
			System.out.println("sprite : " + GsnRectangle.toString(sprite));
		}
			
	}

	@Override
	public void localDraw(SpriteBatch batcher) {
		// TODO Auto-generated method stub
		for (Sprite sprite : list)
			sprite.draw(batcher);
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
