package Assignment_15_Adapter;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        DateInterface date = new CalendarToNewDateAdapter();

        boolean running = true;

        System.out.println("================================");
        System.out.println("     CALENDAR ADAPTER PROGRAM");
        System.out.println("================================");

        while (running) {

            System.out.println("\nChoose an option:");
            System.out.println("1. Set a new date");
            System.out.println("2. Display current date");
            System.out.println("3. Advance days");
            System.out.println("4. Change day");
            System.out.println("5. Change month");
            System.out.println("6. Change year");
            System.out.println("0. Exit");

            System.out.print("\nYour choice: ");

            int choice;

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.next();
                continue;
            }

            choice = scanner.nextInt();

            try {

                switch (choice) {

                    case 1:

                        System.out.print("Enter day: ");
                        int day = scanner.nextInt();

                        System.out.print("Enter month (1-12): ");
                        int month = scanner.nextInt();

                        System.out.print("Enter year: ");
                        int year = scanner.nextInt();

                        LocalDate newDate =
                                LocalDate.of(year, month, day);

                        date.setYear(newDate.getYear());
                        date.setMonth(newDate.getMonthValue());
                        date.setDay(newDate.getDayOfMonth());

                        System.out.println("Date successfully updated!");

                        break;

                    case 2:

                        System.out.println(
                                "Current date: "
                                        + date.getDay() + "/"
                                        + date.getMonth() + "/"
                                        + date.getYear()
                        );

                        break;

                    case 3:

                        System.out.print(
                                "How many days do you want to advance? "
                        );

                        int days = scanner.nextInt();

                        if (days < 0) {
                            System.out.println(
                                    "Please enter a non-negative number."
                            );
                            break;
                        }

                        date.advanceDays(days);

                        System.out.println("Date advanced successfully!");

                        break;

                    case 4:

                        System.out.print("Enter new day: ");
                        int newDay = scanner.nextInt();

                        LocalDate updatedDay = LocalDate.of(
                                date.getYear(),
                                date.getMonth(),
                                newDay
                        );

                        date.setDay(updatedDay.getDayOfMonth());

                        System.out.println("Day updated!");

                        break;

                    case 5:

                        System.out.print("Enter new month (1-12): ");
                        int newMonth = scanner.nextInt();

                        LocalDate updatedMonth = LocalDate.of(
                                date.getYear(),
                                newMonth,
                                date.getDay()
                        );

                        date.setMonth(updatedMonth.getMonthValue());

                        System.out.println("Month updated!");

                        break;

                    case 6:

                        System.out.print("Enter new year: ");
                        int newYear = scanner.nextInt();

                        LocalDate updatedYear = LocalDate.of(
                                newYear,
                                date.getMonth(),
                                date.getDay()
                        );

                        date.setYear(updatedYear.getYear());

                        System.out.println("Year updated!");

                        break;

                    case 0:

                        running = false;

                        System.out.println(
                                "Thank you for using Calendar Adapter!"
                        );

                        break;

                    default:

                        System.out.println(
                                "Invalid option. Please try again."
                        );
                }

            } catch (DateTimeException e) {

                System.out.println(
                        "Invalid date! Please enter a valid calendar date."
                );

            } catch (java.util.InputMismatchException e) {

                System.out.println(
                        "Invalid input! Please enter numbers only."
                );

                scanner.nextLine();
            }
        }

        scanner.close();
    }
}