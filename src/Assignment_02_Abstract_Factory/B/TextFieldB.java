package Assignment_02_Abstract_Factory.B;

import Assignment_02_Abstract_Factory.AbstractClasses.TextField;

public class TextFieldB extends TextField {
    public TextFieldB(String text) {
        super(text);
    }

    @Override
    public void display() {
        System.out.println("< " + text + " >");
    }
}

