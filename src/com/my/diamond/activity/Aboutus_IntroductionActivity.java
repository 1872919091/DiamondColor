package com.my.diamond.activity;

import java.util.ArrayList;
import java.util.List;

import com.my.diamond.R;
import com.my.diamond.R.drawable;
import com.my.diamond.R.id;
import com.my.diamond.R.layout;
import com.my.diamond.tools.BitmapTool;
import com.my.diamond.tools.MenuTool;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class Aboutus_IntroductionActivity extends BaseActivity  { // 公司的介�?

	private LayoutInflater inflater;
	private ViewPager pager;
	private List<View> vlist;
	private Myadapter myadapter;

	@Override
	protected void init(Bundle savedInstanceState) {
		setContentView(R.layout.aboutus_introduction);

		initcontrol();
		startWork();
		show();
		
		initView();
	}

	private void initView() {
		//初始化界面的百分�?
		View back = findViewById(R.id.ab_intro_back);
		ImageView title = (ImageView) findViewById(R.id.ab_intro_jieshao);
		ImageView menu = (ImageView) findViewById(R.id.iv_start);
		pager = (ViewPager) findViewById(R.id.ab_intro_viewpager);
		
		double[] wh = BitmapTool.getScreenWandH(this);
		
		RelativeLayout.LayoutParams backLp = (LayoutParams) back.getLayoutParams();
		backLp.height = (int) (wh[1]/10.0);
		backLp.width = backLp.height;
		backLp.rightMargin = (int) (wh[1]/50.0);
		backLp.topMargin = backLp.rightMargin;
		back.setLayoutParams(backLp);
		
		RelativeLayout.LayoutParams titleLp = (LayoutParams) title.getLayoutParams();
		titleLp.height = (int) (wh[1]/8.0);
		titleLp.topMargin = (int) ((int) (wh[1]/10.0)+(wh[1]/8.0));
		titleLp.rightMargin = (int) (wh[1]/10.0);
		title.setLayoutParams(titleLp);
		
		LinearLayout.LayoutParams menuLp = (LinearLayout.LayoutParams) menu.getLayoutParams();
		menuLp.width = (int) (wh[1]/10.0);
		menuLp.height = menuLp.width;
		menu.setLayoutParams(menuLp);
		
		RelativeLayout.LayoutParams vpLp = (LayoutParams) pager.getLayoutParams();
		
		pager.setLayoutParams(vpLp);
		int pp = titleLp.rightMargin;
		
		View view1 = inflater.inflate(R.layout.aboutus_introduction_a, null);
		View view2 = inflater.inflate(R.layout.aboutus_introduction_b, null);
		TextView tv = (TextView) view1.findViewById(R.id.abc_intro_introtext);
		TextView tvs = (TextView) view2.findViewById(R.id.abc_intro_introtext02);
		ImageView iv = (ImageView) view1.findViewById(R.id.abc_intro_introimage);
		ImageView ivs = (ImageView) view2.findViewById(R.id.abc_intro_introimage02);
		int tp = (int) (wh[1]/50.0);
		tv.setPadding(tp, tp, tp, tp);
		tvs.setPadding(tp, tp, tp, tp);
		tv.setTextSize(tp);
		tvs.setTextSize(tp);
		
		//RelativeLayout.LayoutParams vlp = (LayoutParams) view1.getLayoutParams();
		view1.setPadding(pp*5, 0, pp, 0);
		view2.setPadding(pp*3, 0, pp, 0);
		
		RelativeLayout.LayoutParams ivlp = (LayoutParams) iv.getLayoutParams();
		RelativeLayout.LayoutParams ivlps = (LayoutParams) ivs.getLayoutParams();
		ivlp.topMargin = (int) (wh[1]/15.0);
		ivlps.topMargin = (int) (wh[1]/7.0);
		iv.setLayoutParams(ivlp);
		ivs.setLayoutParams(ivlps);
		
		vlist.add(view1);
		vlist.add(view2);
		vlist.add(getShowView("KCRKCRKCRKCKRKvyhujkfcdgtcjhcgdhjvhgjfghfjghjfghjfghjfghjfghjfghjfgh" +
				"gbfdsbfdsnbfdsnfdshbfdshfdsatfewagbvfdxnjhgdhfdsgdsfa" +
				"jgjk;ov hdnb;ooooofgueopwiahsdioaghbfudsoa416548CKRKCKRKCKRKCKR\n\nfdsfds", BitmapFactory.decodeResource(getResources(), R.drawable.menu_pro2)));
		pager.setAdapter(myadapter);
		myadapter.notifyDataSetChanged();
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.show_layout);
		LinearLayout.LayoutParams buttonLp = (LinearLayout.LayoutParams) ll.getLayoutParams();
		buttonLp.height = (int) (wh[1]/10.0);
		ll.setLayoutParams(buttonLp);
		
		back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void show() {

	}
	
	private View getShowView(String text,Bitmap bit){
		
		RelativeLayout rl = (RelativeLayout) getLayoutInflater().inflate(R.layout.aboutus_introduction_a, null);
		TextView tv = (TextView) rl.findViewById(R.id.abc_intro_introtext);
		ImageView iv = (ImageView) rl.findViewById(R.id.abc_intro_introimage);
		
		double[] wh = BitmapTool.getScreenWandH(this);
		int pp = (int) (wh[1]/10.0);
		int tp = (int) (wh[1]/50.0);
		tv.setTextSize(tp);
		tv.setPadding(tp, tp, tp, tp);
		
		rl.setPadding(pp*5, 0, pp, 0);
		
		tv.setText(text);
		iv.setImageBitmap(bit);
		
		return rl;
	}

	/**
	 * 实例化控�?
	 */
	private void initcontrol() {
		vlist = new ArrayList<View>();
		inflater = LayoutInflater.from(this);
		myadapter = new Myadapter();
		pager = (ViewPager) findViewById(R.id.ab_intro_viewpager);

	}

	/**
	 * 绑定点击控件事件
	 * 
	 * @param v
	 */
	private void startWork() {
		myadapter = new Myadapter();
		inflater = LayoutInflater.from(this);
	}

	

	class Myadapter extends PagerAdapter {

		@Override
		public Object instantiateItem(ViewGroup v, int position) {
			((ViewPager) v).addView(vlist.get(position));
			return vlist.get(position);
		}

		@Override
		public void destroyItem(ViewGroup v, int position, Object object) {
			((ViewPager) v).removeView(vlist.get(position));
		}

		@Override
		public int getCount() {
			return vlist.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}

}
