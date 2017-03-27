package pl.tommmannson.materialdesign.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import pl.tommmannson.materialdesign.R;

/**
 * Created by tomasz.krol on 2017-03-22.
 */

public class RatioImageView extends AppCompatImageView {

    float ratio = 9f/16f;

    public RatioImageView(Context context) {
        super(context);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs){

        int[] attrIndexes = {R.styleable.RatioImageView_ratio};

        TypedArray array = null;
                try{
                    array = context.obtainStyledAttributes(attrs, R.styleable.RatioImageView, 0, 0);
                    String fraction = array.getString(R.styleable.RatioImageView_ratio);
                    if(fraction != null) {
                        String[] fractionNumbers = fraction.split("/");
                        if (fractionNumbers.length <= 2 && fractionNumbers.length > 1) {
                            ratio = Float.parseFloat(fractionNumbers[0]) / Float.parseFloat(fractionNumbers[1]);
                        }
                    }
                }
                finally {
                    array.recycle();
                }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, (int) (width/ratio));
    }


}
