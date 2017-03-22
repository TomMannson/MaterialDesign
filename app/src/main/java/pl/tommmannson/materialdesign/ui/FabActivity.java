package pl.tommmannson.materialdesign.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import pl.tommmannson.materialdesign.R;

/**
 * Created by tomasz.krol on 2017-03-22.
 */

public class FabActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_examples);
    }
}
