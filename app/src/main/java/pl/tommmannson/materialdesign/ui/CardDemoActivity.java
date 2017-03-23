package pl.tommmannson.materialdesign.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import pl.tommmannson.materialdesign.R;

/**
 * Created by tomasz.krol on 2017-03-23.
 */

public class CardDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemlayout_card_demo);
    }
}
