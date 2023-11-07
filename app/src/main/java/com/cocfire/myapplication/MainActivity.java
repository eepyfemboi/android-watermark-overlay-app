package com.cocfire.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private WatermarkOverlayView overlayView;
    private EditText editTextWatermarkText;
    private EditText editTextWatermarkX;
    private EditText editTextWatermarkY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overlayView = new WatermarkOverlayView(this, null);

        editTextWatermarkText = findViewById(R.id.editTextWatermarkText);
        editTextWatermarkX = findViewById(R.id.editTextWatermarkX);
        editTextWatermarkY = findViewById(R.id.editTextWatermarkY);



        Button btnSetWatermark = findViewById(R.id.btnSetWatermark);
        btnSetWatermark.setOnClickListener(new View.OnClickListener() {
            private void saveWatermarkData(String text, int x, int y) {
                SharedPreferences preferences = getSharedPreferences("WatermarkData", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("text", text);
                editor.putInt("x", x);
                editor.putInt("y", y);
                editor.apply();
            }
            @Override
            public void onClick(View v) {
                String watermarkText = editTextWatermarkText.getText().toString();
                int watermarkX = Integer.parseInt(editTextWatermarkX.getText().toString());
                int watermarkY = Integer.parseInt(editTextWatermarkY.getText().toString());

                saveWatermarkData(watermarkText, watermarkX, watermarkY);

                overlayView.setWatermarkText(watermarkText);
                overlayView.setWatermarkPosition(watermarkX, watermarkY);
            }
        });



        Button btnStartWatermark = findViewById(R.id.btnStartOverlay);
        btnStartWatermark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent overlayServiceIntent = new Intent(MainActivity.this, WatermarkOverlayService.class);
                startService(overlayServiceIntent);
            }
        });
    }
}
