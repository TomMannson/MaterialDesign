package pl.tommmannson.materialdesign.controls.slidingHeader.listdecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by tomasz.krol on 2016-03-15.
 */
public class FirstItemInGroupDivider extends RecyclerView.ItemDecoration
{

    private HeaderRenderer mRenderer;
    private DimensionCalculator mDimensionCalculator;
    SideHeadersRecyclerAdapter mAdapter = null;
    private HeaderPositionCalculator mHeaderPositionCalculator;
    private HeaderProvider mHeaderProvider;
    private final SparseArray<Rect> mHeaderRects = new SparseArray<>();

    public FirstItemInGroupDivider(SideHeadersRecyclerAdapter adapter) {
        super();
        mAdapter = adapter;
        mHeaderProvider = new HeaderViewCache(adapter);
        mRenderer = new HeaderRenderer();
        mDimensionCalculator = new DimensionCalculator();
        mHeaderPositionCalculator = new HeaderPositionCalculator(mAdapter, mHeaderProvider, mDimensionCalculator);
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(canvas, parent, state);

        final int childCount = parent.getChildCount();
        if (childCount <= 0 || mAdapter.getItemCount() <= 0) {
            return;
        }

        for (int i = 0; i < childCount; i++) {
            View itemView = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(itemView);
            if (position == RecyclerView.NO_POSITION) {
                continue;
            }


            boolean hasStickyHeader = mHeaderPositionCalculator.hasStickyHeader(itemView, 1, position);

            if (hasStickyHeader || mHeaderPositionCalculator.hasNewHeader(position)) {
                View header = mHeaderProvider.getHeader(parent, position);
                //re-use existing Rect, if any.
                Rect headerOffset = mHeaderRects.get(position);

                if (headerOffset == null) {
                    headerOffset = new Rect();
                    mHeaderRects.put(position, headerOffset);
                }
                mHeaderPositionCalculator.initHeaderBounds(headerOffset, parent, header, itemView, hasStickyHeader);
                mRenderer.drawHeader(parent, canvas, header, headerOffset);
            }
        }

    }
}
