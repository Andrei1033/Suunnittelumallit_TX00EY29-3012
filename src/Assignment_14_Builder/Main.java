package Assignment_14_Builder;

import Assignment_14_Builder.builder_are_building_some_kind_of_stupid_computers_for_no_reason.CrazyComputer;
import Assignment_14_Builder.builder_are_building_some_kind_of_stupid_computers_for_no_reason.GamingComputerBuilder;
import Assignment_14_Builder.builder_are_building_some_kind_of_stupid_computers_for_no_reason.OfficeComputerBuilder;
import Assignment_14_Builder.director_of_these_stupid_conputer_program_with_crazy_Package_names.ComputerDirector;
import Assignment_14_Builder.model_not_ai_model_just_some_crazy_model_for_stupid_computer_program.Computer;

public class Main {
    public static void main(String[] args) {

        ComputerDirector director = new ComputerDirector();

        // Build Gaming Computer
        GamingComputerBuilder gamingBuilder = new GamingComputerBuilder();

        Computer gamingComputer = director.constructComputer(gamingBuilder);

        // Build Office Computer
        OfficeComputerBuilder officeBuilder = new OfficeComputerBuilder();

        Computer officeComputer = director.constructComputer(officeBuilder);

        // Build Crazy Computer
        CrazyComputer crazyBuilder = new CrazyComputer();
        Computer crazyComputer = director.constructComputer(crazyBuilder);

        // Display results
        System.out.println("===== GAMING COMPUTER =====");
        System.out.println(gamingComputer);

        System.out.println();

        System.out.println("===== OFFICE COMPUTER =====");
        System.out.println(officeComputer);

        System.out.println();

        System.out.println("===== CRAZY COMPUTER =====");
        System.out.println(crazyComputer);
    }
}
