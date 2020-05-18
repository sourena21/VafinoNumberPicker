package hseify69.ir.numpad.numberPicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.DecimalFormat;

import hseify69.ir.numpad.R;
import hseify69.ir.numpad.helpers.Consts;

public class NumberPicker extends LinearLayout {

    boolean editable = true;
    boolean showLimitMessage = false;
    DecimalFormat formatter;
    int value = Consts.initValue;
    int minValue = Consts.initMinValue;
    int maxValue = Consts.initMaxValue;
    OnChangeValue onChangeValue;

    ImageButton imbIncrease, imbDecrease;
    TextView txtCount;

    public NumberPicker(Context context) {
        super(context);
        initView(context, null);
    }

    public NumberPicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public NumberPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(final Context context, AttributeSet attrs) {
        View v = View.inflate(context, R.layout.number_picker_layout, null);

        imbDecrease = v.findViewById(R.id.imbDecrease);
        imbIncrease = v.findViewById(R.id.imbIncrease);
        txtCount = v.findViewById(R.id.txtCount);

        formatter = new DecimalFormat("#,###");

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.NumberPicker);

        editable = ta.getBoolean(R.styleable.NumberPicker_editable, true);
        showLimitMessage = ta.getBoolean(R.styleable.NumberPicker_showLimitMessage, false);
        setValue(ta.getInteger(R.styleable.NumberPicker_value, Consts.initValue));
        setMaxValue(ta.getInteger(R.styleable.NumberPicker_maxValue, Consts.initMaxValue));
        setMinValue(ta.getInteger(R.styleable.NumberPicker_minValue, Consts.initMinValue));

        txtCount.setText(formatter.format(value));

        imbDecrease.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editable) {
                    view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));
                    if (value > minValue) {
                        value--;
                        txtCount.setText(formatter.format(value));
                        if (onChangeValue != null) {
                            onChangeValue.onDecrease(value);
                        }
                    }
                }
            }
        });
        imbIncrease.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editable) {
                    view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));
                    if (value < maxValue) {
                        value++;
                        txtCount.setText(formatter.format(value));
                        if (onChangeValue != null) {
                            onChangeValue.onIncrease(value);
                        }
                    } else {
                        Toast.makeText(context, "موجودی کافی نیست!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        this.addView(v);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int val) {
        if (val <= maxValue && val >= minValue) {
            value = val;
            txtCount.setText(formatter.format(value));
        }
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int min) {
        if (min < maxValue) {
            this.minValue = min;
        }
        if (min > value) {
            value = min;
            txtCount.setText(formatter.format(value));
        }
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int max) {
        if (max > minValue) {
            this.maxValue = max;
        }
        if (max < value) {
            value = max;
            txtCount.setText(formatter.format(value));
        }
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public interface OnChangeValue {
        void onIncrease(int newValue);

        void onDecrease(int newValue);
    }

    public void setOnChangeValue(OnChangeValue listener) {
        onChangeValue = listener;
    }
}
