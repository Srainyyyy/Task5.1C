package com.example.a51citube;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText editTextFullName;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Button buttonCreateUser;
    private Button buttonReturn;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 初始化视图控件
        editTextFullName = findViewById(R.id.editTextFullName);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        buttonCreateUser = findViewById(R.id.buttonCreateUser);
        buttonReturn = findViewById(R.id.buttonReturn);
        userDAO = new UserDAO(this);
        userDAO.open();

        // 设置创建用户按钮的点击事件监听器
        buttonCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String confirmPassword = editTextConfirmPassword.getText().toString().trim();

                // 检查密码是否匹配
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "密码不匹配", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 添加用户到数据库
                long result = userDAO.addUser(username, password);
                if (result != -1) {
                    // 用户添加成功
                    Toast.makeText(RegisterActivity.this, "用户创建成功", Toast.LENGTH_SHORT).show();
                    finish(); // 返回登录页面
                } else {
                    // 用户添加失败
                    Toast.makeText(RegisterActivity.this, "用户创建失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 设置返回按钮的点击事件监听器
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 返回登录页面
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userDAO.close();
    }
}
