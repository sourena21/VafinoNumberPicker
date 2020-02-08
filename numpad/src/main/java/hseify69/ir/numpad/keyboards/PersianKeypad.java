package hseify69.ir.numpad.keyboards;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import hseify69.ir.numpad.R;

public class PersianKeypad extends LinearLayout {

    float allTextSize;
    String submitButtonText = "تایید";
    boolean showCharPopup = false;
    boolean showSubmitButton = false;
    boolean isShiftSelected = false;
    OnKeypadEvent onKeypadEvent;

    Button btnChar0, btnChar1, btnChar2, btnChar3, btnChar4, btnChar5, btnChar6, btnChar7, btnChar8,
            btnChar9, btnChar10, btnChar11, btnChar12, btnChar13, btnChar14, btnChar15, btnChar16,
            btnChar17, btnChar18, btnChar19, btnChar20, btnChar21, btnChar22, btnChar23, btnChar24,
            btnChar25, btnChar26, btnChar27, btnChar28, btnChar29, btnChar30, btnChar31, btnChar32,
            btnChar33, btnChar34;
    ImageButton imbBackSpace, imbEnter, imbCharSpace, imbShiftButton;
    private int textColor;
    private boolean showEnterButton;
    private boolean showPunctuations;

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
        btnChar33 = v.findViewById(R.id.KDP_btnChar33);
        btnChar34 = v.findViewById(R.id.KDP_btnChar34);
        imbCharSpace = v.findViewById(R.id.KDP_imbCharSpace);
        imbEnter = v.findViewById(R.id.KDP_imbCharEnter);
        imbBackSpace = v.findViewById(R.id.KDP_imbBackSpace);
        imbShiftButton = v.findViewById(R.id.KDP_imbShift);

        btnChar0.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '&');
                    unselectShift();
                } else {
                    addChar(v, 'آ');
                }
            }
        });
        btnChar1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '$');
                    unselectShift();
                } else {
                    addChar(v, 'ا');
                }
            }
        });
        btnChar2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '@');
                    unselectShift();
                } else {
                    addChar(v, 'ب');
                }
            }
        });
        btnChar3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '/');
                    unselectShift();
                } else {
                    addChar(v, 'پ');
                }
            }
        });
        btnChar4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '*');
                    unselectShift();
                } else {
                    addChar(v, 'ت');
                }
            }
        });
        btnChar5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '2');
                    unselectShift();
                } else {
                    addChar(v, 'ث');
                }
            }
        });
        btnChar6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '0');
                    unselectShift();
                } else {
                    addChar(v, 'ج');
                }
            }
        });
        btnChar7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '؟');
                    unselectShift();
                } else {
                    addChar(v, 'چ');
                }
            }
        });
        btnChar8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '9');
                    unselectShift();
                } else {
                    addChar(v, 'ح');
                }
            }
        });
        btnChar9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '8');
                    unselectShift();
                } else {
                    addChar(v, 'خ');
                }
            }
        });
        btnChar10.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '>');
                    unselectShift();
                } else {
                    addChar(v, 'د');
                }
            }
        });
        btnChar11.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '<');
                    unselectShift();
                } else {
                    addChar(v, 'ذ');
                }
            }
        });
        btnChar12.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '}');
                    unselectShift();
                } else {
                    addChar(v, 'ر');
                }
            }
        });
        btnChar13.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '{');
                    unselectShift();
                } else {
                    addChar(v, 'ز');
                }
            }
        });
        btnChar14.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, ']');
                    unselectShift();
                } else {
                    addChar(v, 'ژ');
                }
            }
        });
        btnChar15.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShiftSelected) {
                    addChar(v, 'س');
                }
            }
        });
        btnChar16.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShiftSelected) {
                    addChar(v, 'ش');
                }
            }
        });
        btnChar17.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '1');
                    unselectShift();
                } else {
                    addChar(v, 'ص');
                }
            }
        });
        btnChar18.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShiftSelected) {
                    addChar(v, 'ض');
                }
            }
        });
        btnChar19.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '[');
                    unselectShift();
                } else {
                    addChar(v, 'ط');
                }
            }
        });
        btnChar20.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShiftSelected) {
                    addChar(v, 'ظ');
                }
            }
        });
        btnChar21.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '6');
                    unselectShift();
                } else {
                    addChar(v, 'ع');
                }
            }
        });
        btnChar22.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '5');
                    unselectShift();
                } else {
                    addChar(v, 'غ');
                }
            }
        });
        btnChar23.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '4');
                    unselectShift();
                } else {
                    addChar(v, 'ف');
                }
            }
        });
        btnChar24.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '3');
                    unselectShift();
                } else {
                    addChar(v, 'ق');
                }
            }
        });
        btnChar25.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '(');
                    unselectShift();
                } else {
                    addChar(v, 'ک');
                }
            }
        });
        btnChar26.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, ')');
                    unselectShift();
                } else {
                    addChar(v, 'گ');
                }
            }
        });
        btnChar27.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '#');
                    unselectShift();
                } else {
                    addChar(v, 'ل');
                }
            }
        });
        btnChar28.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '+');
                    unselectShift();
                } else {
                    addChar(v, 'م');
                }
            }
        });
        btnChar29.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '-');
                    unselectShift();
                } else {
                    addChar(v, 'ن');
                }
            }
        });
        btnChar30.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShiftSelected) {
                    addChar(v, 'و');
                }
            }
        });
        btnChar31.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '7');
                    unselectShift();
                } else {
                    addChar(v, 'ه');
                }
            }
        });
        btnChar32.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    addChar(v, '!');
                    unselectShift();
                } else {
                    addChar(v, 'ی');
                }
            }
        });
        btnChar33.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, '،');
            }
        });
        btnChar34.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, '.');
            }
        });
        imbCharSpace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, ' ');
            }
        });
        imbEnter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addChar(v, '\n');
            }
        });
        imbBackSpace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                backSpaceLast();
            }
        });
        imbShiftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShiftSelected) {
                    unselectShift();
                } else {
                    selectShift();
                }
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

        setTextSizes(allTextSize);
        setTextColors(textColor);

        this.addView(v);
    }

    private void selectShift() {
        isShiftSelected = true;
        imbShiftButton.setImageResource(R.drawable.ic_shift_button_selected);
        btnChar0.setText("&");
        btnChar1.setText("$");
        btnChar2.setText("@");
        btnChar3.setText("/");
        btnChar4.setText("*");
        btnChar5.setText("2");
        btnChar6.setText("0");
        btnChar7.setText("؟");
        btnChar8.setText("9");
        btnChar9.setText("8");
        btnChar10.setText(">");
        btnChar11.setText("<");
        btnChar12.setText("}");
        btnChar13.setText("{");
        btnChar14.setText("]");
        btnChar15.setText(" ");
        btnChar16.setText(" ");
        btnChar17.setText("1");
        btnChar18.setText(" ");
        btnChar19.setText("[");
        btnChar20.setText(" ");
        btnChar21.setText("6");
        btnChar22.setText("5");
        btnChar23.setText("4");
        btnChar24.setText("3");
        btnChar25.setText("(");
        btnChar26.setText(")");
        btnChar27.setText("#");
        btnChar28.setText("+");
        btnChar29.setText("-");
        btnChar30.setText(" ");
        btnChar31.setText("7");
        btnChar32.setText("!");
    }

    private void unselectShift() {
        isShiftSelected = false;
        imbShiftButton.setImageResource(R.drawable.ic_shift_button_unselected);
        btnChar0.setText("آ");
        btnChar1.setText("ا");
        btnChar2.setText("ب");
        btnChar3.setText("پ");
        btnChar4.setText("ت");
        btnChar5.setText("ث");
        btnChar6.setText("ج");
        btnChar7.setText("چ");
        btnChar8.setText("ح");
        btnChar9.setText("خ");
        btnChar10.setText("د");
        btnChar11.setText("ذ");
        btnChar12.setText("ر");
        btnChar13.setText("ز");
        btnChar14.setText("ژ");
        btnChar15.setText("س");
        btnChar16.setText("ش");
        btnChar17.setText("ص");
        btnChar18.setText("ض");
        btnChar19.setText("ط");
        btnChar20.setText("ظ");
        btnChar21.setText("ع");
        btnChar22.setText("غ");
        btnChar23.setText("ف");
        btnChar24.setText("ق");
        btnChar25.setText("ک");
        btnChar26.setText("گ");
        btnChar27.setText("ل");
        btnChar28.setText("م");
        btnChar29.setText("ن");
        btnChar30.setText("و");
        btnChar31.setText("ه");
        btnChar32.setText("ی");
    }

    private void setTextSizes(float allTextSize) {
        btnChar0.setTextSize(allTextSize);
        btnChar1.setTextSize(allTextSize);
        btnChar2.setTextSize(allTextSize);
        btnChar3.setTextSize(allTextSize);
        btnChar4.setTextSize(allTextSize);
        btnChar5.setTextSize(allTextSize);
        btnChar6.setTextSize(allTextSize);
        btnChar7.setTextSize(allTextSize);
        btnChar8.setTextSize(allTextSize);
        btnChar9.setTextSize(allTextSize);
        btnChar10.setTextSize(allTextSize);
        btnChar11.setTextSize(allTextSize);
        btnChar12.setTextSize(allTextSize);
        btnChar13.setTextSize(allTextSize);
        btnChar14.setTextSize(allTextSize);
        btnChar15.setTextSize(allTextSize);
        btnChar16.setTextSize(allTextSize);
        btnChar17.setTextSize(allTextSize);
        btnChar18.setTextSize(allTextSize);
        btnChar19.setTextSize(allTextSize);
        btnChar20.setTextSize(allTextSize);
        btnChar21.setTextSize(allTextSize);
        btnChar22.setTextSize(allTextSize);
        btnChar23.setTextSize(allTextSize);
        btnChar24.setTextSize(allTextSize);
        btnChar25.setTextSize(allTextSize);
        btnChar26.setTextSize(allTextSize);
        btnChar27.setTextSize(allTextSize);
        btnChar28.setTextSize(allTextSize);
        btnChar29.setTextSize(allTextSize);
        btnChar30.setTextSize(allTextSize);
        btnChar31.setTextSize(allTextSize);
        btnChar32.setTextSize(allTextSize);
        btnChar33.setTextSize(allTextSize);
        btnChar34.setTextSize(allTextSize);
    }

    private void setTextColors(int textColor) {
        btnChar0.setTextColor(textColor);
        btnChar1.setTextColor(textColor);
        btnChar2.setTextColor(textColor);
        btnChar3.setTextColor(textColor);
        btnChar4.setTextColor(textColor);
        btnChar5.setTextColor(textColor);
        btnChar6.setTextColor(textColor);
        btnChar7.setTextColor(textColor);
        btnChar8.setTextColor(textColor);
        btnChar9.setTextColor(textColor);
        btnChar10.setTextColor(textColor);
        btnChar11.setTextColor(textColor);
        btnChar12.setTextColor(textColor);
        btnChar13.setTextColor(textColor);
        btnChar14.setTextColor(textColor);
        btnChar15.setTextColor(textColor);
        btnChar16.setTextColor(textColor);
        btnChar17.setTextColor(textColor);
        btnChar18.setTextColor(textColor);
        btnChar19.setTextColor(textColor);
        btnChar20.setTextColor(textColor);
        btnChar21.setTextColor(textColor);
        btnChar22.setTextColor(textColor);
        btnChar23.setTextColor(textColor);
        btnChar24.setTextColor(textColor);
        btnChar25.setTextColor(textColor);
        btnChar26.setTextColor(textColor);
        btnChar27.setTextColor(textColor);
        btnChar28.setTextColor(textColor);
        btnChar29.setTextColor(textColor);
        btnChar30.setTextColor(textColor);
        btnChar31.setTextColor(textColor);
        btnChar32.setTextColor(textColor);
        btnChar33.setTextColor(textColor);
        btnChar34.setTextColor(textColor);
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
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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

    public boolean isShowSubmitButton() {
        return showSubmitButton;
    }

    public void setShowSubmitButton(boolean flag) {
        this.showSubmitButton = flag;
    }

    public void setSubmitButtonText(String text) {
        submitButtonText = text;
    }

    public String getSubmitButtonText() {
        return submitButtonText;
    }

    public void setAllTextSize(float allTextSize) {
        this.allTextSize = allTextSize;
        setTextSizes(allTextSize);
    }

    public float getAllTextSize() {
        return allTextSize;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        setTextColors(textColor);
    }

    public int getTextColor() {
        return textColor;
    }

    public void setShowEnterButton(boolean showEnterButton) {
        this.showEnterButton = showEnterButton;
        if (showEnterButton) {
            imbEnter.setVisibility(VISIBLE);
        } else {
            imbEnter.setVisibility(GONE);
        }
    }

    public boolean getShowEnterButton() {
        return showEnterButton;
    }

    public void setShowPunctuations(boolean showPunctuations) {
        this.showPunctuations = showPunctuations;
        if(showPunctuations)
        {
            btnChar33.setVisibility(VISIBLE);
            btnChar34.setVisibility(VISIBLE);
            imbShiftButton.setVisibility(VISIBLE);
        }else {
            btnChar33.setVisibility(GONE);
            btnChar34.setVisibility(GONE);
            imbShiftButton.setVisibility(GONE);
        }
    }

    public boolean getShowPunctuations() {
        return showPunctuations;
    }
}
