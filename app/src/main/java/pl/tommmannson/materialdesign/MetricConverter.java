package pl.tommmannson.materialdesign;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by tomasz.krol on 2015-11-06.
 */
public class MetricConverter {

    public static float convertDpToPx(Context ctx, float dp){
        Resources r = ctx.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

    public static float convertPxToDp(Context ctx, float px){
        Resources resources = ctx.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }
}
