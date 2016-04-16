package com.my.diamond.tools;

import java.util.ArrayList;
import java.util.List;

import com.my.diamond.R;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MenuTool {
	private boolean is_Show = false;
	private Animation animation;
	private Animation layoutAnimation;

	public void navigation(Activity activity, ImageView imageView,
			LinearLayout layout) {
		if (!is_Show) {
			// 锟斤拷锟矫讹拷锟斤拷执锟叫碉拷时锟斤�?
			animation = AnimationUtils.loadAnimation(activity,
					R.anim.gone_alpha);
			animation.setFillAfter(true); // 设置终止填充
			//imageView.startAnimation(animation);// 使锟斤拷ImageView锟斤拷startAnimation锟斤拷锟斤拷执锟叫讹拷锟斤拷

			layoutAnimation = AnimationUtils.loadAnimation(activity,
					R.anim.translate_right_left_);// 锟斤拷锟矫讹拷锟斤拷锟斤拷尾锟斤拷锟�?
			layoutAnimation.setFillAfter(true);// 锟斤拷锟斤拷锟斤拷锟斤拷
			layout.startAnimation(layoutAnimation); //方法执行动画
			is_Show = true;
			layout.setVisibility(View.VISIBLE);

		} else {
			// 锟斤拷锟矫讹拷锟斤拷执锟叫碉拷时锟斤�?
			animation = AnimationUtils.loadAnimation(activity,
					R.anim.show_alpha);
			animation.setFillAfter(true);
			//imageView.startAnimation(animation);// 使锟斤拷ImageView锟斤拷startAnimation锟斤拷锟斤拷执锟叫讹拷锟斤拷

			layoutAnimation = AnimationUtils.loadAnimation(activity,
					R.anim.translate_left_right_);// 锟斤拷锟矫讹拷锟斤拷锟斤拷尾锟斤拷锟�?
			layoutAnimation.setFillAfter(true); // 锟斤拷锟斤拷锟斤拷锟斤拷
			layout.startAnimation(layoutAnimation); // 执锟斤拷
			is_Show = false;
			layout.setVisibility(View.GONE);
		}

	}

}
