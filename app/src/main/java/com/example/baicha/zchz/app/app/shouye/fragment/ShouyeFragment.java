package com.example.baicha.zchz.app.app.shouye.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.baicha.zchz.R;
import com.example.baicha.zchz.app.app.MainActivity;
import com.example.baicha.zchz.app.app.ShoppingCart.ShoppingCartActivity;
import com.example.baicha.zchz.app.app.base.BaseFragment;
import com.example.baicha.zchz.app.app.base.Dian;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *主页面fragment
 */
public class ShouyeFragment extends BaseFragment {
    private Banner banner;
    private ListView listView;
    private Dian dian = new Dian();
    private TextView shitang,chaoshi,qita;
    @Override

    protected View initView() {
        View view = View.inflate(mContext,R.layout.fragment_shouye,null);
        listView = view.findViewById(R.id.listv);
        banner=view.findViewById(R.id.banner);
        shitang = view.findViewById(R.id.shitang);
        chaoshi = view.findViewById(R.id.chaoshi);
        qita = view.findViewById(R.id.qitq);
        return view;
    }
    @Override
    public void initData() {
        super.initData();
    }
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        init();
        list();
        shitang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new  Intent(getActivity(),Shitang.class);
               startActivity(intent);
            }
        });
        chaoshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(getActivity(),Chaoshi.class);
                startActivity(intent);
            }
        });
        qita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(getActivity(),Qita.class);
                startActivity(intent);
            }
        });


    }


  public void list(){
      List<Map<String,Object>> listItems = new ArrayList<>();
      for (int i = 0; i < dian.imageId.length; i++ ){
          Map<String,Object> map = new HashMap<>();
          map.put("image",dian.imageId[i]);
          map.put("dianming",dian.dianming[i]);
          map.put("xiaoshou",dian.xiaoshou[i]);
          map.put("qisong",dian.qisong[i]);
          map.put("shijian",dian.shijian[i]);
          listItems.add(map);
      }
      SimpleAdapter adapter = new SimpleAdapter(
              getActivity(),
              listItems,
              R.layout.dianjia,
              new  String[]{"dianming","image","xiaoshou","qisong","shijian"},
              new int[] {R.id.dianming, R.id.image, R.id.xiaoshou, R.id.qisong, R.id.shijian}
      );
      listView.setAdapter(adapter);
      //设置listview点击事件
      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //position 点击的Item位置，从0开始算
             // Intent intent=new Intent();
             // intent.putExtra("xx","");//传递给下一个Activity的值
              //  startActivity(intent);//启动Activity
              if (position == 0){
                  Intent intent = new Intent(getActivity(),ShoppingCartActivity.class);
                  startActivity(intent);

              }
              if (position == 1){
                  Toast.makeText(getActivity(),"1",Toast.LENGTH_SHORT).show();}
              if (position == 2){
                  Toast.makeText(getActivity(),"2",Toast.LENGTH_SHORT).show();}
              if (position == 3){
                  Toast.makeText(getActivity(),"3",Toast.LENGTH_SHORT).show();}
          }
      });

    }
    public void init(){
        int[] imageResouceID=new int[]{R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d};
        List<Integer> imageList=new ArrayList<>();
        for (int i=0;i<imageResouceID.length;i++){
            imageList.add(imageResouceID[i]);
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(ShouyeFragment.this).load(path).into(imageView);
                }
            });
            banner.setDelayTime(3000);
            //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
            banner.setBannerAnimation(Transformer.ZoomOutSlide);
            banner.setImages(imageList);
            banner.start();
        }
    }
    public void onStart(){
        super.onStart();
        banner.startAutoPlay();
    }


}


