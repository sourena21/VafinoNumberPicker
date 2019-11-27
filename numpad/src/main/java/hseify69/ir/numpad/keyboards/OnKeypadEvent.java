package hseify69.ir.numpad.keyboards;

public interface OnKeypadEvent {

    void onSubmit();

    void onEnterChar(char c);

    void onBackSpace();

    void onClean();
}
