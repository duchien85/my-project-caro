package com.gsn.test;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.LibgdxToolkit;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.TableLayout;
import com.esotericsoftware.tablelayout.BaseTableLayout;

public class GsnBaseTableLayout extends BaseTableLayout<Actor, Table, TableLayout, LibgdxToolkit>  {
	public GsnBaseTableLayout(LibgdxToolkit toolkit) {
		super(toolkit);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void invalidateHierarchy() {
		// TODO Auto-generated method stub
		
	}

}
