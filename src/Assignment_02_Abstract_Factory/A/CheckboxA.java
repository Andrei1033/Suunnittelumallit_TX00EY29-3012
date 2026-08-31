package Assignment_02_Abstract_Factory.A;

import Assignment_02_Abstract_Factory.AbstractClasses.Checkbox;

public class CheckboxA extends Checkbox {
    public CheckboxA(String text) {
        super(text);
    }

    @Override
    public void display() {
        System.out.println("|  |" + text);
    }
}
