//Copyright (c) 2017. 章钦豪. All rights reserved.
package com.monke.monkeybook.view.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.widget.ImageView;

import com.monke.basemvplib.AppActivityManager;
import com.monke.basemvplib.impl.IPresenter;
import com.monke.monkeybook.R;
import com.monke.monkeybook.base.MBaseActivity;
import com.monke.monkeybook.presenter.ReadBookPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeToReadActivity extends MBaseActivity {

    @BindView(R.id.iv_bg)
    ImageView ivBg;

    @Override
    protected IPresenter initInjector() {
        return null;
    }

    @Override
    protected void onCreateActivity() {
        Intent intent = new Intent(WelcomeToReadActivity.this, ReadBookActivity.class);
        intent.putExtra("from", ReadBookPresenterImpl.OPEN_FROM_APP);
        // 避免从桌面启动程序后，会重新实例化入口类的activity
        if(AppActivityManager.getInstance().isExist(ReadBookActivity.class)){
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        ValueAnimator welAnimator = ValueAnimator.ofFloat(1f, 0f).setDuration(800);
        welAnimator.setStartDelay(500);
        welAnimator.addUpdateListener(animation -> {
            float alpha = (Float) animation.getAnimatedValue();
            ivBg.setAlpha(alpha);
        });
        welAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        welAnimator.start();
    }

    @Override
    protected void initData() {

    }

}
