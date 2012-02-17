package com.gsn.engine.template;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.gsn.caro.asset.ImageAsset;
import com.gsn.engine.myplay.GsnStage;
import com.gsn.test.PlayStage;

public class GSNQuickChat extends GsnStage {

	int NUM_LABEL = 8;
	PlayStage parent;
	Label arrayLabel [];
	Label contentLabel;
	Image backGround;
	String arrayString[] = {"Nhanh lên bạn", "Đánh nhầm", "Giỏi thế", "Pro quá", "Không dễ đâu sói ạ", "Ngu vãi chưởng", "Lâu thế", "Khó của Nam Cường"};
	ImageButton send;
	public GSNQuickChat(PlayStage parent, float width, float height) {
		super(width, height, false);
		this.parent = parent;
		backGround = new Image(ImageAsset.getInstance().quickChat);
		this.addActor(backGround);
		arrayLabel = new Label[NUM_LABEL];
		int i;
		for (i=0; i< NUM_LABEL; i++)
		{
			arrayLabel[i] = new Label(arrayString[i], new Label.LabelStyle(ImageAsset.getInstance().font, new Color(1, 1, 1, 1)));
			this.addActor(arrayLabel[i]);
			arrayLabel[i].x = backGround.width / 10;
			arrayLabel[i].y = (i + 1.5f)* backGround.height / 12;
		}
		contentLabel = new Label("", new Label.LabelStyle(ImageAsset.getInstance().font, new Color(1, 1, 1, 1)));
		this.addActor(contentLabel);
		contentLabel.x = backGround.width / 10;
		contentLabel.y = 8.6f * backGround.height/ 10;
		send = new ImageButton(ImageAsset.getInstance().sendButton, ImageAsset.getInstance().sendButtonClick);
		send.setClickListener(new ClickListener() {
			
			@Override
			public void click(Actor actor, float x, float y) {
				// TODO Auto-generated method stub
				GSNQuickChat.this.parent.hideQuickChat();
				GSNQuickChat.this.parent.chatOther(contentLabel.getText());
			}
		});
		this.addActor(send);
		send.x = 7.8f * backGround.width / 10;
		send.y = 8.1f * backGround.height/ 10;
	}
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		super.touchUp(x, y, pointer, button);
		int i;
		Vector2 out = new Vector2();
		this.toStageCoordinates(x, y, out);
		for (i = NUM_LABEL-1; i>0; i--)
		{
			//Gdx.app.log(" fdsf ", bac)
			
			if(out.y < (i + 1.5)* backGround.height / 12 + backGround.height / 24 && out.y > (i + 0.5)* backGround.height / 12 + backGround.height / 24)
			{
				contentLabel.setText(arrayString[i]);
				break;
			}
		}
		return true;
	}

}
