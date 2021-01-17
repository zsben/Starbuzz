package bjfu.cs.zhouenjie.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import bjfu.cs.zhouenjie.Bean.Coffee;
import bjfu.cs.zhouenjie.Bean.Count;
import bjfu.cs.zhouenjie.R;
import bjfu.cs.zhouenjie.shoppingcart.adapter.ShoppingCartAdapter;
import bjfu.cs.zhouenjie.shoppingcart.utils.CartStorage;
import bjfu.cs.zhouenjie.utils.SPUtils;

public class CountActivity extends AppCompatActivity {

    Button put;
    Button get;

    TextView userName;
    TextView address;
    RecyclerView list;
    TextView totalCount;
    TextView submit;
    ShoppingCartAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        userName = findViewById(R.id.user_name);
        address = findViewById(R.id.address);
        list = findViewById(R.id.list);
        totalCount = findViewById(R.id.total_count);
        submit = findViewById(R.id.submit);

        if (!TextUtils.isEmpty(MyApplication.getUser().getUserName())){
            userName.setText(MyApplication.getUser().getUserName());
        }
        if (!TextUtils.isEmpty(MyApplication.getUser().getAddress())){
            userName.setText(MyApplication.getUser().getAddress());
        }

        list.setLayoutManager(new LinearLayoutManager(MyApplication.getmContext()));
        adapter = new ShoppingCartAdapter(MyApplication.getmContext(), CartStorage.getInstance().getAllData(),null, true);
        list.setAdapter(adapter);

        List<Coffee> allData = CartStorage.getInstance().getAllData();
        float sumPrice = 0.0f;
        int tot = 0;
        for(Coffee coffee:allData){
            sumPrice+=coffee.getNumber()*coffee.getPrice();
            tot+=coffee.getNumber();
        }
        totalCount.setText("总价：￥"+String.valueOf((int)sumPrice));

        final float price = sumPrice;
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Count count = new Count();
                if (submit.getText().toString().equals("提交订单")){
                    Toast.makeText(CountActivity.this, "提交订单成功", Toast.LENGTH_SHORT).show();
                    count.setCoffees(allData);
                    count.setUser(MyApplication.getUser());
                    count.setId(String.valueOf(System.currentTimeMillis()));
                    count.setPrice(price);
                    count.setPay(false);
                    MyApplication.getmCounts().add(count);
                    submit.setText("支付");
                    MyApplication.savaCounts();

                } else {
                    count.setPay(true);
                    MyApplication.savaCounts();
                    Toast.makeText(CountActivity.this, "支付完成", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CountActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    void test(){
        put.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SPUtils.putObject("test1",new Coffee("1",0,0,0,"1"));
                List<Coffee> list = new ArrayList<>();
                list.add(new Coffee("2",0,0,0,"2"));
                list.add(new Coffee("3",0,0,0,"3"));
                SPUtils.putObject("test2", list);
            }
        });
        get.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Coffee coffee = (Coffee) SPUtils.getObject("test1", Coffee.class);
                List<Coffee> list = SPUtils.getList("test2", Coffee.class);
                for (Coffee coffee1 : list){

                }
            }
        });
    }

}
