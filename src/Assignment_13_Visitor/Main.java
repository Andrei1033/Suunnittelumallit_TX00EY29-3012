package Assignment_13_Visitor;

import Assignment_13_Visitor.modelllllllllllllllllllllllllll.Directory;
import Assignment_13_Visitor.modelllllllllllllllllllllllllll.File;
import Assignment_13_Visitor.visitorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr.SearchVisitor;
import Assignment_13_Visitor.visitorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr.SizeCalculatorVisitor;

public class Main {

    public static void main(String[] args) {

        // Create files
        File file1 = new File("document.pdf", 5);
        File file2 = new File("photo.jpg", 3);
        File file3 = new File("Main.java", 2);
        File file4 = new File("notes.txt", 1);
        File file5 = new File("Helper.java", 4);

        // Create directories
        Directory root = new Directory("MyComputer");
        Directory projects = new Directory("Projects");

        // Add files to root directory
        root.addElement(file1);
        root.addElement(file2);

        // Add files to Projects directory
        projects.addElement(file3);
        projects.addElement(file4);
        projects.addElement(file5);

        // Add Projects directory to root
        root.addElement(projects);

        // Apply SizeCalculatorVisitor
        SizeCalculatorVisitor sizeVisitor =
                new SizeCalculatorVisitor();

        root.accept(sizeVisitor);

        System.out.println("Total size: "
                + sizeVisitor.getTotalSize() + " MB");

        // Apply SearchVisitor
        SearchVisitor searchVisitor =
                new SearchVisitor(".java");

        root.accept(searchVisitor);

        System.out.println("\nJava files found:");

        for (File file : searchVisitor.getMatchingFiles()) {
            System.out.println(file.getName());
        }
    }
}
