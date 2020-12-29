package bjfu.cs.addsubview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddSubView addSubView = findViewById(R.id.add_sub_view);
        addSubView.setOnNumberChangerListener(new AddSubView.OnNumberChangerListener() {
            @Override
            public void onNumberChange(int value) {
                Toast.makeText(MainActivity.this, "当前产品数量："+value, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
