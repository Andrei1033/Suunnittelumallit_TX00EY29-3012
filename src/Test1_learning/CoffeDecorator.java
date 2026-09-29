package Test1_learning;

abstract class CoffeDecorator implements Coffee {
    protected Coffee coffee;
    public CoffeDecorator(Coffee c) {coffee = c;}
    public double cost() { return coffee.cost(); }
    public String description() { return coffee.description(); }
}