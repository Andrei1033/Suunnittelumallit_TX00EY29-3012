package Assignment_02_Abstract_Factory.B;

import Assignment_02_Abstract_Factory.AbstractClasses.Button;

public class ButtonB extends Button {
    public ButtonB(String text) {
        super(text);
    }

    @Override
    public void display() {
        System.out.println("< " + text + " >");
    }
}
