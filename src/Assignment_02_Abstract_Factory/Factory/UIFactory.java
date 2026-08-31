package Assignment_02_Abstract_Factory.Factory;

import Assignment_02_Abstract_Factory.AbstractClasses.Button;
import Assignment_02_Abstract_Factory.AbstractClasses.Checkbox;
import Assignment_02_Abstract_Factory.AbstractClasses.TextField;

public abstract class UIFactory {

    public abstract Button createButton(String text);
    public abstract TextField createTextField(String text);
    public abstract Checkbox createCheckbox(String text);
}
