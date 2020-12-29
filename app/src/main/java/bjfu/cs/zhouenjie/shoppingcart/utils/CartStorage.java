package bjfu.cs.zhouenjie.shoppingcart.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.widget.RadioButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import bjfu.cs.zhouenjie.Bean.Coffee;
import bjfu.cs.zhouenjie.R;
import bjfu.cs.zhouenjie.app.MainActivity;
import bjfu.cs.zhouenjie.app.MyApplication;
import bjfu.cs.zhouenjie.utils.CacheUtils;
import q.rorbin.badgeview.QBadgeView;

import static bjfu.cs.zhouenjie.user.fragment.UserFragment.TAG;

/**
 * 购物车实例：单例模式
 */
public class CartStorage {

    private static CartStorage instance;

    private final Context mContext;
    private SparseArray<Coffee> sparseArray;

    public static CartStorage getInstance(){
        if(instance == null){
            instance = new CartStorage(MainActivity.getContext());
        }
        return instance;
    }

    private CartStorage(Context context){
        this.mContext = context;
        // 把之前存的数据读出来
        sparseArray = new SparseArray<>(100);
        listToSpaseArray();
    }

    /**
     * 从本地读取数据加入sparsearray
     * @return
     */
    private void listToSpaseArray(){
        List<Coffee> coffeeList = getAllData();
        // 把列表数据转换成sparsearray
        for(int i=0;i<coffeeList.size();i++){
            Coffee coffee = coffeeList.get(i);
            sparseArray.put(coffee.getId(),coffee);
        }
    }

    /**
     * 获取本地所有数据
     * @return
     */
    public List<Coffee> getAllData(){
        String json = CacheUtils.getString(mContext,"cart_json");
        List<Coffee> res = new ArrayList<>();
        Log.d(TAG, "getAllData: "+json);
        if(!TextUtils.isEmpty(json)){
            res = new Gson().fromJson(json,new TypeToken<List<Coffee>>(){}.getType());
        }
        return res;
    }


    // 增删改查

    public void addData(Coffee coffee){
        // 添加到内存中
        Coffee tempdata = sparseArray.get(coffee.getId());
        if(tempdata!=null){
            Log.d(TAG, "addData: ");
            tempdata.setNumber(tempdata.getNumber()+1);
        }else {
            tempdata = coffee;
            tempdata.setNumber(1);
        }
        sparseArray.put(tempdata.getId(),tempdata);
        Log.d(TAG, "addData: "+sparseArray.get(tempdata.getId()));

        if(mContext!=null){
            QBadgeView qBadgeView = ((MainActivity)mContext).getqBadgeView();
            qBadgeView.setBadgeNumber(Integer.parseInt(String.valueOf(qBadgeView.getBadgeNumber()))+1);
        }

        // 同步到本地
        saveLocal();
    }

    public void delData(Coffee coffee){
        sparseArray.delete(coffee.getId());
        saveLocal();
    }

    public void updateData(Coffee coffee){
        sparseArray.put(coffee.getId(),coffee);
        saveLocal();
    }

    // 数据保存到本地
    private void saveLocal(){
        // 列表转换成String
        List<Coffee> coffeeList = new ArrayList<>();
        for(int i=0;i<sparseArray.size();i++){
            coffeeList.add(sparseArray.valueAt(i));
        }
        String json = new Gson().toJson(coffeeList);
        Log.d(TAG, "saveLocal: "+json);
        // 写入缓存中
        CacheUtils.putString(mContext,"cart_json",json);
    }
}
