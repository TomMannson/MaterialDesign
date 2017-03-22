package pl.tommmannson.materialdesign.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.konifar.fab_transformation.FabTransformation;

import pl.tommmannson.materialdesign.R;

/**
 * Created by tomasz.krol on 2017-03-22.
 */

public class FabTransformationsCardActivity extends AppCompatActivity implements View.OnClickListener {

    View toolbarFooter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_to_card_examples);

        toolbarFooter = findViewById(R.id.sheet);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        Button button1 = (Button) findViewById(R.id.button_1);
        Button button2 = (Button) findViewById(R.id.button_2);
        Button button3 = (Button) findViewById(R.id.button_3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FabTransformation.with(fab)
                .transformTo(toolbarFooter);
    }
}
