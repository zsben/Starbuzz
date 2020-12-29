package bjfu.cs.zhouenjie.community.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import bjfu.cs.zhouenjie.Bean.Coffee;
import bjfu.cs.zhouenjie.R;
import bjfu.cs.zhouenjie.app.CoffeeInfoActivity;
import bjfu.cs.zhouenjie.shoppingcart.adapter.ShoppingCartAdapter;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {

    private Context mContext;
    private List<Coffee> mlist;



    public CommunityAdapter(Context mContext, List<Coffee> mlist) {
        this.mContext = mContext;
        this.mlist = mlist;
        setOnItemClickListener1(new CommunityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                // 点击图片查看商品详情
                Coffee coffee = mlist.get(pos);
                Intent intent = new Intent(mContext, CoffeeInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("coffee",coffee);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext,R.layout.item_com,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Coffee coffee = mlist.get(position);
        Glide.with(mContext).load(coffee.getImageId()).into(holder.img);
        holder.title.setText(coffee.getName());
        holder.price.setText("￥ "+coffee.getPrice());
        holder.isperfer.setChecked(coffee.isPerfer());
        holder.isperfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.isperfer.isChecked()) {
                    coffee.setPerfer(true);
                    Toast.makeText(mContext,"收藏成功",Toast.LENGTH_LONG).show();
                }else {
                    coffee.setPerfer(false);
                    Toast.makeText(mContext, "取消收藏", Toast.LENGTH_SHORT).show();
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView title;
        private TextView price;
        private CheckBox isperfer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_gov_com);
            title = itemView.findViewById(R.id.tv_title_com);
            price = itemView.findViewById(R.id.tv_price_com);
            isperfer = itemView.findViewById(R.id.is_perfer);
            View.OnClickListener listener1 = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener1!=null){
                        onItemClickListener1.onItemClick(getLayoutPosition());
                    }
                }
            };
            title.setOnClickListener(listener1);
            img.setOnClickListener(listener1);
        }
    }

    public interface OnItemClickListener{
        public void onItemClick(int pos);
    }


    public void setOnItemClickListener1(OnItemClickListener onItemClickListener1) {
        this.onItemClickListener1 = onItemClickListener1;
    }
    private OnItemClickListener onItemClickListener1;// 用来处理跳转到商品详情




}
