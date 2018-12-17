package com.example.baicha.zchz.app.app.wode.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baicha.zchz.R;
import com.example.baicha.zchz.app.app.Dengru.Dengru;
import com.example.baicha.zchz.app.app.MainActivity;
import com.example.baicha.zchz.app.app.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class WodeFragment extends BaseFragment {
    private ListView listView;
    private TextView textView,gerenxinxi;
    private SharedPreferences sp;
    private ImageView imageView;
    private int[] imageId = new int[]{R.drawable.wodedizhi,R.drawable.wodepingjia,R.drawable.kefu,R.drawable.tuichu};
    private String[] wenzi = new String[]{"我的评价","帮助与反馈","客服中心","退出登入"};
    private int[] imageId1 = new int[]{R.drawable.gengduo,R.drawable.gengduo,R.drawable.gengduo,R.drawable.gengduo};
    @Override
    protected View initView() {
        View view = View.inflate(mContext,R.layout.fragment_wode,null);
        listView = view.findViewById(R.id.listwode);
        textView = view.findViewById(R.id.txt_account_name1);
        gerenxinxi = view.findViewById(R.id.gerenxinxi);
        imageView = view.findViewById(R.id.img_account_avatar1);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("yonghu", MODE_PRIVATE);
        textView.setText(sharedPreferences.getString("用户名","请先登入"));
        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("yonghu", MODE_PRIVATE);
        gerenxinxi.setText(sharedPreferences1.getString("是否登录","登入查看信息"));

        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }
    public void  onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        list();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Dengru.class));
                // 启动主Activity
                getActivity().finish();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToast();
            }
        });

    }

    private void goToast() {

        final String[] item = {"相册","拍照"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("请选择获取头像方式：");
        builder.setItems(item, new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        xc = 0;
                        goXiangChe();
                        break;
                    case 1:
                        xc=1;
                        goXiangJi();
                        break;
                }
            }
        });
        builder.create().show();
    }

    private void goXiangJi() {

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,false);
            startActivityForResult(intent, 1);
        }

    }

    protected void goXiangChe() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 111);
    }


    private Bitmap bitmap;
    private int xc;
    //不管是拍照还是在相册里选择相片，都会调用这个方法
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            ContentResolver cr = getActivity().getContentResolver();
            try {
                if (xc == 0) {
                    bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                } else {
                    Bundle bundle = data.getExtras();
                    bitmap = (Bitmap) bundle.get("data");

                }
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }






    public void list(){
        List<Map<String,Object>> listItems = new ArrayList<>();
        for (int i = 0; i < imageId.length; i++ ){
            Map<String,Object> map = new HashMap<>();
            map.put("image",imageId[i]);
            map.put("text",wenzi[i]);
            map.put("image1",imageId1[i]);
            listItems.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(
                getActivity(),
                listItems,
                R.layout.item,
                new  String[]{"image","text","image1"},
                new int[] {R.id.image, R.id.text, R.id.image1}
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
                    Toast.makeText(getActivity(),"123",Toast.LENGTH_SHORT).show();}
                if (position == 1){
                    Toast.makeText(getActivity(),"1",Toast.LENGTH_SHORT).show();}
                if (position == 2){
                    Toast.makeText(getActivity(),"2",Toast.LENGTH_SHORT).show();}
                if (position == 3){
                    Toast.makeText(getActivity(),"退出登入",Toast.LENGTH_SHORT).show();
                    textView.setText("请先登入");
                    gerenxinxi.setText("登入查看信息");

                    //1：得到sp对象
                    sp = getActivity().getSharedPreferences("yonghu", Context.MODE_PRIVATE);


                    //2：得到editor对象
                    SharedPreferences.Editor editor = sp.edit();
                    //3：得到输入的keyaule
                    String key = "用户名";
                    String value = "请先登入";
                    String key2 = "密码";
                    String value2 = "123";
                    String key3 = "是否登录";
                    String value3 = "登入查看信息";
                    //4:用editor保存keyaule

                    editor.putString(key,value).commit();
                    editor.putString(key2,value2).commit();
                    editor.putString(key3,value3).commit();

                }
            }
        });
    }
}
