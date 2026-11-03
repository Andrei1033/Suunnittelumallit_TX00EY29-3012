package Assignment_14_Builder.model_not_ai_model_just_some_crazy_model_for_stupid_computer_program;

public class Computer {

    private String processor;
    private int ram;
    private String hardDrive;
    private String graphicsCard;
    private String operatingSystem;

    public Computer() {
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setHardDrive(String hardDrive) {
        this.hardDrive = hardDrive;
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    @Override
    public String toString() {
        return "Computer Configuration:\n" +
                "Processor: " + processor + "\n" +
                "RAM: " + ram + " GB\n" +
                "Hard Drive: " + hardDrive + "\n" +
                "Graphics Card: " + graphicsCard + "\n" +
                "Operating System: " + operatingSystem;
    }
}
