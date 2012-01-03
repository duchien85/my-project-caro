package com.gsn.caro.asset;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GsnParticleEffect extends ParticleEffect {
	private SpriteBatch batcher;
	
	public GsnParticleEffect(){
		super();
	}
	
	public GsnParticleEffect(SpriteBatch batcher){
		super();
		setSpriteBatch(batcher);
	}
	
	public void setSpriteBatch(SpriteBatch batcher){
		this.batcher = batcher;
	}
	
	public SpriteBatch getSpriteBatch(){
		return batcher;
	}
	
	public void draw(){
		this.draw(batcher);
	}
}
