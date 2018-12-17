package com.example.baicha.zchz.app.app.Dengru;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baicha.zchz.R;
import com.example.baicha.zchz.app.app.Dao.UserDAO;
import com.example.baicha.zchz.app.app.MainActivity;
public class Dengru extends Activity {
    private Button button;
    private TextView yonghuming,mima;
    private TextView textView;
    private ImageView imageView;
    private SharedPreferences sp;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dengru);
        textView = findViewById(R.id.zhuce);
        imageView = findViewById(R.id.fanhui);
        button = findViewById(R.id.dengru);
        yonghuming = findViewById(R.id.yonghuming);
        mima = findViewById(R.id.mima);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dengru.this,Zhuce.class));
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = yonghuming.getText().toString();
                String password = mima.getText().toString();
                UserDAO userDAO = new UserDAO(Dengru.this);

                boolean login = userDAO.find(username, password);
                boolean panduan1 = userDAO.find(username);
                boolean panduan2 = userDAO.find1(password);


                //1：得到sp对象
                sp = getSharedPreferences("yonghu", Context.MODE_PRIVATE);


                //2：得到editor对象
                SharedPreferences.Editor editor = sp.edit();
                //3：得到输入的keyaule
                String key = "用户名";
                String value = username;
                String key2 = "密码";
                String value2 = password;
                String key3 = "是否登录";
                String value3 = "查看个人信息";
                //4:用editor保存keyaule

                editor.putString(key,value).commit();
                editor.putString(key2,value2).commit();
                editor.putString(key3,value3).commit();


                if(username.isEmpty()&&password.isEmpty()){
                    Toast.makeText(Dengru.this, "请输入账号和密码", Toast.LENGTH_SHORT).show();
                }
                if(username.isEmpty()&&!password.isEmpty()){
                    Toast.makeText(Dengru.this, "请输入账号", Toast.LENGTH_SHORT).show();
                }
                if(!username.isEmpty()&&password.isEmpty()){
                    Toast.makeText(Dengru.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }
                if(!username.isEmpty()&&!password.isEmpty()) {
                    if (login) {
                       /* Intent intent = new Intent(Dengru.this,Wode.class);

						intent.putExtra("username", username);
						intent.putExtra("password", password);

                        startActivity(intent);
                        */
                        Intent intent = new Intent(Dengru.this, MainActivity.class);
                        intent.putExtra("id",1);
                        startActivity(intent);
                        finish();// 启动主Activity
                    } else{
                        if (panduan1 && !panduan2) {
                            Toast.makeText(Dengru.this, "密码错误", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Dengru.this, "账户不存在", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }
}
