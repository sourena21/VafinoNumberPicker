package hseify69.ir.numpad.datePicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import androidx.annotation.Nullable;

import hseify69.ir.numpad.R;
import hseify69.ir.numpad.helpers.Consts;
import hseify69.ir.numpad.helpers.Utils;
import saman.zamani.persiandate.PersianDate;

public class NumberPersianDatePicker extends LinearLayout {

    int maxYear = Consts.maxYear;
    int year = Consts.minYear;
    int minYear = Consts.minYear;
    int maxMonth = Consts.maxMonth;
    int month = Consts.minMonth;
    int minMonth = Consts.minMonth;
    int maxDay = Consts.maxDay;
    int day = Consts.minDay;
    int minDay = Consts.minDay;
    PersianDate persianDate;

    NumberPicker npcYear, npcMonth, npcDay;

    public NumberPersianDatePicker(Context context) {
        super(context);
        init(context, null);
    }

    public NumberPersianDatePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public NumberPersianDatePicker(Context context, @Nullable AttributeSet attrs, @Nullable int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.removeAllViews();
        View v = View.inflate(context, R.layout.number_persian_date_picker, null);

        npcYear = v.findViewById(R.id.NPDP_npcYear);
        npcMonth = v.findViewById(R.id.NPDP_npcMonth);
        npcDay = v.findViewById(R.id.NPDP_npcDay);

        persianDate = new PersianDate();
        setYear(persianDate.getShYear());
        setMonth(persianDate.getShMonth());
        setDay(persianDate.getShDay());

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NumberPersianDatePicker);

        maxYear = ta.getInteger(R.styleable.NumberPersianDatePicker_maxYear, Consts.maxYear);
        minYear = ta.getInteger(R.styleable.NumberPersianDatePicker_minYear, Consts.minYear);
        year = ta.getInteger(R.styleable.NumberPersianDatePicker_year, persianDate.getShYear());
        month = ta.getInteger(R.styleable.NumberPersianDatePicker_month, persianDate.getShMonth());
        day = ta.getInteger(R.styleable.NumberPersianDatePicker_day, persianDate.getShDay());

        npcYear.setMinValue(minYear);
        npcYear.setMaxValue(maxYear);
        npcYear.setValue(year);
        npcMonth.setMinValue(minMonth);
        npcMonth.setMaxValue(maxMonth);
        npcMonth.setValue(month);
        npcDay.setMinValue(minDay);
        npcDay.setMaxValue(Utils.getDayRange(npcYear.getValue(), npcMonth.getValue()));
        npcDay.setValue(day);

        npcYear.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                persianDate.setShYear(newVal);
                npcDay.setMaxValue(Utils.getDayRange(newVal, npcMonth.getValue()));
            }
        });
        npcMonth.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                persianDate.setShMonth(newVal);
                npcDay.setMaxValue(Utils.getDayRange(npcYear.getValue(), newVal));
            }
        });
        npcDay.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                persianDate.setShDay(newVal);
            }
        });

        this.addView(v);
    }

    public int getMaxYear() {
        return maxYear;
    }

    public void setMaxYear(int max) {
        if (max > minYear) {
            maxYear = max;
            npcYear.setMaxValue(maxYear);
        }
        if (max < year) {
            year = max;
            npcYear.setValue(year);
        }
    }

    public int getMinYear() {
        return minYear;
    }

    public void setMinYear(int min) {
        if (min < maxYear) {
            this.minYear = min;
            npcYear.setMinValue(min);
        }
        if (min > year) {
            year = min;
            npcYear.setValue(year);
        }
    }

    public int getMaxMonth() {
        return maxMonth;
    }

    public void setMaxMonth(int max) {
        if (max > minMonth) {
            maxMonth = max;
            npcMonth.setMaxValue(maxMonth);
        }
        if (max < month) {
            month = max;
            npcMonth.setValue(month);
        }
    }

    public int getMinMonth() {
        return minMonth;
    }

    public void setMinMonth(int min) {
        if (min < maxMonth) {
            this.minMonth = min;
            npcMonth.setMinValue(min);
        }
        if (min > month) {
            month = min;
            npcMonth.setValue(month);
        }
    }

    public int getMaxDay() {
        return maxDay;
    }

    public void setMaxDay(int max) {
        if (max > minDay) {
            maxDay = max;
            npcDay.setMaxValue(maxDay);
        }
        if (max < day) {
            day = max;
            npcDay.setValue(day);
        }
    }

    public int getMinDay() {
        return minDay;
    }

    public void setMinDay(int min) {
        if (min < maxDay) {
            this.minDay = min;
            npcDay.setMinValue(min);
        }
        if (min > day) {
            day = min;
            npcDay.setValue(day);
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int y) {
        if (y <= maxYear && y >= minYear) {
            year = y;
            npcYear.setValue(y);
            npcDay.setMaxValue(Utils.getDayRange(npcYear.getValue(), npcMonth.getValue()));
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int m) {
        if (m <= maxMonth && m >= minMonth) {
            month = m;
            npcMonth.setValue(m);
            npcDay.setMaxValue(Utils.getDayRange(npcYear.getValue(), npcMonth.getValue()));
        }
    }

    public int getDay() {
        return day;
    }

    public void setDay(int d) {
        if (d <= maxDay && d >= minDay) {
            day = d;
            npcDay.setValue(d);
            npcDay.setMaxValue(Utils.getDayRange(npcYear.getValue(), npcMonth.getValue()));
        }
    }

    public String getSelectedDate() {
        return npcYear.getValue() + "/" + npcMonth.getValue() + "/" + npcDay.getValue();
    }

    public void setSelectedDate(int y, int m, int d) {
        setYear(y);
        setMonth(m);
        setDay(d);
    }
}
