package bjfu.cs.zhouenjie.shoppingcart.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import bjfu.cs.zhouenjie.Bean.Coffee;
import bjfu.cs.zhouenjie.R;
import bjfu.cs.zhouenjie.app.CountActivity;
import bjfu.cs.zhouenjie.app.MainActivity;
import bjfu.cs.zhouenjie.base.BaseFragment;
import bjfu.cs.zhouenjie.shoppingcart.adapter.ShoppingCartAdapter;
import bjfu.cs.zhouenjie.shoppingcart.utils.CartStorage;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 发现页面CommunityFragment
 */
public class ShoppingCartFragment extends BaseFragment {

    public static final String TAG = "zsben";
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_shopcart_total)
    TextView tvShopcartTotal;
    @BindView(R.id.btn_check_out)
    Button btnCheckOut;
    @BindView(R.id.ll_check_all)
    LinearLayout llCheckAll;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_empty_cart_tobuy)
    TextView tvEmptyCartTobuy;
    @BindView(R.id.ll_empty_shopcart)
    LinearLayout llEmptyShopcart;

    private ShoppingCartAdapter adapter;
    TextView tv_empty_cart_tobuy;

    @Override
    public View initView() {
        Log.d(TAG, "ShoppingCartFragment initView");
        View view = View.inflate(mContext, R.layout.fragment_shoppingcart, null);
        llEmptyShopcart = view.findViewById(R.id.ll_empty_shopcart);
        recyclerview = view.findViewById(R.id.recyclerview);
        tvShopcartTotal = view.findViewById(R.id.tv_shopcart_total);

        tv_empty_cart_tobuy = view.findViewById(R.id.tv_empty_cart_tobuy);
        tv_empty_cart_tobuy.setOnClickListener(new View.OnClickListener() { //切换到主页去
            @Override
            public void onClick(View v) {
                Fragment nxtFragment = MainActivity.getFragments().get(0);
                Fragment nowFragment = MainActivity.getFragments().get(3);
                if(nxtFragment!=null){
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    if(!nxtFragment.isAdded()){
                        transaction.hide(nowFragment);
                        transaction.add(R.id.frameLayout,nxtFragment).commit();
                    }else {
                        transaction.hide(nowFragment);
                        transaction.show(nxtFragment).commit();
                    }
                }
            }
        });

        btnCheckOut = view.findViewById(R.id.btn_check_out);
        btnCheckOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CountActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.d(TAG, "ShoppingCartFragment initData");
        showData();
    }

    private void showData(){
        List<Coffee> coffeeList = CartStorage.getInstance().getAllData();
        Log.d(TAG, "showData: "+coffeeList.size());
        if(coffeeList!=null && coffeeList.size()>0){ //有数据，隐藏空布局
            llEmptyShopcart.setVisibility(View.GONE);
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
            adapter = new ShoppingCartAdapter(mContext,coffeeList,tvShopcartTotal, false);
            recyclerview.setAdapter(adapter);
        }else { //没有数据，显示空布局
            llEmptyShopcart.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.recyclerview, R.id.tv_shopcart_total, R.id.btn_check_out, R.id.ll_check_all, R.id.iv_empty, R.id.tv_empty_cart_tobuy, R.id.ll_empty_shopcart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.recyclerview:
                break;
            case R.id.tv_shopcart_total:
                break;
            case R.id.btn_check_out:
                break;
            case R.id.ll_check_all:
                break;
            case R.id.iv_empty:
                break;
            case R.id.tv_empty_cart_tobuy:
                break;
            case R.id.ll_empty_shopcart:
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
