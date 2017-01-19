package mobile.tracker.bribe.com.bribetracker.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobile.tracker.bribe.com.bribetracker.R;
import mobile.tracker.bribe.com.bribetracker.models.api.responses.InfoSingleResponseModel;

/**
 * Created by Mikheil on 9/4/2016.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    List<InfoSingleResponseModel> list;
    ListActionsListener listener;

    public NewsAdapter(List<InfoSingleResponseModel> list, ListActionsListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        InfoSingleResponseModel responseItem = list.get(position);
        holder.tvDate.setText(responseItem.getFormattedDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvDate)
        TextView tvDate;

        NewsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(list.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface ListActionsListener {
        void onItemClicked(InfoSingleResponseModel news);
    }
}
