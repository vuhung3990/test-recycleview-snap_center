package com.example.tuandv.snapcenter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Tuandv on 6/17/2015.
 */
public class CustomItemWidth extends View {
    private final int screen_width;
    private boolean isStart;
    private boolean isEnd;

    public CustomItemWidth(Context context) {
        super(context);
        Display display= ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        screen_width = display.getWidth();
    }

    public CustomItemWidth(Context context, AttributeSet attrs) {
        super(context, attrs);
        Display display= ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        screen_width = display.getWidth();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(screen_width/4, heightMeasureSpec);
    }
}
