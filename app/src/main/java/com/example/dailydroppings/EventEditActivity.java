package com.example.dailydroppings;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dailydroppings.data.AppDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import co.ceryle.radiorealbutton.RadioRealButton;
import co.ceryle.radiorealbutton.RadioRealButtonGroup;

@RequiresApi(api = Build.VERSION_CODES.O)
public class EventEditActivity extends AppCompatActivity
{
    private EditText eventNameET;
    private TextView eventDateTV;
    private TextView eventTimeTV;
    private String eventFeeling;
    private LocalTime time;
    TextView tvProgressLabel;
    TimePickerDialog picker;
    EditText eTime;
    Button doneButton;
    SharedPreferences sp;
    String poopType;
    int poopColor;
    String poopDuration;
    String poopFeeling;
    LocalDate date;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

        eTime = findViewById(R.id.editTime);
        eTime.setInputType(InputType.TYPE_NULL);
        eTime.setText(currentTime);
        eTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);

                date = CalenderUtils.selectedDate;


                // time picker dialog
                picker = new TimePickerDialog(EventEditActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eTime.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();

            }
        });

        //POOPFEELING SELECTION
        RadioRealButtonGroup feelingRadioGroup = (RadioRealButtonGroup) findViewById(R.id.feelingRadioGroup);
        poopFeeling = "Neutral";
        feelingRadioGroup.setOnClickedButtonListener(new RadioRealButtonGroup.OnClickedButtonListener() {
            @Override
            public void onClickedButton(RadioRealButton button, int position) {

                final RadioRealButton amazing = (RadioRealButton) findViewById(R.id.amazingFeeling);
                final RadioRealButton good = (RadioRealButton) findViewById(R.id.goodFeeling);
                final RadioRealButton neutral = (RadioRealButton) findViewById(R.id.neutralFeeling);
                final RadioRealButton bad = (RadioRealButton) findViewById(R.id.badFeeling);
                final RadioRealButton painful = (RadioRealButton) findViewById(R.id.painfulFeeling);


                if (amazing.isChecked()) {
                    poopFeeling = "Amazing";
                } else if (good.isChecked()) {
                    poopFeeling = "Good";
                } else if (neutral.isChecked()) {
                    poopFeeling = "Neutral";
                } else if (bad.isChecked()) {
                    poopFeeling = "Bad";
                } else if (painful.isChecked()) {
                    poopFeeling = "Painful";
                } else {
                    poopFeeling = "Neutral";
                }
            }
        });


        //POOPTYPE SELECTION
        RadioGroup poopTypeRadioGroup = (RadioGroup) findViewById(R.id.poopTypeRadioGroup);
        poopType = "Type 1";
        poopTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);

                RadioButton Type1 = (RadioButton)findViewById(R.id.poopType_1);
                RadioButton Type2 = (RadioButton)findViewById(R.id.poopType_2);
                RadioButton Type3 = (RadioButton)findViewById(R.id.poopType_3);
                RadioButton Type4 = (RadioButton)findViewById(R.id.poopType_4);
                RadioButton Type5 = (RadioButton)findViewById(R.id.poopType_5);
                RadioButton Type6 = (RadioButton)findViewById(R.id.poopType_6);
                RadioButton Type7 = (RadioButton)findViewById(R.id.poopType_7);


                if (Type1.isChecked()) {
                    poopType = "Type 1";
                } else if (Type2.isChecked()) {
                    poopType = "Type 2";
                } else if (Type3.isChecked()) {
                    poopType = "Type 3";
                } else if (Type4.isChecked()) {
                    poopType = "Type 4";
                } else if (Type5.isChecked()) {
                    poopType = "Type 5";
                } else if (Type6.isChecked()) {
                    poopType = "Type 6";
                } else if (Type7.isChecked()) {
                    poopType = "Type 7";
                } else {
                    poopType = "Type 1";
                }
            }
        });


        //POOPCOLOR SELECTION

        RadioGroup poopColorRadioGroup = (RadioGroup) findViewById(R.id.poopColorRadioGroup);
        poopColor = R.drawable.circle_smallbrown;
        poopColorRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);

                RadioButton green = (RadioButton)findViewById(R.id.color_green);
                RadioButton yellow = (RadioButton)findViewById(R.id.color_yellow);
                RadioButton red = (RadioButton)findViewById(R.id.color_red);
                RadioButton brown = (RadioButton)findViewById(R.id.color_brown);
                RadioButton black = (RadioButton)findViewById(R.id.color_black);
                RadioButton white = (RadioButton)findViewById(R.id.color_white);

                if (green.isChecked()) {
                    poopColor = R.drawable.circle_smallgreen;
                } else if (yellow.isChecked()) {
                    poopColor = R.drawable.circle_smallyellow;
                } else if (red.isChecked()) {
                    poopColor = R.drawable.circle_smallred;
                } else if (brown.isChecked()) {
                    poopColor = R.drawable.circle_smallbrown;
                } else if (black.isChecked()) {
                    poopColor = R.drawable.circle_smallblack;
                } else if (white.isChecked()) {
                    poopColor = R.drawable.circle_smallwhite;
                } else {
                    poopColor = R.drawable.circle_smallbrown;
                }
            }
        });


        doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveNewUser(eTime.getText().toString(), poopType, poopColor, poopDuration, poopFeeling, date);

                finish();

                Toast.makeText(EventEditActivity.this, "Saved", Toast.LENGTH_LONG).show();
            }
        });
        

        // set a change listener on the SeekBar
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        int progress = seekBar.getProgress();
        tvProgressLabel = findViewById(R.id.textView);
        tvProgressLabel.setText(progress + " Minutes");

        if (progress == 0) {
            poopDuration = "0";
        } else {
            poopDuration = String.valueOf(progress);
        }

    }



    private void saveNewUser(String eText, String poopType, int poopColor, String poopDuration, String poopFeeling, LocalDate date) {
        AppDatabase db  = AppDatabase.getDbInstance(this.getApplicationContext());

        Event newEvent = new Event(eText, poopType, poopColor, poopDuration, poopFeeling, date);
        newEvent.poopFeeling = poopFeeling;
        newEvent.poopType = poopType;
        newEvent.poopColor = poopColor;
        newEvent.poopDuration = poopDuration;
        newEvent.date = CalenderUtils.selectedDate;
        newEvent.name = eText;
        db.eventDao().insertUser(newEvent);

        Event.eventsList.add(newEvent);

        finish();
    }



//    public void saveEventAction(View view)
//    {
//        String eventName = eventNameET.getText().toString();
//        Event newEvent = new Event(eventName, CalenderUtils.selectedDate, time);
//        Event.eventsList.add(newEvent);
//        finish();
//    }

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            tvProgressLabel.setText(progress + " Minutes");

            if (progress == 0) {
                poopDuration = "0";
            } else {
                poopDuration = String.valueOf(progress);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // called after the user finishes moving the SeekBar
        }
    };

}


