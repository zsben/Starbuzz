package bjfu.cs.zhouenjie.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import bjfu.cs.zhouenjie.app.MyApplication;

public class SPUtils {

    public static void putObject(String key, Object o){
        SharedPreferences sp = MyApplication.getmContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(o);
        editor.putString(key, json);
        editor.apply();
    }

    public static <T> void putList(String key, List<T> list){
        SharedPreferences sp = MyApplication.getmContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }

    public static Object getObject(String key, Class clazz){
        SharedPreferences sp = MyApplication.getmContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        String json = sp.getString(key, "");
        Gson gson = new Gson();
        Object o = gson.fromJson(json, clazz);
        return o;
    }

    public static <T> List<T> getList(String key, Class<T> clazz){
        SharedPreferences sp = MyApplication.getmContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        String json = sp.getString(key, "");
        Gson gson = new Gson();
        Type type = new ParameterizedTypeImpl(clazz);
        List<T> list = gson.fromJson(json, type);
        return list;
    }



    private static class ParameterizedTypeImpl implements ParameterizedType {
        Class clazz;

        public ParameterizedTypeImpl(Class clz) {
            clazz = clz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }

}
