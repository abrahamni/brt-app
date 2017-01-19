package mobile.tracker.bribe.com.bribetracker.acitivities;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import mobile.tracker.bribe.com.bribetracker.helpers.General;

/**
 * Created by Mikheil on 6/11/2016.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        hideKeyboardOnOutsideClick();
    }

    public void hideKeyboardOnOutsideClick(){
        View rootView =((ViewGroup)findViewById(android.R.id.content)).
                getChildAt(0);
        rootView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    General.hideKeyboard(BaseActivity.this);
                }
            }
        });
    }

    protected void registerEventBus(){
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    protected void unregisterEventBus(){
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }
}
