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
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class Aboutus_SuperiorityActivity extends BaseActivity { // 我们的优势

	private LayoutInflater inflater;
	private ViewPager pager;
	private List<View> vlist;
	private Myadapter myadapter;

	@Override
	protected void init(Bundle savedInstanceState) {
		setContentView(R.layout.aboutus_superiority);

		initcontrol();
		startWork();
		show();
	}

	private void show() {
		// TODO Auto-generated method stub
		View view1 = inflater.inflate(R.layout.aboutus_superiority_a, null);
		View view2 = inflater.inflate(R.layout.aboutus_superiority_b, null);
		// vlist.add(view1);
		// vlist.add(view2);
		vlist.add(getShowView("今天天气格外不错不错错", BitmapFactory.decodeResource(getResources(), R.drawable.aboutus_js)));
		vlist.add(getShowView(
				"今天天气fgewgfagbfdsahbrasehnfdsghfds\n\n\n\n\n\n\\n\n格外不错不错错\n\n\n\\n\n格外不错不错错" + "\n\n\n\\n\n格外不错不错错"
						+ "\n\n\n\\n\n格外不错不错错" + "\n\n\n\\n\n格外不错不错错" + "\n\n\n\\n\n格外不错不错错" + "\n\n\n\\n\n格外不错不错错",
				BitmapFactory.decodeResource(getResources(), R.drawable.aboutus_ys)));
		pager.setAdapter(myadapter);
		myadapter.notifyDataSetChanged();

	}

	/**
	 * 实例化控件
	 */
	private void initcontrol() {
		vlist = new ArrayList<View>();
		inflater = LayoutInflater.from(this);
		myadapter = new Myadapter();
		pager = (ViewPager) findViewById(R.id.advantage_pager);

		double[] wh = BitmapTool.getScreenWandH(this);
		ImageView title = (ImageView) findViewById(R.id.advantage_title);
		ImageView back = (ImageView) findViewById(R.id.back);

		// 剩下的
		RelativeLayout.LayoutParams backLp = (android.widget.RelativeLayout.LayoutParams) back.getLayoutParams();
		backLp.height = (int) (wh[1] / 10.0);
		backLp.width = backLp.height;
		backLp.rightMargin = (int) (wh[1] / 50.0);
		backLp.topMargin = backLp.rightMargin;
		back.setLayoutParams(backLp);

		RelativeLayout.LayoutParams titleLp = (android.widget.RelativeLayout.LayoutParams) title.getLayoutParams();
		titleLp.height = (int) (wh[1] / 8.0);
		titleLp.topMargin = (int) ((int) (wh[1] / 9.0));
		titleLp.leftMargin = (int) (wh[0] / 7.0);
		titleLp.bottomMargin = (int) (wh[1] / 8.0);
		title.setLayoutParams(titleLp);

		back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}

	private View getShowView(String text, Bitmap bit) {

		double[] wh = BitmapTool.getScreenWandH(this);
		int tp = (int) (wh[1] / 50.0);
		RelativeLayout rl = (RelativeLayout) getLayoutInflater().inflate(R.layout.aboutus_superiority_a, null);
		TextView tv = (TextView) rl.findViewById(R.id.text);
		ImageView iv = (ImageView) rl.findViewById(R.id.image);

		RelativeLayout.LayoutParams tvlp = (android.widget.RelativeLayout.LayoutParams) tv.getLayoutParams();
		tvlp.leftMargin = (int) (wh[1] / 7.0);
		tvlp.rightMargin = (int) (wh[1] / 7.0);
		tv.setLayoutParams(tvlp);

		tv.setTextSize(tp);
		tv.setPadding(tp, tp, tp, tp);

		tv.setText(text);
		iv.setImageBitmap(bit);

		return rl;
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
			// TODO Auto-generated method stub
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
