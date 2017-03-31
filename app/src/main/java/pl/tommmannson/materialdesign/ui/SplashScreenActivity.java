package pl.tommmannson.materialdesign.ui;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;

import pl.tommmannson.materialdesign.R;
import pl.tommmannson.materialdesign.databinding.ActivitySplashBinding;
import pl.tommmannson.materialdesign.ui.actions.Action;
import pl.tommmannson.materialdesign.ui.actions.ActionQueue;
import pl.tommmannson.materialdesign.ui.actions.EndAnimationListener;

/**
 * Created by tomasz.krol on 2017-03-24.
 */

public class SplashScreenActivity extends AppCompatActivity {

    ActivitySplashBinding binding = null;
    ActionQueue queue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        queue = ActionQueue.startWith(Action.start(new Runnable() {
            @Override
            public void run() {
                startUp();
            }
        }));
        queue.followedBy(Action.start(new Runnable() {
            @Override
            public void run() {
                initStartUpAnimation();
            }
        })).followedBy(Action.start(new Runnable() {
            @Override
            public void run() {
                animateLabel();
            }
        })).followedBy(Action.start(new Runnable() {
            @Override
            public void run() {
                Log.d("startup", "CategoryActivity.start");
                Context activity = SplashScreenActivity.this;
                CategoryActivity.start(activity);
                finish();
            }
        }));
    }

    @Override
    protected void onStart() {
        super.onStart();
        queue.resume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        queue.pause();
    }

    private void startUp() {
        binding.logo.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                binding.logo.getViewTreeObserver().removeOnPreDrawListener(this);
                binding.logo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        queue.notifyActionFinished(null);
                    }
                }, 1000);
                return true;
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void initStartUpAnimation() {

        final Animator animator = ViewAnimationUtils.createCircularReveal(binding.logo, (int) (binding.logo.getX() + binding.logo.getWidth() / 2)
                , ((int) binding.logo.getY() + binding.logo.getHeight() / 2), 0f, (float) binding.logo.getHeight());
        binding.logo.setVisibility(View.VISIBLE);
        animator.start();

        animator.addListener(new EndAnimationListener() {

            @Override
            public void onAnimationEnd(Animator animation) {
                animator.removeListener(this);
                queue.notifyActionFinished(null);
            }
        });
    }

    private void animateLabel() {
        binding.title.setVisibility(View.VISIBLE);
        binding.title.setTranslationY(500);

        final ViewPropertyAnimator animator = binding.title.animate();
        animator.setInterpolator(new AccelerateDecelerateInterpolator())
                .setListener(new EndAnimationListener() {

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        binding.logo.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                animator.setListener(null);
                                queue.notifyActionFinished(null);
                            }
                        }, 2000);
                    }
                })
                .translationY(0);
    }
}
