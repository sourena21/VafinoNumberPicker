package hseify69.ir.numpad;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.text.DecimalFormat;

public class NumberPicker extends LinearLayout {

    ImageButton imbIncrease, imbDecrease;
    TextView txtCount;
    DecimalFormat formatter;
    long value = Consts.initValue;
    long minValue = Consts.initMinValue;
    long maxValue = Consts.initMaxValue;
    OnChangeValue onChangeValue;

    public NumberPicker(Context context) {
        super(context);
        initView(context);
    }

    public NumberPicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public NumberPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(final Context context) {
        View v = View.inflate(context, R.layout.number_picker_layout, null);

        imbDecrease = v.findViewById(R.id.imbDecrease);
        imbIncrease = v.findViewById(R.id.imbIncrease);
        txtCount = v.findViewById(R.id.txtCount);

        formatter = new DecimalFormat("#,###");

        txtCount.setText(formatter.format(value));

        imbDecrease.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));
                if (value > minValue) {
                    value--;
                    txtCount.setText(formatter.format(value));
                    if (onChangeValue != null) {
                        onChangeValue.onDecrease(value);
                    }
                }
            }
        });
        imbIncrease.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));
                if (value < maxValue) {
                    value++;
                    txtCount.setText(formatter.format(value));
                    if (onChangeValue != null) {
                        onChangeValue.onIncrease(value);
                    }
                }
            }
        });

        this.addView(v);
    }

    public long getValue() {
        return value;
    }

    public void setValue(long val) {
        if (val <= maxValue && val >= minValue) {
            value = val;
            txtCount.setText(formatter.format(value));
        }
    }

    public long getMinValue() {
        return minValue;
    }

    public void setMinValue(long min) {
        if (min < maxValue) {
            this.minValue = min;
        }
        if (min > value) {
            value = min;
            txtCount.setText(formatter.format(value));
        }
    }

    public long getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(long max) {
        if (max > minValue) {
            this.maxValue = max;
        }
        if (max < value) {
            value = max;
            txtCount.setText(formatter.format(value));
        }
    }

    public interface OnChangeValue {
        void onIncrease(long newValue);

        void onDecrease(long newValue);
    }

    public void setOnChangeValue(OnChangeValue listener) {
        onChangeValue = listener;
    }
}
