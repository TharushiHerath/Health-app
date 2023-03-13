package lk.sltc.healthapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GoalsListViewAdapter extends RecyclerView.Adapter<GoalListViewHoalder> {

    private final List<Goal> goals;
    private SharedPreferences local;
    private final Context ctx;

    public GoalsListViewAdapter(List<Goal> goals, SharedPreferences local, Context ctx) {
        this.goals = goals;
        this.local = local;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public GoalListViewHoalder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_goal_item, parent, false);
        GoalListViewHoalder goalListViewHoalder = new GoalListViewHoalder(view);
        return goalListViewHoalder;
    }

    @Override
    public void onBindViewHolder(@NonNull GoalListViewHoalder holder, int position) {
        holder.setData(goals.get(position).getName(),goals.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person currentPerson = Preferences.getCurrentPerson(local);
                currentPerson.setGoal(goals.get(position));
                Preferences.saveAsCurrentPerson(currentPerson,local);
                Preferences.saveExistingPersonToPeople(currentPerson,local);
                ctx.startActivity(new Intent(ctx, PersonalInfoActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return goals.size();
    }
}
