package Test1_learning;

public class Main {
    public static void main(String[] args) {
        Coffee myCoffee = new SimpleCoffee();
        System.out.println(myCoffee.description() + " = $" + myCoffee.cost());

        myCoffee = new MilkDecorator(myCoffee);
        System.out.println(myCoffee.description() + " = $" + myCoffee.cost());

        myCoffee = new SugarDecorator(myCoffee);
        System.out.println(myCoffee.description() + " = $" + myCoffee.cost());

        Coffee sweetCoffee = new SugarDecorator(new SugarDecorator(new SimpleCoffee()));
        System.out.println(sweetCoffee.description() + " = $" + sweetCoffee.cost());
    }
}
