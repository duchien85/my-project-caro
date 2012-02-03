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
	public AtlasRegion infoActiveBtn;
	public AtlasRegion infoDeactiveBtn;
	public AtlasRegion clockBG;	
	public AtlasRegion pieceO;
	public AtlasRegion pieceX;
	public AtlasRegion scoreBG;
	public AtlasRegion boardBorder;
	public AtlasRegion boardBG;
	public List<AtlasRegion> board9Path;

	public AtlasRegion menuBtn;
	public AtlasRegion menuBtnDown;
	
	public AtlasRegion iconMe;
	public AtlasRegion iconOther;

	public AtlasRegion iconGold;	
	
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
		infoActiveBtn = atlas.findRegion("nut hien thong tin");
		infoDeactiveBtn = atlas.findRegion("nut hien thong tin an");
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
	}

	public void create() {
		Resolution[] resolutions = { new Resolution(320, 480, "320480"), new Resolution(480, 800, "480800"), new Resolution(480, 856, "480854") };
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
		font = new BitmapFont(Gdx.files.internal("data/font/comic.fnt"), Gdx.files.internal("data/font/comic.png"), false);
	}

	public void finishLoading() {
		manager.finishLoading();
	}

	private void loadAll() {
		manager.load("data/content/pack", TextureAtlas.class);
		finishLoading();
		assignContent();
	}
}
// ===================================================================
// Anh khÃ³c, vÃ¬ giá»� Ä‘Ã¢y anh Ä‘Ã£ máº¥t em rá»“i
// Anh khÃ³c, vÃ¬ giá»� Ä‘Ã¢y em Ä‘Ã£ xa tháº­t rá»“i.
// Anh nhá»›, lá»�i háº¹n Æ°á»›c ta khÃ´ng xa rá»�i.
// MÃ  giá»� Ä‘Ã¢y, sao chá»‰ anh láº» loi
// Anh cá»©, cá»‘ gáº¯ng táº¡i sao váº«n cá»© xa vá»�i
// Anh vÃ  em, dÆ°á»�ng nhÆ° ta Ä‘Ã£ háº¿t yÃªu nhau rá»“i.
// Em hÃ£y nÃ³i cho anh nghe Ä‘i há»¡i ngÆ°á»�i yÃªu Æ¡i.
// Táº¡i sao giá»� chÃºng ta lÃ¬a Ä‘Ã´i.
// ===================================================================
// Táº¡i anh Ä‘Ã£ vÃ´ tÃ¢m hay táº¡i anh khÃ´ng quan tÃ¢m em má»—i ngÃ y.
// Ä�á»ƒ giá»� Ä‘Ã¢y khi lá»�i anh nÃ³i, em khÃ´ng tin anh ná»¯a váº­y.
// Trong tÃ¬nh yÃªu, Ä‘Ã´i lÃºc ta hay giáº­n há»�n anh biáº¿t.
// NhÆ°ng anh tháº¥y,giá»� em khÃ´ng cÃ²n yÃªu anh.
// Váº­y thÃ´i anh cho em Ä‘i vá»� nÆ¡i em chÆ°a báº¯t Ä‘áº§u
// NÆ¡i mÃ  em khi chÆ°a quen anh, anh tháº¥y em vui hÆ¡n nhiá»�u.
// Anh xin lá»—i, vÃ¬ Ä‘Ã£ cÆ°á»›p máº¥t khoáº£ng trá»�i cá»§a em, nhÆ°ng cÃ³
// ngÆ°á»�i sáº½ cho em láº¡i má»™t báº§u trá»�i.
