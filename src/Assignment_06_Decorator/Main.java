package Assignment_06_Decorator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("enter a message: ");
        String message = scanner.nextLine();

        Printer basicPrinter = new BasicPrinter();
        Printer xmlPrinter = new XMLPrinter(basicPrinter);
        Printer encryptedPrinter = new EncryptedPrinter(xmlPrinter);

        System.out.println("Basic Printer Output:");
        basicPrinter.print(message);

        System.out.println("\nXML Printer Output:");
        xmlPrinter.print(message);

        System.out.println("\nEncrypted + XML Printer Output:");
        encryptedPrinter.print(message);
    }
}
