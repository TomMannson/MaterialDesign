package pl.tommmannson.materialdesign.controls.chips;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.tommmannson.materialdesign.R;


/**
 * Created by tomasz.krol on 2017-03-14.
 */
public class Chips extends LinearLayout {

    private static final int PLAN_CHIP_PADDING_DP = 12;
    private ChipsState chipsState = new ChipsState();

    private ViewGroup mChipsBody = null;
    private TextView mChipsTextView = null;
    private ImageView mIconImageView = null;
    private ImageView mCloseImageView = null;

    public Chips(Context context) {
        super(context);
    }

    public Chips(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public Chips(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public Chips(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

        if (attrs != null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            mChipsBody = (ViewGroup) inflater.inflate(R.layout.layout_chips, this, true);
            mIconImageView = (ImageView) findViewById(R.id.layout_chips_icon);
            mChipsTextView = (TextView) findViewById(R.id.layout_chips_text);
            mCloseImageView = (ImageView) findViewById(R.id.layout_chips_close);

            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Chips, defStyleAttr, defStyleRes);
            try {
                chipsState.setChipsColor(a.getColor(R.styleable.Chips_chip_color, Color.GRAY));
                chipsState.setChipsColorSelected(a.getColor(R.styleable.Chips_chip_color_selected, Color.GRAY));
                chipsState.setChipsText(a.getString(R.styleable.Chips_chip_text));
                chipsState.setChipsTextColor(a.getColor(R.styleable.Chips_chip_text_color, Color.WHITE));
                chipsState.setChipsImage(a.getDrawable(R.styleable.Chips_chip_image));
                chipsState.setDeleteEnable(a.getBoolean(R.styleable.Chips_chip_delete_enable, false));
                chipsState.setSelected(a.getBoolean(R.styleable.Chips_chip_selected, false));

            } catch (Exception ex) {
                throw ex;
            } finally {
                a.recycle();
            }
        }

        fillUI();
    }

    private void fillUI() {
        setChipsColor(chipsState.getChipsColor());
        mChipsTextView.setText(chipsState.getChipsText());
        mChipsTextView.setTextColor(chipsState.getChipsTextColor());

        int paddinfLeft = mChipsTextView.getPaddingStart();
        int paddinfTop = mChipsTextView.getPaddingTop();
        int paddinfRight = mChipsTextView.getPaddingEnd();
        int paddinfBottom = mChipsTextView.getPaddingBottom();

        if(chipsState.getChipsImage() != null) {
            mIconImageView.setImageDrawable(chipsState.getChipsImage());

            paddinfLeft = paddinfLeft /2;
        }

        mIconImageView.setVisibility(chipsState.getChipsImage() != null ? VISIBLE : GONE);
        mCloseImageView.setVisibility(chipsState.isDeleteEnable() ? VISIBLE : GONE);

        if(chipsState.isDeleteEnable()){
            mCloseImageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewGroup parent = (ViewGroup) v.getParent();
                    while (true){
                        if(parent instanceof Chips){
                            ((ViewGroup)parent.getParent()).removeView(parent);
                            break;
                        }
                        parent = (ViewGroup) parent.getParent();
                    }

                }
            });

            paddinfRight = paddinfRight /2;
        }

        mChipsTextView.setPadding(paddinfLeft, paddinfTop, paddinfRight, paddinfBottom);

        mChipsBody.setSelected(chipsState.isSelected());
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        return chipsState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        chipsState = (ChipsState) state;
    }

    public void setChipsText(String text) {
        mChipsTextView.setText(text);
    }

    public void setChipsColor(@ColorInt int color) {
        StateListDrawable dw = (StateListDrawable) mChipsBody.getChildAt(0).getBackground();

        StateListDrawable states = new StateListDrawable();
        states.setExitFadeDuration(300);

        Drawable presed = ContextCompat.getDrawable(getContext(), R.drawable.shape_chips_pressed);
        presed = DrawableCompat.wrap(presed);
        DrawableCompat.setTint(presed.mutate(), chipsState.getChipsColorSelected());//@drawable/shape_chips_pressed

        Drawable selected = getResources().getDrawable(R.drawable.shape_chips_pressed);
        selected = DrawableCompat.wrap(selected);
        DrawableCompat.setTint(selected.mutate(), chipsState.getChipsColorSelected());//@drawable/shape_chips_pressed

        Drawable normal = getResources().getDrawable(R.drawable.shape_chips);
        normal = DrawableCompat.wrap(normal);
        DrawableCompat.setTint(normal.mutate(), color);

        states.addState(new int[]{android.R.attr.state_pressed}, presed);
        states.addState(new int[]{android.R.attr.state_selected}, selected);
        states.addState(new int[]{}, normal);

        mChipsBody.getChildAt(0).setBackground(states);
    }
//
    public void setChipsState(ChipsState chipsState) {
        this.chipsState = chipsState;
        fillUI();
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        { Log.d("TEST", String.format("button focused: %s, enabled: %s, pressed: %s", isFocused() ? "yes" : "no", isEnabled() ? "yes" : "no", isPressed() ? "yes" : "no")); super.drawableStateChanged(); }
    }


    public Drawable getChipsImage() {
        return chipsState.getChipsImage();
    }

    public void setChipsImage(Drawable mChipsImage) {
        this.chipsState.setChipsImage(mChipsImage);
        this.mIconImageView.setImageDrawable(mChipsImage);
    }

    public String getChipsText() {
        return chipsState.getChipsText();
    }

    public int getChipsTextColor() {
        return chipsState.getChipsTextColor();
    }

    public void setChipsTextColor(@ColorInt int mChipsTextColor) {
        this.chipsState.setChipsTextColor(mChipsTextColor);
        this.mChipsTextView.setTextColor(mChipsTextColor);
    }

    public int getChipsColor() {
        return chipsState.getChipsColor();
    }
}
