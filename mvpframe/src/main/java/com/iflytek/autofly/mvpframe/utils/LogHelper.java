package com.iflytek.autofly.mvpframe.utils;

import android.util.Log;

/**
 * LogHelper
 * @author dwzheng2@iflytek.com <br/>
 * 实现的主要功能
 * Created at 2017/2/7 9:42
 *
 */
public final class LogHelper {
	// log 等级
	public static int LOG_LEVEL = Log.VERBOSE;

	public final static boolean VERBOSE = Log.VERBOSE >= LOG_LEVEL;
	public final static boolean DEBUG = Log.DEBUG >= LOG_LEVEL;
	public final static boolean INFO = Log.INFO >= LOG_LEVEL;
	public final static boolean WARN = Log.WARN >= LOG_LEVEL;
	public final static boolean ERROR = Log.ERROR >= LOG_LEVEL;

	public static void setDebug(boolean debug) {
		if (debug) {
			LOG_LEVEL = Log.VERBOSE;
		} else {
			LOG_LEVEL = Log.ASSERT;
		}
	}


	public static void v(String tag, String msg) {
		if (VERBOSE) {
			Log.d(tag, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (DEBUG) {
			Log.d(tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (INFO) {
			Log.i(tag, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (WARN) {
			Log.w(tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (ERROR) {
			Log.e(tag, msg);
		}
	}
}
