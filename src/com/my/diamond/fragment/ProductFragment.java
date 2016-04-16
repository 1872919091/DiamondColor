package com.my.diamond.fragment;

import com.my.diamond.R;
import com.my.diamond.activity.Product_ListActivity;
import com.my.diamond.tools.BitmapTool;
import com.my.diamond.view.Image3DSwitchView;
import com.my.diamond.view.Image3DSwitchView.OnImageSwitchListener;
import com.my.diamond.view.Image3DView;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class ProductFragment extends BaseFragment implements OnClickListener {

	private View mView;

	private String[] descs = new String[] { "别的页面的资料11-成功", "别的页面的资料22-成功", "别的页面的资料33-成功", "别的页面的资料44-成功",
			"别的页面的资料55-成功", "别的页面的资料66-成功", "别的页面的资料77-成功" };

	public Image3DSwitchView imageSwitchView;
	private Image3DView image3dView, image3dView2, image3dView3, image3dView4, image3dView5, image3dView6, image3dView7;
	private RelativeLayout.LayoutParams layoutParams;// ===暂时没有用上该功能

	private LinearLayout layoutdata;

	private Button detail;
	private ImageView imageView2;
	private TextView textView, textView2;
	
	private boolean isRefresh;

	private FrameLayout mFrameLayout;

	@Override
	public View createView() {
		initView();
		initListener();
		return mView;
	}

	public void initDate() {
		if(imageSwitchView != null && !isRefresh)
			return;
		isRefresh = false;
		if (imageSwitchView != null)
			imageSwitchView.clear();

		imageSwitchView = new Image3DSwitchView(getActivity(), null);
		imageSwitchView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT));
		mFrameLayout.addView(imageSwitchView);

		addChildLayout();
		imageSwitchView.setOnImageSwitchListener(new OnImageSwitchListener() {

			public void onImageSwitch(int currentImage) {
				Log.i("TAG", "current image is " + currentImage);
				Monitor(currentImage);
			}

			protected void Monitor(int currentImage) {
				showText(currentImage);
			}
		});
		imageSwitchView.setCurrentImage(0);
		showText(0);
	}

	private void initListener() {
		imageView2.setOnClickListener(this);
		detail.setOnClickListener(this);
	}

	private void initView() {
		mView = View.inflate(getActivity(), R.layout.product, null);

		imageView2 = (ImageView) mView.findViewById(R.id.ab_intro_backs);
		layoutdata = (LinearLayout) mView.findViewById(R.id.ll_product_data);

		detail = (Button) mView.findViewById(R.id.bt_product_learnmore); // 了解详情
		textView = (TextView) mView.findViewById(R.id.tv_product_data); // 产品详情信情信息
		textView2 = (TextView) mView.findViewById(R.id.tv_product_bt); // 标题

		double[] wh = BitmapTool.getScreenWandH(getActivity());
		View view = mView.findViewById(R.id.fl_image_switch_view);

		int tp = (int) (wh[1] / 50.0);
		android.widget.LinearLayout.LayoutParams layoutParams = (android.widget.LinearLayout.LayoutParams) textView2
				.getLayoutParams();
		textView2.setPadding(tp, tp, tp, tp); // 标题
		layoutParams.setMargins(0, 5 * tp, 0, tp);
		textView2.setTextSize(tp * 2);

		android.widget.LinearLayout.LayoutParams layoutParams3d = (android.widget.LinearLayout.LayoutParams) view
				.getLayoutParams();
		layoutParams3d.height = (int) (wh[1] / 3.0); // 3d
		layoutParams3d.width = (int) (wh[1] / 1.0);
		layoutParams3d.topMargin = (int) (wh[1] / 40.0);
		layoutParams3d.bottomMargin = (int) (wh[1] / 40.0);
		view.setLayoutParams(layoutParams3d);

		android.widget.LinearLayout.LayoutParams layoutParamsdata = (android.widget.LinearLayout.LayoutParams) textView
				.getLayoutParams();
		textView.setPadding(tp, tp, tp, tp); // 产品详情信情信息
		textView.setTextSize(tp);

		android.widget.LinearLayout.LayoutParams layoutParamslj = (android.widget.LinearLayout.LayoutParams) detail
				.getLayoutParams();
		layoutParamslj.setMargins(0, 0, tp, 0); // 了解更多

		android.widget.RelativeLayout.LayoutParams layoutParam = (android.widget.RelativeLayout.LayoutParams) layoutdata
				.getLayoutParams();
		layoutParam.setMargins(tp * 8, tp, tp * 8, tp); // 详情

		// imageSwitchView = (Image3DSwitchView)
		// mView.findViewById(R.id.image_switch_view);

		mFrameLayout = (FrameLayout) mView.findViewById(R.id.fl_image_switch_view);

	}

	private void addChildLayout() {
		layoutParams = new RelativeLayout.LayoutParams( // 暂时没有用上此功能
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		image3dView = new Image3DView(getActivity(), null);
		image3dView.setImageResource(R.drawable.image1);
		image3dView.setScaleType(ScaleType.FIT_XY);
		imageSwitchView.addView(image3dView, layoutParams);

		image3dView2 = new Image3DView(getActivity(), null);
		image3dView2.setImageResource(R.drawable.image2);
		image3dView2.setScaleType(ScaleType.FIT_XY);
		imageSwitchView.addView(image3dView2);

		image3dView3 = new Image3DView(getActivity(), null);
		image3dView3.setImageResource(R.drawable.image3);
		image3dView3.setScaleType(ScaleType.FIT_XY);
		imageSwitchView.addView(image3dView3);

		image3dView4 = new Image3DView(getActivity(), null);
		image3dView4.setImageResource(R.drawable.image4);
		image3dView4.setScaleType(ScaleType.FIT_XY);
		imageSwitchView.addView(image3dView4);

		image3dView5 = new Image3DView(getActivity(), null);
		image3dView5.setImageResource(R.drawable.image5);
		image3dView5.setScaleType(ScaleType.FIT_XY);
		imageSwitchView.addView(image3dView5);

		image3dView6 = new Image3DView(getActivity(), null);
		image3dView6.setImageResource(R.drawable.image6);
		image3dView6.setScaleType(ScaleType.FIT_XY);
		imageSwitchView.addView(image3dView6);

		image3dView7 = new Image3DView(getActivity(), null);
		image3dView7.setImageResource(R.drawable.image7);
		image3dView7.setScaleType(ScaleType.FIT_XY);
		imageSwitchView.addView(image3dView7);

	}

	private void showText(int postion) {
		if (postion > -1 && postion < descs.length)
			textView.setText(descs[postion]);
		else
			textView.setText("没有这个下标");
	}



	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.bt_product_learnmore:
			Intent intent5 = new Intent(getActivity(), Product_ListActivity.class);
			getActivity().startActivity(intent5);
			break;
		case R.id.ab_intro_backs:
			getActivity().onBackPressed();
			break;
		default:
			break;
		}

	}
	
	@Override
	public void onStop() {
		super.onStop();
		isRefresh = true;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		imageSwitchView.clear();
	}
}
