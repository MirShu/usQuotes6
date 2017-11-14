package com.quantpower.usquotes.widget.extend;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * Created by Administrator on 2017/3/28.
 */

public class MarketWebView extends WebView {
    private OnTouchScreenListener onTouchScreenListener;
    public OnScrollChangeListener listener;

    public MarketWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public MarketWebView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public MarketWebView(Context context) {
        super(context);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (onTouchScreenListener != null)
                onTouchScreenListener.onTouchScreen();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (onTouchScreenListener != null)
                onTouchScreenListener.onReleaseScreen();
        }

        return super.onTouchEvent(event);
    }

    public interface OnTouchScreenListener {
        void onTouchScreen();

        void onReleaseScreen();
    }

    public void setOnTouchScreenListener(OnTouchScreenListener onTouchScreenListener) {
        this.onTouchScreenListener = onTouchScreenListener;
    }


//    @Override
//    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        super.onScrollChanged(l, t, oldl, oldt);
//        float webcontent = getContentHeight() * getScale();// webview的高度
//        float webnow = getHeight() + getScrollY();// 当前webview的高度
//        if (Math.abs(webcontent - webnow) < 1) {
//            listener.onPageEnd(l, t, oldl, oldt);
//        } else if (getScrollY() == 0) {
//            listener.onPageTop(l, t, oldl, oldt);
//        } else {
//            listener.onScrollChanged(l, t, oldl, oldt);
//        }
//    }

    public void setOnScrollChangeListener(OnScrollChangeListener listener) {
        this.listener = listener;
    }

    public interface OnScrollChangeListener {
        public void onPageEnd(int l, int t, int oldl, int oldt);

        public void onPageTop(int l, int t, int oldl, int oldt);

        public void onScrollChanged(int l, int t, int oldl, int oldt);
    }
}



