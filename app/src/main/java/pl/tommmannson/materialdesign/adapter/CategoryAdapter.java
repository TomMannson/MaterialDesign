package pl.tommmannson.materialdesign.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.tommmannson.materialdesign.CategoryMapper;
import pl.tommmannson.materialdesign.R;
import pl.tommmannson.materialdesign.databinding.ItemlayoutCategoryBinding;
import pl.tommmannson.materialdesign.ui.StartActivity;

/**
 * Created by tomasz.krol on 2017-03-22.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.VH> {

    String[] names;
    Drawable[] images;
    CategoryMapper mapper;

    public CategoryAdapter(Context ctx) {
        names = ctx.getResources().getStringArray(R.array.category_names_id);
        TypedArray icons = ctx.getResources().obtainTypedArray(R.array.category_image_id);
//        Drawable drawable = icons.getDrawable(0);
        images = new Drawable[icons.length()];
        for(int i =0; i < images.length; i++){
            images[i] = icons.getDrawable(i);
        }
        mapper = new CategoryMapper(ctx);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemlayoutCategoryBinding item = ItemlayoutCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new VH(item);
    }

    @Override
    public void onBindViewHolder(final VH holder, int position) {
        holder.binding.setText(names[position]);
        holder.binding.setIcon(images[position]);

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity.start(v.getContext(), mapper.getScreenClass(holder.getAdapterPosition()));
            }
        });
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class VH extends RecyclerView.ViewHolder {

        ItemlayoutCategoryBinding binding;

        public VH(ItemlayoutCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
