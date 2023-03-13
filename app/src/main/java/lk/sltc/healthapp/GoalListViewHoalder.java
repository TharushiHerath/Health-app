package lk.sltc.healthapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GoalListViewHoalder extends RecyclerView.ViewHolder {

    private final TextView title;
    private final TextView desc;
    private String url;

    public GoalListViewHoalder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        desc = itemView.findViewById(R.id.desc);


    }

    public void setData(String title, String desc){
        this.title.setText(title);
        this.desc.setText(desc);
    }
}
