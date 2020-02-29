package hseify69.ir.numpad.keyboards;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import hseify69.ir.numpad.R;
import hseify69.ir.numpad.helpers.Consts.Language;

public class AlphabetKeypad extends LinearLayout {

    float allTextSize;
    String submitButtonText = "تایید";
    boolean showCharPopup = false;
    boolean showSubmitButton = false;
    boolean isPunctuationSelected = false;
    boolean isShifSelected = false;
    boolean isCapsLockSelected = false;
    OnKeypadEvent onKeypadEvent;
    Language language = Language.PERSIAN;
    char[][] persianAlphabet = {{'&', '$', '@', '/', '*', '2', '0', '؟', '9', '8', '>', '<', '}', '{',
            ']', 0, 0, '1', 0, '[', 0, '6', '5', '4', '3', '(', ')', '#', '+', '-', 0, '7', '!', '،', '.'},
            {'آ', 'ا', 'ب', 'پ', 'ت', 'ث', 'ج', 'چ', 'ح', 'خ', 'د', 'ذ', 'ر', 'ز', 'ژ', 'س', 'ش', 'ص',
                    'ض', 'ط', 'ظ', 'ع', 'غ', 'ف', 'ق', 'ک', 'گ', 'ل', 'م', 'ن', 'و', 'ه', 'ی', '،', '.'}};
    char[][] englishAlphabet = {{'&', '$', '@', '/', '*', '2', '0', '؟', '9', '8', '<', '>', '}', '{',
            '[', 0, 0, '1', 0, ']', 0, '6', '5', '4', '3', ')', '(', '#', '+', '-', 0, '7', '!', ',', '.'},
            {0, 'h', 'f', 'm', 'j', 'e', 0, 0, 'p', 'o', 'n', 'b', 'v', 'c', 0, 's', 'a', 'w', 'q',
                    'x', 'z', 'u', 'y', 't', 'r', 0, 0, 'g', 'l', 'k', 0, 'i', 'd', ',', '.'},
            {0, 'H', 'F', 'M', 'J', 'E', 0, 0, 'P', 'O', 'N', 'B', 'V', 'C', 0, 'S', 'A', 'W', 'Q',
                    'X', 'Z', 'U', 'Y', 'T', 'R', 0, 0, 'G', 'L', 'K', 0, 'I', 'D', ',', '.'}};

    Button btnChar0, btnChar1, btnChar2, btnChar3, btnChar4, btnChar5, btnChar6, btnChar7, btnChar8,
            btnChar9, btnChar10, btnChar11, btnChar12, btnChar13, btnChar14, btnChar15, btnChar16,
            btnChar17, btnChar18, btnChar19, btnChar20, btnChar21, btnChar22, btnChar23, btnChar24,
            btnChar25, btnChar26, btnChar27, btnChar28, btnChar29, btnChar30, btnChar31, btnChar32,
            btnChar33, btnChar34, btnPunctuations;
    ImageButton imbBackSpace, imbEnter, imbCharSpace, imbLanguage, imbCapslock, imbShift;
    int textColor;
    boolean showEnterButton;
    boolean showPunctuations;
    private boolean showLanguageSelectionButton;

    public AlphabetKeypad(Context context) {
        super(context);
        initView(context);
    }

    public AlphabetKeypad(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AlphabetKeypad(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.keypad_persian, this, true);

        btnChar0 = findViewById(R.id.KDP_btnChar0);
        btnChar1 = findViewById(R.id.KDP_btnChar1);
        btnChar2 = findViewById(R.id.KDP_btnChar2);
        btnChar3 = findViewById(R.id.KDP_btnChar3);
        btnChar4 = findViewById(R.id.KDP_btnChar4);
        btnChar5 = findViewById(R.id.KDP_btnChar5);
        btnChar6 = findViewById(R.id.KDP_btnChar6);
        btnChar7 = findViewById(R.id.KDP_btnChar7);
        btnChar8 = findViewById(R.id.KDP_btnChar8);
        btnChar9 = findViewById(R.id.KDP_btnChar9);
        btnChar10 = findViewById(R.id.KDP_btnChar10);
        btnChar11 = findViewById(R.id.KDP_btnChar11);
        btnChar12 = findViewById(R.id.KDP_btnChar12);
        btnChar13 = findViewById(R.id.KDP_btnChar13);
        btnChar14 = findViewById(R.id.KDP_btnChar14);
        btnChar15 = findViewById(R.id.KDP_btnChar15);
        btnChar16 = findViewById(R.id.KDP_btnChar16);
        btnChar17 = findViewById(R.id.KDP_btnChar17);
        btnChar18 = findViewById(R.id.KDP_btnChar18);
        btnChar19 = findViewById(R.id.KDP_btnChar19);
        btnChar20 = findViewById(R.id.KDP_btnChar20);
        btnChar21 = findViewById(R.id.KDP_btnChar21);
        btnChar22 = findViewById(R.id.KDP_btnChar22);
        btnChar23 = findViewById(R.id.KDP_btnChar23);
        btnChar24 = findViewById(R.id.KDP_btnChar24);
        btnChar25 = findViewById(R.id.KDP_btnChar25);
        btnChar26 = findViewById(R.id.KDP_btnChar26);
        btnChar27 = findViewById(R.id.KDP_btnChar27);
        btnChar28 = findViewById(R.id.KDP_btnChar28);
        btnChar29 = findViewById(R.id.KDP_btnChar29);
        btnChar30 = findViewById(R.id.KDP_btnChar30);
        btnChar31 = findViewById(R.id.KDP_btnChar31);
        btnChar32 = findViewById(R.id.KDP_btnChar32);
        btnChar33 = findViewById(R.id.KDP_btnChar33);
        btnChar34 = findViewById(R.id.KDP_btnChar34);
        imbCharSpace = findViewById(R.id.KDP_imbCharSpace);
        imbEnter = findViewById(R.id.KDP_imbCharEnter);
        imbCapslock = findViewById(R.id.KDP_imbCapsLock);
        imbShift = findViewById(R.id.KDP_imbShift);
        imbLanguage = findViewById(R.id.KDP_imbLanguage);
        imbBackSpace = findViewById(R.id.KDP_imbBackSpace);
        btnPunctuations = findViewById(R.id.KDP_btnPunctuations);

        btnChar0.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 0);
            }
        });
        btnChar1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 1);
            }
        });
        btnChar2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 2);
            }
        });
        btnChar3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 3);
            }
        });
        btnChar4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 4);
            }
        });
        btnChar5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 5);
            }
        });
        btnChar6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 6);
            }
        });
        btnChar7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 7);
            }
        });
        btnChar8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 8);
            }
        });
        btnChar9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 9);
            }
        });
        btnChar10.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 10);
            }
        });
        btnChar11.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 11);
            }
        });
        btnChar12.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 12);
            }
        });
        btnChar13.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 13);
            }
        });
        btnChar14.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 14);
            }
        });
        btnChar15.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 15);
            }
        });
        btnChar16.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 16);
            }
        });
        btnChar17.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 17);
            }
        });
        btnChar18.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 18);
            }
        });
        btnChar19.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 19);
            }
        });
        btnChar20.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 20);
            }
        });
        btnChar21.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 21);
            }
        });
        btnChar22.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 22);
            }
        });
        btnChar23.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 23);
            }
        });
        btnChar24.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 24);
            }
        });
        btnChar25.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 25);
            }
        });
        btnChar26.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 26);
            }
        });
        btnChar27.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 27);
            }
        });
        btnChar28.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 28);
            }
        });
        btnChar29.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 29);
            }
        });
        btnChar30.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 30);
            }
        });
        btnChar31.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 31);
            }
        });
        btnChar32.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 32);
            }
        });
        btnChar33.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 33);
            }
        });
        btnChar34.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(v, 34);
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
        imbCapslock.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isShifSelected = false;
                if (isCapsLockSelected) {
                    setEnglishAlphabet();
                    imbCapslock.setBackgroundResource(R.color.transparent);
                } else {
                    imbCapslock.setBackgroundResource(R.drawable.numpad_button_background_transparent_pressed);
                    imbShift.setImageResource(R.drawable.ic_shift_button_unselected);
                    setEnglishCapsLock();
                }
            }
        });
        imbShift.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShifSelected) {
                    isShifSelected = false;
                    setEnglishAlphabet();
                    imbShift.setImageResource(R.drawable.ic_shift_button_unselected);
                } else {
                    isShifSelected = true;
                    setEnglishCapsLock();
                    imbCapslock.setBackgroundResource(R.color.transparent);
                    imbShift.setImageResource(R.drawable.ic_shift_button_selected);
                }
            }
        });
        imbLanguage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imbCapslock.setBackgroundResource(R.color.transparent);
                imbShift.setImageResource(R.drawable.ic_shift_button_unselected);
                isPunctuationSelected = false;
                if (language == Language.PERSIAN) {
                    language = Language.ENGLISH;
                    setEnglishAlphabet();
                } else if (language == Language.ENGLISH) {
                    language = Language.PERSIAN;
                    setPersianAlphabet();
                }
            }
        });
        btnPunctuations.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imbCapslock.setBackgroundResource(R.color.transparent);
                imbShift.setImageResource(R.drawable.ic_shift_button_unselected);
                if (isPunctuationSelected) {
                    changeLanguage(language);
                } else {
                    if (language == Language.PERSIAN) {
                        setPersianPunctuation();
                    } else if (language == Language.ENGLISH) {
                        setEnglishPunctuation();
                    }
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
    }

    private void clickButton(View v, int i) {
        if (language == Language.PERSIAN) {
            if (isPunctuationSelected) {
                addChar(v, persianAlphabet[0][i]);
            } else {
                addChar(v, persianAlphabet[1][i]);
            }
        } else if (language == Language.ENGLISH) {
            if (isPunctuationSelected) {
                addChar(v, englishAlphabet[0][i]);
            } else if (isShifSelected) {
                addChar(v, englishAlphabet[2][i]);
                isShifSelected = false;
                setEnglishAlphabet();
            } else if (isCapsLockSelected) {
                addChar(v, englishAlphabet[2][i]);
            } else {
                addChar(v, englishAlphabet[1][i]);
            }
        }
    }

    private void setPersianPunctuation() {
        imbCapslock.setVisibility(GONE);
        imbShift.setVisibility(GONE);
        setVisibilityButtons();
        isPunctuationSelected = true;
        btnPunctuations.setText("ابپ");
        btnChar0.setText(String.valueOf(persianAlphabet[0][0]));
        btnChar1.setText(String.valueOf(persianAlphabet[0][1]));
        btnChar2.setText(String.valueOf(persianAlphabet[0][2]));
        btnChar3.setText(String.valueOf(persianAlphabet[0][3]));
        btnChar4.setText(String.valueOf(persianAlphabet[0][4]));
        btnChar5.setText(String.valueOf(persianAlphabet[0][5]));
        btnChar6.setText(String.valueOf(persianAlphabet[0][6]));
        btnChar7.setText(String.valueOf(persianAlphabet[0][7]));
        btnChar8.setText(String.valueOf(persianAlphabet[0][8]));
        btnChar9.setText(String.valueOf(persianAlphabet[0][9]));
        btnChar10.setText(String.valueOf(persianAlphabet[0][10]));
        btnChar11.setText(String.valueOf(persianAlphabet[0][11]));
        btnChar12.setText(String.valueOf(persianAlphabet[0][12]));
        btnChar13.setText(String.valueOf(persianAlphabet[0][13]));
        btnChar14.setText(String.valueOf(persianAlphabet[0][14]));
//        btnChar15.setText(String.valueOf(persianAlphabet[0][15]));
        btnChar15.setVisibility(GONE);
//        btnChar16.setText(String.valueOf(persianAlphabet[0][16]));
        btnChar16.setVisibility(GONE);
        btnChar17.setText(String.valueOf(persianAlphabet[0][17]));
//        btnChar18.setText(String.valueOf(persianAlphabet[0][18]));
        btnChar18.setVisibility(GONE);
        btnChar19.setText(String.valueOf(persianAlphabet[0][19]));
//        btnChar20.setText(String.valueOf(persianAlphabet[0][20]));
        btnChar20.setVisibility(GONE);
        btnChar21.setText(String.valueOf(persianAlphabet[0][21]));
        btnChar22.setText(String.valueOf(persianAlphabet[0][22]));
        btnChar23.setText(String.valueOf(persianAlphabet[0][23]));
        btnChar24.setText(String.valueOf(persianAlphabet[0][24]));
        btnChar25.setText(String.valueOf(persianAlphabet[0][25]));
        btnChar26.setText(String.valueOf(persianAlphabet[0][26]));
        btnChar27.setText(String.valueOf(persianAlphabet[0][27]));
        btnChar28.setText(String.valueOf(persianAlphabet[0][28]));
        btnChar29.setText(String.valueOf(persianAlphabet[0][29]));
//        btnChar30.setText(String.valueOf(persianAlphabet[0][30]));
        btnChar30.setVisibility(GONE);
        btnChar31.setText(String.valueOf(persianAlphabet[0][31]));
        btnChar32.setText(String.valueOf(persianAlphabet[0][32]));
        btnChar33.setText(String.valueOf(persianAlphabet[0][33]));
        btnChar34.setText(String.valueOf(persianAlphabet[0][34]));
    }

    private void setPersianAlphabet() {
        imbCapslock.setVisibility(GONE);
        imbShift.setVisibility(GONE);
        setVisibilityButtons();
        isPunctuationSelected = false;
        btnPunctuations.setText("123؟");
        btnChar0.setText(String.valueOf(persianAlphabet[1][0]));
        btnChar1.setText(String.valueOf(persianAlphabet[1][1]));
        btnChar2.setText(String.valueOf(persianAlphabet[1][2]));
        btnChar3.setText(String.valueOf(persianAlphabet[1][3]));
        btnChar4.setText(String.valueOf(persianAlphabet[1][4]));
        btnChar5.setText(String.valueOf(persianAlphabet[1][5]));
        btnChar6.setText(String.valueOf(persianAlphabet[1][6]));
        btnChar7.setText(String.valueOf(persianAlphabet[1][7]));
        btnChar8.setText(String.valueOf(persianAlphabet[1][8]));
        btnChar9.setText(String.valueOf(persianAlphabet[1][9]));
        btnChar10.setText(String.valueOf(persianAlphabet[1][10]));
        btnChar11.setText(String.valueOf(persianAlphabet[1][11]));
        btnChar12.setText(String.valueOf(persianAlphabet[1][12]));
        btnChar13.setText(String.valueOf(persianAlphabet[1][13]));
        btnChar14.setText(String.valueOf(persianAlphabet[1][14]));
        btnChar15.setText(String.valueOf(persianAlphabet[1][15]));
        btnChar16.setText(String.valueOf(persianAlphabet[1][16]));
        btnChar17.setText(String.valueOf(persianAlphabet[1][17]));
        btnChar18.setText(String.valueOf(persianAlphabet[1][18]));
        btnChar19.setText(String.valueOf(persianAlphabet[1][19]));
        btnChar20.setText(String.valueOf(persianAlphabet[1][20]));
        btnChar21.setText(String.valueOf(persianAlphabet[1][21]));
        btnChar22.setText(String.valueOf(persianAlphabet[1][22]));
        btnChar23.setText(String.valueOf(persianAlphabet[1][23]));
        btnChar24.setText(String.valueOf(persianAlphabet[1][24]));
        btnChar25.setText(String.valueOf(persianAlphabet[1][25]));
        btnChar26.setText(String.valueOf(persianAlphabet[1][26]));
        btnChar27.setText(String.valueOf(persianAlphabet[1][27]));
        btnChar28.setText(String.valueOf(persianAlphabet[1][28]));
        btnChar29.setText(String.valueOf(persianAlphabet[1][29]));
        btnChar30.setText(String.valueOf(persianAlphabet[1][30]));
        btnChar31.setText(String.valueOf(persianAlphabet[1][31]));
        btnChar32.setText(String.valueOf(persianAlphabet[1][32]));
        btnChar33.setText(String.valueOf(persianAlphabet[1][33]));
        btnChar34.setText(String.valueOf(persianAlphabet[1][34]));
    }

    private void setEnglishPunctuation() {
        imbCapslock.setVisibility(GONE);
        imbShift.setVisibility(GONE);
        setVisibilityButtons();
        isPunctuationSelected = true;
        isCapsLockSelected = false;
        btnPunctuations.setText("abc");
        btnChar0.setText(String.valueOf(englishAlphabet[0][0]));
        btnChar1.setText(String.valueOf(englishAlphabet[0][1]));
        btnChar2.setText(String.valueOf(englishAlphabet[0][2]));
        btnChar3.setText(String.valueOf(englishAlphabet[0][3]));
        btnChar4.setText(String.valueOf(englishAlphabet[0][4]));
        btnChar5.setText(String.valueOf(englishAlphabet[0][5]));
        btnChar6.setText(String.valueOf(englishAlphabet[0][6]));
        btnChar7.setText(String.valueOf(englishAlphabet[0][7]));
        btnChar8.setText(String.valueOf(englishAlphabet[0][8]));
        btnChar9.setText(String.valueOf(englishAlphabet[0][9]));
        btnChar10.setText(String.valueOf(englishAlphabet[0][10]));
        btnChar11.setText(String.valueOf(englishAlphabet[0][11]));
        btnChar12.setText(String.valueOf(englishAlphabet[0][12]));
        btnChar13.setText(String.valueOf(englishAlphabet[0][13]));
        btnChar14.setText(String.valueOf(englishAlphabet[0][14]));
//        btnChar15.setText(String.valueOf(englishAlphabet[0][15]));
        btnChar15.setVisibility(GONE);
//        btnChar16.setText(String.valueOf(englishAlphabet[0][16]));
        btnChar16.setVisibility(GONE);
        btnChar17.setText(String.valueOf(englishAlphabet[0][17]));
//        btnChar18.setText(String.valueOf(englishAlphabet[0][18]));
        btnChar18.setVisibility(GONE);
        btnChar19.setText(String.valueOf(englishAlphabet[0][19]));
//        btnChar20.setText(String.valueOf(englishAlphabet[0][20]));
        btnChar20.setVisibility(GONE);
        btnChar21.setText(String.valueOf(englishAlphabet[0][21]));
        btnChar22.setText(String.valueOf(englishAlphabet[0][22]));
        btnChar23.setText(String.valueOf(englishAlphabet[0][23]));
        btnChar24.setText(String.valueOf(englishAlphabet[0][24]));
        btnChar25.setText(String.valueOf(englishAlphabet[0][25]));
        btnChar26.setText(String.valueOf(englishAlphabet[0][26]));
        btnChar27.setText(String.valueOf(englishAlphabet[0][27]));
        btnChar28.setText(String.valueOf(englishAlphabet[0][28]));
        btnChar29.setText(String.valueOf(englishAlphabet[0][29]));
//        btnChar30.setText(String.valueOf(englishAlphabet[0][30]));
        btnChar30.setVisibility(GONE);
        btnChar31.setText(String.valueOf(englishAlphabet[0][31]));
        btnChar32.setText(String.valueOf(englishAlphabet[0][32]));
        btnChar33.setText(String.valueOf(englishAlphabet[0][33]));
        btnChar34.setText(String.valueOf(englishAlphabet[0][34]));
    }

    private void setEnglishCapsLock() {
        imbCapslock.setVisibility(VISIBLE);
        imbShift.setVisibility(VISIBLE);
        setVisibilityButtons();
        isPunctuationSelected = false;
        isCapsLockSelected = true;
        btnPunctuations.setText("123?");
//        btnChar0.setText(String.valueOf(englishAlphabet[2][0]));
        btnChar0.setVisibility(GONE);
        btnChar1.setText(String.valueOf(englishAlphabet[2][1]));
        btnChar2.setText(String.valueOf(englishAlphabet[2][2]));
        btnChar3.setText(String.valueOf(englishAlphabet[2][3]));
        btnChar4.setText(String.valueOf(englishAlphabet[2][4]));
        btnChar5.setText(String.valueOf(englishAlphabet[2][5]));
//        btnChar6.setText(String.valueOf(englishAlphabet[2][6]));
        btnChar6.setVisibility(GONE);
//        btnChar7.setText(String.valueOf(englishAlphabet[2][7]));
        btnChar7.setVisibility(GONE);
        btnChar8.setText(String.valueOf(englishAlphabet[2][8]));
        btnChar9.setText(String.valueOf(englishAlphabet[2][9]));
        btnChar10.setText(String.valueOf(englishAlphabet[2][10]));
        btnChar11.setText(String.valueOf(englishAlphabet[2][11]));
        btnChar12.setText(String.valueOf(englishAlphabet[2][12]));
        btnChar13.setText(String.valueOf(englishAlphabet[2][13]));
//        btnChar14.setText(String.valueOf(englishAlphabet[2][14]));
        btnChar14.setVisibility(GONE);
        btnChar15.setText(String.valueOf(englishAlphabet[2][15]));
        btnChar16.setText(String.valueOf(englishAlphabet[2][16]));
        btnChar17.setText(String.valueOf(englishAlphabet[2][17]));
        btnChar18.setText(String.valueOf(englishAlphabet[2][18]));
        btnChar19.setText(String.valueOf(englishAlphabet[2][19]));
        btnChar20.setText(String.valueOf(englishAlphabet[2][20]));
        btnChar21.setText(String.valueOf(englishAlphabet[2][21]));
        btnChar22.setText(String.valueOf(englishAlphabet[2][22]));
        btnChar23.setText(String.valueOf(englishAlphabet[2][23]));
        btnChar24.setText(String.valueOf(englishAlphabet[2][24]));
//        btnChar25.setText(String.valueOf(englishAlphabet[2][25]));
        btnChar25.setVisibility(GONE);
//        btnChar26.setText(String.valueOf(englishAlphabet[2][26]));
        btnChar26.setVisibility(GONE);
        btnChar27.setText(String.valueOf(englishAlphabet[2][27]));
        btnChar28.setText(String.valueOf(englishAlphabet[2][28]));
        btnChar29.setText(String.valueOf(englishAlphabet[2][29]));
//        btnChar30.setText(String.valueOf(englishAlphabet[2][30]));
        btnChar30.setVisibility(GONE);
        btnChar31.setText(String.valueOf(englishAlphabet[2][31]));
        btnChar32.setText(String.valueOf(englishAlphabet[2][32]));
        btnChar33.setText(String.valueOf(englishAlphabet[2][33]));
        btnChar34.setText(String.valueOf(englishAlphabet[2][34]));
    }

    private void setEnglishAlphabet() {
        imbCapslock.setVisibility(VISIBLE);
        imbShift.setVisibility(VISIBLE);
        imbShift.setImageResource(R.drawable.ic_shift_button_unselected);
        imbCapslock.setBackgroundResource(R.color.transparent);
        setVisibilityButtons();
        isPunctuationSelected = false;
        isShifSelected = false;
        isCapsLockSelected = false;
        btnPunctuations.setText("123?");
//        btnChar0.setText(String.valueOf(englishAlphabet[1][0]));
        btnChar0.setVisibility(GONE);
        btnChar1.setText(String.valueOf(englishAlphabet[1][1]));
        btnChar2.setText(String.valueOf(englishAlphabet[1][2]));
        btnChar3.setText(String.valueOf(englishAlphabet[1][3]));
        btnChar4.setText(String.valueOf(englishAlphabet[1][4]));
        btnChar5.setText(String.valueOf(englishAlphabet[1][5]));
//        btnChar6.setText(String.valueOf(englishAlphabet[1][6]));
        btnChar6.setVisibility(GONE);
//        btnChar7.setText(String.valueOf(englishAlphabet[1][7]));
        btnChar7.setVisibility(GONE);
        btnChar8.setText(String.valueOf(englishAlphabet[1][8]));
        btnChar9.setText(String.valueOf(englishAlphabet[1][9]));
        btnChar10.setText(String.valueOf(englishAlphabet[1][10]));
        btnChar11.setText(String.valueOf(englishAlphabet[1][11]));
        btnChar12.setText(String.valueOf(englishAlphabet[1][12]));
        btnChar13.setText(String.valueOf(englishAlphabet[1][13]));
//        btnChar14.setText(String.valueOf(englishAlphabet[1][14]));
        btnChar14.setVisibility(GONE);
        btnChar15.setText(String.valueOf(englishAlphabet[1][15]));
        btnChar16.setText(String.valueOf(englishAlphabet[1][16]));
        btnChar17.setText(String.valueOf(englishAlphabet[1][17]));
        btnChar18.setText(String.valueOf(englishAlphabet[1][18]));
        btnChar19.setText(String.valueOf(englishAlphabet[1][19]));
        btnChar20.setText(String.valueOf(englishAlphabet[1][20]));
        btnChar21.setText(String.valueOf(englishAlphabet[1][21]));
        btnChar22.setText(String.valueOf(englishAlphabet[1][22]));
        btnChar23.setText(String.valueOf(englishAlphabet[1][23]));
        btnChar24.setText(String.valueOf(englishAlphabet[1][24]));
//        btnChar25.setText(String.valueOf(englishAlphabet[1][25]));
        btnChar25.setVisibility(GONE);
//        btnChar26.setText(String.valueOf(englishAlphabet[1][26]));
        btnChar26.setVisibility(GONE);
        btnChar27.setText(String.valueOf(englishAlphabet[1][27]));
        btnChar28.setText(String.valueOf(englishAlphabet[1][28]));
        btnChar29.setText(String.valueOf(englishAlphabet[1][29]));
//        btnChar30.setText(String.valueOf(englishAlphabet[1][30]));
        btnChar30.setVisibility(GONE);
        btnChar31.setText(String.valueOf(englishAlphabet[1][31]));
        btnChar32.setText(String.valueOf(englishAlphabet[1][32]));
        btnChar33.setText(String.valueOf(englishAlphabet[1][33]));
        btnChar34.setText(String.valueOf(englishAlphabet[1][34]));
    }

    private void setVisibilityButtons() {
        btnChar0.setVisibility(VISIBLE);
        btnChar6.setVisibility(VISIBLE);
        btnChar7.setVisibility(VISIBLE);
        btnChar14.setVisibility(VISIBLE);
        btnChar15.setVisibility(VISIBLE);
        btnChar16.setVisibility(VISIBLE);
        btnChar18.setVisibility(VISIBLE);
        btnChar20.setVisibility(VISIBLE);
        btnChar25.setVisibility(VISIBLE);
        btnChar26.setVisibility(VISIBLE);
        btnChar30.setVisibility(VISIBLE);
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
        if (showPunctuations) {
            btnChar33.setVisibility(VISIBLE);
            btnChar34.setVisibility(VISIBLE);
            btnPunctuations.setVisibility(VISIBLE);
        } else {
            btnChar33.setVisibility(GONE);
            btnChar34.setVisibility(GONE);
            btnPunctuations.setVisibility(INVISIBLE);
        }
    }

    public boolean getShowPunctuations() {
        return showPunctuations;
    }

    public void setLanguage(int l) {
        if (l == 1) {
            language = Language.PERSIAN;
        } else if (l == 2) {
            language = Language.ENGLISH;
        }
        changeLanguage(language);
    }

    private void changeLanguage(Language lang) {
        if (lang == Language.PERSIAN) {
            setPersianAlphabet();
        } else if (language == Language.ENGLISH) {
            setEnglishAlphabet();
        }
    }

    public Language getLanguage() {
        return language;
    }

    public void setShowLanguageSelectionButton(boolean flag) {
        this.showLanguageSelectionButton = flag;
        if (flag) {
            imbLanguage.setVisibility(VISIBLE);
        } else {
            imbLanguage.setVisibility(GONE);
        }
    }

    public boolean getShowLanguageSelectionButton() {
        return showLanguageSelectionButton;
    }
}
