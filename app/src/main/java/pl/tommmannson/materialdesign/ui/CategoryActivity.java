package pl.tommmannson.materialdesign.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import pl.tommmannson.materialdesign.R;
import pl.tommmannson.materialdesign.adapter.CategoryAdapter;
import pl.tommmannson.materialdesign.adapter.SimpleAdapter;
import pl.tommmannson.materialdesign.databinding.ActivityCategoryBinding;
import pl.tommmannson.materialdesign.databinding.ActivityStartBinding;

public class CategoryActivity extends AppCompatActivity {

    ActivityCategoryBinding binding;
    String[] listItems = null;
    CategoryAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category);

        adapter = new CategoryAdapter(this);
        binding.list.setAdapter(adapter);
        binding.list.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, CategoryActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }
}
