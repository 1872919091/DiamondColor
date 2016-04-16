package com.my.diamond.activity;

import java.util.ArrayList;
import java.util.List;

import com.my.diamond.R;
import com.my.diamond.tools.BitmapTool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Aboutus_CertificatesActivity extends BaseActivity  { // 关于我们-荣誉证书
	private LayoutInflater inflater;
	private ViewPager pager;
	private List<Bitmap> ivlist;
	private Myadapter myadapter;
	private List<ImageView> imageViews;
	
	@Override
	protected void init(Bundle savedInstanceState) {
		setContentView(R.layout.aboutus_certificates);
		initcontrol();
		startWork();
		show();
		addview();
	}

	private void addview() {  //添加
		imageViews =new ArrayList<ImageView>();
		for (int i = 0; i < ivlist.size(); i++) {
			
			ImageView imageView =new ImageView(this);
			imageView.setImageBitmap(ivlist.get(i));
			imageViews.add(imageView);

		}
		
		pager.setAdapter(myadapter);
		myadapter.notifyDataSetChanged();

	}

	private void show() {
		ivlist.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.main01));
		ivlist.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.main02));
		ivlist.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher));
	}

	/**
	 * 实例化控件
	 */
	private void initcontrol() {
		ivlist = new ArrayList<Bitmap>();
		inflater = LayoutInflater.from(this);
		myadapter = new Myadapter();
		pager = (ViewPager) findViewById(R.id.viewPager);
		
		
		double[] wh = BitmapTool.getScreenWandH(this);
		int tp = (int) (wh[1]/50.0);
		
		RelativeLayout.LayoutParams buttonLp2 = (android.widget.RelativeLayout.LayoutParams) pager.getLayoutParams();
		buttonLp2.setMargins(tp*20, tp*10, tp*20, tp*20);
		pager.setLayoutParams(buttonLp2);
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
			((ViewPager) v).addView(imageViews.get(position));
			return imageViews.get(position);
		}
		
		public void destroyItem(ViewGroup v, int position, Object object) {
			((ViewPager) v).removeView(imageViews.get(position));
		}

		public int getCount() {
			return imageViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}






}
