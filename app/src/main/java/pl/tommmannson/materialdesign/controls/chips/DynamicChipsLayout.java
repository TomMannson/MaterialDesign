package pl.tommmannson.materialdesign.controls.chips;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import pl.tommmannson.materialdesign.R;
import pl.tommmannson.materialdesign.controls.chips.impl.ChipsTextAdapter;

/**
 * Created by tomasz.krol on 2016-09-05.
 */
public class DynamicChipsLayout extends HorizontalScrollView {
    //
    private static final String TAG = "DynamicChipsLayout";
    private LinearLayout root;
    ChipsLayoutAdapter adapter = null;

    public DynamicChipsLayout(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public DynamicChipsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public DynamicChipsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DynamicChipsLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        LayoutInflater inflater = LayoutInflater.from(context);
        this.root = (LinearLayout) inflater.inflate(R.layout.marge_chips_layout, this, false);
        this.addView(root);

        TypedArray extractedArray = null;
        try {
            extractedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DynamicChipsLayout, defStyleAttr, defStyleRes);
            CharSequence[] items = extractedArray.getTextArray(R.styleable.DynamicChipsLayout_dynamicchips_items);
            if (items != null) {
                ChipsTextAdapter adapter = new ChipsTextAdapter(this, items);
                this.setAdapter(adapter);
            }

        } finally {
            if (extractedArray != null) {
                extractedArray.recycle();
            }
        }


    }


    public void setAdapter(ChipsLayoutAdapter adapter) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        this.adapter = adapter;
        root.removeAllViews();
        if (adapter != null) {
            for (int i = 0; i < adapter.getCount(); i++) {


//                Chips item = (Chips) inflater.inflate(adapter.getChipsLayoutId(), root, false);
//                ChipsState state = adapter.getState(i);

                Chips item = adapter.getView(this.root, i);

                final int finalI = i;
//                item.setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        int index = root.indexOfChild(view) - 1;
//                        for (int j = 0; j < chipsVMs.size(); j++) {
//                            root.getChildAt(j).setSelected(view == root.getChildAt(j));
//                        }
//
//                        if (listener != null) {
//                            listener.onChipsClick(chipsVMs.get(finalI).getID());
//                        }
//                    }
//                });


//                Log.d(TAG, "" + state.getChipsText());
//                item.setChipsState(state);
                root.addView(item);
            }
        }
    }

    //
    public void setSelected(View selectedView) {

        int position = root.indexOfChild((View) selectedView.getParent());

        for (int i = 0; i < root.getChildCount(); i++) {
            View view = root.getChildAt(i);
            if(view.isSelected() && position != i){
                view.setSelected(false);
            }
            else if(!view.isSelected() && position == i){
                view.setSelected(true);
            }
        }
//                Chips item = (RenaultChips) inflater.inflate(R.layout.marge_chips_item, root, false);
//                final int finalI = i;
//                item.setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        int index = root.indexOfChild(view) - 1;
//                        for (int j = 0; j < chipsVMs.size(); j++) {
//                            root.getChildAt(j).setSelected(false);
//                        }
//                        view.setSelected(true);
//
//                        if (listener != null) {
//                            listener.onChipsClick(chipsVMs.get(finalI).getID());
//                        }
//                    }
//                });
//                Log.d(TAG, "" + chipsVMs.get(i).getName());
//                item.setChipsText(chipsVMs.get(i).getName());
//                root.addView(item);
//            }

//        LayoutInflater inflater = LayoutInflater.from(getContext());
//        root.removeAllViews();
//        if (chipsVMs != null) {
//
//            for (int i = 0; i < chipsVMs.size(); i++) {
//                Chips item = (RenaultChips) inflater.inflate(R.layout.marge_chips_item, root, false);
//                final int finalI = i;
//                item.setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        int index = root.indexOfChild(view) - 1;
//                        for (int j = 0; j < chipsVMs.size(); j++) {
//                            root.getChildAt(j).setSelected(false);
//                        }
//                        view.setSelected(true);
//
//                        if (listener != null) {
//                            listener.onChipsClick(chipsVMs.get(finalI).getID());
//                        }
//                    }
//                });
//                Log.d(TAG, "" + chipsVMs.get(i).getName());
//                item.setChipsText(chipsVMs.get(i).getName());
//                root.addView(item);
//            }
//
//
//            for (int j = 0; j < chipsVMs.size(); j++) {
//                root.getChildAt(j).setSelected(false);
//                if (root.getChildAt(j).getId() == id) {
//                    root.getChildAt(j).setSelected(true);
//                }
//            }
//            checkSelection();
//        }
    }

}


