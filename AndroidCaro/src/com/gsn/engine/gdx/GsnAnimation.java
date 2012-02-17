package com.gsn.engine.gdx;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GsnAnimation extends Image {
	float period = 0;
	final TextureRegion[] keyFrames;
	public float frameDuration;
	Boolean loop = false;
	public interface IFinishEffectListener
	{
		public String typeEffect = "";
		public void onFinish();
	}
	public IFinishEffectListener listener;
	public GsnAnimation(float frameDuration, List<AtlasRegion> a) {
		super(a.get(0));
		this.frameDuration = frameDuration;
		this.keyFrames = new TextureRegion[a.size()];
		for (int i = 0, n = a.size(); i < n; i++) {
			this.keyFrames[i] = a.get(i);
		}
	}

	public void resetTime() {
		period = 0;
	}

	public TextureRegion getStaticKeyFrame(int index) {
		return keyFrames[index];
	}

	public TextureRegion getKeyFrame(float stateTime, boolean looping) {
		int frameNumber = (int) (stateTime / frameDuration);
		if (!looping) {
			if (frameNumber >= keyFrames.length) {
				return null;
			}
			frameNumber = Math.min(keyFrames.length - 1, frameNumber);

		} else {
			frameNumber = frameNumber % keyFrames.length;
		}
		return keyFrames[frameNumber];
	}

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		period += delta;
		TextureRegion frame = getKeyFrame(period, false);
		if (frame != null)
			setRegion(frame);
		else {
			if(loop)
			{
				period = 0;
			}
			else
			{
				if(listener!=null)
					listener.onFinish();
				remove();
				resetTime();
			}
		}
		// System.out.println("act animation... " + delta );
	}
	public void setLoop(Boolean _loop)
	{
		loop = _loop;
	}
	public void setListener(IFinishEffectListener finish)
	{
		listener = finish;
	}
}
