package hseify69.ir.vafinonumberpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import hseify69.ir.numpad.Numpad;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Numpad numpad = findViewById(R.id.numpad);
        TextView txtInput1 = findViewById(R.id.txtInput1);
        TextView txtInput2 = findViewById(R.id.txtInput2);
        TextView txtInput3 = findViewById(R.id.txtInput3);

        numpad.setMaxLength(10);

        numpad.setOnChangeEntered(new Numpad.OnChangeEntered() {
            @Override
            public void onChange(String wholeEntered) {
            }
        });
        txtInput1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
