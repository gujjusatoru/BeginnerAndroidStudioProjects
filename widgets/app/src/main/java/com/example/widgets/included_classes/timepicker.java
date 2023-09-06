package com.example.widgets.included_classes;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class timepicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c=Calendar.getInstance();
        int hr=c.get(Calendar.HOUR_OF_DAY);
        int mint=c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(),this, hr,mint,false);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int min) {
        Toast.makeText(getActivity(), "Time picked: "+hour+":"+min, Toast.LENGTH_SHORT).show();

    }
}

