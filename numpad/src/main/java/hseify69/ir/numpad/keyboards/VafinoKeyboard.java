package hseify69.ir.numpad.keyboards;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import hseify69.ir.numpad.R;
import hseify69.ir.numpad.helpers.Utils;

public class VafinoKeyboard extends LinearLayout implements OnKeypadEvent {

    String submitButtonText = "تایید";
    String hintText = "";
    int maxLength = Integer.MAX_VALUE;
    int textColor = 0;
    int hintColor = 0;
    int inputType = 0;
    int displayVisibility = 0;
    boolean showCharPopup = false;
    boolean showSubmitButton = false;
    boolean showEnterButton = false;
    boolean showPunctuations = true;
    boolean showInputTypeSelection = false;
    float textSize;
    // Our communication link to the EditText
    InputConnection inputConnection;

    OnMobileDetected onMobileDetected;
    OnNumberEnter onNumberEnter;
    OnChangeEntered onChangeEntered;
    OnSubmitEntered onSubmitEntered;
    OnBackspace onBackspace;

    View v;
    LinearLayout llKeyboardBox, llKeypadBox;
    EditText edtInput;
    RadioGroup rgInputType;
    RadioButton rbPersian, rbDecimal;

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
        edtInput = v.findViewById(R.id.VK_edtInput);
        rgInputType = v.findViewById(R.id.VK_rgInputType);
        rbPersian = v.findViewById(R.id.VK_rbPersian);
        rbDecimal = v.findViewById(R.id.VK_rbDecimal);

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

        textColor = ta.getColor(R.styleable.VafinoKeyboard_inputTextColor, context.getResources().getColor(R.color.colorBlack));
        textSize = ta.getDimension(R.styleable.VafinoKeyboard_textSize, context.getResources().getDimension(R.dimen.text_small_size));
        hintColor = ta.getColor(R.styleable.VafinoKeyboard_inputHintColor, context.getResources().getColor(R.color.colorGrayDark));
        showCharPopup = ta.getBoolean(R.styleable.VafinoKeyboard_showPersianCharPopup, false);
        showSubmitButton = ta.getBoolean(R.styleable.VafinoKeyboard_showSubmitButton, false);
        showEnterButton = ta.getBoolean(R.styleable.VafinoKeyboard_showEnterInPersian, false);
        showPunctuations = ta.getBoolean(R.styleable.VafinoKeyboard_showPunctuationsInPersian, true);
        showInputTypeSelection = ta.getBoolean(R.styleable.VafinoKeyboard_showInputTypeSelection, false);

        edtInput.setHint(hintText);
        if (tempValue != null && tempValue.length() > 0) {
            edtInput.setText(tempValue);
        }
        edtInput.setTextColor(textColor);
        edtInput.setHintTextColor(hintColor);
        edtInput.setTextSize(textSize);

        setShowInputTypeSelection(showInputTypeSelection);
        setDisplayVisibility(displayVisibility);
        setInputType(inputType);

        rbPersian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    initKeyboardByPersian(getContext());
                }
            }
        });
        rbDecimal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    initKeyboardByDecimal(getContext());
                }
            }
        });

        // prevent system keyboard from appearing when EditText is tapped
        edtInput.setRawInputType(InputType.TYPE_CLASS_TEXT);
        edtInput.setTextIsSelectable(true);

        // pass the InputConnection from the EditText to the keyboard
        InputConnection ic = edtInput.onCreateInputConnection(new EditorInfo());
        this.setInputConnection(ic);

        this.addView(v);
    }

    // The activity (or some parent or controller) must give us
    // a reference to the current EditText's InputConnection
    public void setInputConnection(InputConnection ic) {
        this.inputConnection = ic;
    }

    public void initKeyboardByPersian(Context context) {
        PersianKeypad persianKeypad = new PersianKeypad(context);
        persianKeypad.setOnKeypadEvent(this);
        persianKeypad.setShowCharPopup(showCharPopup);
        persianKeypad.setShowEnterButton(showEnterButton);
        persianKeypad.setShowPunctuations(showPunctuations);
        persianKeypad.setSubmitButtonText(submitButtonText);
        persianKeypad.setTextColor(textColor);
        persianKeypad.setAllTextSize(textSize);
        llKeypadBox.removeAllViews();
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
        llKeypadBox.addView(decimalKeypad);
    }

    private void cleanView() {
        showEnteredNumber("");
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
//            enteredNumber = enteredNumber.substring(0, enteredNumber.length() - 1);
//            if (enteredNumber.length() > 0) {
//                showEnteredNumber(enteredNumber);
//            } else {
//                cleanView();
//            }
        }
    }

    private void showEnteredNumber(String text) {
        edtInput.setText(text);
        if (onChangeEntered != null) {
            onChangeEntered.onChange(text);
        }
    }

    private void addChar(char c) {
        if (edtInput.getText().toString().length() < maxLength) {
//            enteredNumber += c;
//            showEnteredNumber(enteredNumber);
            inputConnection.commitText(String.valueOf(c), 1);
            if (onNumberEnter != null) {
                onNumberEnter.onEnter(c, edtInput.getText().toString());
            }
            if (Utils.isEnteredCellNumber(edtInput.getText().toString()) && onMobileDetected != null) {
                onMobileDetected.onDetect(Utils.getStandardMobileFormat(edtInput.getText().toString()));
                cleanView();
            }
        }
    }

    public void setEnteredNumber(String number) {
        showEnteredNumber(number);
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
        edt.setRawInputType(InputType.TYPE_CLASS_TEXT);
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

    public boolean isShowInputTypeSelection() {
        return showInputTypeSelection;
    }

    public void setShowInputTypeSelection(boolean showInputTypeSelection) {
        this.showInputTypeSelection = showInputTypeSelection;
        if (showInputTypeSelection) {
            rgInputType.setVisibility(VISIBLE);
        } else {
            rgInputType.setVisibility(GONE);
        }
    }

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
        switch (inputType) {
            case 1:
                initKeyboardByPersian(getContext());
                rbPersian.setChecked(true);
                break;
            case 2:
                initKeyboardByDecimal(getContext());
                rbDecimal.setChecked(true);
                break;
            default:
                initKeyboardByPersian(getContext());
                rbPersian.setChecked(true);
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
