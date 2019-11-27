package hseify69.ir.numpad.keyboards;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import hseify69.ir.numpad.R;
import hseify69.ir.numpad.helpers.Utils;

public class VafinoKeyboard extends LinearLayout implements OnKeypadEvent {

    String enteredNumber = "";
    String hintText = "";
    int maxLength = 100;
    OnMobileDetected onMobileDetected;
    OnNumberEnter onNumberEnter;
    OnChangeEntered onChangeEntered;
    OnSubmitEntered onSubmitEntered;

    LinearLayout llKeyboardBox, llKeypadBox;
    TextView txtEnteredNumber;

    public VafinoKeyboard(Context context) {
        super(context);
        initView(context);
    }

    public VafinoKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public VafinoKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.removeAllViews();
        View v = View.inflate(context, R.layout.vafino_keyboard, null);

        llKeyboardBox = v.findViewById(R.id.VK_llKeyboardBox);
        llKeypadBox = v.findViewById(R.id.VK_rlKeypadBox);
        txtEnteredNumber = v.findViewById(R.id.VK_txtInput);

        initKeyboardByPersian(context);

        this.addView(v);
    }

    private void initKeyboardByPersian(Context context) {
        PersianKeypad persianKeypad = new PersianKeypad(context);
        persianKeypad.setOnKeypadEvent(this);
        llKeypadBox.removeAllViews();
        llKeypadBox.addView(persianKeypad);
    }

    private void initKeyboardByDecimal(Context context) {
        DecimalKeypad decimalKeypad = new DecimalKeypad(context);
        decimalKeypad.setOnKeypadEvent(this);
        llKeypadBox.removeAllViews();
        llKeypadBox.addView(decimalKeypad);
    }

    private void cleanView() {
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

    private void showEnteredNumber(String text) {
        txtEnteredNumber.setText(text);
        txtEnteredNumber.setTextColor(getResources().getColor(R.color.colorBlack));
        if (onChangeEntered != null) {
            onChangeEntered.onChange(enteredNumber);
        }
    }

    private void addChar(char c) {
        if (enteredNumber.length() < maxLength) {
            enteredNumber += c;
            showEnteredNumber(enteredNumber);
            if (onNumberEnter != null) {
                onNumberEnter.onEnter(c, enteredNumber);
            }
            if (Utils.isEnteredCellNumber(enteredNumber) && onMobileDetected != null) {
                onMobileDetected.onDetect(Utils.getStandardMobileFormat(enteredNumber));
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

    public void setBackSpaceButtonBackground(int src) {
    }

    public interface OnMobileDetected {
        void onDetect(String cellNumber);
    }

    public interface OnNumberEnter {
        void onEnter(char c, String wholeEntered);
    }

    public interface OnChangeEntered {
        void onChange(String wholeEntered);
    }

    public interface OnSubmitEntered {
        void onSubmit(String wholeEntered);
    }

    public void setOnSubmitEntered(OnSubmitEntered listener) {
        onSubmitEntered = listener;
    }

    public void setSubmitButtonVisibility(int visibility) {
        if (visibility == VISIBLE || visibility == INVISIBLE || visibility == GONE) {
        }
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

    @Override
    public void onSubmit() {
        if (onSubmitEntered != null) {
            onSubmitEntered.onSubmit(enteredNumber);
        }
    }

    @Override
    public void onEnterChar(char c) {
        addChar(c);
    }

    @Override
    public void onBackSpace() {
        backSpaceLast();
    }

    @Override
    public void onClean() {
        cleanView();
    }
}
