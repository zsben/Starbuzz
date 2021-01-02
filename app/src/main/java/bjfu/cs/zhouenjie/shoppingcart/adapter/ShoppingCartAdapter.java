package bjfu.cs.zhouenjie.shoppingcart.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import bjfu.cs.addsubview.AddSubView;
import bjfu.cs.zhouenjie.Bean.Coffee;
import bjfu.cs.zhouenjie.R;
import bjfu.cs.zhouenjie.app.CoffeeInfoActivity;
import bjfu.cs.zhouenjie.app.MainActivity;
import bjfu.cs.zhouenjie.shoppingcart.utils.CartStorage;
import q.rorbin.badgeview.QBadgeView;

import static bjfu.cs.zhouenjie.home.fragment.HomeFragment.TAG;

/**
 * 购物车recycleview的适配器
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {

    private Context mContext;
    private List<Coffee> coffeeList;
    private TextView tvShopcartTotal;
    public boolean isCount;

    public ShoppingCartAdapter(Context mContext, List<Coffee> coffeeList, TextView tvShopcartTotal, boolean isCount) {
        this.coffeeList=coffeeList;
        this.mContext=mContext;
        this.tvShopcartTotal = tvShopcartTotal;
        this.isCount = isCount;
        showTotalPrrice();
        //设置点击item事件
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                // 点击图片查看商品详情
                Coffee coffee = coffeeList.get(pos);
                Intent intent = new Intent(mContext, CoffeeInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("coffee",coffee);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

    }

    private void showTotalPrrice(){
        if (isCount) return;
        float sumPrice = 0.0f;
        int tot = 0;
        for(Coffee coffee:coffeeList){
            sumPrice+=coffee.getNumber()*coffee.getPrice();
            tot+=coffee.getNumber();
        }
        tvShopcartTotal.setText("合计："+sumPrice);
        QBadgeView qBadgeView = ((MainActivity)mContext).getqBadgeView();
        qBadgeView.setBadgeNumber(tot);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_shop_cart,null);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Coffee coffee = coffeeList.get(position);
        Log.d(TAG, "onBindViewHolder: "+coffee.getImageId());
        Glide.with(mContext).load(coffee.getImageId()).into(holder.iv_gov);
        holder.tv_price.setText("￥ "+String.valueOf(coffee.getPrice()*coffee.getNumber()));
        holder.tv_title.setText(coffee.getName());
        holder.addSubView.setValue(coffee.getNumber());
        holder.addSubView.setOnNumberChangerListener(new AddSubView.OnNumberChangerListener() {
            @Override
            public void onNumberChange(int value) {
                // 内存+适配器刷新
                coffee.setNumber(value);
                notifyItemChanged(position);
                // 本地更新
                CartStorage.getInstance().updateData(coffee);
                // 再次计算总价
                showTotalPrrice();
            }
        });
    }

    @Override
    public int getItemCount() {
        return coffeeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_gov;
        private TextView tv_title;
        private TextView tv_price;
        private AddSubView addSubView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_price = itemView.findViewById(R.id.tv_price);
            iv_gov = itemView.findViewById(R.id.iv_gov);
            addSubView = itemView.findViewById(R.id.AddSubView);
            // 设定item的点击事件
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.onItemClick(getLayoutPosition());
                    }
                }
            };
            iv_gov.setOnClickListener(listener);
            tv_title.setOnClickListener(listener);
        }
    }

    // 设置点击item监听着
    public interface OnItemClickListener{
        public void onItemClick(int pos);
    }
    private OnItemClickListener onItemClickListener;
    // 设置item监听
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
