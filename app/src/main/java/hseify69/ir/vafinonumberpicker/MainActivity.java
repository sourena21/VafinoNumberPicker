package hseify69.ir.vafinonumberpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import hseify69.ir.numpad.Numpad;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Numpad numpad = findViewById(R.id.numpad);

        numpad.setOnChangeEntered(new Numpad.OnChangeEntered() {
            @Override
            public void onChange(String wholeEntered) {
            }
        });
    }
}
