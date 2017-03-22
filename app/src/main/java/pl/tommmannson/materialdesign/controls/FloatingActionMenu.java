package pl.tommmannson.materialdesign.controls;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import java.util.ArrayList;
import java.util.List;

import pl.tommmannson.materialdesign.R;

/**
 * Created by tommanson on 2017-01-08.
 */
public class FloatingActionMenu extends RelativeLayout implements View.OnClickListener, Animator.AnimatorListener {

    private static final String TRANSLATION_Y = "translationY";
    private int space = 250;

    private FloatingActionButton mFloatingActionButton = null;
    private ImageView mImgView = null;
    private boolean mExpandable = false;

    private float[] mOffsets = null;
    private View[] mMenuMiniFabs = null;

    private Animation mRotateForward, mRotateBackward;

    private OnClickListener mOnClickListener = null;

    private boolean mBlocked = false;

    public FloatingActionMenu(Context context) {
        super(context);
//        setSpaceBaseOnDensity(context);
        initAnimation(context);
        init();
    }

    public FloatingActionMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
//        setSpaceBaseOnDensity(context);
        initAnimation(context);
        init();
    }

    public FloatingActionMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        setSpaceBaseOnDensity(context);
        initAnimation(context);
        init();
    }

    private void setSpaceBaseOnDensity(Context context) {
        int density = getResources().getDisplayMetrics().densityDpi;
//        Logger.d(getClass(), "density: " + density);
        switch (density) {
            case DisplayMetrics.DENSITY_TV:
                space = 81;
                break;
//            case DisplayMetrics.DENSITY_LOW:
            case DisplayMetrics.DENSITY_MEDIUM:
                space = 60;
                break;
//            case DisplayMetrics.DENSITY_HIGH:
//                space = 30;
//                break;
//
//            case DisplayMetrics.DENSITY_XHIGH:
//                space = 0;
//                break;
//            case DisplayMetrics.DENSITY_XXHIGH:
//            case DisplayMetrics.DENSITY_XXXHIGH:
//                space = 100;
//                break;
        }
    }

    public void initAnimation(Context ctx) {
        mRotateForward = AnimationUtils.loadAnimation(ctx, R.anim.rotate_forward);
        mRotateBackward = AnimationUtils.loadAnimation(ctx, R.anim.rotate_backward);
    }

    private void initMenuFab() {
        int childCount = getContent().getChildCount();
        if (childCount == 0) {
            return;
        }
        ViewGroup view = (ViewGroup) getContent().getChildAt(childCount - 1);
        if (view.getChildCount() != 2) {
            return;
        }
        if (((ViewGroup) (view.getChildAt(0))).getChildCount() == 1) {
            mFloatingActionButton = (FloatingActionButton) ((ViewGroup) (view.getChildAt(0))).getChildAt(0);
            mFloatingActionButton.setOnClickListener(this);
        }
        if (((ViewGroup) (view.getChildAt(1))).getChildCount() == 1) {
            mImgView = (ImageView) ((ViewGroup) (view.getChildAt(1))).getChildAt(0);
        }
    }

    private RelativeLayout getContent() {
        return this;
    }

    private FloatingActionButton getFab(View v) {
        try {
            if (v instanceof ViewGroup) {
                return (FloatingActionButton) ((ViewGroup) v).getChildAt(0);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
        return null;
    }

    private void init() {
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);
                initMenuFab();
                initMiniFabs();
                initAnimOffsets();
                return true;
            }
        });
    }

    private void initMiniFabs() {
        int count = getContent().getChildCount();
        if (count == 0) {
            return;
        }
        mMenuMiniFabs = new View[count - 1];
        mOffsets = new float[count - 1];
        for (int i = 0; i < count - 1; i++) {
            mMenuMiniFabs[i] = getContent().getChildAt(i);
            FloatingActionButton fab = getFab(mMenuMiniFabs[i]);
            if (fab != null) {
                fab.setOnClickListener(FloatingActionMenu.this);
            }
        }
    }

    private void initAnimOffsets() {
        int counter = 1;
        int index = 0;
        for (View child : mMenuMiniFabs) {
            FloatingActionButton fab = getFab(child);
            int offset = (int) Math.abs(mFloatingActionButton.getY() - fab.getY());
//            offset = (int) MetricConverter.convertPxToDp(getContext(), offset);
//            space = (int) MetricConverter.convertPxToDp(getContext(), child.getHeight());
            space = (int) (child.getHeight() * 0.8);
//            int spacePadding = (int) MetricConverter.convertDpToPx(getContext(), 16);
            mOffsets[index++] = offset + ((space) * counter);
            counter++;
        }
    }

    @Override
    public void onClick(View v) {
        if (mBlocked) {
            return;
        }
        mBlocked = true;
        if (mExpandable) {
            mImgView.startAnimation(mRotateBackward);
//            mImgView.setBackgroundResource(R.drawable.fab_anim_revert);
            collapseFab();
        } else {
            mImgView.startAnimation(mRotateForward);
//            mImgView.setBackgroundResource(R.drawable.fab_anim);
            expandFab();
        }
        mExpandable = !mExpandable;
//        animateFab();
        notifyOnClickListener(v);
    }

    private void notifyOnClickListener(View v) {
        if (mOnClickListener != null) {
            mOnClickListener.onClick(v);
        }
    }

    private void collapseFab() {
        AnimatorSet animatorSet = new AnimatorSet();
        List<Animator> animators = new ArrayList<Animator>();
        int counter = 0;
        for (View child : mMenuMiniFabs) {
            float offset = mOffsets[counter++];
            animators.add(createCollapseAnimator(child, offset));
        }
        animatorSet.playTogether(animators);
        animatorSet.addListener(this);
        animatorSet.start();
    }

    private void expandFab() {
        AnimatorSet animatorSet = new AnimatorSet();
        List<Animator> animators = new ArrayList<Animator>();
        int counter = 0;
        for (View child : mMenuMiniFabs) {
            float offset = mOffsets[counter++];
            animators.add(createExpandAnimator(child, offset));
        }
        animatorSet.playTogether(animators);
        animatorSet.addListener(this);
        animatorSet.start();
    }

    private Animator createCollapseAnimator(View view, float offset) {
        return ObjectAnimator.ofFloat(view, TRANSLATION_Y, -offset, 0)
                .setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
    }

    private Animator createExpandAnimator(View view, float offset) {
        return ObjectAnimator.ofFloat(view, TRANSLATION_Y, 0, -offset)
                .setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
    }

//    private void animateFab() {
//        Drawable drawable = mImgView.getBackground();
//        if (drawable instanceof Animatable) {
//            ((Animatable) drawable).start();
//        }
//    }

    @Override
    public void onAnimationStart(Animator animation) {
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        mBlocked = false;
    }

    @Override
    public void onAnimationCancel(Animator animation) {
        mBlocked = false;
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
    }

    public OnClickListener getOnClickListener() {
        return mOnClickListener;
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }
}
