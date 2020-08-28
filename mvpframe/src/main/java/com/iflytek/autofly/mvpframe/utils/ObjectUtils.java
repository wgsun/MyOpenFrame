package com.iflytek.autofly.mvpframe.utils;

/**
 * @author wgsun
 * @descrbe
 * @since 2020/2/4 10:20
 */
public class ObjectUtils {

    public static boolean isNull(Object object) {
        try {
            if (null == object) {
                return true;
            }

            if (object instanceof String) {
                if (object.equals("")) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;

    }


    public static int string2Int(String str) {

        if (isNull(str)) {
            return 0;
        }

        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return 0;
        }
    }

    public static long string2Long(String str) {

        if (isNull(str)) {
            return 0L;
        }

        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return 0L;
        }
    }

    public static float string2Float(String str) {

        if (isNull(str)) {
            return 0f;
        }

        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            return 0f;
        }
    }

    public static double string2Double(String str) {

        if (isNull(str)) {
            return 0;
        }

        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return 0;
        }
    }

    public static boolean string2Boolean(String str) {

        if (isNull(str)) {
            return false;
        }
        try {
            return Boolean.parseBoolean(str);
        } catch (Exception e) {
            return false;
        }

    }

    public static byte string2Byte(String str) {

        if (isNull(str)) {
            return 0;
        }
        try {
            return Byte.parseByte(str);
        } catch (Exception e) {
            return 0;
        }

    }

    public static String object2String(Object object) {
        return object == null ? "" : object.toString();
    }
}
