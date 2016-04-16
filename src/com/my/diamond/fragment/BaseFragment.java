package com.my.diamond.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Toast;

public abstract class BaseFragment extends Fragment{
	private View  mView;
	@Override
	public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(mView==null)
		{
			mView = createView();
		}
		else
		{
			// 先找到爹 在通过爹去移除孩子
			ViewParent parent = mView.getParent();
			//所有的控件 都有爹  爹一般情况下 就是ViewGoup
			if(parent instanceof ViewGroup){
				ViewGroup group=(ViewGroup) parent;
				group.removeView(mView);
			}
		}
		return mView;
	}
	
	/***
	 *  创建界面
	 * @return
	 */
	public  abstract View createView();
}
