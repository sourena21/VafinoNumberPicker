package hseify69.ir.numpad.keyboards;

import android.content.Context;
import android.content.res.TypedArray;
import android.icu.util.MeasureUnit;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import hseify69.ir.numpad.R;
import hseify69.ir.numpad.helpers.Utils;

public class VafinoKeyboard extends LinearLayout implements OnKeypadEvent {

    String submitButtonText = "تایید";
    String enteredNumber = "";
    String hintText = "";
    int maxLength = Integer.MAX_VALUE;
    int textColor = 0;
    int hintColor = 0;
    int inputType = 0;
    int displayVisibility = 0;
    boolean showCharPopup = false;
    boolean showSubmitButton = false;
    float textSize;

    OnMobileDetected onMobileDetected;
    OnNumberEnter onNumberEnter;
    OnChangeEntered onChangeEntered;
    OnSubmitEntered onSubmitEntered;
    OnBackspace onBackspace;

    View v;
    LinearLayout llKeyboardBox, llKeypadBox;
    TextView txtEnteredNumber;

    public VafinoKeyboard(Context context) {
        super(context);
        initView(context, null);
    }

    public VafinoKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public VafinoKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        this.removeAllViews();
        v = View.inflate(context, R.layout.vafino_keyboard, null);

        llKeyboardBox = v.findViewById(R.id.VK_llKeyboardBox);
        llKeypadBox = v.findViewById(R.id.VK_rlKeypadBox);
        txtEnteredNumber = v.findViewById(R.id.VK_txtInput);

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.VafinoKeyboard);

        inputType = ta.getInteger(R.styleable.VafinoKeyboard_inputType, 1);
        displayVisibility = ta.getInteger(R.styleable.VafinoKeyboard_displayVisibility, VISIBLE);
        maxLength = ta.getInteger(R.styleable.VafinoKeyboard_maxLeangth, Integer.MAX_VALUE);
        String tempHint = ta.getString(R.styleable.VafinoKeyboard_inputHintText);
        if (tempHint != null && tempHint.length() > 0) {
            hintText = tempHint;
        }
        String submitText = ta.getString(R.styleable.VafinoKeyboard_setSubmitText);
        if (submitText != null && submitText.length() > 0) {
            submitButtonText = submitText;
        }
        String tempValue = ta.getString(R.styleable.VafinoKeyboard_inputValue);
        if (tempValue != null && tempValue.length() > 0) {
            enteredNumber = tempValue;
        }
        textColor = ta.getColor(R.styleable.VafinoKeyboard_inputTextColor, context.getResources().getColor(R.color.colorBlack));
        textSize = ta.getDimension(R.styleable.VafinoKeyboard_textSize, context.getResources().getDimension(R.dimen.text_small_size));
        hintColor = ta.getColor(R.styleable.VafinoKeyboard_inputHintColor, context.getResources().getColor(R.color.colorGrayDark));
        showCharPopup = ta.getBoolean(R.styleable.VafinoKeyboard_showPersianCharPopup, false);
        showSubmitButton = ta.getBoolean(R.styleable.VafinoKeyboard_showSubmitButton, false);

        txtEnteredNumber.setHint(hintText);
        txtEnteredNumber.setText(enteredNumber);
        txtEnteredNumber.setTextColor(textColor);
        txtEnteredNumber.setHintTextColor(hintColor);
        txtEnteredNumber.setTextSize(textSize);

        switch (displayVisibility) {
            case VISIBLE:
                setInputDisplayVisibility(VISIBLE);
                break;
            case INVISIBLE:
                setInputDisplayVisibility(INVISIBLE);
                break;
            case GONE:
                setInputDisplayVisibility(GONE);
                break;
            default:
                setInputDisplayVisibility(VISIBLE);
                break;
        }
        switch (inputType) {
            case 1:
                initKeyboardByPersian(context);
                break;
            case 2:
                initKeyboardByDecimal(context);
                break;
            default:
                initKeyboardByPersian(context);
                break;
        }

        this.addView(v);
    }

    public void initKeyboardByPersian(Context context) {
        PersianKeypad persianKeypad = new PersianKeypad(context);
        persianKeypad.setOnKeypadEvent(this);
        persianKeypad.setShowCharPopup(showCharPopup);
        persianKeypad.setSubmitButtonText(submitButtonText);
        persianKeypad.setTextColor(textColor);
        persianKeypad.setAllTextSize(textSize);
        llKeypadBox.removeAllViews();
        cleanView();
        llKeypadBox.addView(persianKeypad);
    }

    public void initKeyboardByDecimal(Context context) {
        DecimalKeypad decimalKeypad = new DecimalKeypad(context);
        decimalKeypad.setOnKeypadEvent(this);
        decimalKeypad.setShowSubmitButton(showSubmitButton);
        decimalKeypad.setSubmitButtonText(submitButtonText);
        decimalKeypad.setTextColor(textColor);
        decimalKeypad.setAllTextSize(textSize);
        llKeypadBox.removeAllViews();
        cleanView();
        llKeypadBox.addView(decimalKeypad);
    }

    private void cleanView() {
        enteredNumber = "";
        showEnteredNumber(enteredNumber);
    }

    private void backSpaceLast() {
        if (onBackspace != null) {
            onBackspace.onBackspace();
        }
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

    public interface OnBackspace {
        void onBackspace();
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

    public void setOnBackspace(OnBackspace listener) {
        onBackspace = listener;
    }

    public void setInputDisplayVisibility(int visibility) {
        if (visibility == VISIBLE || visibility == INVISIBLE || visibility == GONE) {
            txtEnteredNumber.setVisibility(visibility);
        }
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public void setInput(TextView textView) {
        if (textView != null) {
            txtEnteredNumber = textView;
            enteredNumber = textView.getText().toString();
        } else {
            txtEnteredNumber = v.findViewById(R.id.VK_txtInput);
            enteredNumber = txtEnteredNumber.getText().toString();
        }
    }

    public boolean isShowCharPopup() {
        return showCharPopup;
    }

    public void setShowCharPopup(boolean flag) {
        this.showCharPopup = flag;
    }

    public boolean isShowSubmitButton() {
        return showSubmitButton;
    }

    public void setShowSubmitButton(boolean showSubmitButton) {
        this.showSubmitButton = showSubmitButton;
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
