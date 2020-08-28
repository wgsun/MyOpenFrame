package com.iflytek.autofly.mvpframe.mvp.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import com.iflytek.autofly.mvpframe.R;
import com.iflytek.autofly.mvpframe.utils.LogHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by tiandawu on 2018/9/28.
 */
public class IRecyclerView extends RecyclerView {

    private final String TAG = IRecyclerView.class.getSimpleName();

    private LayoutManager mLayoutManager;

    private Paint mPaint;

    Rect trackRect;
    Rect thumbRect;
    int trackLeft = 0;
    int trackTop = 0;
    int trackRight = 0;
    int trackBottom = 0;

    int thumbLeft = 0;
    int thumbTop = 0;
    int thumbRight = 0;
    int thumbBottom = 0;

    private int scrollBarTrackWidth = 0;       //滚动条宽度
    private int scrollBarTrackHeight = 0;        //滚动条高度
    private int scrollBarThumbSize = 0;  //滚动块宽度或者高度

    private static final int MSG_SCROLL_HIDE = 1;

    private static final int CHECK_SCROLL_HIDE_DELAY_MILLIS = 500;

    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SCROLL_HIDE:
                    showScrollBar = false;
                    invalidate();
                    break;
            }
        }
    };

    /**
     * 滑动条显示
     */
    private boolean showScrollBar = false;

    public IRecyclerView(Context context) {
        this(context, null);
    }

    public IRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }


    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.IRecyclerView);
            scrollBarTrackWidth = array.getDimensionPixelOffset(R.styleable.IRecyclerView_scrollBarTrackWidth, 0);
            scrollBarTrackHeight = array.getDimensionPixelOffset(R.styleable.IRecyclerView_scrollBarTrackHeight, 0);
            scrollBarThumbSize = array.getDimensionPixelOffset(R.styleable.IRecyclerView_scrollBarThumbSize, 0);
            array.recycle();
        }

        trackRect = new Rect();
        thumbRect = new Rect();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d(TAG, "--------------onScrollStateChanged ------------state:" + newState);
                if(mHandler.hasMessages(MSG_SCROLL_HIDE)) {
                    mHandler.removeMessages(MSG_SCROLL_HIDE);
                }
                if (newState == SCROLL_STATE_IDLE ) {
                    mHandler.sendEmptyMessageDelayed(MSG_SCROLL_HIDE, CHECK_SCROLL_HIDE_DELAY_MILLIS);
                } else {
                    showScrollBar = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!showScrollBar){
            LogHelper.d(TAG, "hide scrollbar and return");
            return;
        }
        if (mLayoutManager == null || scrollBarTrackWidth == 0 || scrollBarTrackHeight == 0 || scrollBarThumbSize == 0) {
            return;
        }

        int width = getWidth();
        int height = getHeight();
        if (getChildCount() <= 0 || width == 0 || height == 0) {
            return;
        }

        if (mLayoutManager.canScrollHorizontally()) {       //水平滚动
            int offSet = computeHorizontalScrollOffset();
            int range = computeHorizontalScrollRange();
            int extent = computeHorizontalScrollExtent();
            if ((range - extent <= 0)) {
                LogHelper.d(TAG, "onDraw: range - extent <= 0");
                return;
            }

            trackLeft = (width - scrollBarTrackWidth) / 2;
            trackTop = height - scrollBarTrackHeight;
            trackRight = (width - scrollBarTrackWidth) / 2 + scrollBarTrackWidth;
            trackBottom = height;

            int realOffset = (offSet * (scrollBarTrackWidth - scrollBarThumbSize)) / (range - extent);
            LogHelper.d(TAG, "onDraw: realOffset = " + realOffset);

            thumbLeft = trackLeft + realOffset;
            thumbTop = trackTop;
            thumbRight = (width - scrollBarTrackWidth) / 2 + scrollBarThumbSize + realOffset;
            thumbBottom = trackBottom;

        } else if (mLayoutManager.canScrollVertically()) {        //垂直滚动
            int offSet = computeVerticalScrollOffset();
            int range = computeVerticalScrollRange();
            int extent = computeVerticalScrollExtent();
            if ((range - extent <= 0)) {
                LogHelper.d(TAG, "onDraw: range - extent <= 0");
                return;
            }

            trackLeft = width - scrollBarTrackWidth + 1;
            trackTop = (height - scrollBarTrackHeight) / 2;
            trackRight = width - 1;
            trackBottom = (height - scrollBarTrackHeight) / 2 + scrollBarTrackHeight;

            int realOffset = (offSet * (scrollBarTrackHeight - scrollBarThumbSize)) / (range - extent);
            LogHelper.d(TAG, "onDraw: realOffset = " + realOffset);

            thumbLeft = width - scrollBarTrackWidth;
            thumbTop = trackTop + realOffset;
            thumbRight = width;
            thumbBottom = (height - scrollBarTrackHeight) / 2 + realOffset + scrollBarThumbSize;

        }

        trackRect.set(trackLeft, trackTop, trackRight, trackBottom);
        mPaint.setColor(0XFF373739);
        canvas.drawRect(trackRect, mPaint);

        thumbRect.set(thumbLeft, thumbTop, thumbRight, thumbBottom);
        mPaint.setColor(0XFF0BBE06);
        canvas.drawRect(thumbRect, mPaint);
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
        if (mLayoutManager != layout) {
            mLayoutManager = layout;
        }
    }
}
