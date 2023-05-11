package com.example.fortunekg.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fortunekg.R;
import com.example.fortunekg.wheel.Wheel;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView infoTextView;
    private ImageView imageView1, imageView2, imageView3;
    private Wheel wheel1, wheel2, wheel3;
    private Button button;
    private boolean isStarted;
    public static final Random random = new Random();

    public static long randomLong(long lower, long upper) {
        return lower + (long) (random.nextDouble() * (upper - lower));
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.image3);
        button = findViewById(R.id.button);
        infoTextView = findViewById(R.id.textView);
        button.setOnClickListener(view -> {
            if (isStarted) {
                wheel1.stopWheel();
                wheel2.stopWheel();
                wheel3.stopWheel();
                if (wheel1.currentIndex == wheel2.currentIndex && wheel2.currentIndex == wheel3.currentIndex) {
                    infoTextView.setText("Cиз чоң байгеге ээ болдуңуз! (5000 сом)");
                } else if (wheel1.currentIndex == wheel2.currentIndex || wheel2.currentIndex == wheel3.currentIndex || wheel1.currentIndex == wheel3.currentIndex) {
                    infoTextView.setText("Кичинекей сыйлык (100 сом)");
                } else {
                    infoTextView.setText("Сиз утулдуңуз! =(");
                }

                button.setText("Start");
                isStarted = false;

            } else {

                wheel1 = new Wheel(img -> runOnUiThread(() -> imageView1.setImageResource(img)), 200, randomLong(0, 200));

                wheel1.start();

                wheel2 = new Wheel(img -> runOnUiThread(() -> imageView2.setImageResource(img)), 200, randomLong(150, 400));

                wheel2.start();

                wheel3 = new Wheel(img -> runOnUiThread(() -> imageView3.setImageResource(img)), 200, randomLong(150, 400));

                wheel3.start();

                button.setText("Токтотуу");
                infoTextView.setText("");
                isStarted = true;
            }
        });
    }
}
