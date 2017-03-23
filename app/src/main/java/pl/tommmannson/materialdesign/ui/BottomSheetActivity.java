package pl.tommmannson.materialdesign.ui;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import pl.tommmannson.materialdesign.R;
import pl.tommmannson.materialdesign.ui.fragment.BottomSheetModalFragment;

/**
 * Created by tomasz.krol on 2017-03-22.
 */

public class BottomSheetActivity extends AppCompatActivity implements View.OnClickListener {

    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

        View bottomSheet = findViewById( R.id.bottom_sheet );
        Button button1 = (Button) findViewById( R.id.button_1 );
        Button button2 = (Button) findViewById( R.id.button_2 );
        Button button3 = (Button) findViewById( R.id.button_3 );
        findViewById(R.id.fab)
                .setOnClickListener(this);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_1: {
                if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;
            }
            case R.id.button_2: {
//                bottomModal.show(getSupportFragmentManager(), bottomModal.getTag());
            }
            case R.id.fab:{
                Snackbar.make(v, "Snackbar", Snackbar.LENGTH_SHORT).show();
            }
            break;
        }
    }

//    @Override
//    public void onClick(View v) {
//        switch( v.getId() ) {
//            case R.id.button_1: {
//                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                break;
//            }
//        }
//    }
}
