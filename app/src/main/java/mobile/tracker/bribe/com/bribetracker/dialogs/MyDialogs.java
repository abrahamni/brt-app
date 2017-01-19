package mobile.tracker.bribe.com.bribetracker.dialogs;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.util.Calendar;

import mobile.tracker.bribe.com.bribetracker.R;

/**
 * Created by Mikheil on 6/12/2016.
 */
public class MyDialogs {
    public static AlertDialog getSimpleDialog(Context context, String[] items, DialogInterface.OnClickListener clickListener) {
        AlertDialog builder = new AlertDialog.Builder(context).setSingleChoiceItems( items, -1, clickListener).setNegativeButton(context.getString(R.string.close), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        })
                .create();
        return builder;
    }



    public static AlertDialog getSimpleDialog(Context context, String message, DialogInterface.OnClickListener positive, DialogInterface.OnClickListener negative) {
        AlertDialog builder = new AlertDialog.Builder(context).setMessage(message).setNegativeButton(context.getString(R.string.yes), positive)
                .setPositiveButton(context.getString(R.string.no), negative)
                .create();
        return builder;
    }

    public static AlertDialog getSimpleDialog(Context context, String message) {
        AlertDialog builder = new AlertDialog.Builder(context).setMessage(message).setNegativeButton(context.getString(R.string.yes), null)
                .create();
        return builder;
    }

    public static AlertDialog getSimpleDialog(Context context, int message) {
        AlertDialog builder = new AlertDialog.Builder(context).setMessage(message).setNegativeButton(context.getString(R.string.yes), null)
                .create();
        return builder;
    }

    public static AlertDialog getSimpleDialog(Context context, int message, DialogInterface.OnClickListener listener) {
        AlertDialog builder = new AlertDialog.Builder(context).setMessage(message).setNegativeButton( R.string.yes,  listener ).create();
        return builder;
    }

    public static AlertDialog getSimpleDialog(Context context, String message, DialogInterface.OnClickListener listener) {
        AlertDialog builder = new AlertDialog.Builder(context).setMessage(message).setNegativeButton( R.string.yes,  listener ).create();
        return builder;
    }


    public static DatePickerDialog getDatePicker(Context context, DatePickerDialog.OnDateSetListener listener){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog picker = new DatePickerDialog(context, listener, year, month, day);
        return picker;
    }
}
