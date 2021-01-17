package bjfu.cs.zhouenjie.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import bjfu.cs.zhouenjie.R;
import bjfu.cs.zhouenjie.app.CoffeeInfoActivity;
import bjfu.cs.zhouenjie.Bean.Coffee;
import bjfu.cs.zhouenjie.app.LoginActivity;
import bjfu.cs.zhouenjie.app.MainActivity;
import bjfu.cs.zhouenjie.app.MyApplication;
import bjfu.cs.zhouenjie.app.RegisterActivity;
import bjfu.cs.zhouenjie.shoppingcart.utils.CartStorage;
import q.rorbin.badgeview.QBadgeView;

import static bjfu.cs.zhouenjie.home.fragment.HomeFragment.TAG;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.ViewHolder> {

    private Context mContext;
    private List<Coffee> mCoffeeList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.coffee_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        /*holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Coffee coffee = mCoffeeList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("coffee",coffee);
                Intent intent = new Intent(mContext, CoffeeInfoActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });*/
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Coffee coffee = mCoffeeList.get(position);
        holder.coffeePrice.setText(String.valueOf(coffee.getPrice()));
        holder.coffeeName.setText(coffee.getName());
        Log.d(TAG, "onBindViewHolder: ."+coffee.getImageId());
        Glide.with(mContext).load(coffee.getImageId()).into(holder.coffeeImage);
    }

    @Override
    public int getItemCount() {
        return mCoffeeList.size();
    }

    public CoffeeAdapter(List<Coffee> list,Context context){
        mCoffeeList = list;
        mContext = context;
        setOnItemClickListener1(new OnItemClickListener1() {
            @Override //点击图片
            public void onItemClick(int pos) {
                if (!MyApplication.isLogin()){
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                    return;
                }
                Coffee coffee = list.get(pos);
                Intent intent = new Intent(mContext, CoffeeInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("coffee",coffee);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        setOnItemClickListener2(new OnItemClickListener2() {
            @Override //点击按钮添加到购物车
            public void onItemClick(int pos) {
                if (!MyApplication.isLogin()){
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                    return;
                }
                CartStorage.getInstance().addData(list.get(pos));
                QBadgeView qBadgeView = ((MainActivity)mContext).getqBadgeView();
                Log.d(TAG, "onItemClick: "+qBadgeView.getBadgeNumber());
                Toast.makeText(mContext, "添加成功", Toast.LENGTH_SHORT).show();
            }
        });
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView coffeeImage;
        TextView coffeeName;
        TextView coffeePrice;
        Button addToCart;
        public ViewHolder(View view){
            super(view);
            cardView = (CardView)view;
            coffeeImage = (ImageView)view.findViewById(R.id.coffee_image);
            coffeeName = (TextView)view.findViewById(R.id.coffee_name);
            coffeePrice = (TextView)view.findViewById(R.id.coffee_price);
            addToCart = view.findViewById(R.id.add_to_cart);
            // 设置点击监听事件
            coffeeImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener1!=null)
                        onItemClickListener1.onItemClick(getAdapterPosition());
                }
            });
            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener2!=null)
                        onItemClickListener2.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener1{ // 进入商品详情监听
        public void onItemClick(int pos);
    }
    public interface OnItemClickListener2{ // 添加到购物车监听
        public void onItemClick(int pos);
    }
    OnItemClickListener1 onItemClickListener1;
    public void setOnItemClickListener1(OnItemClickListener1 onItemClickListener1) {
        this.onItemClickListener1 = onItemClickListener1;
    }
    OnItemClickListener2 onItemClickListener2;
    public void setOnItemClickListener2(OnItemClickListener2 onItemClickListener2) {
        this.onItemClickListener2 = onItemClickListener2;
    }


}
