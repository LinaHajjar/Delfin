package dolphine.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserInputUtil {
    //<editor-fold desc="UserInputUtil">

    /**
     * Get an integer input from the user.
     *
     * @param promptMessage The message prompt displayed to the user.
     * @return The integer input by the user.
     */
    public static int getIntInput(String promptMessage) {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        boolean validInput = false;

        do {
            try {
                System.out.print(promptMessage);
                userInput = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer.");
            }
        } while (!validInput);

        return userInput;
    }

    /**
     * Get an integer input from the user with a custom error message.
     *
     * @param promptMessage The message prompt displayed to the user.
     * @param errorMessage  The error message displayed if the input is invalid.
     * @return The integer input by the user.
     */
    public static int getIntInput(String promptMessage, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        boolean validInput = false;

        do {
            try {
                System.out.print(promptMessage);
                userInput = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        } while (!validInput);

        return userInput;
    }

    /**
     * Get an integer input from the user within specified conditions.
     *
     * @param promptMessage The message prompt displayed to the user.
     * @param errorMessage  The error message displayed if the input is invalid.
     * @param conditions    The array of integer conditions.
     * @return The integer input by the user.
     */
    public static int getIntInput(String promptMessage, String errorMessage, int[] conditions) {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        boolean validInput = false;

        do {
            try {
                System.out.print(promptMessage);
                userInput = Integer.parseInt(scanner.nextLine());
                validInput = true;
                if (!contains(conditions, userInput)) {
                    System.out.println(errorMessage);
                    validInput = false;
                }
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        } while (!validInput);

        return userInput;
    }

    /**
     * Get an integer input from the user within a specified range.
     * Ignores range if maxNum is smaller than minNum
     *
     * @param promptMessage The message prompt displayed to the user.
     * @param errorMessage  The error message displayed if the input is invalid.
     * @param minNum        The minimum value of the range.
     * @param maxNum        The maximum value of the range.
     * @return The integer input by the user. Ignores range if minNum > maxNum
     */
    public static int getIntInput(String promptMessage, String errorMessage, int minNum, int maxNum) {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        boolean validInput = false;

        do {
            try {
                System.out.print(promptMessage);
                userInput = Integer.parseInt(scanner.nextLine());
                validInput = true;
                if (minNum < maxNum && (userInput > maxNum || userInput < minNum)) {
                    System.out.println(errorMessage);
                    validInput = false;
                }
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        } while (!validInput);

        return userInput;
    }

    /**
     * Get a long integer input from the user.
     *
     * @param promptMessage The message prompt displayed to the user.
     * @return The long integer input by the user.
     */
    public static long getLongInput(String promptMessage) {
        Scanner scanner = new Scanner(System.in);
        long userInput = 0;
        boolean validInput = false;

        do {
            try {
                System.out.print(promptMessage);
                userInput = Long.parseLong(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a long integer.");
            }
        } while (!validInput);

        return userInput;
    }

    /**
     * Get a long integer input from the user with a custom error message.
     *
     * @param promptMessage The message prompt displayed to the user.
     * @param errorMessage  The error message displayed if the input is invalid.
     * @return The long integer input by the user.
     */
    public static long getLongInput(String promptMessage, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        long userInput = 0;
        boolean validInput = false;

        do {
            try {
                System.out.print(promptMessage);
                userInput = Long.parseLong(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        } while (!validInput);

        return userInput;
    }

    /**
     * Get a long integer input from the user within specified conditions.
     *
     * @param promptMessage The message prompt displayed to the user.
     * @param errorMessage  The error message displayed if the input is invalid.
     * @param conditions    The array of long conditions.
     * @return The long integer input by the user.
     */
    public static long getLongInput(String promptMessage, String errorMessage, long[] conditions) {
        Scanner scanner = new Scanner(System.in);
        long userInput = 0;
        boolean validInput = false;

        do {
            try {
                System.out.print(promptMessage);
                userInput = Long.parseLong(scanner.nextLine());
                validInput = true;
                if (!contains(conditions, userInput)) {
                    System.out.println(errorMessage);
                    validInput = false;
                }
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        } while (!validInput);

        return userInput;
    }

    private static boolean contains(int[] arr, int targetValue) {
        for (int num : arr) {
            if (num == targetValue) {
                return true;
            }
        }
        return false;
    }

    private static boolean contains(long[] arr, long value) {
        for (long num : arr) {
            if (num == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get a double input from the user.
     *
     * @param promptMessage The message prompt displayed to the user.
     * @return The double input by the user.
     */
    public static double getDoubleInput(String promptMessage) {
        Scanner scanner = new Scanner(System.in);
        double userInput = 0.0;
        boolean validInput = false;

        do {
            try {
                System.out.print(promptMessage);
                userInput = Double.parseDouble(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        } while (!validInput);

        return userInput;
    }

    /**
     * Get a double input from the user with a custom error message.
     *
     * @param promptMessage The message prompt displayed to the user.
     * @param errorMessage  The error message displayed if the input is invalid.
     * @return The double input by the user.
     */
    public static double getDoubleInput(String promptMessage, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        double userInput = 0.0;
        boolean validInput = false;

        do {
            try {
                System.out.print(promptMessage);
                userInput = Double.parseDouble(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        } while (!validInput);

        return userInput;
    }

    /**
     * Get a string input from the user.
     *
     * @param promptMessage The message prompt displayed to the user.
     * @return The string input by the user.
     */
    public static String getStringInput(String promptMessage) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        boolean validInput = false;

        do {
            System.out.print(promptMessage);
            userInput = scanner.nextLine();
            if (!userInput.trim().isEmpty()) {
                validInput = true;
            } else {
                System.out.println("Invalid input! Please enter a non-empty string.");
            }
        } while (!validInput);

        return userInput;
    }

    /**
     * Get a string input from the user with a custom error message.
     *
     * @param promptMessage The message prompt displayed to the user.
     * @param errorMessage  The error message displayed if the input is invalid.
     * @return The string input by the user.
     */
    public static String getStringInput(String promptMessage, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        boolean validInput = false;

        do {
            System.out.print(promptMessage);
            userInput = scanner.nextLine();
            if (!userInput.trim().isEmpty()) {
                validInput = true;
            } else {
                System.out.println(errorMessage);
            }
        } while (!validInput);

        return userInput;
    }

    /**
     * Get a string input from the user within specified conditions.
     *
     * @param promptMessage The message prompt displayed to the user.
     * @param errorMessage  The error message displayed if the input is invalid.
     * @param conditions    The array of string conditions.
     * @return The string input by the user.
     */
    public static String getStringInput(String promptMessage, String errorMessage, String[] conditions) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        boolean validInput = false;

        do {
            System.out.printf(promptMessage);
            userInput = scanner.nextLine();
            boolean meetsConditions = false;
            for (String condition : conditions) {
                if (condition.equalsIgnoreCase(userInput)) {
                    meetsConditions = true;
                    break;
                }
            }
            if (!userInput.trim().isEmpty() && meetsConditions) {
                validInput = true;
            } else {
                System.out.printf(errorMessage + "\n");
            }
        } while (!validInput);


        return userInput;
    }

    /**
     * Get a string input from the user matching a regular expression pattern.
     *
     * @param promptMessage The message prompt displayed to the user.
     * @param errorMessage  The error message displayed if the input does not match the pattern.
     * @param regexPattern  The regular expression pattern to match.
     * @return The string input by the user.
     */
    public static String getStringInput(String promptMessage, String errorMessage, Pattern regexPattern) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        boolean validInput = false;

        do {
            System.out.printf(promptMessage);
            userInput = scanner.nextLine();

            if (!userInput.trim().isEmpty() && regexPattern.matcher(userInput).matches()) {
                validInput = true;
            } else {
                System.out.printf(errorMessage + "\n");
            }
        } while (!validInput);


        return userInput;
    }

    /**
     * Select an object from a list of objects.
     *
     * @param objects The list of objects to choose from.
     * @param <T>     The type of objects in the list.
     * @return The selected object.
     */
    public static <T> T selectObject(List<T> objects) {
        if (objects.isEmpty()) {
            return null;

        }
        // Print the list of objects with numbers
        for (int i = 0; i < objects.size(); i++) {
            System.out.println("--------------------------------------");
            System.out.println((i + 1) + ". " + objects.get(i).toString());
        }

        // Prompt the user to select an object
        int choice = getIntInput("Enter the number of your choice: ", "Invalid choice", 1, objects.size());

        // Return the selected object
        return objects.get(choice - 1);
    }

    /**
     * Select an object from an array of objects.
     *
     * @param objects The array of objects to choose from.
     * @param <T>     The type of objects in the array.
     * @return The selected object.
     */
    public static <T> T selectObject(T[] objects) {
        // Print the list of objects with numbers
        for (int i = 0; i < objects.length; i++) {
            System.out.println((i + 1) + ". " + objects[i].toString());
        }

        // Prompt the user to select an object
        int choice = getIntInput("Enter the number of your choice: ", "Invalid choice", 1, objects.length);

        // Return the selected object
        return objects[choice - 1];
    }

    /**
     * Get a LocalDate input from the user.
     *
     * @return The LocalDate input by the user.
     */
    public static LocalDate getLocalDateInput() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = null;
        do {
            String inputDate = getStringInput("Enter a date (yyyy-MM-dd): ");
            try {
                date = LocalDate.parse(inputDate, dateFormatter);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
            }
        } while (date == null);
        return date;
    }

    /**
     * Get a LocalDate input from the user with a custom date formatter.
     *
     * @param dateFormatter The date formatter for parsing the input.
     * @return The LocalDate input by the user.
     */
    public static LocalDate getLocalDateInput(DateTimeFormatter dateFormatter, String formatString) {
        LocalDate date = null;
        do {
            String inputDate = getStringInput("Enter a date (" + formatString + "): ");
            try {
                date = LocalDate.parse(inputDate, dateFormatter);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter date in " + formatString + " format.");
            }
        } while (date == null);
        return date;
    }
    //</editor-fold>
}
