package pl.tommmannson.materialdesign.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;
import java.util.List;

import pl.tommmannson.materialdesign.R;
import pl.tommmannson.materialdesign.databinding.ItemlayoutSelectItemBinding;
import pl.tommmannson.materialdesign.model.ContactItem;

/**
 * Created by tomasz.krol on 2017-03-22.
 */

public class StickyHeaderAdapter extends RecyclerView.Adapter<StickyHeaderAdapter.VH>
        implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {

    List<ContactItem> items = new ArrayList<>();

    public OnItemSelectedListener getListener() {
        return listener;
    }

    public void setListener(OnItemSelectedListener listener) {
        this.listener = listener;
    }

    OnItemSelectedListener listener;

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemlayoutSelectItemBinding binding = ItemlayoutSelectItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlayout_select_item, parent, false);
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public long getHeaderId(int position) {
        return getItem(position).getGroupId();
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlayout_select_item_header, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView.findViewById(R.id.header_text);
        textView.setText(
                String.valueOf(getItem(position).getGroupName()));
    }

    public StickyHeaderAdapter() {
        setHasStableIds(true);
    }

    public void setItems(List<ContactItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public ContactItem getItem(int position) {
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

        ItemlayoutSelectItemBinding binding;

        public VH(ItemlayoutSelectItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ContactItem contactItem) {

            binding.itemTitle.setText(contactItem.getTitle());
//            DrawableCompat.setTint(binding.itemImage.getBackground(), contactItem.getColor());
            binding.itemSubtitle.setText(contactItem.getSubTitle());
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

        public void OnItemSelected(ContactItem selectedItem);

    }
}
