package hseify69.ir.numpad.keyboards;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.core.widget.PopupWindowCompat;

import hseify69.ir.numpad.R;

public class PersianKeypad extends LinearLayout {

    boolean showCharPopup = true;
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
                addChar(v, 'آ');
            }
        });
        btnChar1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ا');
            }
        });
        btnChar2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ب');
            }
        });
        btnChar3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'پ');
            }
        });
        btnChar4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ت');
            }
        });
        btnChar5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ث');
            }
        });
        btnChar6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ج');
            }
        });
        btnChar7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'چ');
            }
        });
        btnChar8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ح');
            }
        });
        btnChar9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'خ');
            }
        });
        btnChar10.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'د');
            }
        });
        btnChar11.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ذ');
            }
        });
        btnChar12.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ر');
            }
        });
        btnChar13.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ز');
            }
        });
        btnChar14.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ژ');
            }
        });
        btnChar15.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'س');
            }
        });
        btnChar16.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ش');
            }
        });
        btnChar17.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ص');
            }
        });
        btnChar18.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ض');
            }
        });
        btnChar19.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ط');
            }
        });
        btnChar20.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ظ');
            }
        });
        btnChar21.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ع');
            }
        });
        btnChar22.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'غ');
            }
        });
        btnChar23.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ف');
            }
        });
        btnChar24.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ق');
            }
        });
        btnChar25.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ک');
            }
        });
        btnChar26.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'گ');
            }
        });
        btnChar27.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ل');
            }
        });
        btnChar28.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'م');
            }
        });
        btnChar29.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ن');
            }
        });
        btnChar30.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'و');
            }
        });
        btnChar31.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ه');
            }
        });
        btnChar32.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, 'ی');
            }
        });
        btnCharSpace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, ' ');
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

    private void addChar(View v, char c) {
        if (showCharPopup) {
            showCharPopup(v, c);
        }

        if (onKeypadEvent != null) {
            onKeypadEvent.onEnterChar(c);
        }
    }

    private void showCharPopup(final View v, char c) {
        final PopupWindow popupWindow = new PopupWindow(getContext());
        View popupView = View.inflate(getContext(), R.layout.popup_button_clicked, null);
        TextView txtChar = popupView.findViewById(R.id.PBC_txtChar);
        txtChar.setText(String.valueOf(c));
        popupWindow.setContentView(popupView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        popupWindow.showAsDropDown(v);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                int[] cordinate = new int[2];
                v.getLocationInWindow(cordinate);
                popupWindow.update((int) cordinate[0] + ((v.getWidth() - 96) / 2), (cordinate[1] - 72), 96, 96);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        popupWindow.dismiss();
                    }
                }, 500);
            }
        }, 10);
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

    public void setShowCharPopup(boolean flag) {
        showCharPopup = flag;
    }
}
