package lk.sltc.healthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.List;

public class GoalsActivity extends AppCompatActivity {
    SharedPreferences localdata;
    List<Goal> goals;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    GoalsListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        localdata = getSharedPreferences("localData", MODE_PRIVATE);
        populateGoals();

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new GoalsListViewAdapter(goals,localdata, this);
        recyclerView.setAdapter(adapter);
    }

    private void populateGoals(){
        Goal g1 = new Goal("g1", "Fat loss", "Lorem");
        Goal g2 = new Goal("g2", "Build Muscle", "Lorem");
        Goal g3 = new Goal("g3", "Lean Gains", "Lorem");
        Goal g4 = new Goal("g4", "General Health", "Lorem");
        goals.add(g1);
        goals.add(g2);
        goals.add(g3);
        goals.add(g4);
    }
}