package hseify69.ir.numpad.keyboards;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import hseify69.ir.numpad.R;

public class PersianKeypad extends LinearLayout {

    OnKeypadEvent onKeypadEvent;

    Button btnChar0, btnChar1, btnChar2, btnChar3, btnChar4, btnChar5, btnChar6, btnChar7, btnChar8,
            btnChar9, btnChar10, btnChar11, btnChar12, btnChar13, btnChar14, btnChar15, btnChar16,
            btnChar17, btnChar18, btnChar19, btnChar20, btnChar21, btnChar22, btnChar23, btnChar24,
            btnChar25, btnChar26, btnChar27, btnChar28, btnChar29, btnChar30, btnChar31, btnChar32,
            btnCharSpace;
    ImageButton imbBackSpace;

    public PersianKeypad(Context context) {
        super(context);
        initView(context);
    }

    public PersianKeypad(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PersianKeypad(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.removeAllViews();
        View v = View.inflate(context, R.layout.keypad_persian, null);

        btnChar0 = v.findViewById(R.id.KDP_btnChar0);
        btnChar1 = v.findViewById(R.id.KDP_btnChar1);
        btnChar2 = v.findViewById(R.id.KDP_btnChar2);
        btnChar3 = v.findViewById(R.id.KDP_btnChar3);
        btnChar4 = v.findViewById(R.id.KDP_btnChar4);
        btnChar5 = v.findViewById(R.id.KDP_btnChar5);
        btnChar6 = v.findViewById(R.id.KDP_btnChar6);
        btnChar7 = v.findViewById(R.id.KDP_btnChar7);
        btnChar8 = v.findViewById(R.id.KDP_btnChar8);
        btnChar9 = v.findViewById(R.id.KDP_btnChar9);
        btnChar10 = v.findViewById(R.id.KDP_btnChar10);
        btnChar11 = v.findViewById(R.id.KDP_btnChar11);
        btnChar12 = v.findViewById(R.id.KDP_btnChar12);
        btnChar13 = v.findViewById(R.id.KDP_btnChar13);
        btnChar14 = v.findViewById(R.id.KDP_btnChar14);
        btnChar15 = v.findViewById(R.id.KDP_btnChar15);
        btnChar16 = v.findViewById(R.id.KDP_btnChar16);
        btnChar17 = v.findViewById(R.id.KDP_btnChar17);
        btnChar18 = v.findViewById(R.id.KDP_btnChar18);
        btnChar19 = v.findViewById(R.id.KDP_btnChar19);
        btnChar20 = v.findViewById(R.id.KDP_btnChar20);
        btnChar21 = v.findViewById(R.id.KDP_btnChar21);
        btnChar22 = v.findViewById(R.id.KDP_btnChar22);
        btnChar23 = v.findViewById(R.id.KDP_btnChar23);
        btnChar24 = v.findViewById(R.id.KDP_btnChar24);
        btnChar25 = v.findViewById(R.id.KDP_btnChar25);
        btnChar26 = v.findViewById(R.id.KDP_btnChar26);
        btnChar27 = v.findViewById(R.id.KDP_btnChar27);
        btnChar28 = v.findViewById(R.id.KDP_btnChar28);
        btnChar29 = v.findViewById(R.id.KDP_btnChar29);
        btnChar30 = v.findViewById(R.id.KDP_btnChar30);
        btnChar31 = v.findViewById(R.id.KDP_btnChar31);
        btnChar32 = v.findViewById(R.id.KDP_btnChar32);
        btnCharSpace = v.findViewById(R.id.KDP_btnCharSpace);
        imbBackSpace = v.findViewById(R.id.KDP_imbBackSpace);

        btnChar0.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('آ');
            }
        });
        btnChar1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ا');
            }
        });
        btnChar2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ب');
            }
        });
        btnChar3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('پ');
            }
        });
        btnChar4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ت');
            }
        });
        btnChar5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ث');
            }
        });
        btnChar6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ج');
            }
        });
        btnChar7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('چ');
            }
        });
        btnChar8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ح');
            }
        });
        btnChar9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('خ');
            }
        });
        btnChar10.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('د');
            }
        });
        btnChar11.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ذ');
            }
        });
        btnChar12.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ر');
            }
        });
        btnChar13.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ز');
            }
        });
        btnChar14.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ژ');
            }
        });
        btnChar15.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('س');
            }
        });
        btnChar16.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ش');
            }
        });
        btnChar17.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ص');
            }
        });
        btnChar18.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ض');
            }
        });
        btnChar19.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ط');
            }
        });
        btnChar20.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ظ');
            }
        });
        btnChar21.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ع');
            }
        });
        btnChar22.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('غ');
            }
        });
        btnChar23.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ف');
            }
        });
        btnChar24.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ق');
            }
        });
        btnChar25.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ک');
            }
        });
        btnChar26.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('گ');
            }
        });
        btnChar27.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ل');
            }
        });
        btnChar28.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('م');
            }
        });
        btnChar29.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ن');
            }
        });
        btnChar30.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('و');
            }
        });
        btnChar31.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ه');
            }
        });
        btnChar32.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar('ی');
            }
        });
        btnCharSpace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(' ');
            }
        });
        imbBackSpace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                backSpaceLast();
            }
        });

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

    private void addChar(char c) {
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
        }
    }
}
