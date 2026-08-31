package Assignment_02_Abstract_Factory.A;

import Assignment_02_Abstract_Factory.AbstractClasses.TextField;

public class TextFieldA extends TextField {
    public TextFieldA(String text) {
        super(text);
    }

    @Override
    public void display() {
        System.out.println("+-------------+");
        System.out.println("| " + text + " |");
        System.out.println("+-------------+");
    }
}
