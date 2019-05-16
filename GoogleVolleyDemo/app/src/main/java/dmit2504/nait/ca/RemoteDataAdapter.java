package dmit2504.nait.ca;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RemoteDataAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<RemoteData> mRemoteDataArrayList;

    public RemoteDataAdapter(Context context) {
        mContext = context;
        mRemoteDataArrayList = new ArrayList<>();
    }

    public void addRemoteData(RemoteData data) {
        mRemoteDataArrayList.add(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mRemoteDataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mRemoteDataArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView dateTextView = null;
        TextView messageTextView = null;
        TextView senderTextView = null;

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.listview_item, null);
        dateTextView = convertView.findViewById(R.id.item_date);
        messageTextView = convertView.findViewById(R.id.item_message);
        senderTextView = convertView.findViewById(R.id.item_sender);

        RemoteData data = (RemoteData) getItem(position);
        dateTextView.setText(data.getDate());
        messageTextView.setText(data.getMessage());
        senderTextView.setText(data.getSender());

        return convertView;
    }
}
