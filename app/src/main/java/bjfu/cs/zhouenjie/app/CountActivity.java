package bjfu.cs.zhouenjie.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import bjfu.cs.zhouenjie.Bean.Coffee;
import bjfu.cs.zhouenjie.R;
import bjfu.cs.zhouenjie.utils.SPUtils;

public class CountActivity extends AppCompatActivity {

    Button put;
    Button get;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        

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
