package bjfu.cs.zhouenjie.user.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import bjfu.cs.zhouenjie.R;
import bjfu.cs.zhouenjie.app.LoginActivity;
import bjfu.cs.zhouenjie.base.BaseFragment;
import butterknife.BindView;

/**
 * 用户UserFragment
 */
public class UserFragment extends BaseFragment {

    public static final String TAG = "zsben";
    @BindView(R.id.img_header)
    ImageView imgHeader;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    public View initView() {
        Log.d(TAG, "UserFragment initView");
        View view = View.inflate(mContext, R.layout.fragment_user, null);
        imgHeader = view.findViewById(R.id.img_header);
        Glide.with(mContext).load(R.drawable.ic_launcher_foreground).into(imgHeader);

        btnLogin = view.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(mContext, LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.d(TAG, "UserFragment initData");
    }
}
