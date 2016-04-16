package com.my.diamond.fragment;

import com.my.diamond.R;

import android.view.View;

public class Contact_usFragment extends BaseFragment {

	private View mView;
	
	@Override
	public View createView() {
		initView();
		return mView;
	}
	
	private void initView() {
		mView = View.inflate(getActivity(), R.layout.contact_us, null);
	}

}
