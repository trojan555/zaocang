package com.example.baicha.zchz.app.app.Dengru;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.baicha.zchz.R;
import com.example.baicha.zchz.app.app.Dao.UserDAO;
import com.example.baicha.zchz.app.app.Modle.Tb_user;

public class Zhuce extends Activity {
    private ImageView imageView;
    private Button zhuce1;
    private EditText yonghuming,mima,mima1;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce);
        imageView = findViewById(R.id.fanhui);
        zhuce1 = findViewById(R.id.zhuce);
        yonghuming = findViewById(R.id.yonghuming);
        mima = findViewById(R.id.mima);
        mima1 = findViewById(R.id.mima1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //为注册按钮设置监听事件
        zhuce1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //将编辑框中的账号与密码转为String类型，并赋值给变量
                String username = yonghuming.getText().toString();
                String password = mima.getText().toString();
                String password1 = mima1.getText().toString();
                //判断密码是否为空
                UserDAO userDAO = new UserDAO(Zhuce.this);
                if(username.isEmpty() || password.isEmpty() || password1.isEmpty())
                {
                    Toast.makeText(Zhuce.this, "请完善注册信息!", Toast.LENGTH_SHORT).show();
                }
                if (!username.isEmpty() && !password.isEmpty() && !password1.isEmpty()) {

                    if(!password.equals(password1)) {
                        Toast.makeText(Zhuce.this, "两次密码不一样，请重新输入!", Toast.LENGTH_SHORT).show();
                        mima.setText("");
                        mima1.setText("");
                    }
                    if(password.equals(password1)) {

                        boolean register = userDAO.find(username);
                        if(register) {
                            Toast.makeText(Zhuce.this, "账号已经存在", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            Tb_user tb_user = new Tb_user(
                                    userDAO.getMaxId() + 1,
                                    username,
                                    password );
                            userDAO.add(tb_user);
                            Toast.makeText(Zhuce.this, "注册成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
            }
        });
    }
}
