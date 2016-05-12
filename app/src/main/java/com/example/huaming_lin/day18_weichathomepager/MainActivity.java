package com.example.huaming_lin.day18_weichathomepager;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        initViewPager();
        initTab();
        setListener();
    }


    private ViewPager vp;

    private String[] tabsTitle = {"微信", "通讯录", "发现", "我"};
    private int[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.GRAY};

    private void initViewPager() {
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                BlankFragment fragment = new BlankFragment();
                Bundle bundle = new Bundle();
                bundle.putString("content", tabsTitle[position]);
                bundle.putInt("color", colors[position]);
                fragment.setArguments(bundle);
                return fragment;
            }

            @Override
            public int getCount() {
                return tabsTitle.length;
            }
        });
    }


    private void setListener() {
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             *
             * @param position 屏幕最左边可见的页面的下标
             * @param positionOffset  最左边的页面的 右边界距离屏幕右边的距离的比例
             * @param positionOffsetPixels 最左边的页面的 右边界距离屏幕右边的距离像素
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                System.out.println("----position-===" + position);
                System.out.println("----positionOffset-===" + positionOffset);
                System.out.println("----positionOffsetPixels-===" + positionOffsetPixels);
                if (isClick || position == 3)
                    return;
                if (position == currentPagerIndex) {//手势向左，屏幕向右
                    changTabAlpha(position + 1, positionOffset);
                } else {
                    changTabAlpha(position, 1 - positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                currentPagerIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 0) {
                    changCompleteTabAlpha(currentPagerIndex);
                    isClick = false;
                }
            }
        });
    }


    private View[] tabs = new View[4];

    private void initTab() {
        tabs[0] = findViewById(R.id.iv_wei);
        tabs[1] = findViewById(R.id.iv_tong);
        tabs[2] = findViewById(R.id.iv_fa);
        tabs[3] = findViewById(R.id.iv_wo);
        for (View v : tabs) {
            v.setOnClickListener(this);
        }
    }

    private int currentPagerIndex = 0;

    /**
     * 改变Tab的透明度
     *
     * @param nextPagerIndex 下一个页面的下标
     * @param nextPagerAlpha 下一个页面的透明度
     */
    private void changTabAlpha(int nextPagerIndex, float nextPagerAlpha) {
        tabs[nextPagerIndex].setAlpha(nextPagerAlpha);
        tabs[currentPagerIndex].setAlpha(1 - nextPagerAlpha);
    }

    private void changCompleteTabAlpha(int nextPagerIndex) {
        for (View v : tabs) {
            v.setAlpha(0);
        }
        tabs[nextPagerIndex].setAlpha(1);
    }

    private boolean isClick = false;

    @Override
    public void onClick(View v) {
        isClick = true;
        for (int i = 0; i < tabs.length; i++) {
            if (tabs[i] == v) {
                changCompleteTabAlpha(i);
                vp.setCurrentItem(i);
            }
        }
    }
}
