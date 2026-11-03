package Assignment_14_Builder.builder_are_building_some_kind_of_stupid_computers_for_no_reason;

import Assignment_14_Builder.model_not_ai_model_just_some_crazy_model_for_stupid_computer_program.Computer;

public class CrazyComputer implements ComputerBuilder {

    private Computer computer;

    public CrazyComputer() {
        reset();
    }

    @Override
    public void buildProcessor() {
        computer.setProcessor("trrtr");
    }

    @Override
    public void buildRAM() {
        computer.setRam(6545654);
    }

    @Override
    public void buildHardDrive() {
        computer.setHardDrive("765456765 GB SSD");
    }

    @Override
    public void buildGraphicsCard() {
        computer.setGraphicsCard("ytrtyujytrtyj");
    }

    @Override
    public void buildOperatingSystem() {
        computer.setOperatingSystem("hjhgrerthjhtre");
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
