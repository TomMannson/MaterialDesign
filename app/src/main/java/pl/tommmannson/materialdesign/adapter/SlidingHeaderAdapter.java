package pl.tommmannson.materialdesign.adapter;

import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.tommmannson.materialdesign.R;
import pl.tommmannson.materialdesign.controls.slidingHeader.listdecoration.SideHeadersRecyclerAdapter;
import pl.tommmannson.materialdesign.databinding.ItemlayoutSlidingItemBinding;
import pl.tommmannson.materialdesign.model.GroupItemVM;

/**
 * Created by tomasz.krol on 2017-03-22.
 */

public class SlidingHeaderAdapter extends SideHeadersRecyclerAdapter<SlidingHeaderAdapter.VH> {

    List<GroupItemVM> items = new ArrayList<>();

    public SelectItemAdapter.OnItemSelectedListener getListener() {
        return listener;
    }

    public void setListener(SelectItemAdapter.OnItemSelectedListener listener) {
        this.listener = listener;
    }

    SelectItemAdapter.OnItemSelectedListener listener;

    @Override
    public SlidingHeaderAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemlayoutSlidingItemBinding binding = ItemlayoutSlidingItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlayout_select_item, parent, false);
        return new SlidingHeaderAdapter.VH(binding);
    }

    @Override
    public void onBindViewHolder(SlidingHeaderAdapter.VH holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public long getHeaderId(int position) {
        return getItem(position).getGroupId();
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlayout_sliding_header, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView.findViewById(R.id.header_text);
        textView.setText(
                String.valueOf(getItem(position).getGroupName()));
    }

    public SlidingHeaderAdapter() {
        setHasStableIds(true);
    }

    public void setItems(List<GroupItemVM> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public GroupItemVM getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class VH extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemlayoutSlidingItemBinding binding;

        public VH(ItemlayoutSlidingItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(GroupItemVM reservationItemVM) {

            binding.itemText.setText(reservationItemVM.getName());
            DrawableCompat.setTint(binding.itemImage.getBackground(), reservationItemVM.getColor());
            binding.itemImage.setText(""+reservationItemVM.getName().charAt(0));
//            binding.clickArea.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener != null){
                listener.OnItemSelected(items.get(getAdapterPosition()));
            }
        }
    }

    public interface OnItemSelectedListener {

        public void OnItemSelected(GroupItemVM selectedItem);

    }
}
