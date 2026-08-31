package Assignment_02_Abstract_Factory.B;

import Assignment_02_Abstract_Factory.AbstractClasses.Checkbox;

public class CheckboxB extends Checkbox {
    public CheckboxB(String text) {
        super(text);
    }

    @Override
    public void display() {
        System.out.println("< >" + text);
    }
}
