package lk.sltc.healthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText firstname, lastname, email;
    Button next;
    SharedPreferences localdata;
Preferences pref = new Preferences();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstname = findViewById(R.id.et_fname);
        lastname = findViewById(R.id.et_lname);
        email = findViewById(R.id.et_email);
        next = findViewById(R.id.btn_next);

        localdata = getSharedPreferences("localData", MODE_PRIVATE);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a,b,c;
                a = firstname.getText().toString();
                b = lastname.getText().toString();
                c = email.getText().toString();

                Person person = new Person(a,b,c,null,null,0,0,0);
                pref.saveAsCurrentPerson(person,localdata);
                startActivity(new Intent(MainActivity.this, GoalsActivity.class));
            }
        });

    }
}