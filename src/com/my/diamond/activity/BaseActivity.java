package com.my.diamond.activity;

import com.my.diamond.R;
import com.my.diamond.tools.BitmapTool;
import com.my.diamond.tools.MenuTool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public abstract class BaseActivity extends FragmentActivity implements OnClickListener { // 首页
	private ImageView imageView;
	private LinearLayout layout;
	private ImageButton one, two, three, four;
	private MenuTool diamondbrite;

	@Override
	protected final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init(savedInstanceState);
		initcontrol();
		initListener();

	}

	protected abstract void init(Bundle savedInstanceState);

	private void initListener() {
		imageView.setOnClickListener(this);
		layout.setOnClickListener(this);
		one.setOnClickListener(this);
		two.setOnClickListener(this);
		three.setOnClickListener(this);
		four.setOnClickListener(this);
	}

	private void initcontrol() {
		imageView = (ImageView) findViewById(R.id.iv_start);
		layout = (LinearLayout) findViewById(R.id.show_layout);
		diamondbrite = new MenuTool();

		one = (ImageButton) findViewById(R.id.home);
		two = (ImageButton) findViewById(R.id.about_us);
		three = (ImageButton) findViewById(R.id.product);
		four = (ImageButton) findViewById(R.id.connect);

		double[] wh = BitmapTool.getScreenWandH(this);
		LinearLayout.LayoutParams menuLp = (LayoutParams) imageView.getLayoutParams();
		menuLp.width = (int) (wh[1] / 10.0);
		menuLp.height = menuLp.width;
		imageView.setLayoutParams(menuLp);

		LinearLayout ll = (LinearLayout) findViewById(R.id.show_layout);
		LinearLayout.LayoutParams buttonLp = (LayoutParams) ll.getLayoutParams();
		buttonLp.height = (int) (wh[1] / 10.0);
		ll.setLayoutParams(buttonLp);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.iv_start:
			diamondbrite.navigation(this, imageView, layout);
			break;
		case R.id.home:
			intent = new Intent(this, HomeActivity.class);
			intent.putExtra(HomeActivity.CURRENT_NAME, 0);
			startActivity(intent);
			break;
		case R.id.about_us:
			intent = new Intent(this, HomeActivity.class);
			intent.putExtra(HomeActivity.CURRENT_NAME, 1);
			startActivity(intent);
			break;
		case R.id.product:
			intent = new Intent(this, HomeActivity.class);
			intent.putExtra(HomeActivity.CURRENT_NAME, 2);
			startActivity(intent);
			break;
		case R.id.connect:
			intent = new Intent(this, HomeActivity.class);
			intent.putExtra(HomeActivity.CURRENT_NAME, 3);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
