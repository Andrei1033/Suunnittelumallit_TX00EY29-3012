package Test1_learning;

class MilkDecorator extends CoffeDecorator {
    public MilkDecorator(Coffee c) {super(c);}
    public double cost() {return super.cost() + 0.3;}
    public String description() { return super.description() + ", Milk"; }
}
