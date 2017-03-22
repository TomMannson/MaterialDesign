package pl.tommmannson.materialdesign;

import android.content.Context;

/**
 * Created by tomasz.krol on 2017-03-22.
 */

public class ScreenMapper {

    String[] screenNames = new String[0];

    public ScreenMapper(Context ctx){
        screenNames = ctx.getResources().getStringArray(R.array.mapping_screans);
    }

    public static final String PACKAGE_IF_SCREANS = "pl.tommmannson.materialdesign.ui";

    public String getScreenClass(int position){
        return String.format("%s.%s", PACKAGE_IF_SCREANS, screenNames[position]);
    }

}
