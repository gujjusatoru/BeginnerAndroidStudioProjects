package com.example.widgets.included_classes;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class datepicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
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
        Toast.makeText(getContext(), "Date picked: "+yr+"-"+mn+"-"+dy, Toast.LENGTH_SHORT).show();

    }
}
