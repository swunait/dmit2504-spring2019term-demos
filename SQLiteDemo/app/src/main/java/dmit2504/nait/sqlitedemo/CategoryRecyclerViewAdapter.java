package dmit2504.nait.sqlitedemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

// Step 2: inherit from RecyclerView.Adapter super class
public class CategoryRecyclerViewAdapter extends
    RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryViewHolder> {

    // Step 3: Define fields for the data source
    private Context mContext;
    private List<Category> mCategories;

    // Step 4: Create an parameterized constructor
    public CategoryRecyclerViewAdapter(Context context, List<Category> categories) {
        mContext = context;
        mCategories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new CategoryViewHolder(inflater.inflate(R.layout.listitem, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        Category currentCategory = mCategories.get(i);
        categoryViewHolder.categoryIdTextView.setText( "" + currentCategory.getCategoryId() );
        categoryViewHolder.categoryNameTextView.setText( currentCategory.getCategoryName() );
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    // Step 1: Create a ViewHolder class that defines the views for a single item
    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryIdTextView;
        public TextView categoryNameTextView;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            categoryIdTextView = itemView.findViewById(R.id.listitem_categoryId);
            categoryNameTextView = itemView.findViewById(R.id.listitem_categoryName);
        }
    }
}
