package com.gsn.engine.template;



import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

public class GSNButton extends ImageButton {
	ClickListener clickListener;
	Boolean enable;
	ImageButtonStyle enableState;
	ImageButtonStyle disableState;
	
	public GSNButton(NinePatch normal, NinePatch down, NinePatch up, NinePatch disable) {
		// TODO Auto-generated constructor stub
		super(normal, down, up);
		enableState = new ImageButtonStyle(normal, down, up, 0, 0, 0, 0, normal, down, up);	
		
		if(disable!=null)
		{
			disableState = new ImageButtonStyle(disable, disable, disable, 0, 0, 0, 0, disable, disable, disable);
		}
	}
	
	public GSNButton(NinePatch normal, NinePatch down, NinePatch disable) {
		// TODO Auto-generated constructor stub
		super(normal, down, normal);
		enableState = new ImageButtonStyle(normal, down, normal, 0, 0, 0, 0, normal, down, normal);	
		
		if(disable!=null)
		{
			disableState = new ImageButtonStyle(disable, disable, disable, 0, 0, 0, 0, disable, disable, disable);
		}
	}
	
	public GSNButton(TextureRegion normal, TextureRegion down, TextureRegion up) {
		// TODO Auto-generated constructor stub
		super(normal, down, up);
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
