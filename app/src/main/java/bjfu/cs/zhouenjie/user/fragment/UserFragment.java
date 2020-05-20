package bjfu.cs.zhouenjie.user.fragment;

import android.util.Log;
import android.view.View;

import bjfu.cs.zhouenjie.base.BaseFragment;

/**
 * 用户UserFragment
 *
 */
public class UserFragment extends BaseFragment {

    public static final String TAG = "zsben";

    @Override
    public View initView() {
        Log.d(TAG, "UserFragment initView");
        return null;
    }

    @Override
    public void initData() {
        super.initData();
        Log.d(TAG, "UserFragment initData");
    }
}
