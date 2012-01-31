package com.gsn.test;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.AssetOld;
import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.GdxUtility;
import com.gsn.engine.GsnPinchToZoom;
import com.gsn.engine.GsnPinchToZoom.ITouchUpWithoutZoomListener;

public class BoardStage extends Stage implements ITouchUpWithoutZoomListener {
	ImageAsset asset;
	Image pieceO, pieceX, board;
	Image bubleChat;
	
	public GsnPinchToZoom pinch;
	
	Vector2 vector2 = new Vector2();	
	
	private Vector3 vector3 = new Vector3();
	public BoardStage(float width, float height) {
		super(width, height, false);
		pinch = new GsnPinchToZoom(this);
		pinch.setTouchUpWithoutZoomListener(this);
		//pinch.setRangeZoom(0.5f, 1.5f, 1.5f);
		pinch.resetCamera();
		asset = ImageAsset.getInstance();
		
		
		TextureRegion[] a = GdxUtility.convertListRegionToArray(AssetOld.getInstance().bbChat);
		
		bubleChat = new Image(new NinePatch(a));
		bubleChat.width = 100;
		bubleChat.height = 50;
					
		board = new Image(asset.board);
		ActorUtility.setCenter(board, 0, 0);
		
		board.setClickListener(new ClickListener() {
			Vector2 vector = new Vector2();
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				System.out.println("Click Board");
				float rX = actor.x + x;
				float rY = actor.y + y;
				toScreenCoordinates(rX, rY, vector);				
				DataProvider.getInstance().clickEffect.startNow(DataProvider.getInstance().screenStage.getCamera(), vector.x, vector.y);
			}
		});
		this.addActor(board);	
		this.addActor(bubleChat);
	}	
	
	
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

	public void resetPinchToZoom(){
		pinch.reset();
	}

	public void toScreenCoordinates (float x, float y, Vector2 out) {
		camera.project(vector3.set(x, y, 0));
		out.x = vector3.x;
		out.y = vector3.y;		
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		// System.out.println("touch down ne : " + pointer);
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
		// TODO Auto-generated method stub
		this.toStageCoordinates(x, y, vector2);
		Actor actor = this.hit(vector2.x, vector2.y);
		if (actor == board){
			pieceO = new Image(asset.pieceO);
			pieceX = new Image(asset.pieceX);
			pieceO.x = vector2.x;
			pieceO.y = vector2.y;
			BoardStage.this.addActor(pieceO);
		}
	}
}
