package com.my.diamond.fragment;

import java.util.HashMap;
import java.util.Map;

public class FragmentFactory {

	private Map<Integer, BaseFragment> mFragments = new HashMap<Integer, BaseFragment>();

	public BaseFragment createFragment(int position) {
		BaseFragment fragment = null;
		fragment = mFragments.get(position); // 在集合中取出来Fragment
		if (fragment == null) { // 如果再集合中没有取出来 需要重新创建
			if (position == 0) {
				fragment = new HomeFragment();
			} else if (position == 1) {
				fragment = new AboutusFragment();
			} else if (position == 2) {
				fragment = new ProductFragment();
			} else if (position == 3) {
				fragment = new Contact_usFragment();
			}
			if (fragment != null) {
				mFragments.put(position, fragment);// 把创建好的Fragment存放到集合中缓存起来
			}
		}
		return fragment;

	}
}
