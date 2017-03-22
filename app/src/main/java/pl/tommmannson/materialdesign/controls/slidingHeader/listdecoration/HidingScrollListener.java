package pl.tommmannson.materialdesign.controls.slidingHeader.listdecoration;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

import pl.tommmannson.materialdesign.MetricConverter;


/**
 * Created by tomasz.krol on 2016-03-17.
 */
public abstract class HidingScrollListener extends RecyclerView.OnScrollListener {

    private int mToolbarOffset = 0;
    private int mToolbarHeight;

    public HidingScrollListener(final View v, final RecyclerView rv) {
        rv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mToolbarHeight = (int) (v.getHeight() + MetricConverter.convertDpToPx(v.getContext(), 16f));

                RecyclerView.ViewHolder holder = rv.findViewHolderForAdapterPosition(0);
                if (holder != null) {
                    int position = holder.getLayoutPosition();
                    if(position == 0){
                        onShow();
                    }
                } else {
                    onShow();
                }
            }
        });
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);


        int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
//        if(firstVisibleItem == 0 ) {
        clipToolbarOffset();
        onMoved(mToolbarOffset);

        if (dy > 0) {
            onHide();
        } else {
            onShow();
        }

//            if ((mToolbarOffset < mToolbarHeight && dy > 0) || (mToolbarOffset > 0 && dy < 0 && firstVisibleItem == 0)) {
//                mToolbarOffset += dy;
//            }
//        }
    }

    private void clipToolbarOffset() {
        if (mToolbarOffset > mToolbarHeight) {
            mToolbarOffset = mToolbarHeight;
        } else if (mToolbarOffset < 0) {
            mToolbarOffset = 0;
        }
    }

    public abstract void onMoved(int distance);

    public abstract void onShow();

    public abstract void onHide();
}
