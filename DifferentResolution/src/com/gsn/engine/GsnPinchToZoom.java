package com.gsn.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class GsnPinchToZoom{
	public static interface ITouchUpWithoutZoom{
		void touchUpWithoutZoom(int x, int y, int pointer, int button);
	}
	
	GsnBoardStage stage;

	int numberOfFingers = 0;
	int fingerOnePointer;
	int fingerTwoPointer;
	float lastDistance = 0;
	Vector3 fingerOne = new Vector3();
	Vector3 fingerTwo = new Vector3();
	boolean isPinched = false;
	boolean isDragged = false;
	float oldX, oldY;
	float oldDownX, oldDownY;
	float tolerant = 7;
	Vector2 vector = new Vector2();
	
	public GsnPinchToZoom(GsnBoardStage gsnStage){
		this.stage = gsnStage;
		resetCamera();
	}	
	
	float minZoom = 0.2f; 
	float maxZoom = 5f;
	float defaultZoom = 1f;
	
	float minX = -50;
	float maxX = 50;
	float defaultX = 0;
	
	float minY = -100;
	float maxY = 100;
	float defaultY = 0;
	
	private static boolean inRange(float x, float a, float b){
		return (a <= x) && (x <= b);
	}
	
	private void setZoomCamera(float zoom){
		if (inRange(zoom, minZoom, maxZoom)){
			stage.globalCam.zoom = zoom;
		}
	}
	
	public void setRangeZoom(float minZoom, float maxZoom, float defaulZoom){
		
	}
	
	public void resetCamera(){
		stage.globalCam.position.set(defaultX, defaultY, 0);
		stage.globalCam.zoom = defaultZoom;
	}
	
	private void translateCamera(float dx, float dy){
		float x = stage.globalCam.position.x + dx;
		float y = stage.globalCam.position.y + dy;
		if (inRange(x , minX, maxX) && inRange(y , minY, maxY)){
			System.out.println(" translate : " + x + " " + y);
			stage.globalCam.translate(dx, dy, 0);
		}
	}
		
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub					
		numberOfFingers--;

		// just some error prevention... clamping number of fingers (ouch! :-)
		if (numberOfFingers < 0) {
			numberOfFingers = 0;
		}

		if (numberOfFingers < 1) {
//			System.out.println("x : " + x +  " " + oldDownX + " " + (Math.abs((x - oldDownX))));
//			System.out.println("y : " + y +  " " + oldDownY + " " + (Math.abs((y - oldDownY))));
			if (!isDragged && ((Math.abs((x - oldDownX)) < tolerant) && (Math.abs((y - oldDownY)) < tolerant))){
				//System.out.println(" vao roi ");
				stage.touchUpWithoutZoom(x, y, pointer, button);
			}
		}

		if (numberOfFingers == 0) {
			isPinched = false;
			lastDistance = 0;
		}
		isDragged = false;
		return false;
	}

	public void pause() {
		numberOfFingers = 0;
	}
	
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		// for pinch-to-zoom
		//System.out.println(" touch down : " + x + " " + y + " " + pointer);
		numberOfFingers++;
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
		//System.out.println(" touch drag : " + x + " " + y + " " + pointer);
		
		if (pointer == fingerOnePointer) {
			fingerOne.set(x, y, 0);
		}
		if (pointer == fingerTwoPointer) {
			fingerTwo.set(x, y, 0);
		}

		float distance = fingerOne.dst(fingerTwo);

		if (numberOfFingers > 1) {
			if (lastDistance != 0 && distance != 0) {
				setZoomCamera(stage.globalCam.zoom * lastDistance / distance);
				
			//	System.out.println("zoom : " + globalCam.zoom);
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
			//System.out.println("keo camera");
			float dx = -x + oldX;
			float dy = -y + oldY;
			translateCamera(dx * stage.globalCam.zoom, - dy * stage.globalCam.zoom);
		}
		oldX = x;
		oldY = y;
		isDragged = true;
		return true;
	}
	
	/// simulator multi touch	
	private final int POINTER = 2;
	private final int BUTTON = 0;
	private boolean pressAlt = false;	

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
}
