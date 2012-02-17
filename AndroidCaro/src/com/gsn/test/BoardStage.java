package com.gsn.test;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.DataProvider;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.caro.asset.SoundAsset;
import com.gsn.engine.ActorUtility;
import com.gsn.engine.GsnPinchToZoom;
import com.gsn.engine.GsnPinchToZoom.ITouchUpWithoutZoomListener;
import com.gsn.engine.gdx.GsnAnimation;
import com.gsn.engine.layout.GsnPoint;
import com.gsn.engine.myplay.GsnStage;
import com.gsn.packet.PacketFactory;

public class BoardStage extends GsnStage implements ITouchUpWithoutZoomListener {
	ImageAsset asset;
	Image pieceO, pieceX, board;

	public GsnPinchToZoom pinch;

	Vector2 vector2 = new Vector2();
	PlayStage parent;

	// bien logic caro
	Image xSignal;
	// Image board;
	int tmp = 50;
	Image arrayImage[][];
	GsnAnimation arrayAnimationWillWin[][];
	GsnAnimation arrayAnimationWin[][];
	int countSinal = 0;
	GsnPoint arrayCoordinate[][];
	boolean xStep = true;
	int boardLogic[][];
	private BitmapFont font;
	int xNumber = 0;
	int yNumber = 0;
	GsnAnimation animation;
	int idPlayerX = 0;
	int idPlayerY = 0;
	private float heightMenu;
	final int X_STEP = 1;
	final int Y_STEP = 2;
	Vector2 lastPoint;
	final int X_WIN_STEP = 3;
	final int Y_WIN_STEP = 4;

	final int X_WON = 5;
	final int Y_WON = 6;
	private float size;

	public BoardStage(PlayStage parent, float width, float height, float heightMenu) {
		super(width, height, false);
		this.heightMenu = heightMenu;
		this.parent = parent;
		pinch = new GsnPinchToZoom(this);
		pinch.setTouchUpWithoutZoomListener(this);
		// pinch.setRangeZoom(0.5f, 1.5f, 1.5f);

		pinch.resetCamera();
		asset = ImageAsset.getInstance();

		board = new Image(asset.board);
		ActorUtility.setCenter(board, 0, 0);
		lastPoint = new Vector2(-1, -1);
		board.setClickListener(new ClickListener() {
			Vector2 vector = new Vector2();

			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				SoundAsset.playSoundClick();
//				Vector2 vector2 = new Vector2();
//				BoardStage.this.toStageCoordinates((int) x, (int) y, vector2);
//
//				float rX = actor.x + x;
//				float rY = actor.y + y;
//				toScreenCoordinates(rX, rY, vector);
//				BoardStage.this.parent.clickEffect(vector.x, vector.y);
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
		size = board.height / 15.8f;
		for (i = 0; i < 16; i++) {
			for (j = 0; j < 16; j++) {
				arrayCoordinate[i][j] = new GsnPoint(startX + size * (j + 0.5f), startY + size * (i + 0.5f));

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
		arrayAnimationWillWin = new GsnAnimation[15][15];
		for (i = 0; i < 15; i++) {
			arrayAnimationWillWin[i] = new GsnAnimation[15];
		}
		arrayAnimationWin = new GsnAnimation[15][15];
		for (i = 0; i < 15; i++) {
			arrayAnimationWin[i] = new GsnAnimation[15];
		}
		float rootWidth = width - width / 10;
		float rootHeight = height - height / 10;
		pinch.setRangeX(rootWidth / 2 - board.width / 2, board.width / 2 - rootWidth / 2, 0);
		pinch.setRangeY(rootHeight / 2 - board.height / 2, board.height / 2 - rootHeight / 2 + heightMenu, 0);
		pinch.setRootSize(rootWidth, rootHeight, heightMenu);
		pinch.setStageSize(board.width, board.height);
		pinch.setRangeZoom(0.2f, board.width / (rootWidth), 1);
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
		System.out.println("touch up without zoom");
		if (xStep) {
			Vector2 coordinate = getCoordinate(vector2.x, vector2.y);

			if (coordinate != null && boardLogic[(int) coordinate.x][(int) coordinate.y] == 0) {
				if (xStep){
					parent.sendJson(PacketFactory.createMove(idPlayerX, (int) coordinate.x * 15 + (int) coordinate.y));
					Vector2 vector = new Vector2();
					DataProvider.getInstance().screenStage.toStageCoordinates(x, y, vector);
					BoardStage.this.parent.clickEffect(vector.x, vector.y);
				}
				else
					parent.sendJson(PacketFactory.createMove(idPlayerY, (int) coordinate.x * 15 + (int) coordinate.y));
			}
		}
	}

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		// animation.act(delta);
	}

	// Logic play caro
	public void initGame() {
		int i, j;
		for (i = 0; i < 15; i++)
			for (j = 0; j < 15; j++) {
				if (boardLogic[i][j] != 0) {
					if (boardLogic[i][j] == X_STEP || boardLogic[i][j] == Y_STEP)
						removeActor(arrayImage[i][j]);
					else if (boardLogic[i][j] == X_WIN_STEP || boardLogic[i][j] == Y_WIN_STEP)
						arrayAnimationWillWin[i][j].remove();
					else if (boardLogic[i][j] == X_WON || boardLogic[i][j] == Y_WON)
						arrayAnimationWin[i][j].remove();
					boardLogic[i][j] = 0;

					// arrayImage[i][j].
				}
			}
		lastPoint.x = -1;
		lastPoint.y = -1;
		this.pinch.resetCamera();
	//	xStep = true;
	}

	public void resetPointMatch() {
		xNumber = 0;
		yNumber = 0;
		parent.menuStage.setPointMatch(xNumber, yNumber);
	}

	public Vector2 getCoordinate(float x, float y) {
		int i, j;
		for (i = 0; i < 15; i++)
			for (j = 0; j < 15; j++) {
				if (arrayCoordinate[i][j].x - 0.5f * size <= x && arrayCoordinate[i + 1][j + 1].x - 0.5 * size > x && arrayCoordinate[i][j].y - 0.5 * size <= y && arrayCoordinate[i + 1][j + 1].y - 0.5 * size > y) {					
				
					return new Vector2(i, j);
				}
			}
		return null;
	}

	public void makeVictory(Vector2 arrayTemp[], int sinal) {
		int i, j;
		if (sinal == X_STEP)
			sinal = X_WON;
		else
			sinal = Y_WON;
		
		int boardLogicCopy[][] = new int[15][15];

		for (i = 0; i < 15; i++) {
			boardLogicCopy[i] = new int[15];
			for (j = 0; j < 15; j++)
				boardLogicCopy[i][j] = 0;
		}
		copyBoard(boardLogic, boardLogicCopy);
		for (i = 0; i < 5; i++)
			boardLogicCopy[(int) arrayTemp[i].x][(int) arrayTemp[i].y] = sinal;
		for (i = 0; i < 15; i++)
			for (j = 0; j < 15; j++) {

				if (boardLogicCopy[i][j] != boardLogic[i][j]) {
					

					if (boardLogic[i][j] == X_STEP || boardLogic[i][j] == Y_STEP) {
						arrayImage[i][j].remove();
						arrayImage[i][j] = null;
					} else if (boardLogic[i][j] == X_WIN_STEP || boardLogic[i][j] == Y_WIN_STEP) {
						arrayAnimationWillWin[i][j].remove();
						arrayAnimationWillWin[i][j] = null;
					}
					boardLogic[i][j] = boardLogicCopy[i][j];
					if (boardLogic[i][j] == X_WON) {
						arrayAnimationWin[i][j] = new GsnAnimation(0.2f, ImageAsset.getInstance().listXWinEffect);
						arrayAnimationWin[i][j].setLoop(true);
						this.addActor(arrayAnimationWin[i][j]);
						ActorUtility.setCenter(arrayAnimationWin[i][j], arrayCoordinate[i][j].x, arrayCoordinate[i][j].y);
						// arrayAnimationWillWin[i][j].x =
						// arrayCoordinate[i][j].x;
						// arrayAnimationWillWin[i][j].y =
						// arrayCoordinate[i][j].y;
					} else if (boardLogic[i][j] == Y_WON) {
						arrayAnimationWin[i][j] = new GsnAnimation(0.2f, ImageAsset.getInstance().listOWinEffect);
						arrayAnimationWin[i][j].setLoop(true);
						this.addActor(arrayAnimationWin[i][j]);
						ActorUtility.setCenter(arrayAnimationWin[i][j], arrayCoordinate[i][j].x, arrayCoordinate[i][j].y);
						// arrayAnimationWillWin[i][j].x =
						// arrayCoordinate[i][j].x;
						// arrayAnimationWillWin[i][j].y =
						// arrayCoordinate[i][j].y;
					} else if (boardLogic[i][j] == X_STEP) {
						arrayImage[i][j] = new Image(ImageAsset.getInstance().pieceX);
						this.addActor(arrayImage[i][j]);
						ActorUtility.setCenter(arrayImage[i][j], arrayCoordinate[i][j].x, arrayCoordinate[i][j].y);
						// arrayImage[i][j].x = arrayCoordinate[i][j].x;
						// arrayImage[i][j].y = arrayCoordinate[i][j].y;
					} else {
						System.out.println("dkm ");
						arrayImage[i][j] = new Image(ImageAsset.getInstance().pieceO);
						this.addActor(arrayImage[i][j]);
						ActorUtility.setCenter(arrayImage[i][j], arrayCoordinate[i][j].x, arrayCoordinate[i][j].y);
						// arrayImage[i][j].x = arrayCoordinate[i][j].x;
						// arrayImage[i][j].y = arrayCoordinate[i][j].y;
					}
				}
			}
	}

	public boolean checkVictory(int sinal, Vector2 coordinate) {
		int i, j, count;
		Boolean blank = false;

		Vector2 arrayTemp[] = new Vector2[15];
		Boolean arrayCheck[][] = new Boolean[15][15];
		int boardLogicCopy[][] = new int[15][15];
		for (i = 0; i < 15; i++)
			arrayTemp[i] = new Vector2();
		for (i = 0; i < 15; i++) {
			boardLogicCopy[i] = new int[15];
			for (j = 0; j < 15; j++)
				boardLogicCopy[i][j] = 0;
		}
		arrayTemp[0].x = coordinate.x;
		arrayTemp[0].y = coordinate.y;
		count = 1;
		copyBoard(boardLogic, boardLogicCopy);
		for (i = (int) coordinate.x + 1; i < 15; i++) {
			if (boardLogicCopy[i][(int) coordinate.y] == sinal) {
				arrayTemp[count].x = i;
				arrayTemp[count].y = coordinate.y;
				count++;

				if (count > 5)
					break;

			} else {
				if (i == 15 || boardLogicCopy[i][(int) coordinate.y] == 0)
					blank = true;
				break;
			}
		}
		for (i = (int) coordinate.x - 1; i >= 0; i--) {
			System.out.println(i + "  " + coordinate.y);
			if (boardLogicCopy[i][(int) coordinate.y] == sinal) {
				arrayTemp[count].x = i;
				arrayTemp[count].y = coordinate.y;
				count++;

				if (count > 5)
					break;
			} else {
				System.out.println(" count " + count);
				if (count == 5 && (blank || i < 0 || boardLogicCopy[i][(int) coordinate.y] == 0)) {
					System.out.println(" vao day 1");
					makeVictory(arrayTemp, sinal);
					return true;

				}
				break;
			}
		}

		System.out.println(" count " + count);

		// kiem tra ngang
		count = 1;
		blank = false;
		for (i = (int) coordinate.y + 1; i < 15; i++) {
			if (boardLogicCopy[(int) coordinate.x][i] == sinal) {
				arrayTemp[count].x = coordinate.y + 1;
				arrayTemp[count].y = i;
				count++;
				if (count > 5)
					break;
			} else {

				if (i == 15 || boardLogicCopy[(int) coordinate.x][i] == 0)
					blank = true;

				break;
			}
		}

		for (i = (int) coordinate.y - 1; i >= 0; i--) {
			if (boardLogicCopy[(int) coordinate.x][i] == sinal) {
				arrayTemp[count].x = coordinate.y + 1;
				arrayTemp[count].y = i;
				count++;
				if (count > 5)
					break;
			} else {
				if (count == 5 && (blank || i < 0 || boardLogicCopy[(int) coordinate.x][i] == 0)) {
					System.out.println(" vao day 2");
					makeVictory(arrayTemp, sinal);
					return true;
				}

			}
			break;

		}

		count = 1;
		blank = false;

		// kiem tra cheo tren phai
		for (i = 1; (int) coordinate.x - i >= 0 && coordinate.y + i < 15; i++) {
			if (boardLogicCopy[(int) coordinate.x - i][(int) coordinate.y + i] == sinal) {
				arrayTemp[count].x = coordinate.x - i;
				arrayTemp[count].y = coordinate.y + i;
				count++;
				if (count > 5)
					break;
			} else {
				if (coordinate.x - i < 0 || coordinate.y + i == 15 || boardLogicCopy[(int) coordinate.x - i][(int) coordinate.y + i] == 0)
					blank = true;

				break;
			}
		}
		for (i = 1; (int) coordinate.x + i < 15 && (int) coordinate.y - i >= 0; i++) {
			if (boardLogicCopy[(int) coordinate.x + i][(int) coordinate.y - i] == sinal) {
				arrayTemp[count].x = coordinate.x - i;
				arrayTemp[count].y = coordinate.y + i;
				count++;
				if (count > 5)
					break;
			} else {
				if (count == 5 && (blank || coordinate.x + i == 15 || coordinate.y - i < 0 || boardLogicCopy[(int) coordinate.x + i][(int) coordinate.y - i] == 0)) {
					System.out.println(" vao day 3");
					makeVictory(arrayTemp, sinal);
					return true;
				}
				break;
			}
		}

		// kiem tra cheo tren trai
		count = 1;
		blank = false;
		for (i = 1; (int) coordinate.x + i < 15 && (int) coordinate.y + i < 15; i++) {
			if (boardLogicCopy[(int) coordinate.x + i][(int) coordinate.y + i] == sinal) {
				arrayTemp[count].x = coordinate.x + i;
				arrayTemp[count].y = coordinate.y + i;
				count++;
				if (count > 5)
					break;
			} else {
				if (coordinate.x - i < 0 || coordinate.y + i == 15 || boardLogicCopy[(int) coordinate.x - i][(int) coordinate.y + i] == 0)
					blank = true;
				for (i = 1; coordinate.x - i >= 0 && coordinate.y - i >= 0; i++) {
					if (boardLogicCopy[(int) coordinate.x - i][(int) coordinate.y - i] == sinal) {
						arrayTemp[count].x = coordinate.x + i;
						arrayTemp[count].y = coordinate.y + i;
						count++;
						if (count > 5)
							break;
					} else {
						if (count == 5 && (blank || coordinate.x - i < 0 || coordinate.y - i < 0 || boardLogicCopy[(int) coordinate.x - i][(int) coordinate.y - i] == 0)) {
							System.out.println(" vao day 4");
							makeVictory(arrayTemp, sinal);
							return true;
						}
						break;
					}
				}
				break;
			}
		}
		return false;
	}

	public void doStep(int x, int y, int idPlayer) {

		SoundAsset.playHitSound();
		int i, j, sinal;
		if (idPlayer == idPlayerX) {
			xStep = true;
		} else {
			xStep = false;
		}
		if(lastPoint.x >= 0 && lastPoint.y >= 0)
		{
			arrayImage[(int)lastPoint.x][(int)lastPoint.y].remove();
			if(boardLogic[(int)lastPoint.x][(int)lastPoint.y] == X_STEP)
				arrayImage[(int)lastPoint.x][(int)lastPoint.y] = new Image(asset.pieceX);
			else 
				arrayImage[(int)lastPoint.x][(int)lastPoint.y] = new Image(asset.pieceO);
			this.addActor(arrayImage[(int)lastPoint.x][(int)lastPoint.y]);
			ActorUtility.setCenter(arrayImage[(int)lastPoint.x][(int)lastPoint.y], 
					arrayCoordinate[(int)lastPoint.x][(int)lastPoint.y].x, arrayCoordinate[(int)lastPoint.x][(int)lastPoint.y].y);
		}
		lastPoint.x = x;
		lastPoint.y = y;
		if (xStep) {
			arrayImage[x][y] = new Image(asset.xEffect);
			boardLogic[x][y] = sinal = X_STEP;
			this.addActor(arrayImage[x][y]);		
			parent.menuStage.otherStep();
			// meTimer.stop();
			// otherTimer.start();
			// if (checkVictory(1, coordinate)) {
			// xNumber++;
			// Gdx.app.log("Victory", " message");
			// parent.menuStage.win();
			// // animation.start();
			// parent.menuStage.setPointMatch(xNumber, yNumber);
			// initGame();
			// }
		} else {
			arrayImage[x][y] = new Image(asset.oEffect);
			boardLogic[x][y] = sinal = Y_STEP;
			this.addActor(arrayImage[x][y]);

			// if (checkVictory(2, coordinate)) {
			// yNumber++;
			// Gdx.app.log("Victory", " message");
			// parent.menuStage.setPointMatch(xNumber, yNumber);
			// // animation.start();
			// parent.menuStage.win();
			// initGame();
			// }

			parent.menuStage.myStep();
			// otherTimer.stop();
			// meTimer.start();
		}

		xStep = !xStep;
		// arrayImage[0] = new Image(asset.pieceO);
		// this.addActor(arrayImage[0]);
		ActorUtility.setCenter(arrayImage[x][y], arrayCoordinate[x][y].x, arrayCoordinate[x][y].y);
		// arrayImage[x][y].x = arrayCoordinate[x][y].x;// - // 2;
		// arrayImage[x][y].y = arrayCoordinate[x][y].y;// - // 2;
		if (!checkVictory(sinal, new Vector2(x, y))) {
			int boardLogicChange[][] = new int[15][15];
			for (i = 0; i < 15; i++) {
				boardLogicChange[i] = new int[15];
				for (j = 0; j < 15; j++)
					boardLogicChange[i][j] = 0;
			}
			updateStateBoard(boardLogicChange);
			for (i = 0; i < 15; i++)
				for (j = 0; j < 15; j++) {
					if (boardLogic[i][j] != boardLogicChange[i][j]) {
						if (boardLogic[i][j] == X_STEP || boardLogic[i][j] == Y_STEP) {
							arrayImage[i][j].remove();
							arrayImage[i][j] = null;
							if(i==lastPoint.x && j == lastPoint.y)
							{
								lastPoint.x = -1;
								lastPoint.y = -1;
							}
						}	
						else if(boardLogic[i][j] == X_WIN_STEP || boardLogic[i][j] == Y_WIN_STEP)
						{
							arrayAnimationWillWin[i][j].remove();
							arrayAnimationWillWin[i][j] = null;
						}
						boardLogic[i][j] = boardLogicChange[i][j];
						if (boardLogic[i][j] == X_WIN_STEP) {
							arrayAnimationWillWin[i][j] = new GsnAnimation(0.2f, ImageAsset.getInstance().listXEffect);
							arrayAnimationWillWin[i][j].setLoop(true);
							this.addActor(arrayAnimationWillWin[i][j]);
							ActorUtility.setCenter(arrayAnimationWillWin[i][j], arrayCoordinate[i][j].x, arrayCoordinate[i][j].y);
							// arrayAnimationWillWin[i][j].x =
							// arrayCoordinate[i][j].x;
							// arrayAnimationWillWin[i][j].y =
							// arrayCoordinate[i][j].y;

						} else if (boardLogic[i][j] == Y_WIN_STEP) {
							arrayAnimationWillWin[i][j] = new GsnAnimation(0.2f, ImageAsset.getInstance().listOEffect);
							arrayAnimationWillWin[i][j].setLoop(true);
							this.addActor(arrayAnimationWillWin[i][j]);
							ActorUtility.setCenter(arrayAnimationWillWin[i][j], arrayCoordinate[i][j].x, arrayCoordinate[i][j].y);
							// arrayAnimationWillWin[i][j].x =
							// arrayCoordinate[i][j].x;
							// arrayAnimationWillWin[i][j].y =
							// arrayCoordinate[i][j].y;
						} else if (boardLogic[i][j] == X_STEP) {
							arrayImage[i][j] = new Image(ImageAsset.getInstance().pieceX);
							this.addActor(arrayImage[i][j]);
							ActorUtility.setCenter(arrayImage[i][j], arrayCoordinate[i][j].x, arrayCoordinate[i][j].y);
							// boardLogicChangearrayImage[i][j].x =
							// arrayCoordinate[i][j].x;
							// arrayImage[i][j].y = arrayCoordinate[i][j].y;
						} else {
							arrayImage[i][j] = new Image(ImageAsset.getInstance().pieceO);
							this.addActor(arrayImage[i][j]);
							ActorUtility.setCenter(arrayImage[i][j], arrayCoordinate[i][j].x, arrayCoordinate[i][j].y);
							// arrayImage[i][j].x = arrayCoordinate[i][j].x;
							// arrayImage[i][j].y = arrayCoordinate[i][j].y;
						}
					}
				}
		}
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
				// doStep(i, j);
				break;
			}
		}
	}

	public void setXStep(Boolean _xStep) {
		xStep = _xStep;

	}

	public void setIdPlayer(Boolean isX, int id) {
		if (isX)
			idPlayerX = id;
		else
			idPlayerY = id;
	}

	public void finishGame(String type) {
		if (type.equals("WIN")) {
			xNumber++;
			SoundAsset.playSoundWin();
		} else if (type.equals("LOSE")) {
			yNumber++;
			SoundAsset.playSoundLose();
		} else {

		}
		parent.menuStage.setPointMatch(xNumber, yNumber);
		// initGame();
	}

	public void resetData() {
		xNumber = 0;
		yNumber = 0;
		if (parent.menuStage != null)
			parent.menuStage.setPointMatch(xNumber, yNumber);
		initGame();
	}

	public void updateStateBoard(int boardLogicChange[][]) {
		int countSame = 1, i, j, countBlank = 0, x, y;
		Boolean notTestNext = false;
		Vector2 arrayTemp[] = new Vector2[15];
		Boolean arrayCheck[][] = new Boolean[15][15];
		int boardLogicCopy[][] = new int[15][15];
		for (i = 0; i < 15; i++) {
			boardLogicCopy[i] = new int[15];
			for (j = 0; j < 15; j++)
				boardLogicCopy[i][j] = 0;
		}
		copyBoard(boardLogic, boardLogicCopy);
		copyBoard(boardLogic, boardLogicChange);

		for (i = 0; i < 15; i++) {
			arrayCheck[i] = new Boolean[15];
			for (j = 0; j < 15; j++)
				arrayCheck[i][j] = false;
		}

		for (i = 0; i < 15; i++)
			arrayTemp[i] = new Vector2();

		// check viec xem no co thay doi trang thai cac o xung quanh ko

		for (x = 0; x < 15; x++)
			for (y = 0; y < 15; y++) {
				// kiem tra dinh dau trai

				countSame = 1;
				countBlank = 0;
				if (boardLogicCopy[x][y] != 0 && !arrayCheck[x][y]) {
					arrayTemp[0].x = x;
					arrayTemp[0].y = y;
					for (i = 1; x + i < 15 && y - i >= 0; i++)
						if (boardLogicCopy[x + i][y - i] == boardLogicCopy[x][y]) {
							arrayTemp[countSame].x = x + i;
							arrayTemp[countSame].y = y - i;

							countSame++;

						} else if (countBlank < 2 && boardLogicCopy[x + i][y - i] == 0) {
							countBlank++;
							if (countBlank == 2)
								break;
						} else
							break;

					if (countSame <= 4) {

						for (; countBlank < 2 && x + i < 15 && y - i >= 0; i++)
							if (boardLogicCopy[x + i][y - i] == 0) {
								countBlank++;
							} else
								break;
						if (x + i == 15 || y - i < 0)
							countBlank++;
						for (i = 1; x - i >= 0 && y + i < 15; i++)
							if (boardLogicCopy[x - i][y + i] == boardLogicCopy[x][y]) {
								arrayTemp[countSame].x = x - i;
								arrayTemp[countSame].y = y + i;
								countSame++;
							} else if (countBlank < 2 && boardLogicCopy[x - i][y + i] == 0) {
								countBlank++;
								if (countBlank == 2)
									break;
							} else {
								break;
							}

						if (countSame == 4) {
							for (; x - i >= 0 && y + i < 15; i++)
								if (boardLogicCopy[x - i][y + i] == 0) {
									countBlank++;
									if (countBlank >= 2)
										break;
								} else
									break;
							if (x - i < 0 || y + i >= 15)
								countBlank++;
							if (countBlank >= 2) {

								// co the win
								for (i = 0; i < 4; i++) {
									if (boardLogicCopy[x][y] == X_STEP)
										boardLogicChange[(int) arrayTemp[i].x][(int) arrayTemp[i].y] = X_WIN_STEP;
									else
										boardLogicChange[(int) arrayTemp[i].x][(int) arrayTemp[i].y] = Y_WIN_STEP;
									arrayCheck[(int) arrayTemp[i].x][(int) arrayTemp[i].y] = true;
								}
							}
						}

					}
					// System.out.println(countSame);

					// reset lai bo luu
					countSame = 1;
					countBlank = 0;
					notTestNext = false;

					for (i = 1; x + i < 15 && y + i < 15; i++)
						if (boardLogicCopy[x + i][y + i] == boardLogicCopy[x][y]) {
							arrayTemp[countSame].x = x + i;
							arrayTemp[countSame].y = y + i;

							countSame++;

						} else if (countBlank < 2 && boardLogicCopy[x + i][y + i] == 0) {
							countBlank++;
							if (countBlank == 2)
								break;
						} else {
							break;
						}
					if (countSame <= 4) {
						for (; x + i < 15 && y + i < 15; i++)
							if (boardLogicCopy[x + i][y + i] == 0) {
								countBlank++;
							} else
								break;
						if (x + i >= 15 || y + i >= 15)
							countBlank++;
						for (i = 1; x - i >= 0 && y - i >= 0; i++)
							if (boardLogicCopy[x - i][y - i] == boardLogicCopy[x][y]) {

								arrayTemp[countSame].x = x - i;
								arrayTemp[countSame].y = y - i;
								countSame++;
							} else if (countBlank < 2 && boardLogicCopy[x - i][y - i] == 0) {
								countBlank++;
								if (countBlank == 2)
									break;
							} else {
								break;
							}
						if (countSame == 4) {
							for (; x - i >= 0 && y - i >= 0; i++)
								if (boardLogicCopy[x - i][y - i] == 0) {
									countBlank++;
									if (countBlank >= 2)
										break;
								} else
									break;
							if (x - i < 0 || y - i < 0)
								countBlank++;
							if (countBlank >= 2) {

								// co the win
								for (i = 0; i < 4; i++) {
									if (boardLogicCopy[x][y] == X_STEP)
										boardLogicChange[(int) arrayTemp[i].x][(int) arrayTemp[i].y] = X_WIN_STEP;
									else
										boardLogicChange[(int) arrayTemp[i].x][(int) arrayTemp[i].y] = Y_WIN_STEP;
									arrayCheck[(int) arrayTemp[i].x][(int) arrayTemp[i].y] = true;
								}
							}
						}
					}
					// System.out.println(countSame);
					// reset lai bo luu
					countSame = 1;
					countBlank = 0;
					notTestNext = false;

					for (i = 1; y - i >= 0; i++)
						if (boardLogicCopy[x][y - i] == boardLogicCopy[x][y]) {
							arrayTemp[countSame].x = x;
							arrayTemp[countSame].y = y - i;
							countSame++;

						} else if (countBlank < 2 && boardLogicCopy[x][y - i] == 0) {
							countBlank++;
							if (countBlank == 2)
								break;
						} else
							break;
					if (countSame <= 4) {
						for (; countBlank < 2 && y - i >= 0; i++)
							if (boardLogicCopy[x][y - i] == 0) {
								countBlank++;
							} else
								break;
						if (y - i < 0)
							countBlank++;
						for (i = 1; y + i < 15; i++)
							if (boardLogicCopy[x][y + i] == boardLogicCopy[x][y]) {

								arrayTemp[countSame].x = x;
								arrayTemp[countSame].y = y + i;
								countSame++;
							} else if (countBlank < 2 && boardLogicCopy[x][y + i] == 0) {
								countBlank++;
								if (countBlank == 2)
									break;
							} else
								break;
						if (countSame == 4) {
							for (; y + i < 15; i++)
								if (boardLogicCopy[x][y + i] == 0) {
									countBlank++;
									if (countBlank >= 2)
										break;
								} else
									break;
							if (y + i >= 15)
								countBlank++;
							if (countBlank >= 2) {

								// co the win
								for (i = 0; i < 4; i++) {
									if (boardLogicCopy[x][y] == X_STEP)
										boardLogicChange[(int) arrayTemp[i].x][(int) arrayTemp[i].y] = X_WIN_STEP;
									else
										boardLogicChange[(int) arrayTemp[i].x][(int) arrayTemp[i].y] = Y_WIN_STEP;
									arrayCheck[(int) arrayTemp[i].x][(int) arrayTemp[i].y] = true;
								}
							}
						}
					}

					// System.out.println(countSame);
					// reset lai bo luu
					countSame = 1;
					countBlank = 0;
					notTestNext = false;

					for (i = 1; x + i < 15; i++)
						if (boardLogicCopy[x + i][y] == boardLogicCopy[x][y]) {
							arrayTemp[countSame].x = x + i;
							arrayTemp[countSame].y = y;

							countSame++;

						} else if (countBlank < 2 && boardLogicCopy[x + i][y] == 0) {
							countBlank++;
							if (countBlank == 2)
								break;
						} else
							break;
					if (countSame <= 4) {
						for (; countBlank < 2 && x + i < 15; i++)
							if (boardLogicCopy[x + i][y] == 0) {
								countBlank++;
							} else
								break;
						if (x + i >= 15)
							countBlank++;

						for (i = 1; x - i >= 0; i++)
							if (boardLogicCopy[x - i][y] == boardLogicCopy[x][y]) {

								arrayTemp[countSame].x = x - i;
								arrayTemp[countSame].y = y;
								countSame++;
							} else if (countBlank < 2 && boardLogicCopy[x - i][y] == 0) {
								countBlank++;
								if (countBlank == 2)
									break;
							} else
								break;
						if (countSame == 4) {
							for (; x - i >= 0; i++)
								if (boardLogicCopy[x - i][y] == 0) {
									countBlank++;
									if (countBlank >= 2)
										break;
								} else
									break;
							if (x - i < 0)
								countBlank++;
							if (countBlank >= 2) {

								// co the win
								for (i = 0; i < 4; i++) {
									if (boardLogicCopy[x][y] == X_STEP)
										boardLogicChange[(int) arrayTemp[i].x][(int) arrayTemp[i].y] = X_WIN_STEP;
									else
										boardLogicChange[(int) arrayTemp[i].x][(int) arrayTemp[i].y] = Y_WIN_STEP;
									arrayCheck[(int) arrayTemp[i].x][(int) arrayTemp[i].y] = true;
								}
							}
						}
					}

				}

			}
	}

	public void copyBoard(int array1[][], int array2[][]) {
		int i, j;
		for (i = 0; i < array1.length; i++)
			for (j = 0; j < array2[i].length; j++) {
				array2[i][j] = array1[i][j];
				if (array2[i][j] == X_WIN_STEP)
					array2[i][j] = X_STEP;
				else if (array2[i][j] == Y_WIN_STEP)
					array2[i][j] = Y_STEP;
			}

	}

}
