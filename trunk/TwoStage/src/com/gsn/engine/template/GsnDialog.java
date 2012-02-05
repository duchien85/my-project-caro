package com.gsn.engine.template;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.gsn.engine.ActorUtility;

public class GsnDialog extends Group {
	public GsnDialog(Image background, ImageButton button) {
		super();
		
		this.width = background.width;
		this.height = background.height;
		
		ActorUtility.setCenterBottom(button, this.width / 2, 0);
		
		this.addActor(background);
		this.addActor(button);
	}
}
