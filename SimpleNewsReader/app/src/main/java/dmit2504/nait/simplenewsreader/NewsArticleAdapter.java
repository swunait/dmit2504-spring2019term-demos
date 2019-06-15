package dmit2504.nait.simplenewsreader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsArticleAdapter
        extends RecyclerView.Adapter<NewsArticleAdapter.NewsArticleViewHolder> {

    private Context mContext;
    private List<Article> mArticles;

    public NewsArticleAdapter(Context context, List<Article> articles) {
        mContext = context;
        mArticles = articles;
    }

    @NonNull
    @Override
    public NewsArticleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new NewsArticleViewHolder(
                inflater.inflate(R.layout.listitem_article, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsArticleViewHolder newsArticleViewHolder, int i) {
        Article currentArticle = mArticles.get(i);
        newsArticleViewHolder.articleTitleTextView.setText(currentArticle.getTitle());
        newsArticleViewHolder.articleDateTextView.setText(currentArticle.getPublishedAt());
        Glide.with(mContext).load(currentArticle.getUrlToImage()).into(newsArticleViewHolder.articleImageView);
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public static class NewsArticleViewHolder extends RecyclerView.ViewHolder {

        public ImageView articleImageView;
        public TextView articleTitleTextView;
        public TextView articleDateTextView;

        public NewsArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            articleImageView = itemView.findViewById(R.id.listitem_article_urlToImage);
            articleTitleTextView = itemView.findViewById(R.id.listitem_article_title);
            articleDateTextView = itemView.findViewById(R.id.listitem_artcile_publishedAt);
        }
    }
}
