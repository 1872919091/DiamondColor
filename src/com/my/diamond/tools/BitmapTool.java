package com.my.diamond.tools;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.view.WindowManager;
import android.widget.Toast;

public class BitmapTool {

	/**
	 * 从资源里面拿资源并且将图片按照指定宽高压缩裁�??
	 */
	public static Bitmap decodeSampledBitmapFromResource(Resources res,
			int resId, int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
		}
		return inSampleSize;
	}

	/**
	 * 以最省内存的方式读取本地资源的图�??
	 * 
	 * @param context
	 * @param resId
	 * @return
	 */
	public static Bitmap readBitMap(Context context, int resId) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		// 获取资源图片
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, opt);
	}

	/**
	 * 按比例缩放，真TMD好用 但是是新建一个bitmap，如果要涉及到内存优化的问题的话�??��修改�??��
	 * 
	 * @param bitmap
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
		if (bitmap == null) {
			return null;
		}

		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) width / w);
		float scaleHeight = ((float) height / h);
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
		return newbmp;
	}

	/**
	 * 拿到原版的图片并放在指定的目录底�??目录不存在会自动创建)
	 * 
	 * @param con
	 * @param filepath
	 * @param resultkey
	 */
	public static void takePictureForResult(Activity con, String filepath,
			String filename, int resultkey) {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			Intent getImageByCamera = new Intent(
					"android.media.action.IMAGE_CAPTURE");
			String out_file_path = filepath;
			File dir = new File(out_file_path);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String capturePath = filepath + filename;
			getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(new File(capturePath)));
			getImageByCamera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
			getImageByCamera.putExtra(MediaStore.EXTRA_MEDIA_FOCUS, true);
			// getImageByCamera.putExtra("outputX", 50); //返回数据的时候的 X 像素大小�??
			// getImageByCamera.putExtra("outputY", 100); //返回的时�??Y 的像素大小�?
			// getImageByCamera.putExtra("return-data", true);
			con.startActivityForResult(getImageByCamera, resultkey);
		} else {
			Toast.makeText(con.getApplicationContext(), "请确认已经插入SD�?",
					Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 通过拿到图片里面的地�??
	 * 
	 * @param ac
	 * @param resultkey
	 */
	public static void pickPictureForResult(Activity ac, int resultkey) {
		Intent it = new Intent(Intent.ACTION_PICK);
		it.setType("image/*");
		ac.startActivityForResult(it, resultkey);
	}

	/**
	 * 好像是获得裁剪后的图片并返回bitmap，没用过，不知道具体情况
	 * 
	 * @param ac
	 * @param resultkey
	 */
	public static void pickCropPictureForResult(Activity ac, int resultkey) {
		Intent it = new Intent(Intent.ACTION_PICK);
		it.setType("image/*");
		it.putExtra("crop", "circle");
		ac.startActivityForResult(it, resultkey);
	}

	/**
	 * 通过拍照拿到压缩过的图片
	 * 
	 * @param ac
	 * @param resultkey
	 */
	public static void takePictureForResult(Activity ac, int resultkey) {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			Intent getImageByCamera = new Intent(
					"android.media.action.IMAGE_CAPTURE");
			ac.startActivityForResult(getImageByCamera, resultkey);
		} else {
			Toast.makeText(ac, "请确认已经插入SD�?", Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 获得裁剪后的图片，传入一个bitmap，会返回�??��bitmap 要用【data.getParcelableExtra("data");】拿�??
	 * 默认返回的图片是128x128像素�??
	 * 
	 * @param data
	 * @return
	 */
	public static void cropPictureFromBitmap(Activity ac, Bitmap data,
			int resultkey) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setType("image/*");
		intent.putExtra("data", data);
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 128);
		intent.putExtra("outputY", 128);
		intent.putExtra("return-data", true);
		ac.startActivityForResult(intent, resultkey);
	}

	private static final int TIME_OUT = 10 * 1000; // 超时时间
	private static final String CHARSET = "utf-8"; // 设置编码

	/**
	 * 上传文件到服务器
	 * 
	 * @param file
	 *            �??��上传的文�??
	 * @param RequestURL
	 *            请求的rul
	 * @return 返回响应的内�??
	 */
	/*public static int uploadFile(CanbeNoticeByMessage cbm,File file, String RequestURL) {
		int res = 0;
		// String result = null;
		String BOUNDARY = UUID.randomUUID().toString(); // 边界标识 随机生成
		String PREFIX = "--", LINE_END = "\r\n";
		String CONTENT_TYPE = "multipart/form-data"; // 内容类型

		try {
			URL url = new URL(RequestURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(TIME_OUT);
			conn.setConnectTimeout(TIME_OUT);
			conn.setDoInput(true); // 允许输入�??
			conn.setDoOutput(true); // 允许输出�??
			conn.setUseCaches(false); // 不允许使用缓�??
			conn.setRequestMethod("POST"); // 请求方式
			conn.setRequestProperty("Charset", CHARSET); // 设置编码
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
					+ BOUNDARY);
			
			//conn.setRequestProperty(field, newValue)

			if (file != null) {
				*//**
				 * 当文件不为空时执行上�??
				 *//*
				DataOutputStream dos = new DataOutputStream(
						conn.getOutputStream());
				StringBuffer sb = new StringBuffer();
				sb.append(PREFIX);
				sb.append(BOUNDARY);
				sb.append(LINE_END);
				*//**
				 * 这里重点注意�??name里面的�?为服务器端需要key 只有这个key 才可以得到对应的文件
				 * filename是文件的名字，包含后�??��
				 *//*

				sb.append("Content-Disposition: form-data; name=\"file\"; filename=\""
						+ file.getName() + "\"" + LINE_END);
				sb.append("Content-Type: application/octet-stream; charset="
						+ CHARSET + LINE_END);
				sb.append(LINE_END);
				dos.write(sb.toString().getBytes());
				InputStream is = new FileInputStream(file);
				byte[] bytes = new byte[1024];
				int len = 0;
				while ((len = is.read(bytes)) != -1) {
					dos.write(bytes, 0, len);
				}
				is.close();
				dos.write(LINE_END.getBytes());
				byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END)
						.getBytes();
				dos.write(end_data);
				dos.flush();
				*//**
				 * 获取响应�??200=成功 当响应成功，获取响应的流
				 *//*
				res = conn.getResponseCode();
				// Log.e(TAG, "response code:" + res);
				if (res == 200) {
					// Log.e(TAG, "request success");
					InputStream input = conn.getInputStream();
					StringBuffer sb1 = new StringBuffer();
					int ss;
					while ((ss = input.read()) != -1) {
						sb1.append((char) ss);
					}
					Message msg = new Message();
					msg.what = 0x09;
					msg.obj = sb1;
					if(cbm!=null){
						cbm.noticeByMessage(msg);
					}
					// result = sb1.toString();
				} else {
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}*/

	/*
	 * 如果要拿到图片的话必须要activity重写onActivityResult方法 if (requestCode ==
	 * TREEPICTURE_RESULT_PICK) Uri uri = data.getData(); 这种方式拿到选择的图�??Bundle b =
	 * data.getExtras(); Bitmap photo = (Bitmap) b.get("data"); 用这种方式拿到压缩后的图�??
	 */

	/**
	 * 从SD卡中拿到压缩后的bitmap，这个是按照比例压缩�??会减少像素点
	 * 
	 * @param srcPath
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap compressImageFromFile(String srcPath, float width,
			float height) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;// 只读�??不读内容
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		float hh = height;//
		float ww = width;//
		int be = 1;
		if (w > h && w > ww) {
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置采样�??
		newOpts.inPreferredConfig = Config.ARGB_8888;// 该模式是默认�??可不�??
		newOpts.inPurgeable = true;// 同时设置才会有效
		newOpts.inInputShareable = true;// 。当系统内存不够时�?图片自动被回�??
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		// return compressBmpFromBmp(bitmap);//原来的方法调用了这个方法企图进行二次压缩
		// 其实是无效的,大家尽管尝试
		return bitmap;
	}

	/**
	 * 压缩bitmap并扔到file中，这个是不会减低像素数的，只是减少图片的质�??
	 * 
	 * @param bmp
	 * @param file
	 */
	public static void compressBmpToFile(Bitmap bmp, File file) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int options = 80;// 个人喜欢�??0�??��,
		bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
		while (baos.toByteArray().length / 1024 > 100) {
			baos.reset();
			options -= 10;
			bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
		}
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(baos.toByteArray());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得�??��与屏幕大小相同的图片
	 * @param m
	 * @param ScreenWidth
	 * @param ScreenHeight
	 * @return
	 */
	public static Bitmap fitTheScreenSizeImage(Bitmap m, int ScreenWidth,
			int ScreenHeight) {
		float width = (float) ScreenWidth / m.getWidth();
		float height = (float) ScreenHeight / m.getHeight();
		Matrix matrix = new Matrix();
		matrix.postScale(width, height);
		return Bitmap.createBitmap(m, 0, 0, m.getWidth(), m.getHeight(),
				matrix, true);
	}

	/**
	 * 拿到屏幕大�??
	 * @param con
	 * @return
	 */
	public static double[] getScreenWandH(Context con){
		double[] wah = new double[2];
		WindowManager wm=(WindowManager)con.getSystemService(Context.WINDOW_SERVICE);
		  wah[0]=wm.getDefaultDisplay().getWidth();//手机屏幕的宽�??
		  wah[1]=wm.getDefaultDisplay().getHeight();//手机屏幕的高�??
		  /*
		   * double[] wah = new double[2];
		WindowManager wm=(WindowManager)con.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		  wah[0]=dm.widthPixels;//手机屏幕的宽�??
		  wah[1]=dm.heightPixels;//手机屏幕的高�??
		   */
		  
		  return wah;
	}
	
}
