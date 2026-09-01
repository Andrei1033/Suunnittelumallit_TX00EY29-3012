package Assignment_03_Composite;

import java.util.ArrayList;
import java.util.List;

public class Department extends OrganizationComponent {
    private final List<OrganizationComponent> components = new ArrayList<>();

    public Department(String name) {
        super(name);
    }

    @Override
    public void add(OrganizationComponent component) {
        this.components.add(component);
    }

    @Override
    public void remove(OrganizationComponent component) {
        this.components.remove(component);
    }

    @Override
    public OrganizationComponent getChild(int index) {
        return this.components.get(index);
    }

    @Override
    public double getTotalSalary() {
        double totalSalary = 0;
        for (OrganizationComponent component : components) {
            totalSalary += component.getTotalSalary();
        }
        return totalSalary;
    }

    @Override
    public void printXML() {
        System.out.println("<department name=\"" + name + "\">");
        for (OrganizationComponent component : components) {
            component.printXML();
        }
        System.out.println("</department>");
    }
}
