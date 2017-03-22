package pl.tommmannson.materialdesign.controls.slidingHeader.listdecoration;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by tomasz.krol on 2016-03-15.
 */
public abstract class SideHeadersRecyclerAdapter<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {

    public abstract long getHeaderId(int position);

    public abstract RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent);

    public abstract void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position);
}
