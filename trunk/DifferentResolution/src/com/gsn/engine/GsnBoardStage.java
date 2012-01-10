package com.gsn.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gsn.engine.layout.GsnTableLayout;

public abstract class GsnBoardStage extends Stage {
	static public void setViewport(Camera camera, float width, float height, boolean stretch) {
		float thiswidth = 0f, thisheight = 0f;
		if (!stretch) {
			if (width > height && width / (float) Gdx.graphics.getWidth() <= height / (float) Gdx.graphics.getHeight()) {
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

	protected GsnParticleEffect clickEffect;
	final protected OrthographicCamera globalCam;

	final protected OrthographicCamera localCam;
	protected SpriteBatch myBatch = new SpriteBatch();
	protected Vector2 point = new Vector2();

	final public String tag = GsnBoardStage.class.getSimpleName();

	public GsnBoardStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		localCam = new OrthographicCamera();
		GsnBoardStage.setViewport(localCam, width, height, stretch);
		localCam.update();
		globalCam = (OrthographicCamera) camera;
	}

	protected GsnTableLayout createMenuLayout(boolean isRatio, float rHeight) {
		float h;
		if (isRatio)
			h = height * rHeight;
		else
			h = rHeight;
		return new GsnTableLayout(0, height - h, width, h);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		localCam.update();
		globalCam.update();
		super.draw();
		camera = localCam;
		myBatch.begin();
		localDraw(myBatch);
		myBatch.end();
		camera = globalCam;

		if (clickEffect != null) {
			clickEffect.drawNow(Gdx.graphics.getDeltaTime());
		}
	}

	abstract public void localDraw(SpriteBatch batcher);

	abstract public boolean localTouchUp(float x, float y, int pointer, int button);

	abstract public boolean globalTouchUp(float x, float y, int pointer, int button);

	public void setClickEffect(GsnParticleEffect effect) {
		this.clickEffect = effect;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		System.out.println(" touch up : " + x + " " + y + " " + pointer);
		if (numberOfFingers == 1) {
			Vector3 touchPoint = new Vector3(x, y, 0);
			globalCam.unproject(touchPoint);
		}
		numberOfFingers--;

		// just some error prevention... clamping number of fingers (ouch! :-)
		if (numberOfFingers < 0) {
			numberOfFingers = 0;
		}

		lastDistance = 0;
		// /xong phan pinch
		localCam.update();
		globalCam.update();
		camera = localCam;
		this.toStageCoordinates(x, y, point);
		boolean checkGlobal = localTouchUp(point.x, point.y, pointer, button);
		camera = globalCam;
		if (checkGlobal) {
			super.touchUp(x, y, pointer, button);
			toStageCoordinates(x, y, point);
			globalTouchUp(point.x, point.y, pointer, button);
		}
		return true;
	}

	int numberOfFingers = 0;
	int fingerOnePointer;
	int fingerTwoPointer;
	float lastDistance = 0;
	Vector3 fingerOne = new Vector3();
	Vector3 fingerTwo = new Vector3();	
	GsnMultiTouchSimulator multiTouch = new GsnMultiTouchSimulator(this);
	public void pause() {
		numberOfFingers = 0;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		// for pinch-to-zoom
		System.out.println(" touch down : " + x + " " + y + " " + pointer);
		numberOfFingers++;
		if (numberOfFingers == 1) {
			fingerOnePointer = pointer;
			fingerOne.set(x, y, 0);
		} else if (numberOfFingers == 2) {
			fingerTwoPointer = pointer;
			fingerTwo.set(x, y, 0);
			float distance = fingerOne.dst(fingerTwo);
			lastDistance = distance;
		}
		return super.touchDown(x, y, pointer, button);
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub\
		// for pinch-to-zoom
		System.out.println(" touch drag : " + x + " " + y + " " + pointer);
		if (pointer == fingerOnePointer) {
			fingerOne.set(x, y, 0);
		}
		if (pointer == fingerTwoPointer) {
			fingerTwo.set(x, y, 0);
		}
  
		float distance = fingerOne.dst(fingerTwo);

		if (lastDistance != 0 && distance != 0) {
			float zoom = globalCam.zoom * lastDistance / distance;			
			globalCam.zoom = zoom;			
			System.out.println("zoom : " + zoom);
			// setZoomCamera(zoom);
		}

		// clamps field of view to common angles...
		// if (cam.fieldOfView < 10f) cam.fieldOfView = 10f;
		// if (cam.fieldOfView > 120f) cam.fieldOfView = 120f;
		lastDistance = distance;
		globalCam.update();
		return super.touchDragged(x, y, pointer);
	}
	
	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		multiTouch.keyUp(keycode);
		return super.keyUp(keycode);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		multiTouch.keyDown(keycode);
		return super.keyDown(keycode);
	}
}
