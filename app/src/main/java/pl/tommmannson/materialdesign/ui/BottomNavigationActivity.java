package pl.tommmannson.materialdesign.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import pl.tommmannson.materialdesign.R;


/**
 * Created by tomasz.krol on 2016-09-15.
 */
public class BottomNavigationActivity extends AppCompatActivity implements OnTabSelectListener {
    @Override
    public void onTabSelected(@IdRes int tabId) {

    }

    protected BottomBar navbar = null;
    protected FrameLayout content = null;
    protected Toolbar toolbar = null;
    protected FloatingActionButton newFitnessButton;
    protected FloatingActionButton newMealButton;
    protected FloatingActionButton newMeasureButton;
//
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bottom_nav_activity);

        navbar = (BottomBar) findViewById(R.id.bottom_nav_bar);
        content = (FrameLayout) findViewById(R.id.bottom_nav_activity_content);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        newFitnessButton = (FloatingActionButton) findViewById(R.id.newFitnessButton);
//        newMealButton = (FloatingActionButton) findViewById(R.id.newMealButton);
//        newMeasureButton = (FloatingActionButton) findViewById(R.id.newMeasurementButton);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.gofit_icon);

        navbar.setOnTabSelectListener(this);
    }
//
//    public void setContentNested(@LayoutRes int layout) {
//        LayoutInflater inflater = LayoutInflater.from(this);
//        inflater.inflate(layout, content, true);
//    }
//
//    public void setContentNested(View v) {
//        content.addView(v);
//    }
//
//    @Override
//    public void onTabSelected(@IdRes int tabId) {
//
//    }
}
