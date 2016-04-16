package com.my.diamond.fragment;

import com.my.diamond.R;

import android.view.View;

public class HomeFragment extends BaseFragment {

	@Override
	public View createView() {
		View view = View.inflate(getActivity(), R.layout.home_fragment, null);
		return view;
	}
}
