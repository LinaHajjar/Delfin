package dolphine.ui;

import dolphine.Role;
import dolphine.User;
import dolphine.repository.UserRepository;
import dolphine.util.UserInputUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static dolphine.util.UserInputUtil.getLocalDateInput;

public class UserUI {
    public static void MenuUser() {

        int choice;
        do {
            System.out.println(" 1: Create new user.");
            System.out.println(" 2: Edit user");
            System.out.println(" 3: Delete user");
            choice = UserInputUtil.getIntInput("Enter the number from the list (0 to exit): ", "wrong input, choose a number between 0 and 3", 0, 3);

            switch (choice) {
                case 0:
                    System.out.println("You chose the option exit \nReturning to Main Menu");
                    break;
                case 1:
                    saveAndCreateUser();
                    break;
                case 2:
                    editAndSaveUser();
                    break;
                case 3:
                    deleteAndSaveUser();
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }// end switch
        } while (choice != 0);
    }//end MenuUser()

    public static void saveAndCreateUser() {
        User newUser = createUser();
        UserRepository.createUser(newUser);
        System.out.println("User created \n" + newUser);
    }

    public static User createUser() {
        String name = UserInputUtil.getStringInput("Name of the user: ");
        System.out.println("Enter date of birth");
        LocalDate dateOfBirth = getLocalDateInput(DateTimeFormatter.ofPattern("dd-MM-yyyy"), "dd-MM-yyyy");
        System.out.println("Select role of the user");
        Role role = UserInputUtil.selectObject(Role.values());

        return new User(name, dateOfBirth, role);
    }//end of createUser

    public static void editAndSaveUser() {
        System.out.println("--Edit user--");
        User userToEdit = findUserByName();
        if (userToEdit == null) {
            return;
        }

        editUser(userToEdit);
        UserRepository.updateUser(userToEdit);
    }

    public static void editUser(User user) {
        int choice;
        do {
            System.out.println(" 0: Exit.");
            System.out.println(" 1: Name");
            System.out.println(" 2: Date of birth");
            System.out.println(" 3: role");
            choice = UserInputUtil.getIntInput("which information do you want to edit?", "wrong input, choose a number between 0 and 3", 0, 3);
            switch (choice) {
                case 0:
                    break;
                case 1:
                    String name = UserInputUtil.getStringInput("What is the new name of the user?");
                    user.setName(name);
                    break;
                case 2:
                    System.out.println("what is the new date of birth?");
                    LocalDate dateOfBirth = getLocalDateInput();
                    user.setDateOfBirth(dateOfBirth);
                    break;
                case 3:
                    System.out.println("What is the new role of the user?");
                    Role role = UserInputUtil.selectObject(Role.values());
                    user.setRole(role);
                    break;
                default:
                    System.out.println("wrong input, try again");
                    break;
            }
        } while (choice != 0);
        System.out.println("User was edited");
    }//end of editUser

    public static void deleteAndSaveUser() {
        System.out.println("--Delete user--");
        User userToDelete = findUserByName();
        if (userToDelete != null) {
            UserRepository.deleteUser(userToDelete);
        }
    }

    public static User findUserByName() {
        boolean findingUser = false;
        ArrayList<User> userList;
        do {
            String name = UserInputUtil.getStringInput("Name of the user: ");
            userList = UserRepository.getUserListByName(name);
            if (userList.isEmpty()) {
                System.out.println("User not found");
                findingUser = UserInputUtil.getStringInput("Try again? y/n", "Please write y or n", new String[]{"y", "n"}).equals("y");
            }
        } while (findingUser);
        if (userList.isEmpty()) {
            return null;
        }
        System.out.println("Select user");
        return UserInputUtil.selectObject(userList);
    }

}//end of class UserUI
