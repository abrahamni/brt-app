package mobile.tracker.bribe.com.bribetracker.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import mobile.tracker.bribe.com.bribetracker.R;
import mobile.tracker.bribe.com.bribetracker.acitivities.QuestionActivity;

public class SuccessFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_success, container, false);
        ButterKnife.bind(this, view);
        getLastActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        return view;
    }

    public static SuccessFragment newInstance() {

        Bundle args = new Bundle();

        SuccessFragment fragment = new SuccessFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick(R.id.tvBack)
    public void onClick() {
        ((QuestionActivity)getLastActivity()).clearStack();
        ((QuestionActivity)getLastActivity()).initFirstFragment();
    }

}
