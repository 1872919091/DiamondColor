package com.my.diamond.activity;

import java.util.ArrayList;

import com.my.diamond.R;
import com.my.diamond.fragment.FragmentFactory;
import com.my.diamond.fragment.FragmentFactoryManage;
import com.my.diamond.fragment.ProductFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class HomeActivity extends BaseActivity implements OnClickListener { // 首页
	private ViewPager mViewPager;
	private FragmentManager mSupportFragmentManager;
	public static final String CURRENT_NAME = "index";
	private int mCurrentItem = 0;
	private boolean isBack = false;
	private ArrayList<Integer> mBackCurrentItem = new ArrayList<Integer>();

	@Override
	protected void init(Bundle savedInstanceState) {
		setContentView(R.layout.home_activity);
		initcontrol();
		initAdapter();
		initListener();
		initDate();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		if (intent != null) {
			int backIndex = intent.getIntExtra(CURRENT_NAME, -1);
			if (backIndex != -1)
				mCurrentItem = backIndex;
		}
		mViewPager.setCurrentItem(mCurrentItem, false);
		checkBackTask();
	}

	private void initDate() {
		mViewPager.setCurrentItem(mCurrentItem, false);
	}

	private void addBackTask() {
		if (isBack) {
			isBack = false;
		} else {
			int index = mBackCurrentItem.indexOf(new Integer(mCurrentItem));
			if (-1 == index) {
				mBackCurrentItem.add(new Integer(mCurrentItem));
			} else {
				for (int i = mBackCurrentItem.size() - 1; i > index; i--) {
					mBackCurrentItem.remove(i);
				}
			}
		}
	}

	private void checkBackTask() {
		int index = mBackCurrentItem.indexOf(new Integer(mCurrentItem));
		for (int i = mBackCurrentItem.size() - 1; i >= index; i--) {
			mBackCurrentItem.remove(i);
		}
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		addBackTask();
		super.startActivityForResult(intent, requestCode);
	}

	private void initAdapter() {
		mViewPager.setAdapter(new FragmentPagerAdapter(mSupportFragmentManager) {

			@Override
			public int getCount() {
				return 4;
			}

			@Override
			public Fragment getItem(int position) {
				System.out.println("getItem:position:"+position);
				System.out.println("getItem:FragmentFactoryManage.getInstance():"+FragmentFactoryManage.getInstance());
				System.out.println("getItem:createFragmentFactory():"+FragmentFactoryManage.getInstance().createFragmentFactory());
				System.out.println("getItem:FragmentFactory:"+FragmentFactoryManage.getInstance().createFragmentFactory().createFragment(position));
				
				return  FragmentFactoryManage.getInstance().createFragmentFactory().createFragment(position);
			}
		});
	}

	private void initListener() {
		mViewPager.setOnPageChangeListener(new SimpleOnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				addBackTask();
				mCurrentItem = position;
			}

		});
	}

	private void initcontrol() {
		mViewPager = (ViewPager) findViewById(R.id.vp_main);
		mSupportFragmentManager = getSupportFragmentManager();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.home:
			mViewPager.setCurrentItem(0, true);
			break;
		case R.id.about_us:
			mViewPager.setCurrentItem(1, true);
			break;
		case R.id.product:
			mViewPager.setCurrentItem(2, true);
			((ProductFragment) FragmentFactoryManage.getInstance().createFragmentFactory().createFragment(2)).initDate();
			break;
		case R.id.connect:
			mViewPager.setCurrentItem(3, true);
			break;
		default:
			super.onClick(v);
			break;
		}

	}

	private long isExit;

	@Override
	public void onBackPressed() {
		if (mBackCurrentItem.size() < 1) {
			long currentTimeMillis = System.currentTimeMillis();
			if (currentTimeMillis - isExit < 500) {
				finish();
				return;
			}
			isExit = currentTimeMillis;
			Toast.makeText(this, "再按一次退出", 0).show();
		} else {
			int backIndex = mBackCurrentItem.remove(mBackCurrentItem.size() - 1);
			isBack = true;
			mViewPager.setCurrentItem(backIndex, true);
		}
	}
}
