package bjfu.cs.zhouenjie.home.fragment;

import android.util.Log;
import android.view.View;

import bjfu.cs.zhouenjie.base.BaseFragment;

/**
 * 主页面HomeFragment
 *
 */
public class HomeFragment extends BaseFragment {

    public static final String TAG = "zsben";

    @Override
    public View initView() {
        Log.d(TAG, "HomeFragment initView");
        return null;
    }

    @Override
    public void initData() {
        super.initData();
        Log.d(TAG, "HomeFragment initData");
    }
}
