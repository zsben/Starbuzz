package bjfu.cs.zhouenjie.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import bjfu.cs.zhouenjie.R;
import bjfu.cs.zhouenjie.home.Bean.Coffee;

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
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Coffee coffee = mCoffeeList.get(position);
        holder.coffeePrice.setText(String.valueOf(coffee.getPrice()));
        holder.coffeeName.setText(coffee.getName());
        Glide.with(mContext).load(coffee.getImageId()).into(holder.coffeeImage);

    }

    @Override
    public int getItemCount() {
        return mCoffeeList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView coffeeImage;
        TextView coffeeName;
        TextView coffeePrice;
        public ViewHolder(View view){
            super(view);
            cardView = (CardView)view;
            coffeeImage = (ImageView)view.findViewById(R.id.coffee_image);
            coffeeName = (TextView)view.findViewById(R.id.coffee_name);
            coffeePrice = (TextView)view.findViewById(R.id.coffee_price);
        }
    }

    public CoffeeAdapter(List<Coffee> list){
        mCoffeeList = list;
    }

}
