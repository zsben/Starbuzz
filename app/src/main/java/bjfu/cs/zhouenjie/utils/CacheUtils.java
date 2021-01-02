package bjfu.cs.zhouenjie.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheUtils {

    public static String getString(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences("starbuzz",context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    public static void putString(Context context,String key,String value){
        SharedPreferences sp = context.getSharedPreferences("starbuzz",context.MODE_PRIVATE);
        sp.edit().putString(key,value).apply();
    }
}
