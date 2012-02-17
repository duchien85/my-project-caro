package com.gsn.caro.asset;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gsn.engine.gdx.GsnParticleEffect;

public class ImageAsset {
	private static ImageAsset _instance;

	static AssetManager manager;
	
	final static String tag = ImageAsset.class.getSimpleName();

	public static ImageAsset getInstance() {
		if (_instance == null)
			_instance = new ImageAsset();
		return _instance;
	}

	public AtlasRegion backActiveBtn;
	public AtlasRegion backDeactiveBtn;

	public AtlasRegion betBG;
	public AtlasRegion board;
	public GsnParticleEffect clickEffect = new GsnParticleEffect();
	public BitmapFont font = new BitmapFont();
	public AtlasRegion showInfoBtn;
	public AtlasRegion showInfoBtnDown;
	public AtlasRegion hideInfoBtn;
	public AtlasRegion hideInfoBtnDown;
	public AtlasRegion clockBG;	
	public AtlasRegion pieceO;
	public AtlasRegion pieceX;
	public AtlasRegion scoreBG;
	public AtlasRegion boardBorder;
	public AtlasRegion boardBG;
	public List<AtlasRegion> board9Path;
	public List<AtlasRegion> bubleChatOther9Path;
	public List<AtlasRegion> bubleChatMe9Path;
	public List<AtlasRegion> infoUserBG9Path;
	public List<AtlasRegion> numberPointRed;
	public List<AtlasRegion> numberPointBlue;
	public List<AtlasRegion> time;

	public AtlasRegion menuBtn;
	public AtlasRegion menuBtnDown;
	
	public AtlasRegion iconMe;
	public AtlasRegion iconOther;

	public AtlasRegion iconGold;
	
	public AtlasRegion chatBtn;
	public AtlasRegion chatBtnDown;
	public AtlasRegion greyBG;
	
	public AtlasRegion avatar;	
	
	public TextureRegion musicOn;
	public TextureRegion musicOff;
	public TextureRegion musicClick;
	
	public TextureRegion soundOn;
	public TextureRegion soundOff;
	public TextureRegion soundClick;
	
	public TextureRegion exitNormal;
	public TextureRegion exitClick;
	
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
	
	public TextureRegion enterRoom;
	public TextureRegion enterRoomClick;
	public AtlasRegion background;
	public AtlasRegion avatarLobby;
	public AtlasRegion num100Gold;
	public AtlasRegion num500Gold;
	public AtlasRegion num5000Gold;
	public AtlasRegion num1G;
	public AtlasRegion num10G;
	
	public AtlasRegion iconXu;
	public AtlasRegion quickChat;
	public TextureRegion sendButton;
	public TextureRegion sendButtonClick;
	
	private ImageAsset() {		
	}

	private void assignContent() {
		clickEffect.load(Gdx.files.internal("data/particle/click.p"), Gdx.files.internal("data/particle"));
		TextureAtlas atlas = manager.get("data/content/pack", TextureAtlas.class);
		clockBG = atlas.findRegion("khung dong ho");		
		betBG = atlas.findRegion("nen muc cuoc");
		scoreBG = atlas.findRegion("bang ti so");
		backActiveBtn = atlas.findRegion("nut back");
		backDeactiveBtn = atlas.findRegion("nut back an");
		showInfoBtn = atlas.findRegion("nut so");
		showInfoBtnDown = atlas.findRegion("nut so an");
		board = atlas.findRegion("ban choi");
		pieceO = atlas.findRegion("dau o");
		pieceX = atlas.findRegion("dau x");
		boardBorder = atlas.findRegion("khung ban choi");
		
		board9Path = atlas.findRegions("boarder");
		boardBG = atlas.findRegion("BG ban choi");
		
		menuBtn = atlas.findRegion("nut menu");
		menuBtnDown = atlas.findRegion("nut menu an");
		
		iconGold = atlas.findRegion("icon $ muc cuoc");
		
		iconMe = atlas.findRegion("icon nguoi choi");
		iconOther = atlas.findRegion("icon doi thu");
		bubleChatOther9Path = atlas.findRegions("bubble-chat-2");
		bubleChatMe9Path = atlas.findRegions("bubble-chat-1");
		infoUserBG9Path = atlas.findRegions("nen-thong-tin");
		
		hideInfoBtn = atlas.findRegion("nut dong");
		hideInfoBtnDown = atlas.findRegion("nut dong an");
		
		chatBtn = atlas.findRegion("nut chat");
		chatBtnDown = atlas.findRegion("nut chat an");
		avatar = atlas.findRegion("khung avatar");	
		greyBG = atlas.findRegion("nen-xam");		
		
		musicClick = atlas.findRegion("nut nhac an");
		musicOn = atlas.findRegion("nut nhac");
		musicOff = atlas.findRegion("nut nhac an");
		
		soundClick = atlas.findRegion("nut loa an");
		soundOn = atlas.findRegion("nut loa");
		soundOff = atlas.findRegion("nut loa an");
		
		exitNormal = atlas.findRegion("nut thoat");
		exitClick = atlas.findRegion("nut thoat an");
		
		bet100_1 = atlas.findRegion("muc cuoc 100$");
		bet100_2 = atlas.findRegion("muc cuoc 100$ an");
		bet100_3 = atlas.findRegion("muc cuoc 100$ disable");

		bet500_1 = atlas.findRegion("muc cuoc 500$");
		bet500_2 = atlas.findRegion("muc cuoc 500$ an");
		bet500_3 = atlas.findRegion("muc cuoc 500$ disable");

		bet5000_1 = atlas.findRegion("muc cuoc 5k$");
		bet5000_2 = atlas.findRegion("muc cuoc 5k$ an");
		bet5000_3 = atlas.findRegion("muc cuoc 5k$ disable");

		bet1G_1 = atlas.findRegion("muc cuoc 1G");
		bet1G_2 = atlas.findRegion("muc cuoc 1G an");
		bet1G_3 = atlas.findRegion("muc cuoc 1G disable");

		bet10G_1 = atlas.findRegion("muc cuoc 10G");
		bet10G_2 = atlas.findRegion("muc cuoc 10G an");
		bet10G_3 = atlas.findRegion("muc cuoc 10G disable");
		
		enterRoom = atlas.findRegion("nut choi ngay");
		enterRoomClick = atlas.findRegion("nut choi ngay an");
		background = atlas.findRegion("BG lobby");
		avatarLobby = atlas.findRegion("khung avatar lobby");
		
		num100Gold = atlas.findRegion("cuoc 100$");
		num500Gold = atlas.findRegion("cuoc 500$");
		num5000Gold = atlas.findRegion("cuoc 5k$");
		num1G = atlas.findRegion("cuoc 1G");
		num10G = atlas.findRegion("cuoc 10G");
		iconXu = atlas.findRegion("icon G muc cuoc");
		numberPointRed = atlas.findRegions("ti so do");
		numberPointBlue = atlas.findRegions("ti so xanh");
		quickChat = atlas.findRegion("khung chat");
		sendButton = atlas.findRegion("nut gui");
		sendButtonClick = atlas.findRegion("nut gui an");
		time = atlas.findRegions("time vang");
		
		font =  manager.get("data/font/comic.fnt", BitmapFont.class);
	}

	public void create() {
		Resolution[] resolutions = { new Resolution(240, 320, "240320"), new Resolution(480, 800, "480800")};
		ResolutionFileResolver resolver = new ResolutionFileResolver(new InternalFileHandleResolver(), resolutions);
		manager = new AssetManager(resolver);
		manager.setErrorListener(new AssetErrorListener() {
			@Override
			public void error(String fileName, Class type, Throwable throwable) {
				// TODO Auto-generated method stub
				Gdx.app.error("AssetManagerTest", "couldn't load asset '" + fileName + "'", (Exception) throwable);
			}
		});
		loadAll();
		Texture.setAssetManager(manager);		
		// font = new BitmapFont(Gdx.files.internal("data/font.fnt"), false);
		//font = new BitmapFont(Gdx.files.internal("data/font/comic.fnt"), Gdx.files.internal("data/font/comic.png"), false);
		
	}

	public void finishLoading() {
		manager.finishLoading();
	}

	private void loadAll() {
		manager.load("data/content/pack", TextureAtlas.class);
		manager.load("data/font/comic.fnt", BitmapFont.class);
		finishLoading();
		assignContent();
	}
}


