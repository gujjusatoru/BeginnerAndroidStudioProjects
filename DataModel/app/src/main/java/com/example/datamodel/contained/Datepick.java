package com.example.datamodel.contained;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class Datepick extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        GoBetweendate get;
@NonNull
@Override
public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

final Calendar c= Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(requireContext(),this,year,month,day);
        }
@Override
public void onDateSet(DatePicker datePicker, int yr, int mn, int dy) {
        String year= String.valueOf(yr);
        String month= String.valueOf(mn);
        String day= String.valueOf(dy);
        String date=year+"-"+month+"-"+day;
        get.getDate(date);
        }

        @Override
        public void onAttach(@NonNull Context context) {
                super.onAttach(context);
                if(context instanceof GoBetweendate)
                {
                        get=(GoBetweendate)context;
                }
        }

        public interface GoBetweendate {
                void getDate(String x);

        }
}
