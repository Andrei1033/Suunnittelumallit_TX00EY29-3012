package Assignment_02_Abstract_Factory.Factory;

import Assignment_02_Abstract_Factory.AbstractClasses.Button;
import Assignment_02_Abstract_Factory.AbstractClasses.Checkbox;
import Assignment_02_Abstract_Factory.AbstractClasses.TextField;
import Assignment_02_Abstract_Factory.B.ButtonB;
import Assignment_02_Abstract_Factory.B.CheckboxB;
import Assignment_02_Abstract_Factory.B.TextFieldB;

public class BFactory extends UIFactory {

    @Override
    public Button createButton(String text) {
        return new ButtonB(text);
    }

    @Override
    public TextField createTextField(String text) {
        return new TextFieldB(text);
    }

    @Override
    public Checkbox createCheckbox(String text) {
        return new CheckboxB(text);
    }
}









































































/*Iso Kakka Ohjelma B*/