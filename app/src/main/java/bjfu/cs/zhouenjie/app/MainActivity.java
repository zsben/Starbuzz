package bjfu.cs.zhouenjie.app;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import bjfu.cs.zhouenjie.Bean.Coffee;
import bjfu.cs.zhouenjie.R;
import bjfu.cs.zhouenjie.base.BaseFragment;
import bjfu.cs.zhouenjie.community.fragment.CommunityFragment;
import bjfu.cs.zhouenjie.home.fragment.HomeFragment;
import bjfu.cs.zhouenjie.shoppingcart.fragment.ShoppingCartFragment;
import bjfu.cs.zhouenjie.shoppingcart.utils.CartStorage;
import bjfu.cs.zhouenjie.type.fragment.TypeFragment;
import bjfu.cs.zhouenjie.user.fragment.UserFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;

    RadioButton bt;
    static QBadgeView qBadgeView;
    public RadioButton getBt() {
        return bt;
    }

    static public QBadgeView getqBadgeView() {
        return qBadgeView;
    }

    private static List<BaseFragment>fragments;

    public static List<BaseFragment> getFragments() {
        return fragments;
    }

    private int position;
    private Fragment tempFragemnt;

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 绑定后赋值
        ButterKnife.bind(this);
        rgMain.check(R.id.rb_home);
        mContext = this;

        bt = findViewById(R.id.red_bt);
        // 初始化Fragment
        initFragment();

        if(qBadgeView==null) {
            qBadgeView = new QBadgeView(this);
        }
        int tot = 0;
        for(Coffee a:CartStorage.getInstance().getAllData()){
            tot += a.getNumber();
        }
        qBadgeView.bindTarget(bt).setBadgeNumber(tot);


        // 设置RadioGroup的监听
        initListener();
    }

    private void initListener(){
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 获取radiobutton对应的位置
                switch (checkedId){
                    case R.id.rb_home: // 主页
                        position = 0;
                        break;
                    case R.id.rb_type: // 分类
                        position = 1;
                        break;
                    case R.id.rb_community: // 发现
                        position = 2;
                        break;
                    case R.id.rb_cart: // 购物车
                        position = 3;
                        break;
                    case R.id.rb_user: // 用户中心
                        position = 4;
                        break;
                    default:
                        position = 0;
                        break;
                }
                // 根据位置取出fragment
                BaseFragment nextFragment = getFragment(position);

                // 切换fragment
                switchFragment(tempFragemnt,nextFragment);
            }
        });

        rgMain.check(R.id.rb_home);
    }

    // 根据位置获取fragment
    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /**
     * 切换fragment
     * @param fromFragment 上次显示的fragment
     * @param nextFragment 当前要显示的fragment
     */
    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragemnt != nextFragment) {
            tempFragemnt = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断 nextFragment 是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
    }
}
