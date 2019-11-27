package hseify69.ir.numpad.keyboards;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import hseify69.ir.numpad.R;

public class DecimalKeypad extends LinearLayout implements View.OnClickListener {

    OnKeypadEvent onKeypadEvent;

    LinearLayout llNumBox;
    Button btnNumber0, btnNumber1, btnNumber2, btnNumber3, btnNumber4, btnNumber5, btnNumber6,
            btnNumber7, btnNumber8, btnNumber9, btnSubmit;
    ImageButton imbBackSpace;

    public DecimalKeypad(Context context) {
        super(context);
        initView(context);
    }

    public DecimalKeypad(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DecimalKeypad(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.removeAllViews();
        View v = View.inflate(context, R.layout.keypad_decimal, null);

        llNumBox = v.findViewById(R.id.KD_llNumpadBox);
        btnSubmit = v.findViewById(R.id.KD_btnSubmit);
        btnNumber0 = v.findViewById(R.id.KD_btnNumber0);
        btnNumber1 = v.findViewById(R.id.KD_btnNumber1);
        btnNumber2 = v.findViewById(R.id.KD_btnNumber2);
        btnNumber3 = v.findViewById(R.id.KD_btnNumber3);
        btnNumber4 = v.findViewById(R.id.KD_btnNumber4);
        btnNumber5 = v.findViewById(R.id.KD_btnNumber5);
        btnNumber6 = v.findViewById(R.id.KD_btnNumber6);
        btnNumber7 = v.findViewById(R.id.KD_btnNumber7);
        btnNumber8 = v.findViewById(R.id.KD_btnNumber8);
        btnNumber9 = v.findViewById(R.id.KD_btnNumber9);
        imbBackSpace = v.findViewById(R.id.KD_imbBackSpace);

        btnNumber0.setOnClickListener(this);
        btnNumber1.setOnClickListener(this);
        btnNumber2.setOnClickListener(this);
        btnNumber3.setOnClickListener(this);
        btnNumber4.setOnClickListener(this);
        btnNumber5.setOnClickListener(this);
        btnNumber6.setOnClickListener(this);
        btnNumber7.setOnClickListener(this);
        btnNumber8.setOnClickListener(this);
        btnNumber9.setOnClickListener(this);
        imbBackSpace.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        imbBackSpace.setOnTouchListener(new OnTouchListener() {

            long pressAmount = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != MotionEvent.ACTION_UP) {
                    if (pressAmount > 60) {
                        pressAmount = 0;
                        cleanAction();
                    } else {
                        pressAmount++;
                    }
                } else {
                    pressAmount = 0;
                }
                return false;
            }
        });

        this.addView(v);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.KD_btnNumber0) {
            addNumber('0');
        } else if (id == R.id.KD_btnNumber1) {
            addNumber('1');
        } else if (id == R.id.KD_btnNumber2) {
            addNumber('2');
        } else if (id == R.id.KD_btnNumber3) {
            addNumber('3');
        } else if (id == R.id.KD_btnNumber4) {
            addNumber('4');
        } else if (id == R.id.KD_btnNumber5) {
            addNumber('5');
        } else if (id == R.id.KD_btnNumber6) {
            addNumber('6');
        } else if (id == R.id.KD_btnNumber7) {
            addNumber('7');
        } else if (id == R.id.KD_btnNumber8) {
            addNumber('8');
        } else if (id == R.id.KD_btnNumber9) {
            addNumber('9');
        } else if (id == R.id.KD_imbBackSpace) {
            backSpaceLast();
        } else if (id == R.id.KD_btnSubmit) {
            submit();
        }
    }

    private void cleanAction() {
        if (onKeypadEvent != null) {
            onKeypadEvent.onClean();
        }
    }

    private void submit() {
        if (onKeypadEvent != null) {
            onKeypadEvent.onSubmit();
        }
    }

    private void backSpaceLast() {
        if (onKeypadEvent != null) {
            onKeypadEvent.onBackSpace();
        }
    }

    private void addNumber(char c) {
        if (onKeypadEvent != null) {
            onKeypadEvent.onEnterChar(c);
        }
    }

    public void setBackSpaceButtonBackground(int src) {
        imbBackSpace.setImageResource(src);
    }

    public void setOnKeypadEvent(OnKeypadEvent listener) {
        onKeypadEvent = listener;
    }

    public void setSubmitButtonVisibility(int visibility) {
        if (visibility == VISIBLE || visibility == INVISIBLE || visibility == GONE) {
            btnSubmit.setVisibility(visibility);
        }
    }
}
