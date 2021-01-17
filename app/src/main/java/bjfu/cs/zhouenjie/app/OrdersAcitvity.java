package bjfu.cs.zhouenjie.app;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import bjfu.cs.zhouenjie.Bean.Count;
import bjfu.cs.zhouenjie.R;

public class OrdersAcitvity extends AppCompatActivity {

    RecyclerView list;
    CountAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        list = findViewById(R.id.list);

        list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CountAdapter(MyApplication.getmCounts(), this);
        list.setAdapter(adapter);
    }
}
class CountAdapter extends RecyclerView.Adapter<CountViewHolder>{

    List<Count> list = new ArrayList();
    Context context;

    public CountAdapter(List<Count> list,Context context){
        super();
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_count, parent, false);
        return new CountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class CountViewHolder extends RecyclerView.ViewHolder{

    TextView countId;
    TextView isPay;
    TextView price;
    TextView user;

    public CountViewHolder(@NonNull View itemView) {
        super(itemView);
        countId = itemView.findViewById(R.id.count_id);
        isPay = itemView.findViewById(R.id.is_pay);
        price = itemView.findViewById(R.id.price);
        user=  itemView.findViewById(R.id.user);
    }

    public void bind(Count count){
        countId.setText(count.getId());
        if(count.isPay()){
            isPay.setText("已支付");
        } else {
            isPay.setText("未支付");
        }
        price.setText(String.valueOf((int)count.getPrice()));
        if (count.getUser() == null){
            user.setText(null);
        }
        user.setText(count.getUser().getUserName());
    }


}
