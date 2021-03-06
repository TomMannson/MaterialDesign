package pl.tommmannson.materialdesign.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import pl.tommmannson.materialdesign.R;
import pl.tommmannson.materialdesign.adapter.SimpleAdapter;
import pl.tommmannson.materialdesign.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {

    public static final String CATEGORY_ID = "CATEGORY_ID";

    ActivityStartBinding binding;
    String[] listItems = null;
    SimpleAdapter adapter = null;
    int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_start);

        categoryId = getIntent().getIntExtra(CATEGORY_ID, 0);

        adapter = new SimpleAdapter(this, categoryId);
        binding.list.setAdapter(adapter);
        binding.list.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

    }

    public static void start(Context context, int idOfCategory) {
        Intent starter = new Intent(context, StartActivity.class);
        starter.putExtra(CATEGORY_ID, idOfCategory);
        context.startActivity(starter);
    }
}
