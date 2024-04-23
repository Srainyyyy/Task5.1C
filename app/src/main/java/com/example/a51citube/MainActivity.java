package com.example.a51citube;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonRegister;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化视图控件
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        userDAO = new UserDAO(this);
        userDAO.open();

        // 设置登录按钮的点击事件监听器
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // 检查用户名和密码是否匹配
                if (userDAO.userExists(username, password)) {
                    // 登录成功，跳转到主页面或其他需要登录后才能访问的页面
                    Intent intent = new Intent(MainActivity.this, urlActivity.class);
                    startActivity(intent);
                    finish(); // 关闭当前登录页面
                } else {
                    // 登录失败，显示错误消息或提示用户重新输入
                    Toast.makeText(MainActivity.this, "用户名或密码无效", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 设置注册按钮的点击事件监听器
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打开注册页面
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userDAO.close();
    }
}
