 /**
 *
 */
 package com.robin.idoumovie.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * @author haitao
 */
public class LogUtil {

    public final static String TAG = ".err";

    /**
     * @param caller
     * @return
     */
    private static String genTag(StackTraceElement caller) {
        String tag = "%s.%s(L:%d)";
        String clsName = caller.getClassName();
        clsName = clsName.substring(clsName.lastIndexOf(".") + 1);
        tag = String.format(tag, clsName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(TAG) ? tag : TAG + " " + tag;
        return tag;
    }

    /**
     * @param cls
     * @param methodname
     * @param info
     */
    @Deprecated
    public static void v(Class cls, String methodname, String info) {
        StackTraceElement stackTraceElement = getStackTrace();
        String tag = genTag(stackTraceElement);
        Log.v(tag, "! " + info);
    }

    /**
     * @param info
     */
    public static void v(String info) {
        StackTraceElement stackTraceElement = getStackTrace();
        String tag = genTag(stackTraceElement);
        Log.v(tag, "! " + info);
    }

    /**
     * @return
     */
    public static StackTraceElement getStackTrace() {
        return Thread.currentThread().getStackTrace()[4];
    }
}
