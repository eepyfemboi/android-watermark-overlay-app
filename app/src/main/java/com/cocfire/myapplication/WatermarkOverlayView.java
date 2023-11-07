package com.cocfire.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class WatermarkOverlayView extends View {
    private String watermarkText = "Text";
    private int watermarkX = 50; // Default X position
    private int watermarkY = 50; // Default Y position
    private Paint paint;

    public WatermarkOverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED); // Set the watermark color
        paint.setTextSize(50); // Set the text size
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(watermarkText, watermarkX, watermarkY, paint); // Use watermarkX and watermarkY
    }

    public void setWatermarkText(String text) {
        watermarkText = text;
        invalidate();
    }

    public void setWatermarkPosition(int x, int y) {
        watermarkX = x;
        watermarkY = y;
        invalidate();
    }
}
