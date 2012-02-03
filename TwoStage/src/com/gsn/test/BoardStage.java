package com.gsn.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.GsnPinchToZoom;
import com.gsn.engine.GsnPinchToZoom.ITouchUpWithoutZoomListener;
import com.gsn.engine.gdx.GsnAnimation;
import com.gsn.engine.layout.GsnPoint;

public class BoardStage extends Stage implements ITouchUpWithoutZoomListener {
	ImageAsset asset;
	Image pieceO, pieceX, board;

	public GsnPinchToZoom pinch;

	Vector2 vector2 = new Vector2();
	PlayStage parent;

	private Vector3 vector3 = new Vector3();

	// bien logic caro
	Image xSignal;
	// Image board;
	int tmp = 50;
	Image arrayImage[][];
	int countSinal = 0;
	GsnPoint arrayCoordinate[][];
	boolean xStep = true;
	int boardLogic[][];
	private BitmapFont font;
	int xNumber = 0;
	int yNumber = 0;
	GsnAnimation animation;

	public BoardStage(PlayStage parent, float width, float height) {
		super(width, height, false);
		this.parent = parent;
		pinch = new GsnPinchToZoom(this);
		pinch.setTouchUpWithoutZoomListener(this);
		// pinch.setRangeZoom(0.5f, 1.5f, 1.5f);
		pinch.resetCamera();
		asset = ImageAsset.getInstance();

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
				DataProvider.getInstance().clickEffect.startNow(
						DataProvider.getInstance().screenStage.getCamera(),
						vector.x, vector.y);
			}
		});
		this.addActor(board);
		
		
		xSignal = new Image(asset.pieceX);
		// create the bitmap font used to draw the text
		font = new BitmapFont();

		// set colour to red
		font.setColor(Color.RED);

		// arrayImage = new A
		// board.setClickListener(new ClickListener() {
		// @Override
		// public void click(Actor actor, float x, float y) {
		// // TODO Auto-generated method stub
		// System.out.println("click : " + x + ", " + y);
		// }
		// });
		this.addActor(board);
		// this.addActor(xSignal);
		board.x = -board.width / 2;
		board.y = -board.height / 2;
		xSignal.x = -100;
		xSignal.y = -100;
		// arrayImage = new Image[10];
		int i, j;
		float startX = -(board.width / 2) + board.width / 40;
		float startY = -(board.height / 2) + board.width / 40;
		arrayCoordinate = new GsnPoint[16][16];
		boardLogic = new int[15][15];
		for (i = 0; i < 16; i++) {
			arrayCoordinate[i] = new GsnPoint[16];
		}
		for (i = 0; i < 15; i++) {
			boardLogic[i] = new int[15];
			for (j = 0; j < 15; j++) {
				boardLogic[i][j] = 0;
			}
		}
		float size = board.height / 15.8f;
		for (i = 0; i < 16; i++) {
			for (j = 0; j < 16; j++) {
				Image xSignal1 = new Image(asset.pieceO);
				arrayCoordinate[i][j] = new GsnPoint(startX + size * j, startY
						+ size * i);

				// arrayImage[0] = new Image(asset.pieceO);
				// this.addActor(arrayImage[0]);
				// this.addActor(xSignal1);
				// xSignal1.x = arrayCoordinate[i][j].x;
				// xSignal1.y = arrayCoordinate[i][j].y;
			}
		}
		arrayImage = new Image[15][15];
		for (i = 0; i < 15; i++) {
			arrayImage[i] = new Image[15];
		}
		
//		this.addActor(animation);
//		animation.x = width / 2;
//		animation.y = height / 2;
		// meTimer.start();
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

	public void resetPinchToZoom() {
		pinch.reset();
	}

	public void toScreenCoordinates(float x, float y, Vector2 out) {
		camera.project(vector3.set(x, y, 0));
		out.x = vector3.x;
		out.y = vector3.y;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		System.out.println("touch down ne : " + pointer);
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
		// Actor actor = this.hit(vector2.x, vector2.y);
		// if (actor == board) {
		// pieceO = new Image(asset.pieceO);
		// pieceX = new Image(asset.pieceX);
		// pieceO.x = vector2.x;
		// pieceO.y = vector2.y;
		// BoardStage.this.addActor(pieceO);
		// }
		//
		Vector2 coordinate = getCoordinate(vector2.x, vector2.y);

		if (coordinate != null
				&& boardLogic[(int) coordinate.x][(int) coordinate.y] == 0) {
			doStep((int) coordinate.x, (int) coordinate.y);
		}
	}

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		//animation.act(delta);
	}

	// Logic play caro
	public void initGame() {
		int i, j;
		for (i = 0; i < 15; i++)
			for (j = 0; j < 15; j++) {
				if (boardLogic[i][j] != 0) {
					removeActor(arrayImage[i][j]);
					// arrayImage[i][j].remove();
					boardLogic[i][j] = 0;

					// arrayImage[i][j].
				}
			}
		xStep = true;
	}

	public Vector2 getCoordinate(float x, float y) {
		int i, j;
		for (i = 0; i < 15; i++)
			for (j = 0; j < 15; j++) {
				if (arrayCoordinate[i][j].x <= x
						&& arrayCoordinate[i + 1][j + 1].x > x
						&& arrayCoordinate[i][j].y <= y
						&& arrayCoordinate[i + 1][j + 1].y > y) {
					return new Vector2(i, j);
				}
			}
		return null;
	}

	public boolean checkVictory(int sinal, Vector2 coordinate) {
		int i, j, count;
		count = 1;

		for (i = (int) coordinate.x + 1; i < 15; i++) {
			if (boardLogic[i][(int) coordinate.y] == sinal) {
				count++;
			} else {

				for (i = (int) coordinate.x - 1; i >= 0; i--) {
					if (boardLogic[i][(int) coordinate.y] == sinal) {
						count++;
					} else {
						if (count == 5)
							return true;
						break;
					}
				}
				break;
			}
		}
		count = 1;
		for (i = (int) coordinate.y + 1; i < 15; i++) {
			if (boardLogic[(int) coordinate.x][i] == sinal) {
				count++;
			} else {

				for (i = (int) coordinate.y - 1; i >= 0; i--) {
					if (boardLogic[(int) coordinate.x][i] == sinal) {
						count++;
					} else {
						if (count == 5)
							return true;
						break;
					}
				}
				break;
			}
		}

		count = 1;
		for (i = 1; (int) coordinate.x - i >= 0 && coordinate.y + i < 15; i++) {
			if (boardLogic[(int) coordinate.x - i][(int) coordinate.y + i] == sinal) {
				count++;
			} else {

				for (i = 1; (int) coordinate.x + i < 15
						&& (int) coordinate.y - i >= 0; i++) {
					if (boardLogic[(int) coordinate.x + i][(int) coordinate.y
							- i] == sinal) {
						count++;
					} else {
						if (count == 5)
							return true;
						break;
					}
				}
				break;
			}
		}
		count = 1;
		for (i = 1; (int) coordinate.x + i < 15 && (int) coordinate.y + i < 15; i++) {
			if (boardLogic[(int) coordinate.x + i][(int) coordinate.y + i] == sinal) {
				count++;
			} else {

				for (i = 1; coordinate.x - i >= 0 && coordinate.y - i >= 0; i++) {
					if (boardLogic[(int) coordinate.x - i][(int) coordinate.y
							- i] == sinal) {
						count++;
					} else {
						if (count == 5)
							return true;
						break;
					}
				}
				break;
			}
		}
		return false;
	}

	public void doStep(int i, int j) {
		Vector2 coordinate = new Vector2(i, j);

		if (xStep) {
			arrayImage[(int) coordinate.x][(int) coordinate.y] = new Image(
					asset.pieceX);
			boardLogic[(int) coordinate.x][(int) coordinate.y] = 1;
			this.addActor(arrayImage[(int) coordinate.x][(int) coordinate.y]);

			parent.menuStage.otherStep();
			// meTimer.stop();
			// otherTimer.start();
			if (checkVictory(1, coordinate)) {
				xNumber++;
				Gdx.app.log("Victory", " message");
				parent.menuStage.win();
				// animation.start();
				initGame();
			}
		} else {
			arrayImage[(int) coordinate.x][(int) coordinate.y] = new Image(
					asset.pieceO);
			boardLogic[(int) coordinate.x][(int) coordinate.y] = 2;
			this.addActor(arrayImage[(int) coordinate.x][(int) coordinate.y]);
			if (checkVictory(2, coordinate)) {
				yNumber++;
				Gdx.app.log("Victory", " message");
				// animation.start();
				initGame();
			}

			parent.menuStage.myStep();
			// otherTimer.stop();
			// meTimer.start();
		}

		xStep = !xStep;
		// arrayImage[0] = new Image(asset.pieceO);
		// this.addActor(arrayImage[0]);

		arrayImage[(int) coordinate.x][(int) coordinate.y].x = arrayCoordinate[(int) coordinate.x][(int) coordinate.y].x;// -
																															// width
																															// /
																															// 2;
		arrayImage[(int) coordinate.x][(int) coordinate.y].y = arrayCoordinate[(int) coordinate.x][(int) coordinate.y].y;// -
																															// height
																															// /
																															// 2;
	}

	public void randomStep() {
		int i = 0;
		int j = 0;
		while (true) {
			i = (int) (Math.random() * 15);
			if (i == 15)
				i = 14;
			j = (int) (Math.random() * 15);
			if (j == 15)
				j = 14;
			if (boardLogic[i][j] == 0) {
				doStep(i, j);
				break;
			}
		}
	}
}
