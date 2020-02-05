package hseify69.ir.numpad.datePicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hseify69.ir.numpad.R;
import hseify69.ir.numpad.helpers.Consts;
import hseify69.ir.numpad.helpers.Utils;
import hseify69.ir.numpad.keyboards.VafinoKeyboard;
import saman.zamani.persiandate.PersianDate;

public class KeyboardPersianDatePicker extends RelativeLayout {

    int maxYear = Consts.maxYear;
    int minYear = Consts.minYear;
    int maxMonth = Consts.maxMonth;
    int minMonth = Consts.minMonth;
    int maxDay = Consts.maxDay;
    int minDay = Consts.minDay;
    int day, month, year;

    TextView txtYear, txtMonth, txtDay, txtSelected;
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
        View v = View.inflate(context, R.layout.keyboard_persian_date_picker, null);

        txtYear = v.findViewById(R.id.KPDP_edtYear);
        txtMonth = v.findViewById(R.id.KPDP_edtMonth);
        txtDay = v.findViewById(R.id.KPDP_edtDay);
        keyboard = v.findViewById(R.id.KPDP_dateInput);

        switchToInput(txtDay);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.KeyboardPersianDatePicker);

        setMaxYear(ta.getInteger(R.styleable.KeyboardPersianDatePicker_maxYear, Consts.maxYear));
        setMinYear(ta.getInteger(R.styleable.KeyboardPersianDatePicker_minYear, Consts.minYear));
        setYear(ta.getInteger(R.styleable.KeyboardPersianDatePicker_year, 0));
        setMonth(ta.getInteger(R.styleable.KeyboardPersianDatePicker_month, 0));
        setDay(ta.getInteger(R.styleable.KeyboardPersianDatePicker_day, 0));

        txtYear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    year = Integer.parseInt(String.valueOf(s));
                    if (s.length() == 4) {
                        maxDay = Utils.getDayRange(year, month);
                        setDayAmount();
                        if (year > maxYear) {
                            txtYear.setText(String.valueOf(maxYear));
                        } else if (year < minYear) {
                            txtYear.setText(String.valueOf(minYear));
                        }
                    } else if (s.length() == 0) {
                        switchToInput(txtMonth);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtMonth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    month = Integer.parseInt(String.valueOf(s));
                    if (s.length() == 2 || month > 1) {
                        maxDay = Utils.getDayRange(year, month);
                        setDayAmount();
                        if (month > maxMonth) {
                            txtMonth.setText(String.valueOf(maxMonth));
                        } else if (month < minMonth) {
                            txtMonth.setText(String.valueOf(minMonth));
                        }
                        switchToInput(txtYear);
                    } else if (s.length() == 0) {
                        switchToInput(txtDay);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    day = Integer.parseInt(String.valueOf(s));
                    if (s.length() == 2 || day > 3) {
                        maxDay = Utils.getDayRange(year, month);
                        setDayAmount();
                        switchToInput(txtMonth);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        txtYear.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    try {
                        year = Integer.parseInt(String.valueOf(txtYear.getText()));
                        if (txtYear.getText().length() == 4) {
                            maxDay = Utils.getDayRange(year, month);
                            setDayAmount();
                            if (year > maxYear) {
                                txtYear.setText(String.valueOf(maxYear));
                            } else if (year < minYear) {
                                txtYear.setText(String.valueOf(minYear));
                            }
                        } else if (txtYear.getText().length() == 0) {
                            switchToInput(txtMonth);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        txtYear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToInput(txtYear);
            }
        });
        txtMonth.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToInput(txtMonth);
            }
        });
        txtDay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToInput(txtDay);
            }
        });
        keyboard.setOnNumberEnter(new VafinoKeyboard.OnNumberEnter() {
            @Override
            public void onEnter(char c, String wholeEntered) {
                txtSelected.append(String.valueOf(c));
            }
        });
        keyboard.setOnBackspace(new VafinoKeyboard.OnBackspace() {
            @Override
            public void onBackspace() {
                if (txtSelected.getText().length() > 0) {
                    backspaceInput();
                } else {
                    if (txtSelected.equals(txtYear)) {
                        switchToInput(txtMonth);
                        if (!backspaceInput()) {
                            switchToInput(txtDay);
                            backspaceInput();
                        }
                    } else if (txtSelected.equals(txtMonth)) {
                        switchToInput(txtDay);
                        backspaceInput();
                    }
                }
            }
        });

        this.addView(v);
    }

    private void setDayAmount() {
        if (day > maxDay) {
            day = maxDay;
            txtDay.setText(String.valueOf(maxDay));
        } else if (day < minDay) {
            day = minDay;
            txtDay.setText(String.valueOf(minDay));
        }

    }

    private boolean backspaceInput() {
        if (txtSelected.getText().length() > 0) {
            txtSelected.setText(txtSelected.getText().subSequence(0, txtSelected.getText().length() - 1));
            return true;
        } else {
            return false;
        }
    }

    private void switchToInput(TextView textView) {
        txtSelected = textView;
        txtDay.setBackground(null);
        txtMonth.setBackground(null);
        txtYear.setBackground(null);
        txtSelected.setBackground(getResources().getDrawable(R.drawable.numpad_border_bottom_black));
    }

    public int getMaxYear() {
        return maxYear;
    }

    public void setMaxYear(int max) {
        if (max > minYear) {
            maxYear = max;
        }
    }

    public int getMinYear() {
        return minYear;
    }

    public void setMinYear(int min) {
        if (min < maxYear) {
            this.minYear = min;
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int y) {
        if (y > 0 && y <= maxYear && y >= minYear) {
            year = y;
            txtYear.setText(String.valueOf(y));
            maxDay = Utils.getDayRange(year, month);
            setDay(day);
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int m) {
        if (m <= maxMonth && m >= minMonth) {
            month = m;
            txtMonth.setText(String.valueOf(m));
            maxDay = Utils.getDayRange(year, month);
            setDay(day);
        }
    }

    public int getDay() {
        return day;
    }

    public void setDay(int d) {
        if (d <= maxDay && d >= minDay) {
            day = d;
            txtDay.setText(String.valueOf(d));
            maxDay = Utils.getDayRange(year, month);
        }
    }

    public String getSelectedDate() {
        if (txtYear.getText().toString().trim().length() > 0 &&
                txtMonth.getText().toString().trim().length() > 0 &&
                txtDay.getText().toString().trim().length() > 0) {
            return year + "/" + month + "/" + day;
        } else {
            return null;
        }
    }

    public void setSelectedDate(int y, int m, int d) {
        setYear(y);
        setMonth(m);
        setDay(d);
    }
}
