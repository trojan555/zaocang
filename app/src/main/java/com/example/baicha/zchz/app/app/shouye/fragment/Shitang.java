package com.example.baicha.zchz.app.app.shouye.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.baicha.zchz.R;
import com.example.baicha.zchz.app.app.base.Dian;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shitang extends FragmentActivity {

    private ImageView imageView;
    private Dian dian = new Dian();
    private ListView listView;
    private ViewPager viewpager;
    private TextView tv_title;
    private LinearLayout ll_point_group;
    private ArrayList<ImageView> imageviews;
    private int prePosition = 0;
    private int a = 0;
    private int realposition;

    private int position = 0;

    String[] title1 = new String[] {
            "一食堂","二食堂","三食堂","四食堂","五食堂"};
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shitang);
       imageView = findViewById(R.id.fanhui);
       imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });

        viewpager = (ViewPager)findViewById(R.id.viewpager);
        tv_title = (TextView)findViewById(R.id.tv_title);
        ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);
        listView = findViewById(R.id.listv);


        int[] imageId1 = new int[] {
                R.drawable.a,R.drawable.b,
                R.drawable.c,R.drawable.d,
                R.drawable.a};

        imageviews = new ArrayList<ImageView>();
        for (int i=0;i<imageId1.length;i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageId1[i]);
            imageviews.add(imageView);
            //添加点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_selector);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(16,16);
            if(i==0){
                point.setEnabled(true);//显示白色
            }else {
                point.setEnabled(false);//显示灰色
                params.leftMargin= 20;
            }
            point.setLayoutParams(params);
            ll_point_group.addView(point);
        }

        viewpager.setAdapter(new MyPagerAdapter());
        //设置监听viewpager页面的改变
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());
        //设置中间页面
        int item = Integer.MAX_VALUE/2 - Integer.MAX_VALUE/2%imageviews.size();//要保证imaggerView的整数倍
        viewpager.setCurrentItem(item);
        tv_title.setText(title1[prePosition]);
/*
        List<Map<String, Object>> listItems = new ArrayList<>();
        for (int i = position; i < 2; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", dian.imageId[position+5]);
            map.put("dianming", dian.dianming[position+5]);
            map.put("xiaoshou", dian.xiaoshou[position+5]);
            map.put("qisong", dian.qisong[position+5]);
            map.put("shijian", dian.shijian[position+5]);
            listItems.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                listItems,
                R.layout.dianjia,
                new String[]{"dianming", "image", "xiaoshou", "qisong", "shijian"},
                new int[]{R.id.dianming, R.id.image, R.id.xiaoshou, R.id.qisong, R.id.shijian}
        );
        listView.setAdapter(adapter);
*/
    }



  public   class  MyOnPageChangeListener implements ViewPager.OnPageChangeListener{
        /**
         * 当页面滚动了的时候回调这个方法
         * @param i 当前页面
         * @param v 滑动页面的百分比
         * @param i1 在屏幕上滑动的像素
         */
        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }
        /**
         *当某个页面被选中了的时候回调
         * @param i  被选中页面的位置
         */
        @Override
        public void onPageSelected(int i) {
             realposition = i%imageviews.size();
             int b = 0;

            List<Map<String, Object>> listItems = new ArrayList<>();
            for (int a = realposition; a < realposition + 6; a = a + 5) {
                Map<String, Object> map = new HashMap<>();
                map.put("image", dian.imageId[a]);
                map.put("dianming", dian.dianming[a]);
                map.put("xiaoshou", dian.xiaoshou[a]);
                map.put("qisong", dian.qisong[a]);
                map.put("shijian", dian.shijian[a]);
                listItems.add(map);
                b++;
            }
            SimpleAdapter adapter = new SimpleAdapter(
                    Shitang.this,
                    listItems,
                    R.layout.dianjia,
                    new String[]{"dianming", "image", "xiaoshou", "qisong", "shijian"},
                    new int[]{R.id.dianming, R.id.image, R.id.xiaoshou, R.id.qisong, R.id.shijian}
            );
            listView.setAdapter(adapter);
            //设置对应文本
            tv_title.setText(title1[realposition]);

            //把上一个设置为灰色
            ll_point_group.getChildAt(prePosition).setEnabled(false);
            //把当前设置为白色
            ll_point_group.getChildAt(realposition).setEnabled(true);
            prePosition = realposition;



        }
        /**
         *当页面滚动状态变化的时候回调这个方法
         * 静止->滑动
         * 滑动->静止
         * 静止->拖拽
         * @param i
         */
        @Override
        public void onPageScrollStateChanged(int i) {
        }
    }
    class MyPagerAdapter extends PagerAdapter {
        /**
         * 得到图片的总数
         * @return
         */
        @Override
        public int getCount() {
//            return imageviews.size();
            return Integer.MAX_VALUE;
        }
        /**
         * 相当于getView方法
         * @param container viewPager自身
         * @param position  当前实例化页面的位置
         * @return
         */
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            int realposition = position%imageviews.size();
            ImageView imageView = imageviews.get(realposition);
            container.addView(imageView);//添加到ViewPager中
//            imageView.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    switch (event.getAction()){
//                        case MotionEvent.ACTION_DOWN://手指按下
//                            Log.e(TAG,"onTouch==手指按下");
//                            handler.removeCallbacksAndMessages(null);
//                            break;
//                        case MotionEvent.ACTION_MOVE://手指在这个控件上移动
//                            break;
//                        case MotionEvent.ACTION_UP://手指离开
//                            Log.e(TAG,"onTouch==手指离开");
//                            handler.sendEmptyMessageDelayed(0,3000);
//                            break;
//                    }
//                    return false;
//                }
//            });

            return imageView;
        }
        /**
         * 比较view和object是否是同一个实例
         * @param view  页面
         * @param object 这个方法instantiateItem返回的结果
         * @return
         */
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
        /**
         * 释放资源
         * @param container viewpager
         * @param position  要释放的位置
         * @param object   要释放的页面
         */
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}

