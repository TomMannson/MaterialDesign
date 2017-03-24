package pl.tommmannson.materialdesign.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.konifar.fab_transformation.FabTransformation;

import pl.tommmannson.materialdesign.R;

/**
 * Created by tomasz.krol on 2017-03-22.
 */

public class FabTransformationsActivity extends AppCompatActivity implements View.OnClickListener {

    View toolbarFooter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_to_toolbar_examples);

        toolbarFooter = findViewById(R.id.toolbar_footer);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FabTransformation.with(fab)
                .setGravity(Gravity.RIGHT)
                .duration(600)
                .transformTo(toolbarFooter);
    }

    @Override
    public void onBackPressed() {

        if(toolbarFooter.getVisibility() == View.VISIBLE) {
            FabTransformation.with(fab)
                    .setGravity(Gravity.RIGHT)
                    .duration(600)
                    .transformFrom(toolbarFooter);
        }
        else{
            super.onBackPressed();
        }
    }
}
