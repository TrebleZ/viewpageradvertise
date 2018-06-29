package com.demo.viewpageradvertise;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private String[] titles = {"测试1", "测试2", "测试3"};
    private float MAX_SCALE = 1.0f;
    private float MIN_SCALE = 0.9f;
    private float MIN_Alpha = 0.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), titles);
        ViewPager viewPager = findViewById(R.id.vp_vip);
        viewPager.setOffscreenPageLimit(3);//设置限制数量
        //        //clipChild用来定义他的子控件是否要在他应有的边界内进行绘制。 默认情况下，clipChild被设置为true。 也就是不允许进行扩展绘制。
//        //父容器一定要设置这个，否则看不出效果
        LinearLayout linParent=findViewById(R.id.lin_parent);
        linParent.setClipChildren(false);
        //设置ViewPager切换效果，即实现画廊效果
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.setAdapter(adapter);
    }

    /**
     * 实现的原理是，在当前显示页面放大至原来的MAX_SCALE
     * 其他页面才是正常的的大小MIN_SCALE
     */
    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        @Override
        public void transformPage(View view, float position) {
            //setScaleY只支持api11以上
            if (position < -1) {
                view.setScaleX(MIN_SCALE);
                view.setScaleY(MIN_SCALE);
            } else if (position <= 1) //a页滑动至b页 ; a页从 0.0 -1 ;b页从1 ~ 0.0
            { // [-1,1]
                // Log.e("TAG", view + " , " + position + "");
                float scaleFactor = MIN_SCALE + (1 - Math.abs(position)) * (MAX_SCALE - MIN_SCALE);
                float alpha = MIN_Alpha + ((1 - Math.abs(position)) * (MAX_SCALE - MIN_Alpha));
                //每次滑动后进行微小的移动目的是为了防止在三星的某些手机上出现两边的页面为显示的情况
                if (position > 0) {
                    view.setTranslationX(-scaleFactor * 2);
                } else if (position < 0) {
                    view.setTranslationX(scaleFactor * 2);
                }
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
                view.setAlpha(alpha);
            } else { // (1,+Infinity]
                view.setScaleX(MIN_SCALE);
                view.setScaleY(MIN_SCALE);
                view.setAlpha(MIN_Alpha);
            }
        }
    }
}
