package lk.sltc.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PersonalInfoActivity extends AppCompatActivity {

    EditText weight,height,age;
    Button back, next;
SharedPreferences localdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        weight = findViewById(R.id.weight);
        height =findViewById(R.id.height);
        age = findViewById(R.id.age);
        back = findViewById(R.id.back);
        next = findViewById(R.id.next);

        localdata = getSharedPreferences("localData", MODE_PRIVATE);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person p  = Preferences.getCurrentPerson(localdata);
                p.setWeight(Double.parseDouble(weight.getText().toString()));
                p.setHeight(Double.parseDouble(height.getText().toString()));
                p.setAge(Integer.parseInt(age.getText().toString()));

                Preferences.saveAsCurrentPerson(p, localdata);
                startActivity(new Intent(PersonalInfoActivity.this, SummaryActivity.class));
            }
        });
    }
}