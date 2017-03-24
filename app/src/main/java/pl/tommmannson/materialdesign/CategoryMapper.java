package pl.tommmannson.materialdesign;

import android.content.Context;

/**
 * Created by tomasz.krol on 2017-03-22.
 */

public class CategoryMapper {

    int[] categories = new int[0];

    public CategoryMapper(Context ctx){
        categories = ctx.getResources().getIntArray(R.array.category);
    }

    public int getScreenClass(int position){
        return categories[position];
    }


}
