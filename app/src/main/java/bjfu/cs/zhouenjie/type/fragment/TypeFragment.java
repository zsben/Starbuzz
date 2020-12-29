package bjfu.cs.zhouenjie.type.fragment;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import bjfu.cs.zhouenjie.Bean.Coffee;
import bjfu.cs.zhouenjie.R;
import bjfu.cs.zhouenjie.app.MyApplication;
import bjfu.cs.zhouenjie.base.BaseFragment;
import bjfu.cs.zhouenjie.home.adapter.CoffeeAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类型TypeFragment
 */
public class TypeFragment extends BaseFragment {

    public static final String TAG = "zsben";
    @BindView(R.id.rv_type_drink)
    RecyclerView rvTypeDrink;
    @BindView(R.id.rv_type_food)
    RecyclerView rvTypeFood;
    @BindView(R.id.rv_type_good)
    RecyclerView rvTypeGood;

    @Override
    public View initView() {
        Log.d(TAG, "TypeFragment initView");
        View view = View.inflate(mContext, R.layout.fragment_type, null);
        rvTypeDrink = view.findViewById(R.id.rv_type_drink);
        rvTypeFood = view.findViewById(R.id.rv_type_food);
        rvTypeGood = view.findViewById(R.id.rv_type_good);


        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        rvTypeDrink.setLayoutManager(manager);
        CoffeeAdapter adapter1 = new CoffeeAdapter(MyApplication.getmCoffeeList(),mContext);
        rvTypeDrink.setAdapter(adapter1);

        GridLayoutManager manager1 = new GridLayoutManager(getContext(),2);
        rvTypeFood.setLayoutManager(manager1);
        CoffeeAdapter adapter2 = new CoffeeAdapter(MyApplication.getmFoodList(),mContext);
        rvTypeFood.setAdapter(adapter2);

        GridLayoutManager manager2 = new GridLayoutManager(getContext(),2);
        rvTypeGood.setLayoutManager(manager2);
        CoffeeAdapter adapter3 = new CoffeeAdapter(MyApplication.getmGoodList(),mContext);
        rvTypeGood.setAdapter(adapter3);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.d(TAG, "TypeFragment initData");
    }
}
