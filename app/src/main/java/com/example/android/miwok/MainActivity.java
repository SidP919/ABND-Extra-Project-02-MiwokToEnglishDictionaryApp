package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView numbers;
    private TextView family;
    private TextView colors;
    private TextView phrases;
    private TextView developer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        numbers = findViewById(R.id.numbers);
        family = findViewById(R.id.family);
        colors = findViewById(R.id.colors);
        phrases = findViewById(R.id.phrases);
        developer = findViewById(R.id.MainActivity_TextView_Developer);

        numbers.setOnClickListener(new MyListener());
        family.setOnClickListener(new MyListener());
        colors.setOnClickListener(new MyListener());
        phrases.setOnClickListener(new MyListener());
        developer.setOnClickListener(new MyListener());
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view){
                switch (view.getId()){
                    case R.id.numbers : startActivity(new Intent(MainActivity.this, NumbersActivity.class));
                        break;
                    case R.id.family : startActivity(new Intent(MainActivity.this, FamilyActivity.class));
                        break;
                    case R.id.colors : startActivity(new Intent(MainActivity.this, ColorsActivity.class));
                        break;
                    case R.id.phrases : startActivity(new Intent(MainActivity.this, PhrasesActivity.class));
                        break;
                    case R.id.MainActivity_TextView_Developer: {
                        final TextView dedicatedView = findViewById(R.id.MainActivity_TextView_DedicatedTo);
                        dedicatedView.setVisibility(View.VISIBLE);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dedicatedView.setVisibility(View.GONE);
                            }
                        }, 1200);
                    }
                    break;
            }
        }
    }
}