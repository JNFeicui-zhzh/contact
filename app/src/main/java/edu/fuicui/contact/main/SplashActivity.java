package edu.fuicui.contact.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import edu.fuicui.contact.R;

/**
 * Created by hp on 2016/5/4.
 */
public class SplashActivity extends Activity{
    //定义对象
    private ImageView mImageView;
    private Animation mAnimation;
    private Animation.AnimationListener mAnimationListener=new Animation.AnimationListener() {
        //动画开始
        @Override
        public void onAnimationStart(Animation animation) {

        }
        //动画结束
        @Override
        public void onAnimationEnd(Animation animation) {
            Intent intent =new Intent(SplashActivity.this,TelmsgActivity.class);
            startActivity(intent);
            finish();

        }
        //动画循环
        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);//加载布局
        mImageView= (ImageView) findViewById(R.id.iv_splash);
        mAnimation= AnimationUtils.loadAnimation(this,R.anim.anim_logo);

        mAnimation.setAnimationListener(mAnimationListener);
        mImageView.startAnimation(mAnimation);
    }
}
