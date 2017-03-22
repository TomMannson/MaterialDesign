package pl.tommmannson.materialdesign.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import pl.tommmannson.materialdesign.R;
import pl.tommmannson.materialdesign.adapter.SlidingHeaderAdapter;
import pl.tommmannson.materialdesign.controls.slidingHeader.listdecoration.FirstItemInGroupDivider;
import pl.tommmannson.materialdesign.databinding.ActivityMainBinding;
import pl.tommmannson.materialdesign.model.GroupItemVM;

public class SlidingHeaderActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    SlidingHeaderAdapter itemsAdapter = new SlidingHeaderAdapter();

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

        final FirstItemInGroupDivider headersDecor = new FirstItemInGroupDivider(itemsAdapter);
        binding.rvItems.addItemDecoration(headersDecor);
        binding.rvItems.setAdapter(itemsAdapter);
    }
}
