package com.misu.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Toast单例
 * 
 * @author huangyue
 * 
 */
public class ToastUtil {
	private volatile static Toast mToast;

	public static void show(Context context, String message) {
		if (!TextUtils.isEmpty(message)) {
			if (mToast == null) {
				mToast = Toast.makeText(context.getApplicationContext(),
						message, Toast.LENGTH_SHORT);
				mToast.show();
			} else {
				mToast.setText(message);
				mToast.show();
			}
		}
	}

	public static void cancel() {
		if (mToast != null) {
			mToast.cancel();
		}
	}
}