package bjfu.cs.zhouenjie.type.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.HashMap;
import java.util.Map;

import bjfu.cs.zhouenjie.R;
import bjfu.cs.zhouenjie.app.MyApplication;
import bjfu.cs.zhouenjie.base.BaseFragment;
import bjfu.cs.zhouenjie.home.adapter.CoffeeAdapter;

public class TwoListFragment extends BaseFragment {

    private RecyclerView leftList;
    private RecyclerView rightList;

    private LinearLayoutManager leftManager;
    private GridLayoutManager rightManager;

    private ChapterAdapter leftAdapter;
    private CoffeeAdapter rightAdapter;

    private Map<Integer, Integer> left2Right = new HashMap();
    private Map<Integer, Integer> right2Left = new HashMap();

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_twolist, null);
        leftList = view.findViewById(R.id.left_list);
        rightList = view.findViewById(R.id.right_list);

        leftManager = new LinearLayoutManager(getContext());
        rightManager = new GridLayoutManager(getContext(), 2);
        leftList.setLayoutManager(leftManager);
        rightList.setLayoutManager(rightManager);

        leftAdapter = new ChapterAdapter(mContext);
        rightAdapter = new CoffeeAdapter(MyApplication.getmAllList(), mContext);
        leftList.setAdapter(leftAdapter);
        rightList.setAdapter(rightAdapter);


        rightList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    int rightIndex = rightManager.findFirstVisibleItemPosition();
                    int leftIndex;
                    for(leftIndex = 0;leftIndex<=2;leftIndex++){
                        if (rightIndex <= left2Right.get(leftIndex)){
                            break;
                        }
                    }
                    if(leftIndex >= 2)leftIndex = 2;
                    leftAdapter.setPositionChecked(leftIndex);
                }
            }
        });

        leftAdapter.setOnItemClickListener(new ChapterAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int postion) {
                int rightPos = left2Right.get(postion);
                rightList.scrollToPosition(rightPos);
            }
        });

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        left2Right.put(0,0);
        left2Right.put(1,MyApplication.getmCoffeeList().size());
        left2Right.put(2,MyApplication.getmCoffeeList().size()+MyApplication.getmFoodList().size());
        right2Left.put(0,0);
        right2Left.put(6,MyApplication.getmCoffeeList().size());
        right2Left.put(10,MyApplication.getmCoffeeList().size()+MyApplication.getmFoodList().size());
    }
}
