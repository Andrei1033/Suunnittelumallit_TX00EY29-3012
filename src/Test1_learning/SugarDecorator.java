package Test1_learning;

class SugarDecorator extends CoffeDecorator {
    public SugarDecorator(Coffee c) {super(c);}
    public double cost() {return super.cost() + 0.1;}
    public String description() { return super.description() + ", Sugar"; }
}
