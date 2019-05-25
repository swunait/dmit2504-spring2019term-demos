package dmit2504.nait.statisticscanadaapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import lombok.Getter;

public class RecylerViewHolder extends RecyclerView.ViewHolder {

    @Getter private TextView dateTextView;
    @Getter private TextView typeTextView;
    @Getter private TextView titleTextView;
    @Getter private TextView descriptionTextView;

    public RecylerViewHolder(@NonNull View itemView) {
        super(itemView);

        dateTextView = itemView.findViewById(R.id.recyclerview_item_date);
        typeTextView = itemView.findViewById(R.id.recyclerview_item_type);
        titleTextView = itemView.findViewById(R.id.recyclerview_item_title);
        descriptionTextView = itemView.findViewById(R.id.recylerview_item_description);
    }

}
