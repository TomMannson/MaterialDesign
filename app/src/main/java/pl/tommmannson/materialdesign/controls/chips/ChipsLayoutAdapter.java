package pl.tommmannson.materialdesign.controls.chips;

import android.view.ViewGroup;

/**
 * Created by tomasz.krol on 2017-03-27.
 */

public interface ChipsLayoutAdapter {

    int getCount();

    Chips getView(ViewGroup parrent, int position);

}
