package com.gsn.engine.template;



import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

public class GSNButton extends ImageButton {
	public int id;
	ClickListener clickListener;
	Boolean enable;
	ImageButtonStyle enableState;
	ImageButtonStyle disableState;
	
	
	
	public GSNButton(int id, TextureRegion normal, TextureRegion down, TextureRegion up, TextureRegion disable) {
		// TODO Auto-generated constructor stub
		
		super(normal, down, up);
		enableState = new ImageButtonStyle(null, null, null, 0, 0, 0, 0, normal, down, up);	
		
		if(disable!=null)
		{
			disableState = new ImageButtonStyle(null, null, null, 0, 0, 0, 0, disable, disable, disable);
		}
		this.id = id;
	}
	
	public GSNButton(int id, TextureRegion normal, TextureRegion down, TextureRegion disable) {
		// TODO Auto-generated constructor stub
		
		super(normal, down, disable);
		enableState = new ImageButtonStyle(null, null, null, 0, 0, 0, 0, normal, down, normal);	
		
		if(disable!=null)
		{
			disableState = new ImageButtonStyle(null, null, null, 0, 0, 0, 0, disable, disable, disable);
		}
		this.id = id;
	}
	
	
	public void setAndSaveClickListener(ClickListener listener) {
		// TODO Auto-generated method stub
		super.setClickListener(listener);
		clickListener = listener;
	}
	
	public void setEnable(Boolean _enable) {
		// TODO Auto-generated method stub
		
		enable = _enable;
		if(enable)
		{
			super.setStyle(enableState);
			setClickListener(clickListener);
		}
		else
		{
			super.setStyle(disableState);
			setClickListener(null);
		}
		
	}
}
