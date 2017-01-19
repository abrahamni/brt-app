package mobile.tracker.bribe.com.bribetracker.acitivities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.google.gson.Gson;

import mobile.tracker.bribe.com.bribetracker.R;
import mobile.tracker.bribe.com.bribetracker.fragments.MainQuestionFragment;
import mobile.tracker.bribe.com.bribetracker.helpers.JsonUtils;
import mobile.tracker.bribe.com.bribetracker.models.AnswerModel;
import mobile.tracker.bribe.com.bribetracker.models.api.JsonQuestionsListModel;

public class QuestionActivity extends BaseActivity {
    private AnswerModel answerModel = new AnswerModel();
    private JsonQuestionsListModel questionModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parseQuestionsJson();
        initFirstFragment();
    }

    private void parseQuestionsJson() {
        String json = JsonUtils.loadJSONFromAsset(this, "data.json");
        questionModel = new Gson().fromJson(json, JsonQuestionsListModel.class);
    }

    public void initFirstFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_placeholder, MainQuestionFragment.newInstance()).commit();

    }

    public void clearStack() {
        FragmentManager.BackStackEntry first = getSupportFragmentManager().getBackStackEntryAt(0);
        getSupportFragmentManager().popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out).addToBackStack(null).replace(R.id.fl_placeholder, fragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public AnswerModel getAnswerModel() {
        return answerModel;
    }

    public JsonQuestionsListModel getQuestionModel() {
        return questionModel;
    }
}
