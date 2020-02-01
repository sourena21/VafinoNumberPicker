package hseify69.ir.vafinonumberpicker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import hseify69.ir.numpad.keyboards.VafinoKeyboard;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends Activity {

    RadioButton rbDecimal, rbPersian;
    VafinoKeyboard keyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbDecimal = findViewById(R.id.rbDecimal);
        rbPersian = findViewById(R.id.rbPersian);
        keyboard = findViewById(R.id.keyboard);

        rbDecimal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    keyboard.initKeyboardByDecimal(keyboard.getContext());
                }
            }
        });
        rbPersian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    keyboard.initKeyboardByPersian(keyboard.getContext());
                }
            }
        });
    }

    private void setBackgroundResource(TextView textView, int src) {
        int top = textView.getPaddingTop();
        int bottom = textView.getPaddingBottom();
        int rigth = textView.getPaddingRight();
        int left = textView.getPaddingLeft();
        textView.setBackgroundResource(src);
        textView.setPadding(left, top, rigth, bottom);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
