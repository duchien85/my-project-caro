package com.gsn.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class GsnStage extends Stage{
	final public String tag = GsnStage.class.getSimpleName();
	final protected Camera localCam;
	final protected Camera globalCam;
	
	protected Vector2 point = new Vector2();
	protected SpriteBatch myBatch = new SpriteBatch();
	
	public GsnStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		localCam = new OrthographicCamera();
		GsnUtility.setViewport(localCam, width, height, stretch);
		localCam.update();
		globalCam = camera;
		// TODO Auto-generated constructor stub
	}		
	
//	@Override
//	public void setViewport(float width, float height, boolean stretch) {
//		// TODO Auto-generated method stub
//		super.setViewport(width, height, stretch);
//		if (localCam != null)
//			GsnUtility.setViewport(localCam, width, height, stretch);
//		globalCam = camera;
//	}
	@Override
	public void draw() {
		// TODO Auto-generated method stub		
		super.draw();		
		camera = localCam;		
		myBatch.begin();
		localDraw(myBatch);
		myBatch.end();
		camera = globalCam;
	}
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		camera = localCam;
		Gdx.app.log(tag, " trc convert : " + x + " " + y);
		this.toStageCoordinates(x, y, point);
		
		Gdx.app.log(tag, " sau convert : " + point.x + " " + point.y);
		Gdx.app.log(tag, " - -----------------  ");
		boolean checkGlobal = localTouchUp(point.x, point.y, pointer, button);
		camera = globalCam;
		if (checkGlobal)
			super.touchUp(x, y, pointer, button);			
		return true;				
	}
	
	public abstract void localDraw(SpriteBatch batcher);
	
	abstract public boolean localTouchUp(float x, float y, int pointer, int button);
}
