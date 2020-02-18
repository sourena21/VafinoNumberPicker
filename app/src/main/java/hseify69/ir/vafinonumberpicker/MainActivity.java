package hseify69.ir.vafinonumberpicker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import hseify69.ir.numpad.keyboards.VafinoKeyboard;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends Activity {

    VafinoKeyboard keyboard;
    TextView edtName, edtFamily, txtSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keyboard = findViewById(R.id.keyboard);
        edtName = findViewById(R.id.edtName);
        edtFamily = findViewById(R.id.edtFamily);

        edtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSelected = edtName;
                keyboard.setEntered(txtSelected.getText().toString());
            }
        });
        edtFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSelected = edtFamily;
                keyboard.setEntered(txtSelected.getText().toString());
            }
        });
        keyboard.setOnChangeEntered(new VafinoKeyboard.OnChangeEntered() {
            @Override
            public void onChange(String wholeEntered) {
                showEntered(wholeEntered);
            }
        });
    }

    private void showEntered(String entered) {
        if (txtSelected != null) {
            txtSelected.setText(entered);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}