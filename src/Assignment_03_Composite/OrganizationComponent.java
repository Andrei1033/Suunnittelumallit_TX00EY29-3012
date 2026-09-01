package Assignment_03_Composite;

public abstract class OrganizationComponent {

    protected String name;

    public OrganizationComponent(String name) {
        this.name = name;
    }

    public void printData() {
        System.out.println(this.name);
    }

    public abstract void add(OrganizationComponent component);

    public abstract void remove(OrganizationComponent component);

    public abstract OrganizationComponent getChild(int index);

    public abstract double getTotalSalary();

    public abstract void printXML();
}
