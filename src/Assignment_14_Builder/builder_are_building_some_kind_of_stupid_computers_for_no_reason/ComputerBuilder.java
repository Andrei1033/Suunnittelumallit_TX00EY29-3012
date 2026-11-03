package Assignment_14_Builder.builder_are_building_some_kind_of_stupid_computers_for_no_reason;

import Assignment_14_Builder.model_not_ai_model_just_some_crazy_model_for_stupid_computer_program.Computer;

public interface ComputerBuilder {

    void buildProcessor();

    void buildRAM();

    void buildHardDrive();

    void buildGraphicsCard();

    void buildOperatingSystem();

    void reset();

    Computer getComputer();
}
