package bjfu.cs.zhouenjie.app;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import bjfu.cs.zhouenjie.Bean.Coffee;
import bjfu.cs.zhouenjie.R;
import bjfu.cs.zhouenjie.utils.CacheUtils;
import okhttp3.OkHttpClient;

public class MyApplication extends Application {

    public static Context getmContext() {
        return mContext;
    }

    private static Context mContext;
    private static List<Coffee> mCoffeeList;
    private static List<Coffee> mFoodList;
    private static List<Coffee> mGoodList;

    public static List<Coffee> getmCoffeeList() {
        return mCoffeeList;
    }

    public static void setmCoffeeList(List<Coffee> mCoffeeList) {
        mCoffeeList = mCoffeeList;
    }

    public static List<Coffee> getmFoodList() {
        return mFoodList;
    }

    public static void setmFoodList(List<Coffee> mFoodList) {
        mFoodList = mFoodList;
    }

    public static List<Coffee> getmGoodList() {
        return mGoodList;
    }

    public static void setmGoodList(List<Coffee> mGoodList) {
        mGoodList = mGoodList;
    }
    static {
        mCoffeeList = new ArrayList<>();
        mCoffeeList.add(new Coffee("抹茶星冰乐", R.drawable.green_tea_frappuccino,25.9,1,"抹茶的清新，加一份牛奶的丝滑，与冰块和稀奶油搅拌出特有的清爽。"));
        mCoffeeList.add(new Coffee("摩卡可可碎片星冰乐",R.drawable.mocha_java_chip_frappuccino,29.9,2,"摩卡酱和咖啡在冰块的碰撞下魅力四射，可可碎片带来不同的口感，顶部覆以稀奶油与摩卡酱，让浓郁变得更加有趣。(可可碎片为代可可脂巧克力)"));
        mCoffeeList.add(new Coffee("美式咖啡",R.drawable.caffe_latte,31.5,3,"简单即是美味，萃取经典浓缩咖啡，以水调和，香气浓郁蔓溢。"));
        mCoffeeList.add(new Coffee("白咖啡",R.drawable.flat_white,29.9,4,"本色的经典咖啡。新鲜萃取的浓缩咖啡中缓缓倒入蒸煮牛奶，覆上轻柔奶泡，简单的香与纯。"));
        mCoffeeList.add(new Coffee("摩卡咖啡",R.drawable.caramel_macchiato,18.8,5,"萃取星巴克浓缩烘焙咖啡豆精华，每一口都馥郁满溢，伴随浓郁的焦糖香及多层次的口感，带你开启星巴克咖啡之旅。"));
        mCoffeeList.add(new Coffee("焦糖玛奇朵",R.drawable.caffe_mocha,24.9,6,"蒸煮牛奶与浓缩咖啡相融合，用丰厚绵密的奶泡覆顶，再撒上肉桂粉或可可粉。温馨提示：在奶泡消融前，请尽快享用。"));

        mFoodList = new ArrayList<>();
        mFoodList.add(new Coffee("蓝莓麦芬",R.drawable.oat_caramel_pudding_bread,14.8,7,"享受进口大蓝莓果粒带来的曼妙滋味\n" +
                "星巴克独有美式配方制作,顶层口感酥脆，内部松软,咖啡搭配建议：星巴克®早餐综合咖啡豆 / 星巴克®首选咖啡豆"));
        mFoodList.add(new Coffee("坚果可颂",R.drawable.hazelnut_croissant,16.9,8,"16层制作工艺，松软十足,金黄色的外表，酥脆可口，蜂巢状的内部层次，法国进口高品质黄油，香味浓郁，口感酥脆"));
        mFoodList.add(new Coffee("红豆三明治",R.drawable.red_bean_oats_scone,17.9,9,"口感松软，醇厚的奶香味，再加入红豆沙和红豆，更增加了香甜的味道，同时加入燕麦片，更增加产品的价值"));
        mFoodList.add(new Coffee("香浓巧克力可颂",R.drawable.premium_chocolate_croissant,18.5,10,"借鉴传统27层制作工艺,采用高品质进口巧克力条作为内馅,香味浓郁，酥脆有韧性"));

        mGoodList = new ArrayList<>();
        mGoodList.add(new Coffee("烫金品牌黑色马克杯",R.drawable.black_gilded_brand_mug,125,11,"黑色杯面运用泼点釉搭配镭射Wordmark，隐约的亮色使得整体黑色不再沉闷。"));
        mGoodList.add(new Coffee("烫金品牌黑色试尝杯",R.drawable.bottle_with_strap,136,12,"双层不锈钢材质,别致舒适手柄，一体成型式弯曲舒适感,白色亮面工艺，银色金属拉丝效果"));
        mGoodList.add(new Coffee("原木黑色拎绳不锈钢保温杯",R.drawable.true_essence_stainless_steel_bottle,144,13,"仿木纹水转印工艺。磨砂质感搭配黑色Logo和拎手，中性有格调，给人以回归自然的宁静"));
        mGoodList.add(new Coffee("白色亮面品牌桌面杯",R.drawable.silver_white_brand_handle_mug,98,14,"白色外杯磨砂釉搭配内杯亮面釉，即便饮茶和咖啡也不会溜茶渍"));

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        // 初始化okhttputils
        CacheUtils.putString(getmContext(),"cart_json","");
        initOkHttpUtils();
    }


    private void initOkHttpUtils(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
