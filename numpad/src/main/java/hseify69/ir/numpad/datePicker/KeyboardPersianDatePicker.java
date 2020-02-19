package hseify69.ir.numpad.datePicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import hseify69.ir.numpad.R;
import hseify69.ir.numpad.helpers.Consts;
import hseify69.ir.numpad.helpers.Utils;
import hseify69.ir.numpad.keyboards.VafinoKeyboard;

public class KeyboardPersianDatePicker extends LinearLayout {

    int maxYear = 99;
    int minYear = 0;
    int maxMonth = Consts.maxMonth;
    int minMonth = Consts.minMonth;
    int maxDay = Consts.maxDay;
    int minDay = Consts.minDay;
    int day, month, year;

    View vwYear, vwMonth, vwDay;
    EditText edtYear, edtMonth, edtDay;
    VafinoKeyboard keyboard;

    public KeyboardPersianDatePicker(Context context) {
        super(context);
        init(context, null);
    }

    public KeyboardPersianDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public KeyboardPersianDatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.keyboard_persian_date_picker, this, true);

        vwYear = findViewById(R.id.KPDP_vwYear);
        vwMonth = findViewById(R.id.KPDP_vwMonth);
        vwDay = findViewById(R.id.KPDP_vwDay);
        edtYear = findViewById(R.id.KPDP_edtYear);
        edtMonth = findViewById(R.id.KPDP_edtMonth);
        edtDay = findViewById(R.id.KPDP_edtDay);
        keyboard = findViewById(R.id.KPDP_dateInput);

        edtYear.setRawInputType(InputType.TYPE_CLASS_TEXT);
        edtYear.setTextIsSelectable(true);
        edtMonth.setRawInputType(InputType.TYPE_CLASS_TEXT);
        edtMonth.setTextIsSelectable(true);
        edtDay.setRawInputType(InputType.TYPE_CLASS_TEXT);
        edtDay.setTextIsSelectable(true);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.KeyboardPersianDatePicker);

        setMaxYear(ta.getInteger(R.styleable.KeyboardPersianDatePicker_maxYear, maxYear));
        setMinYear(ta.getInteger(R.styleable.KeyboardPersianDatePicker_minYear, minYear));
        setYear(ta.getInteger(R.styleable.KeyboardPersianDatePicker_year, 0));
        setMonth(ta.getInteger(R.styleable.KeyboardPersianDatePicker_month, 0));
        setDay(ta.getInteger(R.styleable.KeyboardPersianDatePicker_day, 0));

        switchToInput(edtDay);

        edtYear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    year = Integer.parseInt(String.valueOf(s));
                    if (s.length() == 4) {
                        maxDay = Utils.getDayRange(year+1300, month);
                        setDayAmount();
                        if (year > maxYear) {
                            edtYear.setText(String.valueOf(maxYear));
                        } else if (year < minYear) {
                            edtYear.setText(String.valueOf(minYear));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edtMonth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    month = Integer.parseInt(String.valueOf(s));
                    if (s.length() == 2 || month > 1) {
                        maxDay = Utils.getDayRange(year+1300, month);
                        setDayAmount();
                        if (month > maxMonth) {
                            edtMonth.setText(String.valueOf(maxMonth));
                        } else if (month < minMonth) {
                            edtMonth.setText(String.valueOf(minMonth));
                        }
                        switchToInput(edtYear);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edtDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    day = Integer.parseInt(String.valueOf(s));
                    if (s.length() == 2 || day > 3) {
                        maxDay = Utils.getDayRange(year+1300, month);
                        setDayAmount();
                        switchToInput(edtMonth);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        vwYear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToInput(edtYear);
            }
        });
        vwMonth.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToInput(edtMonth);
            }
        });
        vwDay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToInput(edtDay);
            }
        });
        keyboard.setOnBackspace(new VafinoKeyboard.OnBackspace() {
            @Override
            public void onBackspace() {
                if (edtYear.isFocused() && edtYear.getText().length() == 0) {
                    switchToInput(edtMonth);
                } else if (edtMonth.isFocused() && edtMonth.getText().length() == 0) {
                    switchToInput(edtDay);
                }
            }
        });
    }

    private void setDayAmount() {
        if (edtDay.getText().length() > 0) {
            if (day > maxDay) {
                day = maxDay;
                edtDay.setText(String.valueOf(maxDay));
            } else if (day < minDay) {
                day = minDay;
                edtDay.setText(String.valueOf(minDay));
            }
        }
    }

    private void switchToInput(EditText editText) {
        if (editText.getText().toString().length() > 0) {
            editText.selectAll();
        }
        editText.requestFocus();
        keyboard.setInput(editText);
    }

    public int getMaxYear() {
        return maxYear;
    }

    public void setMaxYear(int max) {
        if (max > minYear && max < 100) {
            maxYear = max;
        }
    }

    public int getMinYear() {
        return minYear;
    }

    public void setMinYear(int min) {
        if (min < maxYear && min > 0) {
            this.minYear = min;
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int y) {
        if (y > 1300) {
            y -= 1300;
            if (y <= maxYear && y >= minYear) {
                year = y;
                edtYear.setText(String.valueOf(y));
                maxDay = Utils.getDayRange(year+1300, month);
                setDay(day);
            }
        } else if (y < 100 && y >= 0) {
            if (y <= maxYear && y >= minYear) {
                year = y;
                edtYear.setText(String.valueOf(y));
                maxDay = Utils.getDayRange(year+1300, month);
                setDay(day);
            }
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int m) {
        if (m <= maxMonth && m >= minMonth) {
            month = m;
            edtMonth.setText(String.valueOf(m));
            maxDay = Utils.getDayRange(year+1300, month);
            setDay(day);
        }
    }

    public int getDay() {
        return day;
    }

    public void setDay(int d) {
        if (d <= maxDay && d >= minDay) {
            day = d;
            edtDay.setText(String.valueOf(d));
            maxDay = Utils.getDayRange(year+1300, month);
        }
    }

    public String getSelectedDate() {
        try {
            int y = Integer.parseInt(edtYear.getText().toString());
            int m = Integer.parseInt(edtMonth.getText().toString());
            int d = Integer.parseInt(edtDay.getText().toString());
            if (y >= minYear && y <= maxYear &&
                    m >= minMonth && m <= maxMonth &&
                    d >= minDay && d <= Utils.getDayRange(y, m)) {
                return (y + 1300) + "/" + m + "/" + d;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setSelectedDate(int y, int m, int d) {
        setYear(y);
        setMonth(m);
        setDay(d);
    }
}
