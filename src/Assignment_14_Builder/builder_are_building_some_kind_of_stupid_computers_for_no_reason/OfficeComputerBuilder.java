package Assignment_14_Builder.builder_are_building_some_kind_of_stupid_computers_for_no_reason;

import Assignment_14_Builder.model_not_ai_model_just_some_crazy_model_for_stupid_computer_program.Computer;

public class OfficeComputerBuilder implements ComputerBuilder {

    private Computer computer;

    public OfficeComputerBuilder() {
        reset();
    }

    @Override
    public void buildProcessor() {
        computer.setProcessor("Intel Core i5");
    }

    @Override
    public void buildRAM() {
        computer.setRam(16);
    }

    @Override
    public void buildHardDrive() {
        computer.setHardDrive("512 GB SSD");
    }

    @Override
    public void buildGraphicsCard() {
        computer.setGraphicsCard("Integrated Graphics");
    }

    @Override
    public void buildOperatingSystem() {
        computer.setOperatingSystem("Windows 11");
    }

    @Override
    public void reset() {
        computer = new Computer();
    }

    @Override
    public Computer getComputer() {
        Computer result = computer;
        reset();
        return result;
    }
}
