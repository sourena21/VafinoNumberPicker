package hseify69.ir.vafinonumberpicker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.GZIPInputStream;

import hseify69.ir.numpad.keyboards.VafinoKeyboard;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends Activity {

    TextView txtName, txtFamily;
    VafinoKeyboard keyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        txtFamily = findViewById(R.id.txtFamily);
        keyboard = findViewById(R.id.keyboard);

//        keyboard.setInput(txtName);
//        setBackgroundResource(txtName, R.drawable.border_bottom_accent);
//
//        txtFamily.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                keyboard.setInput(txtFamily);
//                setBackgroundResource(txtFamily, R.drawable.border_bottom_accent);
//                setBackgroundResource(txtName, R.drawable.border_bottom_black);
//            }
//        });
//        txtName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                keyboard.setInput(txtName);
//                setBackgroundResource(txtName, R.drawable.border_bottom_accent);
//                setBackgroundResource(txtFamily, R.drawable.border_bottom_black);
//            }
//        });
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
