/**
 * 
 */
package com.gsn.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gsn.caro.asset.AssetOld;
import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.ImageAsset;

/**
 * @author trungdv2
 *
 */
public class CaroGame extends Game {

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationListener#create()
	 */
	Stage currentStage;
	
	public void setStage(Stage stage ){
		this.currentStage = stage;		
	};
	PlayStage playStage;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		ImageAsset.getInstance().create();		
		AssetOld.getInstance().finishLoadingAll();		
		DataProvider.getInstance().clickEffect = ImageAsset.getInstance().clickEffect;		
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
		playStage = new PlayStage(width, height);
		Gdx.input.setInputProcessor(playStage.input);
		setStage(playStage);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		super.render();
		if (currentStage != null)
			currentStage.draw();
	}
}
