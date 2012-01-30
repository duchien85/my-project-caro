package com.gsn.engine.layout;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gsn.caro.asset.DataProvider;

public class GsnTableLayout {
	private float tmpHeight, tmpWidth, tmpX, tmpY, oldHeight;
	public float x, y, width, height;
	public List<GsnRectangle> list;

	public GsnTableLayout(float x, float y, float width, float height) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tmpX = x;
		this.tmpY = y;
		this.oldHeight = 0;
		list = new ArrayList<GsnRectangle>();
	}
	
	public GsnTableLayout(GsnRectangle table) {
		// TODO Auto-generated constructor stub
		this(table.x, table.y, table.width, table.height);
	}

	public GsnRectangle getBoundingRectangle() {
		return new GsnRectangle(x, y, width, height);
	}

	public void newRow(float rHeight) {
		this.tmpX = x;
		this.tmpY = this.tmpY + oldHeight;

		oldHeight = tmpHeight = rHeight * height;
	}

	public void addList(float... rWidths) {
		for (int i = 0; i < rWidths.length; i++)
			add(rWidths[i]);
	}

	public void add(float rWidth) {
		tmpWidth = width * rWidth;
		list.add(new GsnRectangle(tmpX, tmpY, tmpWidth, tmpHeight));
		tmpX += tmpWidth;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + x + ", " + y + ", " + width + ", " + height + ")";
	}

	public Image toImage() {
		Image tmp = new Image(){
			ShapeRenderer shapeRenderer = new ShapeRenderer();
			@Override
			public void draw(SpriteBatch batch, float parentAlpha) {
				// TODO Auto-generated method stub
				batch.end();
				shapeRenderer.setProjectionMatrix(DataProvider.getInstance().screenStage.getCamera().combined);
				shapeRenderer.begin(ShapeType.FilledRectangle);
				 shapeRenderer.setColor(1, 1, 0, 0);
				 shapeRenderer.filledRect(GsnTableLayout.this.x, GsnTableLayout.this.y, GsnTableLayout.this.width, GsnTableLayout.this.height);
				 shapeRenderer.end();
				
				batch.begin();
			}
		};
		tmp.x = this.x;
		tmp.y = this.y;
		tmp.height = this.height;
		tmp.width = this.width;
		return tmp;
	}
}
