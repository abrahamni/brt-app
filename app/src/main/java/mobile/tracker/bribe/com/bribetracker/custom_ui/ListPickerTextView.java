package mobile.tracker.bribe.com.bribetracker.custom_ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import mobile.tracker.bribe.com.bribetracker.R;
import mobile.tracker.bribe.com.bribetracker.dialogs.MyDialogs;


/**
 * Created by Mikheil on 6/12/2016.
 */
public class ListPickerTextView extends TextView implements DialogInterface.OnClickListener {
    String[] list;
    ItemChooseListener itemChooseListener;
    private String pickedValue = "";
    private int selectedIndex = -1;
    private static final String KEY_SELECTED_POSITION = "SELECTED_POSITION";

    public ListPickerTextView(Context context) {
        super(context);
        setUp();
    }

    public ListPickerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUp();
    }

    public ListPickerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp();
    }

    private void setUp() {
        setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.picker_arrow_down, 0);
    }

    public void setListItemChooseListener(ItemChooseListener itemChooseListener) {
        this.itemChooseListener = itemChooseListener;
    }

    public void setList(final String[] list) {
        this.list = list;
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialogs.getSimpleDialog(getContext(), list, ListPickerTextView.this).show();
            }
        });
    }


    public void selectItem(int index) {
        selectedIndex = index;
        setText(list[selectedIndex]);
        if (itemChooseListener != null) {
            itemChooseListener.itemChosen(index, list[selectedIndex]);
        }
    }


    @Override
    public Parcelable onSaveInstanceState() {
        super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_SELECTED_POSITION, selectedIndex);
        bundle.putParcelable("instanceState", super.onSaveInstanceState());


        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            selectedIndex = bundle.getInt(KEY_SELECTED_POSITION, -1);
            if (selectedIndex != -1) {
                selectItem(selectedIndex);
            }
            state = bundle.getParcelable("instanceState");
        }
        super.onRestoreInstanceState(state);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
        if (itemChooseListener != null) {
            itemChooseListener.itemChosen(which, list[which]);
        }
        pickedValue = list[which];
        setText(pickedValue);
        selectedIndex = which;
    }

    public interface ItemChooseListener {
        void itemChosen(int position, String item);
    }

    public String getStringValue() {
        return pickedValue;
    }

    public boolean isChosen() {
        return selectedIndex != -1;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }
}
