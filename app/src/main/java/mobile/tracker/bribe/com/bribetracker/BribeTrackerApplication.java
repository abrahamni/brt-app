package mobile.tracker.bribe.com.bribetracker;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.widget.TextView;

/**
 * Created by Mikheil on 9/4/2016.

 */
public class BribeTrackerApplication extends Application{

    private static BribeTrackerApplication applicaiton;


    @Override
    public void onCreate() {
        super.onCreate();
        applicaiton = this;
    }

    public static BribeTrackerApplication getInstance(){
        return applicaiton;
    }

    public void setFontBunch(String fontName, TextView... views) {
        Typeface tf = Typeface.createFromAsset(getAssets(), fontName);
        for (TextView view : views) {
            view.setTypeface(tf);
        }
    }

    private SharedPreferences getDefaultSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void clearPrefs(){
        getDefaultSharedPreferences().edit().clear().commit();
    }

    private SharedPreferences.Editor getDefaultSharedPreferencesEditor() {
        return getDefaultSharedPreferences().edit();
    }

    public int getSavedInteger(String key, int defaultValue) {
        return getDefaultSharedPreferences().getInt(key, defaultValue);
    }

    public float getSavedFloat(String key, float defaultValue) {
        return getDefaultSharedPreferences().getFloat(key, defaultValue);
    }

    public String getSavedString(String key, String defaultValue) {
        return getDefaultSharedPreferences().getString(key, defaultValue);
    }

    public boolean getSavedBoolean(String key, boolean defaultValue) {
        return getDefaultSharedPreferences().getBoolean(key, defaultValue);
    }

    public void saveInteger(String key, int value) {
        getDefaultSharedPreferencesEditor().putInt(key, value).commit();
    }

    public void saveFloat(String key, float value) {
        getDefaultSharedPreferencesEditor().putFloat(key, value).commit();
    }

    public void saveString(String key, String value) {
        getDefaultSharedPreferencesEditor().putString(key, value).commit();
    }

    public void saveBoolean(String key, boolean value) {
        getDefaultSharedPreferencesEditor().putBoolean(key, value).commit();
    }

//    public boolean isUserSignedIn(){
//        return getSavedString(RetrofitSingletone.TOKEN_KEY, null) == null;
//
//    }
}
