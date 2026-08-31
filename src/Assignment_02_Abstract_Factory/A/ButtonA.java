package Assignment_02_Abstract_Factory.A;

import Assignment_02_Abstract_Factory.AbstractClasses.Button;

public class ButtonA extends Button {
    public ButtonA(String text) {
        super(text);
    }

    @Override
    public void display() {
        System.out.println("+-------------+");
        System.out.println("| " + text + " |");
        System.out.println("+-------------+");
    }
}
