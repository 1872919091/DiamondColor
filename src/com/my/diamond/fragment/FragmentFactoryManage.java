package com.my.diamond.fragment;

import java.util.HashMap;
import java.util.Map;

public class FragmentFactoryManage {
	public static final FragmentFactoryManage mFragmentFactoryManage = new FragmentFactoryManage();
	private FragmentFactory mFragmentFactory;
	private FragmentFactoryManage() {
	}
	public static FragmentFactoryManage getInstance()
	{
		return mFragmentFactoryManage;
	}
	
	public FragmentFactory createFragmentFactory()
	{
		synchronized (mFragmentFactory) {
			if(null == mFragmentFactory)
				mFragmentFactory = new FragmentFactory();
		}
		return mFragmentFactory;
	}

	
}
