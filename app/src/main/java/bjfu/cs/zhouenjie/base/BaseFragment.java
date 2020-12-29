package bjfu.cs.zhouenjie.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import bjfu.cs.zhouenjie.Bean.Coffee;
import bjfu.cs.zhouenjie.R;
import bjfu.cs.zhouenjie.app.MainActivity;
import bjfu.cs.zhouenjie.app.MyApplication;
import bjfu.cs.zhouenjie.shoppingcart.utils.CartStorage;
import q.rorbin.badgeview.QBadgeView;

/**
 * 抽象基类BaseFragment的子类
 *      首页：HomeFragment
 *      分类：TypeFragment
 *      喜好：CommunityFragment
 *      购物车：ShoppingCartFragment
 *      用户中心：UserFragment
 */
public abstract class BaseFragment extends Fragment {

    private static final String TAG = "zsben";
    protected Context mContext;

    /**
     * 该类被系统创建时
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }
    /**
     * 由继承类实现不同的效果，初始化UI
     * @return
     */
    public abstract View initView();


    /**
     * Activity.onCreate后回调
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 得到数据，显示UI
     * 当子类需要联网请求数据时，可以重写该方法，在该方法中联网实现
     */
    public void initData(){}

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        QBadgeView qBadgeView = ((MainActivity)mContext).getqBadgeView();
        int tot = 0;
        for(Coffee coffee:CartStorage.getInstance().getAllData())
            tot+=coffee.getNumber();
        qBadgeView.setBadgeNumber(tot);
    }
}
