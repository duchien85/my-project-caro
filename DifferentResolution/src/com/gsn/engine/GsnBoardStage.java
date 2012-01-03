package com.gsn.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class GsnBoardStage extends Stage{
	final public String tag = GsnBoardStage.class.getSimpleName();
	final protected Camera localCam;
	final protected Camera globalCam;
	
	protected Vector2 point = new Vector2();
	protected SpriteBatch myBatch = new SpriteBatch();
	
	public GsnBoardStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		localCam = new OrthographicCamera();
		GsnBoardStage.setViewport(localCam, width, height, stretch);
		localCam.update();
		globalCam = camera;
		// TODO Auto-generated constructor stub
	}			
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
		this.toStageCoordinates(x, y, point);				
		boolean checkGlobal = localTouchUp(point.x, point.y, pointer, button);
		camera = globalCam;
		if (checkGlobal)
			super.touchUp(x, y, pointer, button);			
		return true;				
	}
	
	public abstract void localDraw(SpriteBatch batcher);
	
	abstract public boolean localTouchUp(float x, float y, int pointer, int button);
	static public void setViewport (Camera camera, float width, float height, boolean stretch) {
		float thiswidth = 0f, thisheight = 0f;
		if (!stretch) {
			if (width > height && width / (float)Gdx.graphics.getWidth() <= height / (float)Gdx.graphics.getHeight()) {
				float toDeviceSpace = Gdx.graphics.getHeight() / height;
				float toViewportSpace = height / Gdx.graphics.getHeight();
	
				float deviceWidth = width * toDeviceSpace;
				thiswidth = width + (Gdx.graphics.getWidth() - deviceWidth) * toViewportSpace;
				thisheight = height;
			} else {
				float toDeviceSpace = Gdx.graphics.getWidth() / width;
				float toViewportSpace = width / Gdx.graphics.getWidth();
	
				float deviceHeight = height * toDeviceSpace;
				thisheight = height + (Gdx.graphics.getHeight() - deviceHeight) * toViewportSpace;
				thiswidth = width;
			}
		} else {
			thiswidth = width;
			thisheight = height;
		}
		
		float centerX = thiswidth / 2;
		float centerY = thisheight / 2;
	
		camera.position.set(centerX, centerY, 0);
		camera.viewportWidth = thiswidth;
		camera.viewportHeight = thisheight;
	}
}
