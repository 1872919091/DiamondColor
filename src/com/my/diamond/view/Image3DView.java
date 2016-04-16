package com.my.diamond.view;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * ������Image3DSwitchView����ʾ3DͼƬ��
 * 
 * @author guolin
 * @from http://blog.csdn.net/guolin_blog/article/details/17482089
 */
public class Image3DView extends ImageView {
	/**
	 * ��ת�ǶȵĻ�׼ֵ
	 */
	private static final float BASE_DEGREE = 50f;
	/**
	 * ��ת��ȵĻ�׼ֵ
	 */
	private static final float BASE_DEEP = 150f;
	private Camera mCamera;
	private Matrix mMaxtrix;  //ͼ��任
	/**
	 * Bitmap��Androidϵͳ�е�ͼ���������Ҫ��֮һ���������Ի�ȡͼ���ļ���Ϣ,����ͼ����С���ת�����ŵȲ���,������ָ����ʽ����ͼ���ļ�
	 */
	private Bitmap mBitmap;  
	/**
	 * ��ǰͼƬ��Ӧ���±�
	 */
	private int mIndex;
	/**
	 * ��ǰͼƬ��X�᷽������ľ���
	 */
	private int mScrollX;
	/**
	 * Image3DSwitchView�ؼ��Ŀ��
	 */
	private int mLayoutWidth;
	/**
	 * ��ǰͼƬ�Ŀ��
	 */
	private int mWidth;
	/**
	 * ��ǰ��ת�ĽǶ�
	 */
	private float mRotateDegree;
	/**
	 * ��ת�����ĵ�
	 */
	private float mDx;
	/**
	 * ��ת�����
	 */
	private float mDeep;

	public Image3DView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mCamera = new Camera();
		mMaxtrix = new Matrix();
	}

	/**
	 * ��ʼ��Image3DView����Ҫ����Ϣ������ͼƬ��ȣ���ȡ����ͼ�ȡ�
	 */
	public void initImageViewBitmap() {
		if (mBitmap == null) {
			setDrawingCacheEnabled(true);//����ǰ���ڱ��滺����Ϣ
			buildDrawingCache(); //�ؽ�����
			mBitmap = getDrawingCache();//��ȡ����ͼƬ
		}
		mLayoutWidth = Image3DSwitchView.mWidth;
		mWidth = getWidth() + Image3DSwitchView.IMAGE_PADDING * 2;
	}

	/**
	 * ������ת�Ƕȡ�
	 * 
	 * @param index
	 *            ��ǰͼƬ���±�
	 * @param scrollX
	 *            ��ǰͼƬ��X�᷽������ľ���
	 */
	public void setRotateData(int index, int scrollX) {
		mIndex = index;
		mScrollX = scrollX;
	}

	/**
	 * ���յ�ǰ��Bitmap�������ͷ��ڴ档
	 */
	public void recycleBitmap() {
		if (mBitmap != null && !mBitmap.isRecycled()) {
			mBitmap.recycle();
		}
	}

	@Override
	public void setImageResource(int resId) {  //setImageResource(id)������豸�ֱ��ʽ���ͼƬ��С��������
		super.setImageResource(resId);
		mBitmap = null;
		initImageViewBitmap();
	}

	@Override
	public void setImageBitmap(Bitmap bm) {  //��С��Ҫ�ֶ���
		super.setImageBitmap(bm);
		mBitmap = null;
		initImageViewBitmap();
	}

	@Override
	public void setImageDrawable(Drawable drawable) {
		super.setImageDrawable(drawable);
		mBitmap = null;
		initImageViewBitmap();
	}

	@Override
	public void setImageURI(Uri uri) {
		super.setImageURI(uri);
		mBitmap = null;
		initImageViewBitmap();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		try
		{
		if (mBitmap == null) {
			// ���Bitmap���󻹲����ڣ���ʹ�ø����onDraw�������л���
			super.onDraw(canvas);
		} else {
			if (isImageVisible()) {
				// ��ͼʱ��Ҫע�⣬ֻ�е�ͼƬ�ɼ���ʱ��Ž��л��ƣ��������Խ�ʡ����Ч��
				computeRotateData(); //3D���ܴ�
				mCamera.save();  //����
				mCamera.translate(0.0f, 0.0f, mDeep);
				mCamera.rotateY(mRotateDegree); //translate() ����ת���������û�����ϵͳ
				mCamera.getMatrix(mMaxtrix);  //�õ�����
				mCamera.restore(); //�ָ�
				mMaxtrix.preTranslate(-mDx, -getHeight() / 2);
				mMaxtrix.postTranslate(mDx, getHeight() / 2);
				canvas.drawBitmap(mBitmap, mMaxtrix, null);  //canvas.drawbitmap����������������Ϊ�½�һ�������� �ͺñȻ��ҵĻ���
			}
		}
		}catch(Exception e)
		{
			System.out.println("onDraw() Canvas: trying to use a recycled bitmap");
		}
	}

	/**
	 * ���������������ת����Ҫ�����ݡ�
	 */
	private void computeRotateData() {
		float degreePerPix = BASE_DEGREE / mWidth;
		float deepPerPix = BASE_DEEP / ((mLayoutWidth - mWidth) / 2);
		switch (mIndex) {
		case 0:
			mDx = mWidth;
			mRotateDegree = 360f - (2 * mWidth + mScrollX) * degreePerPix;
			if (mScrollX < -mWidth) {
				mDeep = 0;
			} else {
				mDeep = (mWidth + mScrollX) * deepPerPix;
			}
			break;
		case 1:
			if (mScrollX > 0) {
				mDx = mWidth;
				mRotateDegree = (360f - BASE_DEGREE) - mScrollX * degreePerPix;
				mDeep = mScrollX * deepPerPix;
			} else {
				if (mScrollX < -mWidth) {
					mDx = -Image3DSwitchView.IMAGE_PADDING * 2;
					mRotateDegree = (-mScrollX - mWidth) * degreePerPix;
				} else {
					mDx = mWidth;
					mRotateDegree = 360f - (mWidth + mScrollX) * degreePerPix;
				}
				mDeep = 0;
			}
			break;
		case 2:
			if (mScrollX > 0) {
				mDx = mWidth;
				mRotateDegree = 360f - mScrollX * degreePerPix;
				mDeep = 0;
				if (mScrollX > mWidth) {
					mDeep = (mScrollX - mWidth) * deepPerPix;
				}
			} else {
				mDx = -Image3DSwitchView.IMAGE_PADDING * 2;
				mRotateDegree = -mScrollX * degreePerPix;
				mDeep = 0;
				if (mScrollX < -mWidth) {
					mDeep = -(mWidth + mScrollX) * deepPerPix;
				}
			}
			break;
		case 3:
			if (mScrollX < 0) {
				mDx = -Image3DSwitchView.IMAGE_PADDING * 2;
				mRotateDegree = BASE_DEGREE - mScrollX * degreePerPix;
				mDeep = -mScrollX * deepPerPix;
			} else {
				if (mScrollX > mWidth) {
					mDx = mWidth;
					mRotateDegree = 360f - (mScrollX - mWidth) * degreePerPix;
				} else {
					mDx = -Image3DSwitchView.IMAGE_PADDING * 2;
					mRotateDegree = BASE_DEGREE - mScrollX * degreePerPix;
				}
				mDeep = 0;
			}
			break;
		case 4:
			mDx = -Image3DSwitchView.IMAGE_PADDING * 2;
			mRotateDegree = (2 * mWidth - mScrollX) * degreePerPix;
			if (mScrollX > mWidth) {
				mDeep = 0;
			} else {
				mDeep = (mWidth - mScrollX) * deepPerPix;
			}
			break;
		}
	}

	/**
	 * �жϵ�ǰͼƬ�Ƿ�ɼ���
	 * 
	 * @return ��ǰͼƬ�ɼ�����true�����ɼ�����false��
	 */
	private boolean isImageVisible() {
		boolean isVisible = false;
		switch (mIndex) {
		case 0:
			if (mScrollX < (mLayoutWidth - mWidth) / 2 - mWidth) {
				isVisible = true;
			} else {
				isVisible = false;
			}
			break;
		case 1:
			if (mScrollX > (mLayoutWidth - mWidth) / 2) {
				isVisible = false;
			} else {
				isVisible = true;
			}
			break;
		case 2:
			if (mScrollX > mLayoutWidth / 2 + mWidth / 2
					|| mScrollX < -mLayoutWidth / 2 - mWidth / 2) {
				isVisible = false;
			} else {
				isVisible = true;
			}
			break;
		case 3:
			if (mScrollX < -(mLayoutWidth - mWidth) / 2) {
				isVisible = false;
			} else {
				isVisible = true;
			}
			break;
		case 4:
			if (mScrollX > mWidth - (mLayoutWidth - mWidth) / 2) {
				isVisible = true;
			} else {
				isVisible = false;
			}
			break;
		}
		return isVisible;
	}

}