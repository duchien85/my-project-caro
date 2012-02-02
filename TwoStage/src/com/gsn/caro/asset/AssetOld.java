package com.gsn.caro.asset;

import java.util.List;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetOld extends AssetManager implements AssetErrorListener {
	private final String pack_url = "gdx/pack.txt";

	public TextureRegion Xavatar;
	public TextureRegion Oavatar;
	public TextureRegion avatarReady;
	public TextureRegion defaultAvatar;
	public Sprite cuocBg;
	public Sprite cuocXu;

	public Sprite board;
	public Sprite pieceO;
	public Sprite pieceX;
	public TextureRegion ready;
	public TextureRegion readyDown;
	public Sprite waitReady;
	public TextureRegion back;
	public TextureRegion backDown;
	public TextureRegion iconChat;
	public TextureRegion iconChatDown;

	public TextureRegion background;
	public Sprite backgroundInGame;
	public Sprite leftBg;
	public Sprite waitOpponent;
	public TextureRegion khungBanChoi;

	public TextureRegion dongho;
	public TextureRegion dauOeffect;
	public TextureRegion dauXeffect;
	public List<AtlasRegion> numbers;
	public List<AtlasRegion> timeNumbers;
	public TextureRegion avatarIngame;
	public TextureRegion chon;
	public Sprite goldSign;
	public Sprite GSign;

	// lobby
	public TextureRegion bet100_1;
	public TextureRegion bet100_2;
	public TextureRegion bet100_3;

	public TextureRegion bet500_1;
	public TextureRegion bet500_2;
	public TextureRegion bet500_3;

	public TextureRegion bet5000_1;
	public TextureRegion bet5000_2;
	public TextureRegion bet5000_3;

	public TextureRegion bet1G_1;
	public TextureRegion bet1G_2;
	public TextureRegion bet1G_3;

	public TextureRegion bet10G_1;
	public TextureRegion bet10G_2;
	public TextureRegion bet10G_3;

	public TextureRegion choingayEnable;
	public TextureRegion choingayDisable;
	public TextureRegion choingayDown;
	public Sprite nameBg;
	public TextureRegion avatarLobby;

	// login
	public TextureRegion loginButton;
	public TextureRegion loginButtonDown;
	public TextureRegion enterGameButton;
	public TextureRegion enterGameButtonDown;
	public TextureRegion logoutButton;
	public TextureRegion logoutButtonDown;

	public TextureRegion iconXu;
	public TextureRegion iconGold;

	// menu
	public TextureRegion menuBody;
	public TextureRegion soundMenu;
	public TextureRegion resetCameraMenu;
	public TextureRegion musicMenu;
	public TextureRegion exitRoomMenu;
	public TextureRegion disableMenu;
	public TextureRegion menuButton;
	public TextureRegion menuButtonDown;
	public Sprite bangtiso;
	public List<AtlasRegion> winEffect;
	// dialog
	public TextureRegion dialogBg;
	public TextureRegion okBtn;
	public TextureRegion cancelBtn;
	public TextureRegion okBtnDown;
	public TextureRegion cancelBtnDown;
	public Sprite dialogButton;	
	// public Animation bobJump;
	public Sprite menuItem;

	// sound
	public Music music;
	public Sound hitSound;
	public Sound soundWin;
	public Sound soundLose;
	public Sound soundClick;
	public Sound soundTime;

	// chat
	public TextureRegion chatBg;
	// public TextureRegion chatTab;
	// public TextureRegion emoTab;
	public TextureRegion sendBtn;
	public TextureRegion sendBtnDown;
	public TextureRegion closeBtn;
	public TextureRegion closeBtnDown;
	public List<AtlasRegion> chatSentences;
	public List<AtlasRegion> chatSentencesDown;

	public List<AtlasRegion> bbChat;

	public void finishLoadingAll() {
		// load("gdx/img1.png", Texture.class);
		loadSound();

		load(pack_url, TextureAtlas.class);
		finishLoading();
		assignContent();
	}

	public synchronized void loadAll() {
		// load("gdx/img1.png", Texture.class);
		loadSound();
		load(pack_url, TextureAtlas.class);
	}

	public void assignContent() {
		TextureAtlas atlas = get(pack_url, TextureAtlas.class);
		defaultAvatar = atlas.findRegion("defaultAvatar");
		background = atlas.findRegion("background");
		backgroundInGame = atlas.createSprite("backgroundInGame");
		leftBg = atlas.createSprite("leftBg");
		numbers = atlas.findRegions("so");
		timeNumbers = atlas.findRegions("timer");
		board = atlas.createSprite("board");
		pieceO = atlas.createSprite("dauO");
		pieceX = atlas.createSprite("dauX1");
		ready = atlas.findRegion("ready");
		readyDown = atlas.findRegion("readyDown");
		waitReady = atlas.createSprite("waitOpponentReady");
		back = atlas.findRegion("back");
		backDown = atlas.findRegion("backDown");
		menuItem = atlas.createSprite("menu");
		waitOpponent = atlas.createSprite("waitOpponent");

		// cuXH= Utility.getAnimationFromAtlas(0.1f, atlas, "CuXH");
		dongho = atlas.findRegion("DongHoDemNguoc");
		avatarReady = atlas.findRegion("avatarReady");
		Xavatar = atlas.findRegion("Xavatar");
		Oavatar = atlas.findRegion("Oavatar");
		cuocBg = atlas.createSprite("cuocGold");
		cuocXu = atlas.createSprite("cuocXu");
		dauOeffect = atlas.findRegion("dauOeffect");
		dauXeffect = atlas.findRegion("dauXeffect");

		khungBanChoi = atlas.findRegion("khungBanChoi");
		// avatar
		avatarIngame = atlas.findRegion("avatarIngame");

		// lobby
		chon = atlas.findRegion("chon");
		choingayEnable = atlas.findRegion("choingayEnable");
		choingayDisable = atlas.findRegion("choingayDisable");
		choingayDown = atlas.findRegion("choingayDown");
		goldSign = atlas.createSprite("goldSign");
		GSign = atlas.createSprite("GSign");
		nameBg = atlas.createSprite("nameBg");

		bet100_1 = atlas.findRegion("bet1001");
		bet100_2 = atlas.findRegion("bet1002");
		bet100_3 = atlas.findRegion("bet1003");

		bet500_1 = atlas.findRegion("bet5001");
		bet500_2 = atlas.findRegion("bet5002");
		bet500_3 = atlas.findRegion("bet5003");

		bet5000_1 = atlas.findRegion("bet50001");
		bet5000_2 = atlas.findRegion("bet50002");
		bet5000_3 = atlas.findRegion("bet50003");

		bet1G_1 = atlas.findRegion("bet1G1");
		bet1G_2 = atlas.findRegion("bet1G2");
		bet1G_3 = atlas.findRegion("bet1G3");

		bet10G_1 = atlas.findRegion("bet10G1");
		bet10G_2 = atlas.findRegion("bet10G2");
		bet10G_3 = atlas.findRegion("bet10G3");

		iconXu = atlas.findRegion("iconXu");
		iconGold = atlas.findRegion("iconGold");
		bangtiso = atlas.createSprite("bangtiso");
		avatarLobby = atlas.findRegion("avatarLobby");

		// login
		loginButton = atlas.findRegion("loginButton");
		loginButtonDown = atlas.findRegion("loginButtonDown");
		logoutButton = atlas.findRegion("logoutButton");
		logoutButtonDown = atlas.findRegion("logoutButtonDown");
		enterGameButton = atlas.findRegion("enterGame");
		enterGameButtonDown = atlas.findRegion("enterGameDown");

		// dialog
		dialogBg = atlas.findRegion("dialogBg");
		dialogButton = atlas.createSprite("dialogButton");
		okBtn = atlas.findRegion("okBtn");
		cancelBtn = atlas.findRegion("cancelBtn");
		okBtnDown = atlas.findRegion("okBtnDown");
		cancelBtnDown = atlas.findRegion("cancelBtnDown");

		// menu
		// menuBg = atlas.createSprite("menuBg");
		menuBody = atlas.findRegion("menuBody");
		musicMenu = atlas.findRegion("music");
		exitRoomMenu = atlas.findRegion("exitRoom");
		soundMenu = atlas.findRegion("sound");
		disableMenu = atlas.findRegion("menuButtonDisable");
		menuButton = atlas.findRegion("menuButton");
		menuButtonDown = atlas.findRegion("menuButtonDown");

		// chat
		chatBg = atlas.findRegion("chatBg");
		// chatTab = atlas.findRegion("chatTab");
		// emoTab = atlas.findRegion("emoTab");
		chatSentences = atlas.findRegions("chatSentences");
		chatSentencesDown = atlas.findRegions("chatSentencesDown");
		sendBtn = atlas.findRegion("sendBtn");
		sendBtnDown = atlas.findRegion("sendBtnDown");
		closeBtn = atlas.findRegion("closeBtn");
		closeBtnDown = atlas.findRegion("closeBtnDown");
		bbChat = atlas.findRegions("bbchat");
		iconChat = atlas.findRegion("iconChat");
		iconChatDown = atlas.findRegion("iconChatDown");

		winEffect = atlas.findRegions("Thang");
	}

	public void loadSound() {
	}

	public void finishLoadingPack(String pack) {
		while (!isLoaded(pack)) {
			update();
		}
	}

	private AssetOld() {
		super();
		create();
	}

	private static AssetOld _instance;

	public static AssetOld getInstance() {
		if (_instance == null)
			_instance = new AssetOld();
		return _instance;
	}

	public void create() {
		Gdx.app.setLogLevel(Application.LOG_ERROR);
		Resolution[] resolutions = { new Resolution(320, 240, "240320"), new Resolution(480, 800, "480800"), new Resolution(856, 480, ".480854") };
		ResolutionFileResolver resolver = new ResolutionFileResolver(new InternalFileHandleResolver(), resolutions);
		setLoader(Texture.class, new TextureLoader(resolver));
		setLoader(TextureAtlas.class, new TextureAtlasLoader(resolver));
		setErrorListener(this);
		Texture.setAssetManager(this);
	}

	@Override
	public void error(String fileName, Class type, Throwable throwable) {
		// TODO Auto-generated method stub
		Gdx.app.error("AssetManagerTest", "couldn't load asset '" + fileName + "'", (Exception) throwable);
	}

	public TextureRegion getAvatarRegion() {
		if (isLoaded(pack_url))
			return get(pack_url, TextureAtlas.class).findRegion("avatar");
		else {
			// Debug.trace("unloaded ne");
			return null;
		}
	}
}
