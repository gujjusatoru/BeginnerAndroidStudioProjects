package com.example.datamodel.contained;
import android.view.View;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class Timepick extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    int hr,mint;
    GoBetween get;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final Calendar c=Calendar.getInstance();
        hr=c.get(Calendar.HOUR_OF_DAY);
        mint=c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(),this, hr,mint,false);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int min) {
        if(hour>12) hour=hour-12;
        String hr=String.valueOf(hour);
        String mn=String.valueOf(min);
        String time=hr+":"+mn;
        get.getData(time);
        }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof GoBetween){
            get=(GoBetween)context;
    }
}
    public interface GoBetween {
        void getData(String x);

    }
}