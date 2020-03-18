package hseify69.ir.vafinonumberpicker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hseify69.ir.numpad.datePicker.KeyboardPersianDatePicker;
import hseify69.ir.numpad.datePicker.NumberPersianDatePicker;
import hseify69.ir.numpad.helpers.Consts;
import hseify69.ir.numpad.keyboards.VafinoKeyboard;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends Activity {

//    VafinoKeyboard keyboard;
//    EditText editText;

//    KeyboardPersianDatePicker datePicker;
//    Button btnShowDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        btnShowDate = findViewById(R.id.btnShowDate);
//        datePicker = findViewById(R.id.keyboard);
//
//        btnShowDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "date: " + datePicker.getSelectedDate(),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

//        keyboard = findViewById(R.id.keyboard);
//        editText = findViewById(R.id.editText);
//
//        editText.requestFocus();
//        keyboard.setInput(editText);
//
//        keyboard.setOnChangeEntered(new VafinoKeyboard.OnChangeEntered() {
//            @Override
//            public void onChange(String wholeEntered) {
//                Log.d("CHANGE_ENTERED", "input: " + wholeEntered);
//            }
//        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}