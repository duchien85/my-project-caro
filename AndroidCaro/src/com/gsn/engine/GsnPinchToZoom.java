package com.gsn.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GsnPinchToZoom {
	public static interface ITouchUpWithoutZoomListener {
		void touchUpWithoutZoom(int x, int y, int pointer, int button);
	}

	private static boolean inRange(float x, float a, float b) {
		return (a <= x) && (x <= b);
	}

	private final int BUTTON = 0;
	OrthographicCamera camera;
	float defaultX = 0;
	float defaultY = 0;
	float defaultZoom = 1f;
	Vector3 fingerOne = new Vector3();
	int fingerOnePointer;
	Vector3 fingerTwo = new Vector3();
	int fingerTwoPointer;
	boolean isDragged = false;
	boolean isPinched = false;
	float lastDistance = 0;
	float maxX = 50;

	float maxY = 100;

	float maxZoom = 5f;

	float minX = -50;

	float minY = -100;
	float minZoom = 0.2f;
	int numberOfFingers = 0;

	float oldDownX, oldDownY;
	float oldX, oldY;
	// / simulator multi touch
	private final int POINTER = 2;

	private boolean pressAlt = false;
	Stage stage;
	float tolerant = 7;

	ITouchUpWithoutZoomListener touchUpListener;

	Vector2 vector = new Vector2();
	
	float rootWidth;
	float rootHeight;
	
	float stageWidth;
	float stageHeight;
	private float menuHeight;
	
	public GsnPinchToZoom(Stage gsnStage) {
		this.stage = gsnStage;
		this.camera = (OrthographicCamera) this.stage.getCamera();
		resetCamera();
	}

	public void keyDown(int keycode) {
		switch (keycode) {
		case Input.Keys.ALT_LEFT:
			if (!pressAlt) {
				int x = Gdx.graphics.getWidth() / 2;
				int y = Gdx.graphics.getHeight() / 2;
				pressAlt = true;
				stage.touchDown(x, y, POINTER, BUTTON);
			}
			break;
		}
	}

	public void keyUp(int keycode) {
		switch (keycode) {
		case Input.Keys.ALT_LEFT:
			int x = Gdx.graphics.getWidth() / 2;
			int y = Gdx.graphics.getHeight() / 2;
			pressAlt = false;
			stage.touchUp(x, y, POINTER, BUTTON);
			break;
		}
	}

	public void pause() {
		reset();
	}

	public void reset() {
		numberOfFingers = 0;
		lastDistance = 0;
		isPinched = false;
		isDragged = false;
	}

	public void resetCamera() {
		camera.position.set(defaultX, defaultY, 0);
		camera.zoom = defaultZoom;
	}

	public void setRangeZoom(float minZoom, float maxZoom, float defaulZoom) {
		this.minZoom = minZoom;
		this.maxZoom = maxZoom;
		this.defaultZoom = defaulZoom;
		resetCamera();
	}
	
	public void setRangeX(float minX, float maxX, float defaultX){
		this.minX = minX;
		this.maxX = maxX;
		this.defaultX = defaultX;
	}
	
	public void setRangeY(float minY, float maxY, float defaultY){
		this.minY = minY;
		this.maxY = maxY;
		this.defaultY = defaultY;
	}

	public void setTouchUpWithoutZoomListener(ITouchUpWithoutZoomListener listener) {
		this.touchUpListener = listener;
	}

	private void setZoomCamera(float zoom) {
		if (inRange(zoom, minZoom, maxZoom)) {
			
			float tMinX, tMinY, tMaxX, tMaxY;
			tMinX = (rootWidth / 2 - (stageWidth / 2 / zoom)) * zoom;
			tMaxX = ((stageWidth/2 / zoom) - rootWidth/2) * zoom;
			tMaxY = (stageHeight / 2 / zoom - rootHeight / 2 + menuHeight) * zoom;
			tMinY = (rootHeight / 2 - stageHeight / 2 /zoom) * zoom;
			if(inRange(camera.position.x, tMinX, tMaxX) && inRange(camera.position.y, tMinY, tMaxY))
			{
				camera.zoom = zoom;	
				minX = (rootWidth / 2 - (stageWidth / 2 / zoom)) * zoom;
				maxX = ((stageWidth/2 / zoom) - rootWidth/2) * zoom;
				maxY = (stageHeight / 2 / zoom - rootHeight / 2 + menuHeight) * zoom;
				minY = (rootHeight / 2 - stageHeight / 2 /zoom) * zoom;
			}
//			//minY = (rootHeight/2 - (stageHeight/2 / zoom) - (stageHeight / 2 - rootHeight/2) * (1/zoom - 1)) * zoom;
//			//maxY = ((stageHeight / 2 - rootHeight/2) * (1/zoom - 1) + (stageHeight/2 / zoom) - rootHeight/2) * zoom;
//			minY = (stageHeight / zoom / 2 -  (stageHeight / 2 - rootHeight / 2)  * (1/zoom - 1)) * zoom;
//			minY = (-stageHeight / zoom / 2 + (stageHeight / 2 - rootHeight / 2)  * (1/zoom - 1)) * zoom;
//			System.out.println(minX + " " + maxX + " " + minY + " " + maxY);
//			minY = minY / zoom;
//			maxX = maxX / zoom;
//			maxY = maxY / zoom;
//			minX = minX / zoom;
		}
	}

	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		// for pinch-to-zoom
		// System.out.println(" touch down : " + x + " " + y + " " + pointer);
		numberOfFingers++;
		if (numberOfFingers > 2)
			reset();
		if (numberOfFingers == 1) {
			fingerOnePointer = pointer;
			fingerOne.set(x, y, 0);
		} else if (numberOfFingers == 2) {
			fingerTwoPointer = pointer;
			fingerTwo.set(x, y, 0);
			lastDistance = fingerOne.dst(fingerTwo);
		}
		isPinched = numberOfFingers >= 2;
		oldX = x;
		oldY = y;
		oldDownX = x;
		oldDownY = y;
		return true;
	}

	public boolean touchDragged(float x, float y, int pointer) {
		// TODO Auto-generated method stub\
		// for pinch-to-zoom
		// System.out.println(" touch drag : " + x + " " + y + " " + pointer);

		if (pointer == fingerOnePointer) {
			fingerOne.set(x, y, 0);
		}
		if (pointer == fingerTwoPointer) {
			fingerTwo.set(x, y, 0);
		}

		float distance = fingerOne.dst(fingerTwo);

		if (numberOfFingers > 1) {
			if (lastDistance != 0 && distance != 0) {
				setZoomCamera(camera.zoom * lastDistance / distance);

				// System.out.println("zoom : " + globalCam.zoom);
			}
			lastDistance = distance;
			isPinched = true;
			isDragged = false;
		}

		if ((Math.abs((x - oldDownX)) > tolerant) || (Math.abs((y - oldDownY)) > tolerant)) {
			oldDownX = Integer.MIN_VALUE;
			oldDownY = Integer.MIN_VALUE;
		}
		if (isDragged) {
			// System.out.println("keo camera");
			float dx = -x + oldX;
			float dy = -y + oldY;
			translateCamera(dx * camera.zoom, -dy * camera.zoom);
		}
		oldX = x;
		oldY = y;
		isDragged = true;
		return true;
	}

	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		numberOfFingers--;

		// just some error prevention... clamping number of fingers (ouch! :-)
		if (numberOfFingers < 0) {
			numberOfFingers = 0;
		}

		if (numberOfFingers < 1) {
			if (((Math.abs((x - oldDownX)) < tolerant) && (Math.abs((y - oldDownY)) < tolerant))) {
				if (touchUpListener != null)
					touchUpListener.touchUpWithoutZoom(x, y, pointer, button);
			}
		}

		if (numberOfFingers == 0) {
			isPinched = false;
			lastDistance = 0;
		}
		isDragged = false;
		return false;
	}

	private void translateCamera(float dx, float dy) {
		float x = camera.position.x + dx;
		float y = camera.position.y + dy;
		if (inRange(x, minX, maxX) && inRange(y, minY, maxY)) {
			
			camera.translate(dx, dy, 0);
			
		}
	}
	public void setRootSize(float rootWidth, float rootHeight, float menuHeight)
	{
		this.rootWidth = rootWidth;
		this.rootHeight = rootHeight;
		this.menuHeight = menuHeight;
	}
	public void setStageSize(float stageWidth, float stageHeight)
	{
		this.stageHeight = stageHeight;
		this.stageWidth = stageWidth;
	}
}
