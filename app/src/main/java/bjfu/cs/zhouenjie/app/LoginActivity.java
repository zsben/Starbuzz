package bjfu.cs.zhouenjie.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import bjfu.cs.zhouenjie.R;

public class LoginActivity extends AppCompatActivity {

    private EditText userNameEt;
    private EditText passwordEt;
    private Button loginBtn;
    private Button registerBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameEt = findViewById(R.id.user_name);
        passwordEt = findViewById(R.id.password);
        loginBtn = findViewById(R.id.btn_login);
        registerBtn = findViewById(R.id.btn_registered);

        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String userName = userNameEt.getText().toString();
                String password = passwordEt.getText().toString();
                if (check(userName, password)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    MyApplication.getUser().setUserName(userName);
                    MyApplication.setLogin(true);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean check(String userName, String password){
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            return false;
        }
        SharedPreferences sp = MyApplication.getmContext().getSharedPreferences("user", MODE_PRIVATE);
        if (sp.getString(userName, "").equals(password)){
            return true;
        }
        return false;
    }

}
