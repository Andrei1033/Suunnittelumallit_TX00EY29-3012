package Assignment_03_Composite;

public class Main {

    public static void main(String[] args) {
        Department rootDepartment = new Department("Head Office");

        Department salesDepartment = new Department("Sales");
        salesDepartment.add(new Employee("Alice", 50000));
        salesDepartment.add(new Employee("Bob", 60000));

        Department itDepartment = new Department("IT");
        itDepartment.add(new Employee("Charlie", 70000));
        itDepartment.add(new Employee("David", 80000));

        rootDepartment.add(salesDepartment);
        rootDepartment.add(itDepartment);

        System.out.println("Total Salary: " + rootDepartment.getTotalSalary());
        rootDepartment.printXML();

        System.out.println("");

        salesDepartment.remove(salesDepartment.getChild(0));
        rootDepartment.remove(itDepartment);
        System.out.println("Total Salary after removal: " + rootDepartment.getTotalSalary());
        rootDepartment.printXML();
    }
}
