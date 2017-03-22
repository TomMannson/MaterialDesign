package pl.tommmannson.materialdesign.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;
import java.util.List;

import pl.tommmannson.materialdesign.R;
import pl.tommmannson.materialdesign.adapter.SelectItemAdapter;
import pl.tommmannson.materialdesign.databinding.ActivityMainBinding;
import pl.tommmannson.materialdesign.model.GroupItemVM;

/**
 * Created by tomasz.krol on 2017-03-22.
 */

public class StickyHeaderActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    SelectItemAdapter itemsAdapter = new SelectItemAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        List<GroupItemVM> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            GroupItemVM vm = new GroupItemVM();
            vm.setId(i);
            vm.setColor(0xffff0000);
            int groupid = (i/10+1);
            vm.setGroupName("Group " + groupid );
            vm.setGroupId(groupid);
            vm.setName("Item " + i);
            list.add(vm);
        }
        itemsAdapter.setItems(list);

        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(itemsAdapter);
        binding.rvItems.addItemDecoration(headersDecor);
        binding.rvItems.setAdapter(itemsAdapter);
    }
}
