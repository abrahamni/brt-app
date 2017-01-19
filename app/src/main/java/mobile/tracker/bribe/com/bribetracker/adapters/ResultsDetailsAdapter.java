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
import mobile.tracker.bribe.com.bribetracker.models.api.responses.InfoResultItemModel;

/**
 * Created by Mikheil on 9/4/2016.
 */

public class ResultsDetailsAdapter extends RecyclerView.Adapter<ResultsDetailsAdapter.ResponseDetailViewHolder> {
    List<InfoResultItemModel> list;

    public ResultsDetailsAdapter(List<InfoResultItemModel> list) {
        this.list = list;
    }

    @Override
    public ResponseDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result_detail, parent, false);
        return new ResponseDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResponseDetailViewHolder holder, int position) {
        InfoResultItemModel infoResultItemModel = list.get(position);
        holder.tvLabel.setText(infoResultItemModel.getLabel());
        String type = infoResultItemModel.getOptionType();
        String value = type.equals("number") || type .equals("datetime")?infoResultItemModel.getValue() : infoResultItemModel.getOptionValue();
        holder.tvValue.setText(value);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ResponseDetailViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvValue)
        TextView tvValue;
        @BindView(R.id.tvLabel)
        TextView tvLabel;

        ResponseDetailViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}
