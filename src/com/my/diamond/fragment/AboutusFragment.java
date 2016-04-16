package com.my.diamond.fragment;

import com.my.diamond.R;
import com.my.diamond.activity.Aboutus_CertificatesActivity;
import com.my.diamond.activity.Aboutus_IntroductionActivity;
import com.my.diamond.activity.Aboutus_SuperiorityActivity;
import com.my.diamond.tools.BitmapTool;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class AboutusFragment extends BaseFragment implements OnClickListener {
	
	private View mView;
	private ImageButton buttonjs;
	private ImageButton buttonys;
	private ImageButton buttonzs;
	
	@Override
	public View createView() {
		initView();
		initListener();
		return mView;
	}

	private void initView() {
		mView = View.inflate(getActivity(), R.layout.aboutus, null);
		buttonjs = (ImageButton) mView.findViewById(R.id.ib_aboutus);
		buttonys = (ImageButton) mView.findViewById(R.id.ib_advantage);
		buttonzs = (ImageButton) mView.findViewById(R.id.ib_honour);
		
		double[] wh = BitmapTool.getScreenWandH(getActivity());
		ImageView back = (ImageView) mView.findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				getActivity().onBackPressed();
			}
		});
		RelativeLayout.LayoutParams backLp = (android.widget.RelativeLayout.LayoutParams) back.getLayoutParams();
		backLp.height = (int) (wh[1]/10.0);
		backLp.width = backLp.height;
		backLp.rightMargin = (int) (wh[1]/50.0);
		backLp.topMargin = backLp.rightMargin;
		back.setLayoutParams(backLp);
	}
	
	private void initListener() {
		buttonjs.setOnClickListener(this);
		buttonys.setOnClickListener(this);
		buttonzs.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_aboutus:  //公司介绍
		  Intent intents =new Intent(getActivity(),
				  Aboutus_IntroductionActivity.class);
		  getActivity().startActivity(intents);

			break;
		case R.id.ib_advantage:   //我们的优势
			Toast.makeText(getActivity(), "跳到我们的优势", 1).show();
			Intent intents1 =new Intent(getActivity(),
					Aboutus_SuperiorityActivity.class);
			  getActivity().startActivity(intents1);

			break;
		case R.id.ib_honour:  //荣誉证书
			Toast.makeText(getActivity(), "跳到荣誉证书", 1).show();
			Intent intents2 =new Intent(getActivity(),
					Aboutus_CertificatesActivity.class);
			getActivity().startActivity(intents2);
			break;
		}

	}

}














