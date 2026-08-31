package Assignment_02_Abstract_Factory;

import Assignment_02_Abstract_Factory.AbstractClasses.Button;
import Assignment_02_Abstract_Factory.AbstractClasses.Checkbox;
import Assignment_02_Abstract_Factory.AbstractClasses.TextField;
import Assignment_02_Abstract_Factory.Factory.AFactory;
import Assignment_02_Abstract_Factory.Factory.BFactory;
import Assignment_02_Abstract_Factory.Factory.UIFactory;

public class Main {

    public static void main(String[] args) {

        System.out.println("BFactory Class");
        System.out.println("");

        UIFactory factory = new BFactory();

        Button button = factory.createButton("Click Me");
        TextField textField = factory.createTextField("Enter name");
        Checkbox checkbox = factory.createCheckbox("Remember me");

        System.out.println("Original:");

        button.display();
        textField.display();
        checkbox.display();

        System.out.println();

        System.out.println("After changing text:");

        button.setText("Submit");
        textField.setText("John");
        checkbox.setText("Accept terms");

        button.display();
        textField.display();
        checkbox.display();

        /*AFactory -> */

        System.out.println("");
        System.out.println("AFactory Class");
        System.out.println("");

        UIFactory factory2 = new AFactory();

        Button button2 = factory2.createButton("Click Me");
        TextField textField2 = factory2.createTextField("Enter name");
        Checkbox checkbox2 = factory2.createCheckbox("Remember me");

        System.out.println("Original:");

        button2.display();
        textField2.display();
        checkbox2.display();

        System.out.println();

        System.out.println("After changing text:");

        button2.setText("Submit");
        textField2.setText("John");
        checkbox2.setText("Accept terms");

        button2.display();
        textField2.display();
        checkbox2.display();
    }
}
