package dmit2504.nait.statisticscanadaapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecylerViewHolder> {

    private Context mContext;
    private List<KeyIndicator> mKeyIndicators;

    public RecyclerViewAdapter(Context context) {
        mContext = context;
        mKeyIndicators = new ArrayList<>();
    }

    public void addKeyIndicator(KeyIndicator item) {
        mKeyIndicators.add(item);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecylerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        return new RecylerViewHolder(
                inflater.inflate(R.layout.recylerview_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecylerViewHolder recylerViewHolder, int i) {
        KeyIndicator currentItem = mKeyIndicators.get(i);
        recylerViewHolder.getDateTextView().setText(currentItem.getDate());
        recylerViewHolder.getTypeTextView().setText(currentItem.getType());
        recylerViewHolder.getTitleTextView().setText(currentItem.getTitle());
        recylerViewHolder.getDescriptionTextView().setText(currentItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return mKeyIndicators.size();
    }
}
