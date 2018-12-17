package com.example.baicha.zchz.app.app.shouye.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.baicha.zchz.R;

public class Dian1 extends Activity {
    private ImageView imageView;
    private ListView rightListView;          //右侧商品listview
    private ListView leftListView;   //左侧--商品类型listview

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dian1);
        imageView = findViewById(R.id.fanhui);
        rightListView = findViewById(R.id.menu_lvmenu);
        leftListView = findViewById(R.id.side_menu_lv);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
