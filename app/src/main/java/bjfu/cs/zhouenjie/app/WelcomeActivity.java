package bjfu.cs.zhouenjie.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import bjfu.cs.zhouenjie.R;

public class WelcomeActivity extends AppCompatActivity {

    static String TAG = "zsben";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Log.d(TAG, "onCreate: ");
        // 延时1s进入MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 启动主页面
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        },1000);
    }
}
