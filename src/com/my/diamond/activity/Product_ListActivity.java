package com.my.diamond.activity;

import java.util.ArrayList;
import java.util.List;

import com.my.diamond.R;
import com.my.diamond.tools.BitmapTool;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Product_ListActivity extends BaseActivity  {                                       // 产品系列列表
	private GridView gridView;
	private List<Bitmap> vlist;
	private Myadapter myadapter;
	public  ImageView imageView2 = null;
	
	private ImageView imageView3;
	private LinearLayout layout2;
	private TextView textView1;
	private int tp;

	@Override
	protected void init(Bundle savedInstanceState) {
		setContentView(R.layout.product_list);
		
		gridView = (GridView) findViewById(R.id.gridview);
		vlist = getData();
		myadapter = new Myadapter();
		gridView.setAdapter(myadapter);
		myadapter.notifyDataSetChanged();
		initcontrol();
		startWork();

	}
	
	/**
	 * 实例化控件
	 */
	private void initcontrol() {

		layout2 =(LinearLayout) findViewById(R.id.ll_list);
		imageView3 = (ImageView) findViewById(R.id.back3);
		

		
		double[] wh = BitmapTool.getScreenWandH(this);
		 tp = (int) (wh[1]/50.0);
		
		TextView textView =(TextView) findViewById(R.id.tv_product_title);
		RelativeLayout.LayoutParams tvtitle = (android.widget.RelativeLayout.LayoutParams) textView.getLayoutParams();
		tvtitle.setMargins(tp*8, tp*5, 0, tp*2);//标题名称（XX系列）
		textView.setPadding(tp, tp*3, tp, tp);  //内
		textView.setTextSize(tp*2);  
		textView.setLayoutParams(tvtitle);
		
		RelativeLayout.LayoutParams buttonLp2 = (android.widget.RelativeLayout.LayoutParams) layout2.getLayoutParams();
		buttonLp2.setMargins(tp*8, 0, tp*8, tp*7);//中间图片显示区
		layout2.setLayoutParams(buttonLp2);
	}
	
	/**
	 * 绑定点击控件事件
	 * @param v
	 */
	private void startWork() {
		imageView3.setOnClickListener(this);
	}

	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back3:
			finish();
			break;
		default:
			super.onClick(v);
			break;
		}

	}

	public List<Bitmap> getData() {
		List<Bitmap> list = new ArrayList<Bitmap>();
		for (int i = 0; i < 4; i++) {
			list.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.www));
			list.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.www));
			list.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.www));
			list.add(BitmapFactory.decodeResource(getResources(),
					R.drawable.www));
		}
		return list;

	}

	class Myadapter extends BaseAdapter {

		@Override
		public int getCount() {
			return vlist.size();
		}

		@Override
		public Object getItem(int arg0) {
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int arg0, View v, ViewGroup arg2) {
			
			if (v == null) {
				v =getLayoutInflater().inflate(R.layout.gridview_item, null);
				imageView2 = (ImageView) v.findViewById(R.id.ItemImage);
			}
			imageView2.setImageBitmap(vlist.get(arg0));
			
			textView1 =(TextView) v.findViewById(R.id.tv_article_title);
			RelativeLayout.LayoutParams tvtitle1 = (android.widget.RelativeLayout.LayoutParams) textView1.getLayoutParams();
			tvtitle1.setMargins(0, tp*16, 0, 0);//标题名称（图里面的）
			textView1.setPadding(tp, tp, tp, tp);  //内
			textView1.setTextSize(tp);  
			textView1.setLayoutParams(tvtitle1);
			
			return v;
		}

	}


}
