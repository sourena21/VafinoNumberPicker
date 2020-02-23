package hseify69.ir.vafinonumberpicker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
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

    VafinoKeyboard keyboard;
    EditText edtName, edtFamily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keyboard = findViewById(R.id.keyboard);
        edtName = findViewById(R.id.edtName);
        edtFamily = findViewById(R.id.edtFamily);

        edtName.setRawInputType(InputType.TYPE_CLASS_TEXT);
        edtName.setTextIsSelectable(true);
        edtFamily.setRawInputType(InputType.TYPE_CLASS_TEXT);
        edtFamily.setTextIsSelectable(true);

        edtName.requestFocus();
        keyboard.setInput(edtName);

        edtName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    keyboard.setInput(edtName);
                }
            }
        });
        edtFamily.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    keyboard.setInput(edtFamily);
                }
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}