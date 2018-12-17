package com.example.baicha.zchz.app.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.baicha.zchz.R;
import com.example.baicha.zchz.app.app.base.BaseFragment;
import com.example.baicha.zchz.app.app.dingdan.fragmment.DingdanFragment;
import com.example.baicha.zchz.app.app.faxian.fragment.FaxianFragment;
import com.example.baicha.zchz.app.app.shouye.fragment.ShouyeFragment;
import com.example.baicha.zchz.app.app.wode.fragment.WodeFragment;

import java.util.ArrayList;
public class MainActivity extends FragmentActivity {
    FrameLayout frameLayout;
    private  RadioGroup rgmain;

    private int position = 0;
    /**
     * 缓存的Fragment或者上次显示的Fragment
     */
    private Fragment tempFragment;
    /**
     * 装多个Fragment的实例集合
     */
    private ArrayList<BaseFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rgmain = findViewById(R.id.radioGroup);
        /**
         * 初始化Fragment
         */
        initFragment();
        /**
         * 设置RadioGroup的监听
         */
        initListener();

        int id = getIntent().getIntExtra("id", 0);
        if (id == 1) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment,new ShouyeFragment())
                    .addToBackStack(null)
                    .commit();
        }

    }
    private void initListener() {
        rgmain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.shouye:
                        position = 0;
                        break;
                    case R.id.faxian:
                        position = 1;
                        break;
                    case R.id.dingdan:
                        position = 2;
                        break;
                    case R.id.wode:
                        position = 3;
                        break;

                }
                //根据位置取不同的Fragment
                BaseFragment baseFragment = getFragment(position);
                /**
                 * 第一个参数：上次显示的Fragment
                 * 第二个参数：当前正要显示的Fragment
                 */
                switchFragment(tempFragment,baseFragment);
            }
        });

        rgmain.check(R.id.radioGroup);
    }

    /**
     * 添加的时候按照顺序
     */
    private void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new ShouyeFragment());
        fragments.add(new FaxianFragment());
        fragments.add(new DingdanFragment());
        fragments.add(new WodeFragment());
    }
    private BaseFragment getFragment(int position){
        if(fragments != null && fragments.size() > 0){
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /**
     * 切换Fragment
     * @param fromfragment
     * @param nextFragment
     */
    private void switchFragment(Fragment fromfragment,BaseFragment nextFragment){
        if(tempFragment != nextFragment){
            tempFragment = nextFragment;
            if(nextFragment != null){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if(!nextFragment.isAdded()){
                    //隐藏当前Fragment
                    if(fromfragment != null){
                        transaction.hide(fromfragment);
                    }
                    //添加Fragment
                    transaction.add(R.id.fragment,nextFragment).commit();
                }else {
                    //隐藏当前Fragment
                    if(fromfragment != null){
                        transaction.hide(fromfragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }
}
