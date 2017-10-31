package com.uv.sw;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_ALPHA = 255;

    private static final int SCALE_DURATION = 700;
    Boolean loading = false;
    private Animation mScaleAnimation;
    private Animation mScaleDownAnimation;
    private CircularProgressDrawable mProgress;
    private CircleImageView mCircleView;
    private Animation.AnimationListener mLoadingListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {

            if (loading) {
                reset();
            } else {
                // Make sure the progress view is fully visible
                mProgress.setAlpha(MAX_ALPHA);
                mProgress.start();
            }

            loading = !loading;

        }
    };


    public void onButtonClick(View v) {
        if (loading) {
            startScaleDownAnimation(mLoadingListener);
        } else {
            startScaleUpAnimation(mLoadingListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        CustomDialog.Builder builder = new CustomDialog.Builder(this);
//
//        CustomDialog cd = builder.create();
//
//        Window win = cd.getWindow();
//
//        CircleImageView civ = null;
//        WindowManager.LayoutParams lp = win.getAttributes();
//        win.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
////        lp.x = 100; // 新位置X坐标
//        lp.y = 300; // 新位置Y坐标
////        lp.width = 300; // 宽度
////        lp.height = 300; // 高度
////        lp.alpha = 0.7f; // 透明度
//        win.setAttributes(lp);
////        win.setBackgroundDrawableResource(R.color.colorAccent);
//        cd.show();


        mCircleView = this.findViewById(R.id.civ);

        mProgress = new CircularProgressDrawable(this);
        mProgress.setColorSchemeColors(Color.BLACK, Color.BLUE, Color.DKGRAY);
        mProgress.setStyle(CircularProgressDrawable.DEFAULT);
        mCircleView.setImageDrawable(mProgress);

        mScaleAnimation = new Animation() {
            @Override
            public void applyTransformation(float interpolatedTime, Transformation t) {
                setAnimationProgress(interpolatedTime);
            }
        };
    }


    void setAnimationProgress(float progress) {
        mCircleView.setScaleX(progress);
        mCircleView.setScaleY(progress);
    }

    private void startScaleUpAnimation(Animation.AnimationListener listener) {

        if (listener != null) {
            mCircleView.setAnimationListener(listener);
        }

        mScaleAnimation = new Animation() {
            @Override
            public void applyTransformation(float interpolatedTime, Transformation t) {
                setAnimationProgress(interpolatedTime);
            }
        };
        mScaleAnimation.setDuration(SCALE_DURATION);
        mCircleView.clearAnimation();
        mCircleView.startAnimation(mScaleAnimation);
    }


    void startScaleDownAnimation(Animation.AnimationListener listener) {

        if (listener != null) {
            mCircleView.setAnimationListener(listener);
        }

        mScaleDownAnimation = new Animation() {
            @Override
            public void applyTransformation(float interpolatedTime, Transformation t) {
                setAnimationProgress(1 - interpolatedTime);
            }
        };
        mScaleDownAnimation.setDuration(SCALE_DURATION);
        mCircleView.clearAnimation();
        mCircleView.startAnimation(mScaleDownAnimation);
    }

    void reset() {
        mCircleView.clearAnimation();
        mProgress.stop();
        mCircleView.setVisibility(View.GONE);
        setAnimationProgress(0 /* animation complete and view is hidden */);

    }
}
