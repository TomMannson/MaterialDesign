package pl.tommmannson.materialdesign.controls.chips.impl;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.tommmannson.materialdesign.R;
import pl.tommmannson.materialdesign.controls.chips.Chips;
import pl.tommmannson.materialdesign.controls.chips.ChipsLayoutAdapter;
import pl.tommmannson.materialdesign.controls.chips.ChipsState;
import pl.tommmannson.materialdesign.controls.chips.DynamicChipsLayout;

/**
 * Created by tomasz.krol on 2017-03-27.
 */

public class ChipsTextAdapter implements ChipsLayoutAdapter {
    private final CharSequence[] items;
    DynamicChipsLayout layout;

    public ChipsTextAdapter(DynamicChipsLayout layout, CharSequence[]items) {
        this.items = items;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return items != null ? items.length : 0;
    }

    @Override
    public @LayoutRes int getChipsLayoutId() {
        return R.layout.marge_chips_item;
    }

    @Override
    public ChipsState getState(int position) {
        return new ChipsState()
                .setChipsText((String) items[position]);
    }

    @Override
    public Chips getView(ViewGroup parrent, final int position) {
        LayoutInflater inflater = LayoutInflater.from(parrent.getContext());
        Chips item = (Chips) inflater.inflate(R.layout.marge_chips_item, parrent, false);

        item.getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setSelected(v);
            }
        });
        item.setChipsText((String) items[position]);
        return item;
    }
}
