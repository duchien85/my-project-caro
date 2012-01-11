package com.gsn.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gsn.engine.layout.GsnTableLayout;

public abstract class GsnBoardStage extends Stage implements GsnPinchToZoom.ITouchUpWithoutZoom {

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
	GsnPinchToZoom pinch;
	protected Vector2 vector = new Vector2();

	final public String tag = GsnBoardStage.class.getSimpleName();

	public GsnBoardStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		localCam = new OrthographicCamera();
		GsnBoardStage.setViewport(localCam, width, height, stretch);
		localCam.update();
		globalCam = (OrthographicCamera) camera;
		pinch = new GsnPinchToZoom(this);
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

	abstract public boolean globalTouchUp(float x, float y, int pointer, int button);

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		pinch.keyDown(keycode);
		return super.keyDown(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		pinch.keyUp(keycode);
		return super.keyUp(keycode);
	}

	abstract public void localDraw(SpriteBatch batcher);

	abstract public boolean localTouchUp(float x, float y, int pointer, int button);

	public void setClickEffect(GsnParticleEffect effect) {
		this.clickEffect = effect;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		pinch.touchDown(x, y, pointer, button);
		return super.touchDown(x, y, pointer, button);
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		pinch.touchDragged(x, y, pointer);
		return super.touchDragged(x, y, pointer);
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		pinch.touchUp(x, y, pointer, button);
		return super.touchUp(x, y, pointer, button);
	}

	@Override
	public void touchUpWithoutZoom(int x, int y, int pointer, int button) {
		localCam.update();
		globalCam.update();
		camera = localCam;
		this.toStageCoordinates(x, y, vector);
		boolean checkGlobal = localTouchUp(vector.x, vector.y, pointer, button);
		camera = globalCam;
		if (checkGlobal) {
			super.touchUp(x, y, pointer, button);
			toStageCoordinates(x, y, vector);
			globalTouchUp(vector.x, vector.y, pointer, button);
		}
	}
}
