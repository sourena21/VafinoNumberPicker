package hseify69.ir.vafinonumberpicker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import hseify69.ir.numpad.keyboards.VafinoKeyboard;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends Activity {

    VafinoKeyboard keyboard;
    EditText edtName, edtFamily;
    View vwName, vwFamily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keyboard = findViewById(R.id.keyboard);
        edtName = findViewById(R.id.edtName);
        edtFamily = findViewById(R.id.edtFamily);
        vwName = findViewById(R.id.vwName);
        vwFamily = findViewById(R.id.vwFamily);

        edtName.setRawInputType(InputType.TYPE_CLASS_TEXT);
        edtName.setTextIsSelectable(true);
        edtFamily.setRawInputType(InputType.TYPE_CLASS_TEXT);
        edtFamily.setTextIsSelectable(true);

        edtName.requestFocus();
        keyboard.setInput(edtName);

        vwName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtName.requestFocus();
                keyboard.setInput(edtName);
            }
        });
        vwFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtFamily.requestFocus();
                keyboard.setInput(edtFamily);
            }
        });
        keyboard.setOnChangeEntered(new VafinoKeyboard.OnChangeEntered() {
            @Override
            public void onChange(String wholeEntered) {
                Log.d("ON_CHANGE_ENTERED", "whole entered: " + wholeEntered);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}