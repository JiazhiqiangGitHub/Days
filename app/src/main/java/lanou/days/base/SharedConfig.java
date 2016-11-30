package lanou.days.base;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 封装SharedPreferences 类 ,用于 存储书写内容以及彩蛋资料
 * Created by 张家鑫 on 16/11/29.
 */


public class SharedConfig {
    /**
     *  存储
     * @param context
     * @param name
     * @param key
     * @param floatValue float类型的值
     */
    public static void putSharedConfig(Context context, String name, String key, float floatValue) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        sp.edit().putFloat(key, floatValue).apply();
    }

    public static void putSharedConfig(Context context, String name, String key, boolean booleanValue){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, booleanValue).apply();
    }

    public static void putSharedConfig(Context context, String name, String key, int intValue) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        sp.edit().putInt(key, intValue).apply();
    }

    public static void putSharedConfig(Context context, String name, String key, long longValue) {
        context.getSharedPreferences(name, Context.MODE_PRIVATE).edit().putLong(key, longValue).apply();
    }

    public static void putSharedConfig(Context context, String name, String key, String stringValue) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        sp.edit().putString(key, stringValue).apply();
    }


    /**
     * 获取存储的值
     * @param context
     * @param name
     * @param key
     * @return
     */
    public static float getSharedConfigFloat(Context context, String name, String key) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        float returnValue = sp.getFloat(key, 0.0f);
        return returnValue;
    }
    public static boolean getSharedConfigBoolean(Context context, String name, String key) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        boolean returnValue = sp.getBoolean(key, false);
        return returnValue;
    }
    public static int getSharedConfigInt(Context context, String name, String key) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        int returnValue = sp.getInt(key, 0);
        return returnValue;
    }
    public static long getSharedConfigLong(Context context, String name, String key) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        long returnValue = sp.getLong(key, 0);
        return returnValue;
    }
    public static String getSharedConfigString(Context context, String name, String key) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        String returnValue = sp.getString(key, "取值失败");
        return returnValue;
    }


}
