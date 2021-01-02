package bjfu.cs.zhouenjie.user.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import bjfu.cs.zhouenjie.R;
import bjfu.cs.zhouenjie.app.LoginActivity;
import bjfu.cs.zhouenjie.app.MyApplication;
import bjfu.cs.zhouenjie.base.BaseFragment;

public class UserFragmentV2 extends BaseFragment {

    TextView userName;
    View orders;
    View address;
    View menus;

    @Override
    public View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_user_v2, null);

        userName = view.findViewById(R.id.user_name);
        orders = view.findViewById(R.id.orders);
        address = view.findViewById(R.id.address);
        menus = view.findViewById(R.id.menus);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //if (MyApplication.getUser()!=null) {
        if (!TextUtils.isEmpty(MyApplication.getUser().getUserName())) {
            userName.setText(MyApplication.getUser().getUserName());
        } else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            getActivity().startActivity(intent);
        }
    }
}
