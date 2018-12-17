package com.example.baicha.zchz.app.app.faxian.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.baicha.zchz.R;
import com.example.baicha.zchz.app.app.base.BaseFragment;

public class FaxianFragment extends BaseFragment {
    private ViewFlipper flipper;
    private int[] resId = {R.drawable.pc1, R.drawable.pc2, R.drawable.pc3, R.drawable.pc4};
    @Override
    protected View initView() {

        View view = View.inflate(mContext,R.layout.fragment_faxian,null);
        //找到flipper
        flipper =  view.findViewById(R.id.flipper);
        // 动态导入的方式为ViewFlipper加入子View
        for (int i = 0; i < resId.length; i++) {
            flipper.addView(getImageView(resId[i]));
        }
        //为ViewFlipper去添加动画效果
        flipper.setInAnimation(getContext(), R.anim.in);
        flipper.setOutAnimation(getContext(), R.anim.out);
        flipper.setFlipInterval(2000);
        flipper.startFlipping();
        return view;
    }
    @Override
    public void initData() {
        super.initData();
    }
    public void  onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
    //获取图片背景
    private ImageView getImageView(int resId) {
        ImageView image = new ImageView(getContext());
        image.setBackgroundResource(resId);
        return image;
    }
}
