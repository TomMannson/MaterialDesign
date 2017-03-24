package pl.tommmannson.materialdesign.ui;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;

import pl.tommmannson.materialdesign.R;
import pl.tommmannson.materialdesign.databinding.ActivitySplashBinding;

/**
 * Created by tomasz.krol on 2017-03-24.
 */

public class SplashScreenActivity extends AppCompatActivity {

    ActivitySplashBinding binding = null;
    boolean isVisible;
    boolean isFinished;
    boolean isSplashFinished;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
//        setContentView(R.layout.activity_splash);
//        initStartUpAnimation();
        binding.logo.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                final ViewTreeObserver.OnPreDrawListener listener = this;
                binding.logo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.logo.getViewTreeObserver().removeOnPreDrawListener(listener);
                        isFinished = true;
                        if(isVisible) {
                            initStartUpAnimation();
                        }
                    }
                }, 1000);
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        isVisible = true;
        if(isFinished){
            binding.logo.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isFinished = true;
                    if(isVisible) {
                        initStartUpAnimation();
                    }
                }
            }, 200);
        }
        if(isSplashFinished){
            Context activity = SplashScreenActivity.this;
            CategoryActivity.start(activity);
            finish();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        isVisible = false;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void initStartUpAnimation(){


        final Animator animator = ViewAnimationUtils.createCircularReveal(binding.logo, (int) (binding.logo.getX() + binding.logo.getWidth() / 2)
        , ((int)binding.logo.getY() + binding.logo.getHeight() / 2), 0f, (float) binding.logo.getHeight());
        binding.logo.setVisibility(View.VISIBLE);
        animator.start();

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                binding.title.setVisibility(View.VISIBLE);
                binding.title.setTranslationY(500);
                binding.title.animate()
                        .setInterpolator(new AccelerateDecelerateInterpolator())
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                binding.logo.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        isSplashFinished = true;
                                        if(isVisible){
                                            Context activity = SplashScreenActivity.this;
                                            CategoryActivity.start(activity);
                                            finish();
                                        }
                                    }
                                }, 2000);
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                        .translationY(0);
                animator.removeListener(this);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        isFinished = false;

    }

}
