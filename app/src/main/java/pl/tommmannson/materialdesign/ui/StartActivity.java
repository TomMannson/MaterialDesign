package pl.tommmannson.materialdesign.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import pl.tommmannson.materialdesign.R;
import pl.tommmannson.materialdesign.adapter.SimpleAdapter;
import pl.tommmannson.materialdesign.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {

    ActivityStartBinding binding;
    String[] listItems = null;
    SimpleAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_start);

        adapter = new SimpleAdapter(this);
        binding.list.setAdapter(adapter);
        binding.list.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

    }
}
