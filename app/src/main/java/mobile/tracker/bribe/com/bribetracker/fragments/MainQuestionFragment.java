package mobile.tracker.bribe.com.bribetracker.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mobile.tracker.bribe.com.bribetracker.R;
import mobile.tracker.bribe.com.bribetracker.acitivities.QuestionActivity;
import mobile.tracker.bribe.com.bribetracker.acitivities.ResponseDetailsActivity;
import mobile.tracker.bribe.com.bribetracker.adapters.NewsAdapter;
import mobile.tracker.bribe.com.bribetracker.models.api.responses.InfoResultModel;
import mobile.tracker.bribe.com.bribetracker.models.api.responses.InfoSingleResponseModel;
import mobile.tracker.bribe.com.bribetracker.network.RetrofitSingletone;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainQuestionFragment extends BaseFragment implements NewsAdapter.ListActionsListener {
    @BindView(R.id.tvYes)
    TextView tvYes;
    @BindView(R.id.tvNo)
    TextView tvNo;
    @BindView(R.id.rvNews)
    RecyclerView rvNews;


    private static final String TAG = "MainQuestionFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_question, container, false);
        getLastActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        fillList();
        return view;
    }

    private void fillList() {
        Call<InfoResultModel> call = RetrofitSingletone.getInstance().getRetrofitApi().getResults(RetrofitSingletone.LANGUAGE, RetrofitSingletone.FORM_ID);
        RetrofitSingletone.getInstance().sendRequestWithLoader(getLastActivity(), call, new Callback<InfoResultModel>() {
            @Override
            public void onResponse(Call<InfoResultModel> call, Response<InfoResultModel> response) {
                if(response.body() != null) {
                    rvNews.setAdapter(new NewsAdapter(response.body().getData(), MainQuestionFragment.this));
                }
            }

            @Override
            public void onFailure(Call<InfoResultModel> call, Throwable t) {
                Snackbar.make(getLastActivity().findViewById(android.R.id.content), R.string.internet_connection_problem, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.retry, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                fillList();
                            }
                        })
                        .setActionTextColor(Color.RED)
                        .show();
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        rvNews.setLayoutManager(new LinearLayoutManager(getLastActivity()));
    }

    public static MainQuestionFragment newInstance() {
        Bundle args = new Bundle();
        MainQuestionFragment fragment = new MainQuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick({R.id.tvYes, R.id.tvNo, R.id.rvNews})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvYes:
                tvYes.setSelected(true);
                tvNo.setSelected(false);
                ((QuestionActivity)getLastActivity()).changeFragment(QuestionOneFragment.newInstance());
                ((QuestionActivity)getLastActivity()).getAnswerModel().setOfferedBribe(1);
                break;
            case R.id.tvNo:
                tvYes.setSelected(false);
                tvNo.setSelected(true);
                ((QuestionActivity)getLastActivity()).changeFragment(QuestionOneFragment.newInstance());
                ((QuestionActivity)getLastActivity()).getAnswerModel().setOfferedBribe(2);
                break;
        }
    }

    @Override
    public void onItemClicked(InfoSingleResponseModel result) {
        ResponseDetailsActivity.start(getLastActivity(), result);
    }
}
