package mobile.tracker.bribe.com.bribetracker.custom_ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import mobile.tracker.bribe.com.bribetracker.R;

/**
 * Created by Mikheil on 6/12/2016.
 */
public class DatePickerTextView extends TextView implements DatePickerDialog.OnDateSetListener {
    String[] list;
    ItemDateChosenListener dateChooseListener;
    boolean dateIsSet;
    private int year;
    private int month;
    private int day;
    private String pickedValue = "";

    private static final String KEY_SELECTED_YEAR = "SELECTED_YEAR";
    private static final String KEY_SELECTED_MONTH = "SELECTED_MONTH";
    private static final String KEY_SELECTED_DAY = "SELECTED_DAY";

    private long maxDate;

    private long minDate;

    public DatePickerTextView(Context context) {
        super(context);
        setUp();
    }

    public DatePickerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUp();
    }

    public DatePickerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp();
    }

    private void setUp() {
        setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.calendar_icon, 0);
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog picker = new DatePickerDialog(getContext(), DatePickerTextView.this, year, month, day);
                if (minDate != 0) {
                    picker.getDatePicker().setMinDate(minDate);
                }
                if (maxDate != 0) {
                    picker.getDatePicker().setMaxDate(maxDate);
                }
                picker.show();
            }
        });
    }

    public void setMinDate(long minDate) {
        this.minDate = minDate;
    }

    public void setMaxDate(long maxDate) {
        this.maxDate = maxDate;
    }

    public void setCurrentDate() {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        dateIsSet = true;
        pickedValue = formatDate();
        setText(pickedValue);
    }

    public void setListItemChooseListener(ItemDateChosenListener dateChooseListener) {
        this.dateChooseListener = dateChooseListener;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        dateIsSet = true;
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;
        if (dateChooseListener != null) {
            dateChooseListener.dateChosen(year, monthOfYear, dayOfMonth);
        }

        pickedValue = formatDate();
        setText(pickedValue);

    }

    public interface ItemDateChosenListener {
        void dateChosen(int year, int monthOfYear, int dayOfMonth);
    }

    private String formatDate() {
        return year + "-" + getFormatedDigit(month + 1) + "-" + getFormatedDigit(day);
    }

    private String getFormatedDigit(int digit) {
        return digit < 10 ? "0" + digit : digit + "";
    }

    public String getStringValue() {
        return pickedValue;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isDateSet() {
        return dateIsSet;
    }


    @Override
    public Parcelable onSaveInstanceState() {
        super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_SELECTED_DAY, day);
        bundle.putInt(KEY_SELECTED_MONTH, month);
        bundle.putInt(KEY_SELECTED_YEAR, year);
        bundle.putParcelable("instanceState", super.onSaveInstanceState());


        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            day = bundle.getInt(KEY_SELECTED_DAY, 0);
            month = bundle.getInt(KEY_SELECTED_MONTH, 0);
            year = bundle.getInt(KEY_SELECTED_YEAR, 0);
            if (!((day == 0) || (month == 0) || (year == 0))) {
                pickedValue = formatDate();
                setText(pickedValue);
                dateIsSet = true;
            }
            state = bundle.getParcelable("instanceState");
        }
        super.onRestoreInstanceState(state);
    }


}
