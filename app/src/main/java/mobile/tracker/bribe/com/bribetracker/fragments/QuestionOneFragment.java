package mobile.tracker.bribe.com.bribetracker.fragments;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mobile.tracker.bribe.com.bribetracker.R;
import mobile.tracker.bribe.com.bribetracker.acitivities.QuestionActivity;
import mobile.tracker.bribe.com.bribetracker.constants.Constants;
import mobile.tracker.bribe.com.bribetracker.custom_ui.DatePickerTextView;
import mobile.tracker.bribe.com.bribetracker.custom_ui.ListPickerTextView;
import mobile.tracker.bribe.com.bribetracker.dialogs.MyDialogs;
import mobile.tracker.bribe.com.bribetracker.models.AnswerModel;
import mobile.tracker.bribe.com.bribetracker.models.api.JsonAnswerModel;
import mobile.tracker.bribe.com.bribetracker.models.api.JsonQuestionModel;
import mobile.tracker.bribe.com.bribetracker.models.api.JsonQuestionsListModel;

public class QuestionOneFragment extends BaseFragment {

    @BindView(R.id.tvYes)
    TextView tvYes;
    @BindView(R.id.tvNo)
    TextView tvNo;
    @BindView(R.id.llAnswers)
    LinearLayout llAnswers;
    @BindView(R.id.tvGovenrmentArea)
    ListPickerTextView tvGovenrmentArea;
    @BindView(R.id.tvRason)
    ListPickerTextView tvReason;
    @BindView(R.id.tvCity)
    ListPickerTextView tvCity;
    @BindView(R.id.tvDate)
    DatePickerTextView tvDate;
    @BindView(R.id.etAmount)
    EditText etAmount;
    @BindView(R.id.tvProceed)
    TextView tvProceed;
    private JsonQuestionModel governmentAreas;
    private JsonQuestionModel cities;
    private JsonQuestionModel reasons;
    private ArrayList<JsonAnswerModel> reasonsForArea;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_one, container, false);
        ButterKnife.bind(this, view);
        getLastActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        readValues();
        fillValues();
        return view;
    }

    private void readValues() {
        JsonQuestionsListModel questions = ((QuestionActivity) getLastActivity()).getQuestionModel();
        governmentAreas = questions.getQuestionById(Constants.AREA_OF_GOVENRMENT);
        reasons = questions.getQuestionById(Constants.WHY_ASKED_TO_PAY_BRIBE);
        cities = questions.getQuestionById(Constants.CITY);
        reasonsForArea = new ArrayList<>();
    }

    private void fillValues() {
        tvDate.setMaxDate(new Date().getTime());
        tvGovenrmentArea.setList(governmentAreas.getAnswersArray());
        tvCity.setList(cities.getAnswersArray());

        tvGovenrmentArea.setListItemChooseListener(new ListPickerTextView.ItemChooseListener() {
            @Override
            public void itemChosen(int position, String item) {
                long governmentAreaId = governmentAreas.getOptions().get(position).getId();
                List<JsonAnswerModel> options = reasons.getOptions();
                reasonsForArea = JsonAnswerModel.getAnswersByParentId(options, governmentAreaId);
                tvReason.setList(JsonQuestionModel.toAnswersArray(reasonsForArea));
            }
        });
    }


    public static QuestionOneFragment newInstance() {

        Bundle args = new Bundle();

        QuestionOneFragment fragment = new QuestionOneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick({R.id.tvYes, R.id.tvNo, R.id.tvProceed})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvYes:
                tvYes.setSelected(true);
                tvNo.setSelected(false);
                break;
            case R.id.tvNo:
                tvYes.setSelected(false);
                tvNo.setSelected(true);
                break;
            case R.id.tvProceed:
                submitData();
                break;
        }
    }

    private void submitData() {
        if (isDataValid()) {
            Integer payedBribe = null;
            if (tvYes.isSelected()) {
                payedBribe = 3;
            }

            if (tvNo.isSelected()) {
                payedBribe = 4;
            }
            int governmentArea = tvGovenrmentArea.getSelectedIndex();
            int reason = tvReason.getSelectedIndex();
            int city = tvCity.getSelectedIndex();
            String date = tvDate.getStringValue();
            String amount = getEditTextValue(etAmount);

            QuestionActivity activity = (QuestionActivity) getLastActivity();
            AnswerModel answerObject = activity.getAnswerModel();
            answerObject.setAmount(amount);
            answerObject.setBribeCity(city);
            answerObject.setBribeReason(reason);
            answerObject.setDate(date);
            answerObject.setGovenrtmentArea(governmentArea);
            answerObject.setPayedBribe(payedBribe);
            activity.changeFragment(QuestionTwoFragment.newInstance());
        }
    }

    private boolean isDataValid() {

        if (!tvGovenrmentArea.isChosen()) {
            MyDialogs.getSimpleDialog(getLastActivity(), getString(R.string.validation_government_area)).show();
            return false;
        }

        if (!tvReason.isChosen()) {
            MyDialogs.getSimpleDialog(getLastActivity(), getString(R.string.validation_reason)).show();
            return false;
        }

        if (!tvCity.isChosen()) {
            MyDialogs.getSimpleDialog(getLastActivity(), getString(R.string.validation_city)).show();
            return false;
        }

        if (!tvDate.isDateSet()) {
            MyDialogs.getSimpleDialog(getLastActivity(), getString(R.string.validation_date)).show();
            return false;
        }

        if (tvYes.isSelected() && TextUtils.isEmpty(getEditTextValue(etAmount))) {
            MyDialogs.getSimpleDialog(getLastActivity(), getString(R.string.validation_amount)).show();
            return false;
        }

        return true;

    }
}
