package lk.sltc.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.badge.BadgeUtils;

public class SummaryActivity extends AppCompatActivity {

    TextView sum;
    Button back;
    SharedPreferences localdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        sum = findViewById(R.id.sum);
        back = findViewById(R.id.sum_back);
        localdata = getSharedPreferences("localData", MODE_PRIVATE);

        sum.setText(Preferences.calculateCalorieRequirement(localdata));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}