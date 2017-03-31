package pl.tommmannson.materialdesign.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;
import java.util.List;

import pl.tommmannson.materialdesign.R;
import pl.tommmannson.materialdesign.adapter.StickyHeaderAdapter;
import pl.tommmannson.materialdesign.databinding.ActivityMainBinding;
import pl.tommmannson.materialdesign.model.ContactItem;

/**
 * Created by tomasz.krol on 2017-03-22.
 */

public class StickyHeaderActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    StickyHeaderAdapter itemsAdapter = new StickyHeaderAdapter();
    public static final String[] GROUPS = {"now", "today", "yasterday", "in this month", "this year"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        List<ContactItem> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            ContactItem vm = new ContactItem();
            vm.setId(i);
            vm.setColor(0xffff0000);
            int groupNumber = i/10;
            int groupid = (groupNumber+1);
            vm.setGroupName(GROUPS[groupNumber]);
            vm.setGroupId(groupid);
            vm.setTitle("Title of post on Lisy" + i);
            vm.setSubTitle("Description of post on List, Description of post on List Description of post on List" + i);
            list.add(vm);
        }
        itemsAdapter.setItems(list);

        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(itemsAdapter);
        binding.rvItems.addItemDecoration(headersDecor);
        binding.rvItems.setAdapter(itemsAdapter);
    }
}
