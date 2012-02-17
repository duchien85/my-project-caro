package com.gsn.User;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.GdxUtility;
import com.gsn.engine.layout.GsnTableLayout;

public class InfoGameGroup extends Group {
	InfoOneUser userMe;
	InfoOneUser userOther;

	public InfoGameGroup(float width) {
		ImageAsset asset = ImageAsset.getInstance();
		Image infoBG = new Image(new NinePatch(GdxUtility.convertListRegionToArray(asset.infoUserBG9Path)));
		userMe = new InfoOneUser(ImageAsset.getInstance().font);
		userOther = new InfoOneUser(ImageAsset.getInstance().font);

		infoBG.width = width;
		infoBG.height = userMe.height + userMe.height / 10;

		this.width = infoBG.width;
		this.height = infoBG.height;

		GsnTableLayout table = new GsnTableLayout(0, 0, width, height);
		this.addActor(infoBG);
		this.addActor(userMe);
		this.addActor(userOther);
		
		table.newRow(true, 1);		
		table.add(0.5f).putCenter(userMe);
		table.add(0.5f).putCenter(userOther);
		
		//userOther.x = width / 2 + (width / 2 - userOther.width/2)/2;
	//	userOther.y = (infoBG.height - userOther.height)/2;
	}

	public void setMeInfo(String name, int gold, int xu, int point, int id, int numWin, int numLose, String avatar) {
		userMe.setInfoUser(name, gold, xu, point, id, numWin, numLose, avatar);
	}

	public void setOtherInfo(String name, int gold, int xu, int point, int id, int numWin, int numLose, String avatar)

	{
		userOther.setInfoUser(name, gold, xu, point, id, numWin, numLose, avatar);
	}

	public Boolean checkMeStart(int id) {
		if (id == userMe.info.uid)
			return true;
		return false;
	}

	public UserInfo getMeInfo() {
		return userMe.info;
	}

	public UserInfo getOtherInfo() {
		return userOther.info;
	}

	public void setContentUser() {
		//userMe.setContentUser();
		//userOther.setContentUser();
	}

}
