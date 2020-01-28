package hseify69.ir.vafinonumberpicker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
