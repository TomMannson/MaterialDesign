package pl.tommmannson.materialdesign.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pl.tommmannson.materialdesign.R;
import pl.tommmannson.materialdesign.ScreenMapper;
import pl.tommmannson.materialdesign.databinding.SimpleItemBinding;

/**
 * Created by tomasz.krol on 2017-03-22.
 */

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.VH> {

    String[] names;
    String[] screenNames = new String[0];
    int[] categoryMaping = new int[0];

    public SimpleAdapter(Context ctx){
        names = ctx.getResources().getStringArray(R.array.mapping_screans_names);
//        mapper = new ScreenMapper(ctx, categoryId);

    }

    public SimpleAdapter(Context ctx, int categoryId) {
        names = ctx.getResources().getStringArray(R.array.mapping_screans_names);
        categoryMaping = ctx.getResources().getIntArray(R.array.mapping_screans_category);
        screenNames = ctx.getResources().getStringArray(R.array.mapping_screans);

        List<Integer> indexOfCategory = new ArrayList<>();
        for (int i = 0; i < categoryMaping.length; i++) {
            if(categoryMaping[i] == categoryId){
                indexOfCategory.add(i);
            }
        }

        String[] filtredNames = new String[indexOfCategory.size()];
        for (int i = 0; i < indexOfCategory.size(); i++) {
            filtredNames[i] = names[indexOfCategory.get(i)];
        }
        names = filtredNames;

        filtredNames = new String[indexOfCategory.size()];
        for (int i = 0; i < indexOfCategory.size(); i++) {
            filtredNames[i] = screenNames[indexOfCategory.get(i)];
        }
        screenNames = filtredNames;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        SimpleItemBinding item = SimpleItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new VH(item);
    }

    @Override
    public void onBindViewHolder(final VH holder, int position) {
        holder.binding.setText(names[position]);
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    (v.getContext())
                            .startActivity(new Intent(v.getContext(), Class.forName(getScreenClass(holder.getAdapterPosition()))));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class VH extends RecyclerView.ViewHolder{

        SimpleItemBinding binding;

        public VH(SimpleItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    public static final String PACKAGE_IF_SCREANS = "pl.tommmannson.materialdesign.ui";

    public String getScreenClass(int position){
        return String.format("%s.%s", PACKAGE_IF_SCREANS, screenNames[position]);
    }
}
