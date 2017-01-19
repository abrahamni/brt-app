package mobile.tracker.bribe.com.bribetracker.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import mobile.tracker.bribe.com.bribetracker.acitivities.BaseActivity;
import mobile.tracker.bribe.com.bribetracker.dialogs.MyDialogs;


/**
 * Created by Mikheil on 6/11/2016.
 */
public class BaseFragment extends Fragment {
    private int LOCATION_REQUEST_CODE = 400;
    private static final int LOCATION_SETTINGS_REQUEST_CODE = 401;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Handler myHandler;
    public static final String LOCATION_PROVIDER = LocationManager.GPS_PROVIDER;

    private static final String TAG = "BaseFragment";


    private BaseActivity activity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BaseActivity)context;
    }

    public BaseActivity getLastActivity(){
        return activity;
    }

    protected void registerEventBus(){
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    protected void unRegisterEventBus(){
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    protected String getViewTextVialue(TextView tv){
        return tv.getText().toString();
    }

    protected String[] getStringResourceArray(int resourceId){
        return getLastActivity().getResources().getStringArray(resourceId);
    }

//    protected  void changeToolbarTitle(int titleResource, int mainColorResource){
//        EventBus.getDefault().post(new ChangeToolbarEvent(getString(titleResource), mainColorResource));
//    }
//
//    protected void changeToolbarTitle(int titleResource){
//        EventBus.getDefault().post(new ChangeToolbarEvent(getString(titleResource)));
//    }
//
//    protected void changeToolbarTitle(String title){
//        EventBus.getDefault().post(new ChangeToolbarEvent(title));
//    }

    protected String getEditTextValue(TextView editText){
        return editText.getText().toString();
    }

    protected boolean validateEmptyEditText(EditText editText, int validationText){
        if(getEditTextValue(editText).length() == 0){
            MyDialogs.getSimpleDialog(getLastActivity(), validationText).show();
            return false;
        }
        return true;
    }

    protected boolean isValidEmail(String target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches(); 
        }
    }


//
//    protected boolean isPasswordValid(EditText etPassword) {
//        String password = etPassword.getText().toString();
//        if (password.length()< 6) {
//            MyDialogs.getSimpleDialog(getLastActivity(), activity.getString(R.string.validation_password)).show();
//            return false;
//        }
//        return true;
//    }




    protected void makeClickableTextView(TextView textView, int linkResource){
        String text = getLastActivity().getString(linkResource);
        textView.setText(Html.fromHtml(text));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    protected void disableAllViews(View... views){
        for (View view : views) {
            view.setEnabled(false);
        }
    }


    public void getLocationWithPermissions() {
        if (ActivityCompat.checkSelfPermission(getLastActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getLastActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            return;
        }
        getLocation();
    }

    @SuppressWarnings({"MissingPermission"})
    private void getLocation() {
        int TIMEOUT = 3000;
        locationManager = (LocationManager) getLastActivity().getSystemService(Context.LOCATION_SERVICE);
        Looper myLooper = Looper.myLooper();
        myHandler = new Handler(myLooper);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                locationDetected(location);
                myHandler.removeCallbacksAndMessages(null);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (locationManager.isProviderEnabled(LOCATION_PROVIDER)) {
            Log.e(TAG, "Provicer Disavled " );
            locationManager.requestSingleUpdate(LOCATION_PROVIDER, locationListener, myLooper);
            myHandler.postDelayed(new Runnable() {
                public void run() {
                    locationManager.removeUpdates(locationListener);
                    locationCannotBeDetected();
                }
            }, TIMEOUT);
        } else {
            providerDisabled();
        }
    }


    protected void locationDetected(Location location) {
        Log.e(TAG, "Location " + location.getLatitude() + " - " + location.getLongitude());
    }

    protected void providerDisabled() {
//        openSettingsLinkDialog();
        Log.e(TAG, "Location Settings Problem");
    }

    protected void locationPermissionNotGranted() {
        Log.e(TAG, "Location Permission Not Granted");
    }

    protected void locationPermissionGranted() {
        Log.e(TAG, "Location Permission Granted");
    }

    protected void locationCannotBeDetected() {
        Log.e(TAG, "Location Not Found");
    }

    @SuppressWarnings({"MissingPermission"})
    @Override
    public void onPause() {
        super.onPause();
        if (locationManager != null && locationListener != null) {
            locationManager.removeUpdates(locationListener);
        }

        if (myHandler != null) {
            myHandler.removeCallbacksAndMessages(null);
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LOCATION_SETTINGS_REQUEST_CODE:
                getLocationWithPermissions();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissions.length == 1 && permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            locationPermissionNotGranted();
        }
    }

}
