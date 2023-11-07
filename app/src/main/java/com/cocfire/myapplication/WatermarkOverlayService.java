package com.cocfire.myapplication;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

public class WatermarkOverlayService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Read watermark data from SharedPreferences
        SharedPreferences preferences = getSharedPreferences("WatermarkData", MODE_PRIVATE);
        String watermarkText = preferences.getString("text", "Default Watermark");
        int watermarkX = preferences.getInt("x", 50); // Default X position
        int watermarkY = preferences.getInt("y", 50); // Default Y position

        // Display the overlay watermark with the retrieved data
        displayWatermarkOverlay(watermarkText, watermarkX, watermarkY);

        return START_STICKY;
    }

    private void displayWatermarkOverlay(String text, int x, int y) {
        // Create a TextView for the overlay
        TextView overlayTextView = new TextView(this);
        overlayTextView.setText(text);

        // Set the position
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT
        );

        params.gravity = Gravity.TOP | Gravity.START; // Set the gravity to top-left
        params.x = x; // Set the X position
        params.y = y; // Set the Y position

        // Add the view to the WindowManager
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowManager.addView(overlayTextView, params);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        // Remove the overlay when the service is destroyed
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
