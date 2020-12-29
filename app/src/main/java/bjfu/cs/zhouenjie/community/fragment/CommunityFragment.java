package bjfu.cs.zhouenjie.community.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import bjfu.cs.zhouenjie.Bean.Coffee;
import bjfu.cs.zhouenjie.R;
import bjfu.cs.zhouenjie.app.MyApplication;
import bjfu.cs.zhouenjie.base.BaseFragment;
import bjfu.cs.zhouenjie.community.adapter.CommunityAdapter;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 发现页面CommunityFragment
 */
public class CommunityFragment extends BaseFragment {

    public static final String TAG = "zsben";
    @BindView(R.id.rv_com)
    RecyclerView rvCom;
    @BindView(R.id.iv_empty_com)
    ImageView ivEmptyCom;
    @BindView(R.id.tv_empty_com_tobu)
    TextView tvEmptyComTobu;
    @BindView(R.id.ll_empty_com)
    LinearLayout llEmptyCom;

    @Override
    public View initView() {
        Log.d(TAG, "CommunityFragment initView");
        View view = View.inflate(mContext, R.layout.fragment_community, null);
        rvCom = view.findViewById(R.id.rv_com);
        llEmptyCom = view.findViewById(R.id.ll_empty_com);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.d(TAG, "CommunityFragment initData");
        showData();
    }

    private void showData(){
        List<Coffee> list = new ArrayList<>();
        for(Coffee a: MyApplication.getmCoffeeList()){
            if (a.isPerfer())
                list.add(a);
        }
        for(Coffee a: MyApplication.getmGoodList()){
            if (a.isPerfer())
                list.add(a);
        }
        for(Coffee a: MyApplication.getmFoodList()){
            if (a.isPerfer())
                list.add(a);
        }
        Log.d(TAG, "showData: "+list.size());
        if(list.size()!=0){
            llEmptyCom.setVisibility(View.GONE);
            rvCom.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
            rvCom.setAdapter(new CommunityAdapter(mContext,list));
        }else {
            llEmptyCom.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.rv_com, R.id.iv_empty_com, R.id.tv_empty_com_tobu, R.id.ll_empty_com})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rv_com:
                break;
            case R.id.iv_empty_com:
                break;
            case R.id.tv_empty_com_tobu:
                break;
            case R.id.ll_empty_com:
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            showData();
        }
    }
}
