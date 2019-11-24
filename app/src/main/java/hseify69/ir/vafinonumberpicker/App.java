package hseify69.ir.vafinonumberpicker;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Vazir-Black-FD.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
