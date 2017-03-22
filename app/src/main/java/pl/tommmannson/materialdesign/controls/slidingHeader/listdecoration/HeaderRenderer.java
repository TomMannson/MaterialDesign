package pl.tommmannson.materialdesign.controls.slidingHeader.listdecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by tomasz.krol on 2016-03-15.
 */
public class HeaderRenderer {

    private final DimensionCalculator mDimensionCalculator;

    /**
     * The following field is used as a buffer for internal calculations. Its sole purpose is to avoid
     * allocating new Rect every time we need one.
     */
    private final Rect mTempRect = new Rect();

    public HeaderRenderer() {
        this(new DimensionCalculator());
    }

    private HeaderRenderer(DimensionCalculator dimensionCalculator) {
        mDimensionCalculator = dimensionCalculator;
    }

    /**
     * Draws a header to a canvas, offsetting by some x and y amount
     *
     * @param recyclerView the parent recycler view for drawing the header into
     * @param canvas       the canvas on which to draw the header
     * @param header       the view to draw as the header
     * @param offset       a Rect used to define the x/y offset of the header. Specify x/y offset by setting
     *                     the {@link Rect#left} and {@link Rect#top} properties, respectively.
     */
    public void drawHeader(RecyclerView recyclerView, Canvas canvas, View header, Rect offset) {
        canvas.save();

        if (recyclerView.getLayoutManager().getClipToPadding()) {
            // Clip drawing of headers to the padding of the RecyclerView. Avoids drawing in the padding
            initClipRectForHeader(mTempRect, recyclerView, header);
            canvas.clipRect(mTempRect);
        }

        canvas.translate(offset.left, offset.top);

        header.draw(canvas);
        canvas.restore();
    }

    /**
     * Initializes a clipping rect for the header based on the margins of the header and the padding of the
     * recycler.
     * FIXME: Currently right margin in VERTICAL orientation and bottom margin in HORIZONTAL
     * orientation are clipped so they look accurate, but the headers are not being drawn at the
     * correctly smaller width and height respectively.
     *
     * @param clipRect {@link Rect} for clipping a provided header to the padding of a recycler view
     * @param recyclerView for which to provide a header
     * @param header       for clipping
     */
    private void initClipRectForHeader(Rect clipRect, RecyclerView recyclerView, View header) {
        mDimensionCalculator.initMargins(clipRect, header);
        clipRect.set(
                recyclerView.getPaddingLeft(),
                recyclerView.getPaddingTop(),
                recyclerView.getWidth() - recyclerView.getPaddingRight() - clipRect.right,
                recyclerView.getHeight() - recyclerView.getPaddingBottom());

    }

}