package Assignment_03_Composite;

public class Employee extends OrganizationComponent {
    private final double salary;

    public Employee (String name, double salary) {
        super(name);
        this.salary = salary;
    }

    @Override
    public void add(OrganizationComponent component) {
        throw new UnsupportedOperationException("Cannot add to an employee.");
    }

    @Override
    public void remove(OrganizationComponent component) {
        throw new UnsupportedOperationException("Cannot remove from an employee.");
    }

    @Override
    public OrganizationComponent getChild(int index) {
        throw new UnsupportedOperationException("Cannot get from an employee.");
    }

    @Override
    public double getTotalSalary() {
        return salary;
    }

    @Override
    public void printXML() {
        System.out.println("<employee name=\"" + name + "\" salary=\"" + salary + "\"/>");
    }
}
