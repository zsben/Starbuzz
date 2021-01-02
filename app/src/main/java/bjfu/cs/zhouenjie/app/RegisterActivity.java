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

import org.w3c.dom.Text;

import bjfu.cs.zhouenjie.R;

import static bjfu.cs.zhouenjie.app.MainActivity.getContext;

public class RegisterActivity extends AppCompatActivity {

    private EditText userNameEt;
    private EditText passwordEt;
    private EditText confirmEt;
    private Button registerBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userNameEt = findViewById(R.id.user_name);
        passwordEt = findViewById(R.id.password);
        confirmEt = findViewById(R.id.confirm);
        registerBtn = findViewById(R.id.register_btn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameEt.getText().toString();
                String password = passwordEt.getText().toString();
                String confirm = confirmEt.getText().toString();
                if (check(userName, password, confirm)) {
                    SharedPreferences sp = MyApplication.getmContext().getSharedPreferences("user", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(userName, password);
                    editor.apply();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "信息不合法,请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean check(String userName, String password, String confirm){
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm)){
            return false;
        }
        SharedPreferences sp = MyApplication.getmContext().getSharedPreferences("user", MODE_PRIVATE);
        String ps = sp.getString(userName, null);
        if (ps != null){
            return false;
        }
        if (password.equals(confirm)){
            return true;
        }
        return false;
    }
}
