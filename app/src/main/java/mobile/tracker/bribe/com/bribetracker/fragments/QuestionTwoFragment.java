package mobile.tracker.bribe.com.bribetracker.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mobile.tracker.bribe.com.bribetracker.R;
import mobile.tracker.bribe.com.bribetracker.acitivities.QuestionActivity;
import mobile.tracker.bribe.com.bribetracker.constants.Constants;
import mobile.tracker.bribe.com.bribetracker.dialogs.MyDialogs;
import mobile.tracker.bribe.com.bribetracker.models.AnswerModel;
import mobile.tracker.bribe.com.bribetracker.models.api.ApiResponseModel;
import mobile.tracker.bribe.com.bribetracker.models.api.JsonAnswerModel;
import mobile.tracker.bribe.com.bribetracker.models.api.JsonQuestionResponseItem;
import mobile.tracker.bribe.com.bribetracker.models.api.JsonQuestionResponseModel;
import mobile.tracker.bribe.com.bribetracker.models.api.JsonQuestionsListModel;
import mobile.tracker.bribe.com.bribetracker.network.RetrofitApi;
import mobile.tracker.bribe.com.bribetracker.network.RetrofitSingletone;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionTwoFragment extends BaseFragment {


    @BindView(R.id.etTitle)
    EditText etTitle;
    @BindView(R.id.llUserDataWrapper)
    LinearLayout llUserDataWrapper;
    @BindView(R.id.etTextBody)
    EditText etTextBody;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.chbAnonymous)
    CheckBox chbAnonymous;

    private static final String TAG = "QuestionTwoFragment";

    public QuestionTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_two, container, false);
        ButterKnife.bind(this, view);
        setUpViews();
        return view;
    }

    private void setUpViews() {
        getLastActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        chbAnonymous.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                llUserDataWrapper.setVisibility(isChecked ? View.GONE : View.VISIBLE);
            }
        });
    }

    public static QuestionTwoFragment newInstance() {

        Bundle args = new Bundle();

        QuestionTwoFragment fragment = new QuestionTwoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick(R.id.tvProceed)
    public void onClick() {
        submitData();
    }

    private void submitData() {
        if (isDataValid()) {
            String title = getEditTextValue(etTitle);
            String desc = getEditTextValue(etTextBody);

            boolean isAnonimous = chbAnonymous.isChecked();
            String name = getEditTextValue(etName);
            String email = getEditTextValue(etEmail);
            String phone = getEditTextValue(etPhone);

            final QuestionActivity activity = (QuestionActivity) getLastActivity();
            AnswerModel answerObject = activity.getAnswerModel();
            answerObject.setTitle(title);
            answerObject.setDesc(desc);
            answerObject.setAnonimouse(isAnonimous);
            answerObject.setName(name);
            answerObject.setEmail(email);
            answerObject.setPhone(phone);

            JsonQuestionsListModel questionModel = activity.getQuestionModel();


            int governmentAreaOptionId = questionModel.getQuestionById(Constants.AREA_OF_GOVENRMENT).getOptions().get(answerObject.getGovenrtmentArea()).getId();
            int city = questionModel.getQuestionById(Constants.CITY).getOptions().get(answerObject.getBribeCity()).getId();
            int reason = JsonAnswerModel.getAnswersByParentId(questionModel.getQuestionById(Constants.WHY_ASKED_TO_PAY_BRIBE).getOptions(), governmentAreaOptionId).get(answerObject.getBribeReason()).getId();


            JsonQuestionResponseModel responseModel = new JsonQuestionResponseModel(1);
            List<JsonQuestionResponseItem> items = new ArrayList<>();
            items.add(new JsonQuestionResponseItem(Constants.ASKED_PAY_BRIBE, answerObject.getOfferedBribe(), ""));
            items.add(new JsonQuestionResponseItem(Constants.PAYED_BRIBE, answerObject.getPayedBribe(), ""));
            items.add(new JsonQuestionResponseItem(Constants.AREA_OF_GOVENRMENT, governmentAreaOptionId, ""));
            items.add(new JsonQuestionResponseItem(Constants.WHY_ASKED_TO_PAY_BRIBE, reason, ""));
            items.add(new JsonQuestionResponseItem(Constants.CITY, city, ""));
            items.add(new JsonQuestionResponseItem(Constants.DATE, null, answerObject.getDate()));
            items.add(new JsonQuestionResponseItem(Constants.HOW_MUCH, null, answerObject.getAmount()));
            items.add(new JsonQuestionResponseItem(Constants.TITLE_REPORT, null, answerObject.getTitle()));
            items.add(new JsonQuestionResponseItem(Constants.DETAILED_COMMENT, null, answerObject.getDesc()));
            items.add(new JsonQuestionResponseItem(Constants.CONTACT_DETAILS, answerObject.isAnonimouse() ? 666 : 667, ""));
            if (!answerObject.isAnonimouse()) {
                items.add(new JsonQuestionResponseItem(Constants.FULL_NAME, null, answerObject.getName()));
                items.add(new JsonQuestionResponseItem(Constants.EMAIL, null, answerObject.getEmail()));
                items.add(new JsonQuestionResponseItem(Constants.PHONE, null, answerObject.getPhone()));
            }

            responseModel.setItems(items);


            RetrofitApi api = RetrofitSingletone.getInstance().getRetrofitApi();
            Call<ApiResponseModel> call = api.sendAnswer(responseModel);
            RetrofitSingletone.getInstance().sendRequestWithLoader(getLastActivity(), call, new Callback<ApiResponseModel>() {
                @Override
                public void onResponse(Call<ApiResponseModel> call, Response<ApiResponseModel> response) {
                    if(response.body().getError() == null) {
                        activity.changeFragment(SuccessFragment.newInstance());
                    }else {
                        MyDialogs.getSimpleDialog(getLastActivity(), getString(R.string.data_sending_error)).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponseModel> call, Throwable t) {
                    MyDialogs.getSimpleDialog(getLastActivity(), getString(R.string.error_while_sending_data), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            submitData();
                        }
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                }
            });


        }
    }

    private boolean isDataValid() {
        if (!chbAnonymous.isChecked() && TextUtils.isEmpty(getEditTextValue(etEmail))) {
            MyDialogs.getSimpleDialog(getLastActivity(), getString(R.string.validation_email)).show();
            return false;
        }

        if (!chbAnonymous.isChecked() && !android.util.Patterns.EMAIL_ADDRESS.matcher(getEditTextValue(etEmail)).matches()) {
            MyDialogs.getSimpleDialog(getLastActivity(), getString(R.string.validate_email_format)).show();
            return false;
        }

        return true;
    }
}
