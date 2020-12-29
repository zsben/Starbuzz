package bjfu.cs.zhouenjie.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import bjfu.cs.zhouenjie.Bean.Coffee;
import bjfu.cs.zhouenjie.R;
import bjfu.cs.zhouenjie.shoppingcart.utils.CartStorage;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoffeeInfoActivity extends AppCompatActivity {

    private static final String TAG = "zsben";
    @BindView(R.id.ib_good_info_back)
    ImageView ibGoodInfoBack;
    @BindView(R.id.coffee_info_image)
    ImageView coffeeInfoImage;
    @BindView(R.id.coffee_info_title)
    TextView coffeeInfoTitle;
    @BindView(R.id.coffee_info_desc)
    TextView coffeeInfoDesc;
    @BindView(R.id.coffee_info_price)
    TextView coffeeInfoPrice;
    @BindView(R.id.tv_good_info_callcenter)
    TextView tvGoodInfoCallcenter;

    @BindView(R.id.btn_good_info_addcart)
    Button btnGoodInfoAddcart;
    @BindView(R.id.ll_goods_root)
    LinearLayout llGoodsRoot;

    private Coffee coffee;

    private Coffee tempcoffee;

    @BindView(R.id.perfer_btn)
    Button perferBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        ButterKnife.bind(this);
        coffee = (Coffee) (getIntent().getExtras().getSerializable("coffee"));
        for(Coffee a:MyApplication.getmCoffeeList()){
            if(a.getId() == coffee.getId())tempcoffee=a;
        }
        for(Coffee a:MyApplication.getmGoodList()){
            if(a.getId() == coffee.getId())tempcoffee=a;
        }
        for(Coffee a:MyApplication.getmFoodList()){
            if(a.getId() == coffee.getId())tempcoffee=a;
        }
        showCoffee(tempcoffee);
    }

    private void showCoffee(Coffee coffee){
        Glide.with(this).load(coffee.getImageId()).into(coffeeInfoImage);
        coffeeInfoPrice.setText("￥ "+String.valueOf(coffee.getPrice()));
        coffeeInfoTitle.setText(coffee.getName());
        coffeeInfoDesc.setText(coffee.getDesc());
        Log.d(TAG, "showCoffee: "+coffee.isPerfer());
        if (coffee.isPerfer()){
            perferBtn.setBackgroundColor(Color.rgb(255,193, 7));
            perferBtn.setText("取消收藏");
        }else {
            perferBtn.setBackgroundColor(Color.rgb(99,99, 99));
            perferBtn.setText("添加到收藏");
        }
    }

    @OnClick({R.id.ib_good_info_back, R.id.coffee_info_image, R.id.coffee_info_title, R.id.coffee_info_desc, R.id.coffee_info_price, R.id.tv_good_info_callcenter, R.id.btn_good_info_addcart, R.id.ll_goods_root,R.id.perfer_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_good_info_back:
                finish();
                break;
            case R.id.coffee_info_image:
                break;
            case R.id.coffee_info_title:
                break;
            case R.id.coffee_info_desc:
                break;
            case R.id.coffee_info_price:
                break;
            case R.id.tv_good_info_callcenter:
                break;
            case R.id.btn_good_info_addcart:
                CartStorage.getInstance().addData(coffee);
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_goods_root:
                break;
            case R.id.perfer_btn:
                Log.d(TAG, "showCoffee: "+coffee.isPerfer());
                if(coffee.isPerfer()){
                    Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
                    perferBtn.setBackgroundColor(Color.rgb(99,99, 99));
                    perferBtn.setText("添加到收藏");
                    tempcoffee.setPerfer(false);
                }else {
                    Toast.makeText(this,"收藏成功",Toast.LENGTH_LONG).show();
                    tempcoffee.setPerfer(true);
                    perferBtn.setBackgroundColor(Color.rgb(255,193, 7));
                    perferBtn.setText("取消收藏");
                }
                break;
        }
    }
}
