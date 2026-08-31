package Assignment_02_Abstract_Factory;

public abstract class UIElement {

    protected String text;

    public UIElement(String text) {
        this.text = text;
    }

    public void setText(String submit) {
        this.text = submit;
    }

    public abstract void display();


}
