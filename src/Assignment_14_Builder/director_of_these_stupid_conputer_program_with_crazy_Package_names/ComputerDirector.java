package Assignment_14_Builder.director_of_these_stupid_conputer_program_with_crazy_Package_names;

import Assignment_14_Builder.builder_are_building_some_kind_of_stupid_computers_for_no_reason.ComputerBuilder;
import Assignment_14_Builder.model_not_ai_model_just_some_crazy_model_for_stupid_computer_program.Computer;

public class ComputerDirector {

    public Computer constructComputer(ComputerBuilder builder) {

        builder.buildProcessor();

        builder.buildRAM();

        builder.buildHardDrive();

        builder.buildGraphicsCard();

        builder.buildOperatingSystem();

        return builder.getComputer();
    }
}