package mobile.tracker.bribe.com.bribetracker.acitivities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobile.tracker.bribe.com.bribetracker.R;
import mobile.tracker.bribe.com.bribetracker.adapters.ResultsDetailsAdapter;
import mobile.tracker.bribe.com.bribetracker.helpers.JsonUtils;
import mobile.tracker.bribe.com.bribetracker.models.api.responses.InfoSingleResponseModel;

public class ResponseDetailsActivity extends AppCompatActivity {
    private static final String RESPONSE_KEY = "NEWS";
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.rvDetails)
    RecyclerView rvDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this);
        setUpViews();
    }

    private void setUpViews() {
        InfoSingleResponseModel news = (InfoSingleResponseModel) getIntent().getSerializableExtra(RESPONSE_KEY);
        rvDetails.setLayoutManager(new LinearLayoutManager(this));
        rvDetails.setAdapter(new ResultsDetailsAdapter(news.getItems()));

        tvDate.setText(JsonUtils.getFormattedDate(news.getDateTime()));
    }


    public static void start(Context context, InfoSingleResponseModel model) {
        Intent starter = new Intent(context, ResponseDetailsActivity.class);
        starter.putExtra(RESPONSE_KEY, model);
        context.startActivity(starter);
    }

}
