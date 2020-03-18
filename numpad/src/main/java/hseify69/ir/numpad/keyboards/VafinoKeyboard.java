package hseify69.ir.numpad.keyboards;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import hseify69.ir.numpad.R;
import hseify69.ir.numpad.helpers.Utils;

public class VafinoKeyboard extends LinearLayout implements OnKeypadEvent {

    String submitButtonText = "تایید";
    String hintText = "";
    int maxLength = Integer.MAX_VALUE;
    int textColor = 0;
    int textDirection = 0;
    int hintColor = 0;
    int inputType = 0;
    int language = 1;
    int displayVisibility = 0;
    boolean showCharPopup = false;
    boolean showSubmitButton = false;
    boolean showEnterButton = false;
    boolean showPunctuations = true;
    boolean showLanguageSelectionButton = true;
    float textSize;
    InputConnection inputConnection;

    OnMobileDetected onMobileDetected;
    OnNumberEnter onNumberEnter;
    OnChangeEntered onChangeEntered;
    OnSubmitEntered onSubmitEntered;
    OnBackspace onBackspace;

    View v;
    LinearLayout llKeyboardBox, llKeypadBox;
    EditText edtInput;

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
        LayoutInflater.from(context).inflate(R.layout.vafino_keyboard, this, true);

        llKeyboardBox = findViewById(R.id.VK_llKeyboardBox);
        llKeypadBox = findViewById(R.id.VK_rlKeypadBox);
        edtInput = findViewById(R.id.VK_edtInput);

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.VafinoKeyboard);

        inputType = ta.getInteger(R.styleable.VafinoKeyboard_inputType, 1);
        textDirection = ta.getInteger(R.styleable.VafinoKeyboard_textDirection, 0);
        language = ta.getInteger(R.styleable.VafinoKeyboard_language, 1);
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

        textColor = ta.getColor(R.styleable.VafinoKeyboard_inputTextColor, context.getResources().getColor(R.color.colorBlack));
        textSize = ta.getDimension(R.styleable.VafinoKeyboard_textSize, context.getResources().getDimension(R.dimen.text_small_size));
        hintColor = ta.getColor(R.styleable.VafinoKeyboard_inputHintColor, context.getResources().getColor(R.color.colorGrayDark));
        showCharPopup = ta.getBoolean(R.styleable.VafinoKeyboard_showPersianCharPopup, false);
        showSubmitButton = ta.getBoolean(R.styleable.VafinoKeyboard_showSubmitButton, false);
        showEnterButton = ta.getBoolean(R.styleable.VafinoKeyboard_showEnterInPersian, false);
        showPunctuations = ta.getBoolean(R.styleable.VafinoKeyboard_showPunctuationsInPersian, true);
        showLanguageSelectionButton = ta.getBoolean(R.styleable.VafinoKeyboard_showLanguageSelectionButton, true);

        edtInput.setHint(hintText);
        if (tempValue != null && tempValue.length() > 0) {
            edtInput.setText(tempValue);
        }
        edtInput.setTextColor(textColor);
        edtInput.setHintTextColor(hintColor);
        edtInput.setTextSize(textSize);

        setTextDirection(textDirection);
        setDisplayVisibility(displayVisibility);
        setInputType(inputType);

        // prevent system keyboard from appearing when EditText is tapped
//        edtInput.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        edtInput.setTextIsSelectable(true);

        // pass the InputConnection from the EditText to the keyboard
        InputConnection ic = edtInput.onCreateInputConnection(new EditorInfo());
        this.setInputConnection(ic);
    }

    public void setInputConnection(InputConnection ic) {
        this.inputConnection = ic;
    }

    public void initKeyboardByPersian(Context context) {
        AlphabetKeypad alphabetKeypad = new AlphabetKeypad(context);
        alphabetKeypad.setOnKeypadEvent(this);
        alphabetKeypad.setShowCharPopup(showCharPopup);
        alphabetKeypad.setShowEnterButton(showEnterButton);
        alphabetKeypad.setShowPunctuations(showPunctuations);
        alphabetKeypad.setLanguage(language);
        alphabetKeypad.setShowLanguageSelectionButton(showLanguageSelectionButton);
        alphabetKeypad.setSubmitButtonText(submitButtonText);
        alphabetKeypad.setTextColor(textColor);
        alphabetKeypad.setAllTextSize(textSize);
        llKeypadBox.removeAllViews();
        llKeypadBox.addView(alphabetKeypad);
    }

    public void initKeyboardByDecimal(Context context) {
        DecimalKeypad decimalKeypad = new DecimalKeypad(context);
        decimalKeypad.setOnKeypadEvent(this);
        decimalKeypad.setShowSubmitButton(showSubmitButton);
        decimalKeypad.setSubmitButtonText(submitButtonText);
        decimalKeypad.setTextColor(textColor);
        decimalKeypad.setAllTextSize(textSize);
        llKeypadBox.removeAllViews();
        llKeypadBox.addView(decimalKeypad);
    }

    private void cleanView() {
        setEntered("");
    }

    private void backSpaceLast() {
        if (onBackspace != null) {
            onBackspace.onBackspace();
        }
        if (edtInput.getText().toString().length() > 0) {
            CharSequence selectedText = inputConnection.getSelectedText(0);
            if (TextUtils.isEmpty(selectedText)) {
                // no selection, so delete previous character
                inputConnection.deleteSurroundingText(1, 0);
            } else {
                // delete the selection
                inputConnection.commitText("", 1);
            }
            if (onChangeEntered != null) {
                onChangeEntered.onChange(edtInput.getText().toString());
            }
//            enteredNumber = enteredNumber.substring(0, enteredNumber.length() - 1);
//            if (enteredNumber.length() > 0) {
//                showEnteredNumber(enteredNumber);
//            } else {
//                cleanView();
//            }
        }
    }

    private void addChar(char c) {
        if (edtInput.getText().toString().length() < maxLength) {
            inputConnection.commitText(String.valueOf(c), 1);
            if (onNumberEnter != null) {
                onNumberEnter.onEnter(c, edtInput.getText().toString());
            }
            if (Utils.isEnteredCellNumber(edtInput.getText().toString()) && onMobileDetected != null) {
                onMobileDetected.onDetect(Utils.getStandardMobileFormat(edtInput.getText().toString()));
                cleanView();
            }
            if (onChangeEntered != null) {
                onChangeEntered.onChange(edtInput.getText().toString());
            }
        }
    }

    public void setEntered(String text) {
        edtInput.setText(text);
        edtInput.setSelection(edtInput.getText().toString().length());
        if (onChangeEntered != null) {
            onChangeEntered.onChange(text);
        }
    }

    public String getHintText() {
        return hintText;
    }

    public void setHintText(String text) {
        this.hintText = text;
        edtInput.setHint(hintText);
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
            edtInput.setVisibility(visibility);
        }
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public void setInput(EditText edt) {
        if (edt != null) {
            edtInput = edt;
        } else {
            edtInput = v.findViewById(R.id.VK_edtInput);
        }

        // prevent system keyboard from appearing when EditText is tapped
        edt.setInputType(edtInput.getInputType());
        edt.setTextIsSelectable(true);

        // pass the InputConnection from the EditText to the keyboard
        InputConnection ic = edt.onCreateInputConnection(new EditorInfo());
        this.setInputConnection(ic);
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

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
        switch (inputType) {
            case 1:
                initKeyboardByPersian(getContext());
                break;
            case 2:
                initKeyboardByDecimal(getContext());
                break;
            default:
                initKeyboardByPersian(getContext());
                break;
        }
    }

    public int getDisplayVisibility() {
        return displayVisibility;
    }

    public void setDisplayVisibility(int displayVisibility) {
        this.displayVisibility = displayVisibility;
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
    }

    public int getTextDirection() {
        return textDirection;
    }

    public void setTextDirection(int textDirection) {
        if (textDirection == View.TEXT_DIRECTION_LTR || textDirection == View.TEXT_DIRECTION_RTL) {
            this.textDirection = textDirection;
            edtInput.setTextDirection(textDirection);
        }
    }

    @Override
    public void onSubmit() {
        if (onSubmitEntered != null) {
            onSubmitEntered.onSubmit(edtInput.getText().toString());
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
