package hseify69.ir.vafinonumberpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import hseify69.ir.numpad.Numpad;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Numpad numpad = findViewById(R.id.numpad);

        numpad.setMaxLength(11);
        numpad.setHintText("شماره تلفن همراه");
        numpad.setNumpadBackground(R.drawable.img_keypad_background);

        numpad.setOnChangeEntered(new Numpad.OnChangeEntered() {
            @Override
            public void onChange(String wholeEntered) {
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
