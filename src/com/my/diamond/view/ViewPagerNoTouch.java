package com.my.diamond.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ViewPagerNoTouch extends ViewPager {

	public ViewPagerNoTouch(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ViewPagerNoTouch(Context context) {
		super(context);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		return false;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		return false;
	}

}
