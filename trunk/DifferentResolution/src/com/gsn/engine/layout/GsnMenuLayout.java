package com.gsn.engine.layout;

public class GsnMenuLayout {
	public float x, y, width, height;
	public int numRow, numCol;	
	public GsnRectangle[][] recArr;
	public GsnMenuLayout(float x, float y, float width, float height, int numRow, int numCol) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;	
		init(numRow, numCol);
	}	
	
	public void setMargin(float marginX, float marginY){
		for (int iRow = 0; iRow < numRow; iRow++)
			for (int iCol = 0; iCol < numCol; iCol++){
				recArr[iRow][iCol].setMargin(false, marginX, marginY);	
			}
	}
	
	private void init(int numRow, int numCol){
		this.numRow = numRow;
		this.numCol = numCol;
		recArr = new GsnRectangle[numRow][numCol];
		float fRow = height / numRow;
		float fCol = width / numCol;
		for (int iRow = 0; iRow < numRow; iRow++)
			for (int iCol = 0; iCol < numCol; iCol++){
				recArr[iRow][iCol] = new GsnRectangle(x + iCol * fCol, y + iRow * fRow, fCol, fRow);	
			}
	}
}
