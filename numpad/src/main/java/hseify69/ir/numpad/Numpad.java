package hseify69.ir.numpad;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Numpad extends LinearLayout implements View.OnClickListener {

    String enteredNumber = "";
    String hintText = "";
    int maxLength = 100;
    OnMobileDetected onMobileDetected;
    OnNumberEnter onNumberEnter;
    OnChangeEntered onChangeEntered;

    Button btnNumber0, btnNumber1, btnNumber2, btnNumber3, btnNumber4, btnNumber5, btnNumber6,
            btnNumber7, btnNumber8, btnNumber9;
    ImageButton imbBackSpace;
    TextView txtEnteredNumber;

    public Numpad(Context context) {
        super(context);
        initView(context);
    }

    public Numpad(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public Numpad(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.removeAllViews();
        View v = View.inflate(context, R.layout.numpad, null);

        btnNumber0 = v.findViewById(R.id.btnNumber0);
        btnNumber1 = v.findViewById(R.id.btnNumber1);
        btnNumber2 = v.findViewById(R.id.btnNumber2);
        btnNumber3 = v.findViewById(R.id.btnNumber3);
        btnNumber4 = v.findViewById(R.id.btnNumber4);
        btnNumber5 = v.findViewById(R.id.btnNumber5);
        btnNumber6 = v.findViewById(R.id.btnNumber6);
        btnNumber7 = v.findViewById(R.id.btnNumber7);
        btnNumber8 = v.findViewById(R.id.btnNumber8);
        btnNumber9 = v.findViewById(R.id.btnNumber9);
        imbBackSpace = v.findViewById(R.id.imbBackSpace);
        txtEnteredNumber = v.findViewById(R.id.txtEnteredNumber);

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

//        imbBackSpace.setOnLongClickListener(new OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                cleanView();
//                return false;
//            }
//        });
        imbBackSpace.setOnTouchListener(new OnTouchListener() {

            long pressAmount = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != MotionEvent.ACTION_UP) {
                    if (pressAmount > 60) {
                        cleanView();
                        pressAmount = 0;
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
        if (id == R.id.btnNumber0) {
            addNumber(0);
        } else if (id == R.id.btnNumber1) {
            addNumber(1);
        } else if (id == R.id.btnNumber2) {
            addNumber(2);
        } else if (id == R.id.btnNumber3) {
            addNumber(3);
        } else if (id == R.id.btnNumber4) {
            addNumber(4);
        } else if (id == R.id.btnNumber5) {
            addNumber(5);
        } else if (id == R.id.btnNumber6) {
            addNumber(6);
        } else if (id == R.id.btnNumber7) {
            addNumber(7);
        } else if (id == R.id.btnNumber8) {
            addNumber(8);
        } else if (id == R.id.btnNumber9) {
            addNumber(9);
        } else if (id == R.id.imbBackSpace) {
            backSpaceLast();
        }
    }

    private void cleanView() {
        imbBackSpace.setVisibility(INVISIBLE);
        enteredNumber = "";
        txtEnteredNumber.setText(hintText);
        txtEnteredNumber.setTextColor(getResources().getColor(R.color.colorGray));
        if (onChangeEntered != null) {
            onChangeEntered.onChange(enteredNumber);
        }
    }

    private void backSpaceLast() {
        if (enteredNumber.length() > 0) {
            enteredNumber = enteredNumber.substring(0, enteredNumber.length() - 1);
            if (enteredNumber.length() > 0) {
                showEnteredNumber(enteredNumber);
            } else {
                cleanView();
            }
        }
    }

    private void showEnteredNumber(String number) {
        imbBackSpace.setVisibility(VISIBLE);
        txtEnteredNumber.setText(number);
        txtEnteredNumber.setTextColor(getResources().getColor(R.color.colorBlack));
        if (onChangeEntered != null) {
            onChangeEntered.onChange(enteredNumber);
        }
    }

    private void addNumber(int num) {
        if (enteredNumber.length() < maxLength) {
            enteredNumber += num;
            showEnteredNumber(enteredNumber);
            if (onNumberEnter != null) {
                onNumberEnter.onEnter(num, enteredNumber);
            }
            if (utils.isEnteredCellNumber(enteredNumber) && onMobileDetected != null) {
                onMobileDetected.onDetect(utils.getStandardMobileFormat(enteredNumber));
                cleanView();
            }
        }
    }

    public String getEnteredNumber() {
        return enteredNumber;
    }

    public void setEnteredNumber(String number) {
        this.enteredNumber = number;
        showEnteredNumber(enteredNumber);
    }

    public String getHintText() {
        return hintText;
    }

    public void setHintText(String text) {
        this.hintText = text;
        if (enteredNumber.length() == 0) {
            cleanView();
        }
    }

    public interface OnMobileDetected {
        void onDetect(String cellNumber);
    }

    public interface OnNumberEnter {
        void onEnter(int number, String wholeEntered);
    }

    public interface OnChangeEntered {
        void onChange(String wholeEntered);
    }

    public void setOnChangeEntered(OnChangeEntered listener) {
        onChangeEntered = listener;
    }

    public void setOnMobileDetected(OnMobileDetected listener) {
        onMobileDetected = listener;
    }

    public void setOnNumberEnter(OnNumberEnter listener) {
        onNumberEnter = listener;
    }

    public void setInputDisplayVisibility(int visibility) {
        txtEnteredNumber.setVisibility(visibility);
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
}
