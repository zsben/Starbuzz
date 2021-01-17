package bjfu.cs.zhouenjie.home.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import bjfu.cs.zhouenjie.R;
import bjfu.cs.zhouenjie.app.CoffeeInfoActivity;
import bjfu.cs.zhouenjie.app.MainActivity;
import bjfu.cs.zhouenjie.app.MyApplication;
import bjfu.cs.zhouenjie.base.BaseFragment;
import bjfu.cs.zhouenjie.Bean.Coffee;
import bjfu.cs.zhouenjie.home.adapter.CoffeeAdapter;
import bjfu.cs.zhouenjie.utils.MyDatabaseHelper;
import bjfu.cs.zhouenjie.view.RoundImageView;
import butterknife.BindView;

/**
 * 主页面HomeFragment
 */
public class HomeFragment extends BaseFragment implements OnBannerListener {

    public static final String TAG = "zsben";
    @BindView(R.id.tv_search_home)
    TextView tvSearchHome;
    @BindView(R.id.tv_message_home)
    TextView tvMessageHome;
    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    @BindView(R.id.ib_top)
    ImageButton ibTop;

    private CardView tocart;
    private CardView tocom;
    RecyclerView rv_food;
    RecyclerView rv_good;

    private List<Integer> imagePath;
    private List<String> imageTitle;
    private Banner mBanner;


    @Override
    public View initView() {
        Log.d(TAG, "HomeFragment initView");
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        ibTop = (ImageButton) view.findViewById(R.id.ib_top);
        tvSearchHome = (TextView)view.findViewById(R.id.tv_search_home);
        tvMessageHome = (TextView) view.findViewById(R.id.tv_message_home);
        mBanner = (Banner) view.findViewById(R.id.banner);
        rv_food = view.findViewById(R.id.rv_food);
        rv_good = view.findViewById(R.id.rv_good);
        tocart = view.findViewById(R.id.to_cart);
        tocom = view.findViewById(R.id.to_com);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        rvHome.setLayoutManager(layoutManager);
        CoffeeAdapter adapter = new CoffeeAdapter(MyApplication.getmCoffeeList(),mContext);
        rvHome.setAdapter(adapter);

        GridLayoutManager layoutManager2 = new GridLayoutManager(getContext(),2);
        rv_good.setLayoutManager(layoutManager2);
        CoffeeAdapter adapter_good = new CoffeeAdapter(MyApplication.getmGoodList(),mContext);
        rv_good.setAdapter(adapter_good);

        GridLayoutManager layoutManager3 = new GridLayoutManager(getContext(),2);
        rv_food.setLayoutManager(layoutManager3);
        CoffeeAdapter adapter_food = new CoffeeAdapter(MyApplication.getmFoodList(),mContext);
        rv_food.setAdapter(adapter_food);

        initListener();
        return view;
    }

    private void initCoffee(){ }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initData() {
        super.initData();
        Log.d(TAG, "HomeFragment initData");

        imagePath = new ArrayList<>();
        imageTitle = new ArrayList<>();
        imagePath.add(R.drawable.aa);
        imagePath.add(R.drawable.ab);
        imagePath.add(R.drawable.ac);
        imageTitle.add("混凝土泵车");
        imageTitle.add("车载式混凝土泵车");
        imageTitle.add("混凝土布料机");
        mBanner.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0,0,view.getWidth(),view.getHeight(),30);
            }
        });
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                .setImageLoader(new MyImageLoader(20))
                .setBannerAnimation(Transformer.ZoomOutSlide)
                .setDelayTime(3000)
                .isAutoPlay(true)
                .setBannerTitles(imageTitle)
                .setIndicatorGravity(BannerConfig.CENTER)
                .setImages(imagePath)
                .setOnBannerListener(this)
                .start();


    }

    private void initListener(){
        //置顶的监听
        ibTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到顶部
                rvHome.scrollToPosition(0);
            }
        });
        //搜素的监听
        tvSearchHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
            }
        });
        //消息的监听
        tvMessageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "进入消息中心", Toast.LENGTH_SHORT).show();
            }
        });
        tocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: touch tocart");
                switchToFragment(3);
            }
        });
        tocom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToFragment(2);
            }
        });
    }

    void switchToFragment(int pos){
        Log.d(TAG, "switchToFragment: ok"+pos);
        Fragment nxtFragment = MainActivity.getFragments().get(pos);
        if(nxtFragment!=null){
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            if (!nxtFragment.isAdded()){
                transaction.hide(MainActivity.getFragments().get(0));
                transaction.add(R.id.frameLayout,nxtFragment).commit();
            }else {
                transaction.hide(MainActivity.getFragments().get(0));
                Log.d(TAG, "switchToFragment: ok");
                transaction.show(nxtFragment).commit();
            }
        }
    }

    @Override
    public void OnBannerClick(int position) {
        switch (position){
            case 0:
                show(3);
                break;
            case 1:
                show(5);
                break;
            case 2:
                show(6);
                break;
        }
    }

    private void show(int id){
        for(Coffee coffee:MyApplication.getmCoffeeList()){
            if(id==coffee.getId()){
                Intent intent = new Intent(mContext, CoffeeInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("coffee",coffee);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        }
    }

    private class MyImageLoader extends ImageLoader{
        private float radius;
        public MyImageLoader(float radius){
            this.radius = radius;
        }
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext()).load(path)
                    .into(imageView);
        }

        @Override
        public ImageView createImageView(Context context){
            RoundImageView roundImageView = new RoundImageView(context);
            roundImageView.setImageViewRadius(context,radius);
            return roundImageView;
        }
    }
}
