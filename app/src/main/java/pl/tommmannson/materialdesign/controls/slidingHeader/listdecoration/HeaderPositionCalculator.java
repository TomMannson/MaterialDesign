package pl.tommmannson.materialdesign.controls.slidingHeader.listdecoration;

/**
 * Created by tomasz.krol on 2016-03-15.
 */

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Calculates the position and location of header views
 */
public class HeaderPositionCalculator {

    private final SideHeadersRecyclerAdapter mAdapter;
    private final HeaderProvider mHeaderProvider;
    private final DimensionCalculator mDimensionCalculator;

    /**
     * The following fields are used as buffers for internal calculations. Their sole purpose is to avoid
     * allocating new Rect every time we need one.
     */
    private final Rect mTempRect1 = new Rect();
    private final Rect mTempRect2 = new Rect();

    public HeaderPositionCalculator(SideHeadersRecyclerAdapter adapter, HeaderProvider headerProvider,
                                    DimensionCalculator dimensionCalculator) {
        mAdapter = adapter;
        mHeaderProvider = headerProvider;
        mDimensionCalculator = dimensionCalculator;
    }

    /**
     * Determines if a view should have a sticky header.
     * The view has a sticky header if:
     * 1. It is the first element in the recycler view
     * 2. It has a valid ID associated to its position
     *
     * @param itemView    given by the RecyclerView
     * @param orientation of the Recyclerview
     * @param position    of the list item in question
     * @return True if the view should have a sticky header
     */
    public boolean hasStickyHeader(View itemView, int orientation, int position) {
        int offset = 0, margin = 0;
        mDimensionCalculator.initMargins(mTempRect1, itemView);
        offset = itemView.getTop();
        margin = mTempRect1.top;

        return offset <= margin && mAdapter.getHeaderId(position) >= 0;
    }

    /**
     * Determines if an item in the list should have a header that is different than the item in the
     * list that immediately precedes it. Items with no headers will always return false.
     *
     * @param position of the list item in questions
     * @return true if this item has a different header than the previous item in the list
     */
    public boolean hasNewHeader(int position) {
        if (indexOutOfBounds(position)) {
            return false;
        }

        long headerId = mAdapter.getHeaderId(position);

        if (headerId < 0) {
            return false;
        }

        long nextItemHeaderId = -1;
        int prevItemPosition = position - 1;// (isReverseLayout? 1: -1);
        if (!indexOutOfBounds(prevItemPosition)) {
            nextItemHeaderId = mAdapter.getHeaderId(prevItemPosition);
        }

        boolean hasNew = position == 0 || headerId != nextItemHeaderId;

        return hasNew;
    }

    private boolean indexOutOfBounds(int position) {
        return position < 0 || position >= mAdapter.getItemCount();
    }

    public void initHeaderBounds(Rect bounds, RecyclerView recyclerView, View header, View firstView, boolean firstHeader) {
        int orientation = 1;
        initDefaultHeaderOffset(bounds, recyclerView, header, firstView);

        if (firstHeader && isStickyHeaderBeingPushedOffscreen(recyclerView, header)) {

            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int position = manager.getPosition(firstView);
            View nextView = manager.findViewByPosition(position + 1);

//            int height = header.getHeight();
//            int nextHeaderHeight = nextHeader.getHeight();
//
//            int translationX = firstView.getLeft();
//            int translationY = nextHeader.getTop()- header.getBottom();

            int data = nextView.getTop() - header.getHeight();
            bounds.top = data;

//            bounds.bottom = nextHeader.getTop();

//            View viewAfterNextHeader = getFirstViewUnobscuredByHeader(recyclerView, header);
//            int firstViewUnderHeaderPosition = recyclerView.getChildAdapterPosition(viewAfterNextHeader);
//            View secondHeader = mHeaderProvider.getHeader(recyclerView, firstViewUnderHeaderPosition);
//            translateHeaderWithNextHeader(recyclerView, 1, bounds,
//                    header, viewAfterNextHeader, secondHeader);
        }
    }

    private void initDefaultHeaderOffset(Rect headerMargins, RecyclerView recyclerView, View header, View firstView) {
        int translationX, translationY;
        mDimensionCalculator.initMargins(mTempRect1, header);

        ViewGroup.LayoutParams layoutParams = firstView.getLayoutParams();
        int leftMargin = 0;
        int topMargin = 0;
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            leftMargin = marginLayoutParams.leftMargin;
            topMargin = marginLayoutParams.topMargin;
        }
        translationX = 15;//firstView.getLeft();

        if (recyclerView.getChildAt(0) != firstView) {
            translationY = firstView.getTop();
        } else {
            translationY = Math.max(
                    firstView.getTop(),
                    getListTop(recyclerView) + mTempRect1.top);
        }

        headerMargins.set(translationX, translationY, translationX + header.getWidth(),
                translationY + header.getHeight());
    }

    private boolean isStickyHeaderBeingPushedOffscreen(RecyclerView recyclerView, View stickyHeader) {
        View viewAfterHeader = getFirstViewUnobscuredByHeader(recyclerView, stickyHeader);
        int firstViewUnderHeaderPosition = recyclerView.getChildAdapterPosition(viewAfterHeader);
        if (firstViewUnderHeaderPosition == RecyclerView.NO_POSITION) {
            return false;
        }

        if (firstViewUnderHeaderPosition > 0 && hasNewHeader(firstViewUnderHeaderPosition)) {
            View nextHeader = mHeaderProvider.getHeader(recyclerView, firstViewUnderHeaderPosition);
            mDimensionCalculator.initMargins(mTempRect1, nextHeader);
            mDimensionCalculator.initMargins(mTempRect2, stickyHeader);

            int topOfNextHeader = viewAfterHeader.getTop();
            int bottomOfThisHeader = stickyHeader.getBottom();
            if (topOfNextHeader < bottomOfThisHeader) {
                return true;
            }
        }

        return false;
    }

    private void translateHeaderWithNextHeader(RecyclerView recyclerView, int orientation, Rect translation,
                                               View currentHeader, View viewAfterNextHeader, View nextHeader) {
        mDimensionCalculator.initMargins(mTempRect1, nextHeader);
        mDimensionCalculator.initMargins(mTempRect2, currentHeader);

        int topOfStickyHeader = getListTop(recyclerView) + mTempRect2.top + mTempRect2.bottom;
        int shiftFromNextHeader = viewAfterNextHeader.getTop() - nextHeader.getHeight() - mTempRect1.bottom - mTempRect1.top - currentHeader.getHeight() - topOfStickyHeader;
        if (shiftFromNextHeader < topOfStickyHeader) {
            translation.top += shiftFromNextHeader;
        }
    }

    /**
     * Returns the first item currently in the RecyclerView that is not obscured by a header.
     *
     * @param parent Recyclerview containing all the list items
     * @return first item that is fully beneath a header
     */
    private View getFirstViewUnobscuredByHeader(RecyclerView parent, View firstHeader) {
        int step = 1;
        int from = 0;
        for (int i = from; i >= 0 && i <= parent.getChildCount() - 1; i += step) {
            View child = parent.getChildAt(i);
            if (!itemIsObscuredByHeader(parent, child, firstHeader, 1)) {
                return child;
            }
        }
        return null;
    }

    /**
     * Determines if an item is obscured by a header
     *
     * @param parent
     * @param item        to determine if obscured by header
     * @param header      that might be obscuring the item
     * @param orientation of the {@link RecyclerView}
     * @return true if the item view is obscured by the header view
     */
    private boolean itemIsObscuredByHeader(RecyclerView parent, View item, View header, int orientation) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) item.getLayoutParams();
        mDimensionCalculator.initMargins(mTempRect1, header);

        int adapterPosition = parent.getChildAdapterPosition(item);
        if (adapterPosition == RecyclerView.NO_POSITION || mHeaderProvider.getHeader(parent, adapterPosition) != header) {
            // Resolves https://github.com/timehop/sticky-headers-recyclerview/issues/36
            // Handles an edge case where a trailing header is smaller than the current sticky header.
            return false;
        }

        if (orientation == LinearLayoutManager.VERTICAL) {
            int itemTop = item.getTop() - layoutParams.topMargin;
            int headerBottom = getListTop(parent) + header.getBottom() + mTempRect1.bottom + mTempRect1.top;
            if (itemTop >= headerBottom) {
                return false;
            }
        } else {
            int itemLeft = item.getLeft() - layoutParams.leftMargin;
            int headerRight = getListLeft(parent) + header.getRight() + mTempRect1.right + mTempRect1.left;
            if (itemLeft >= headerRight) {
                return false;
            }
        }

        return true;
    }

    private int getListTop(RecyclerView view) {
        if (view.getLayoutManager().getClipToPadding()) {
            return view.getPaddingTop();
        } else {
            return 0;
        }
    }

    private int getListLeft(RecyclerView view) {
        if (view.getLayoutManager().getClipToPadding()) {
            return view.getPaddingLeft();
        } else {
            return 0;
        }
    }
}
