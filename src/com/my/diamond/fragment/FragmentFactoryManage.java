package com.my.diamond.fragment;

public class FragmentFactoryManage {
	public static final FragmentFactoryManage mFragmentFactoryManage = new FragmentFactoryManage();
	private FragmentFactory mFragmentFactory = null;

	private FragmentFactoryManage() {
	}

	public static FragmentFactoryManage getInstance() {
		return mFragmentFactoryManage;
	}

	public synchronized FragmentFactory createFragmentFactory() {
		if (null == mFragmentFactory)
			mFragmentFactory = new FragmentFactory();
		return mFragmentFactory;
	}

}
