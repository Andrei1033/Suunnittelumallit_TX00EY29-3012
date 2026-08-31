package Assignment_02_Abstract_Factory.Factory;

import Assignment_02_Abstract_Factory.A.ButtonA;
import Assignment_02_Abstract_Factory.A.CheckboxA;
import Assignment_02_Abstract_Factory.A.TextFieldA;
import Assignment_02_Abstract_Factory.AbstractClasses.Button;
import Assignment_02_Abstract_Factory.AbstractClasses.Checkbox;
import Assignment_02_Abstract_Factory.AbstractClasses.TextField;

public class AFactory extends UIFactory {

    @Override
    public Button createButton(String text) {
        return new ButtonA(text);
    }

    @Override
    public TextField createTextField(String text) {
        return new TextFieldA(text);
    }

    @Override
    public Checkbox createCheckbox(String text) {
        return new CheckboxA(text);
    }
}
