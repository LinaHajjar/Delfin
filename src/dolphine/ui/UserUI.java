package dolphine.ui;

import dolphine.Role;
import dolphine.User;
import dolphine.repository.UserRepository;
import dolphine.util.UserInputUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static dolphine.util.UserInputUtil.getLocalDateInput;

public class UserUI {
    public static void MenuUser() {

        int choice;
        do {
            System.out.println(" 0: Exit.");
            System.out.println(" 1: Create new user.");
            System.out.println(" 2: Edit user");
            System.out.println(" 3: Delete user");
            choice = UserInputUtil.getIntInput("Enter the number from the list: ", "wrong input, choose a number between 0 and 3", 0, 3);

            switch (choice) {
                case 0:
                    System.out.println("You chose the option exit \nReturning to Main Menu");
                    MainMenu.HovedMenu();
                    break;
                case 1:
                    System.out.println("You chose option: create a new user.");
                    createUser();
                    break;
                case 2:
                    System.out.println("You chose option: edit a user.");
                    String idEdit = UserInputUtil.getStringInput("What is the ID of the user you want to edit?");
                    ArrayList<User> users = new ArrayList<>();
                    User editUser = null;
                    for (User user : users) {
                        if (user.getId().equals(idEdit)) {
                            editUser = user;
                            break; // Exit the loop once user is found
                        }
                    }

                    if (editUser != null) {
                        System.out.println("this is the name of the user you want to edit: " + editUser .getName());
                    } else {
                        System.out.println("User with ID: " + idEdit + " is not found.");
                    }

                    editUser(editUser);
                    break;
                case 3:
                    System.out.println("You chose option: delete a user.");
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }// end switch
        } while (choice != 0);
    }//end MenuUser()

    public static void saveAndCreateUser(){
        User newUser = createUser();
        UserRepository.createUser(newUser);
    }

    public static User createUser() {
        String name = UserInputUtil.getStringInput("What is the name of the user?");

        LocalDate dateOfBirth = getLocalDateInput();
        //System.out.println(dateOfBirth);

        Role role = UserInputUtil.selectObject(Role.values()); // public static <T> T selectObject(T[] objects)
        /*Returns an array containing the constants of this enum type, in the order they're declared. This method may be used to iterate over the constants as follows:
             for(Role c : Role.values())
                System.out.println(c);*/

        User user= new User(name, dateOfBirth,role);
        return user;

    }//end of createUser

    public static void editUser(User user){
        Scanner scan =new Scanner(System.in);
        
        int choice= UserInputUtil.getIntInput("which information do you want to edit?", "wrong input, choose a number between 0 and 3", 0, 3);
        System.out.println(" 0: Exit.");
        System.out.println(" 1: Name");
        System.out.println(" 2: Date of birth");
        System.out.println(" 3: role");
        switch (choice){
            case 0:
                System.out.println("You chose the option exit \nReturning to Main Menu");
                MainMenu.HovedMenu();
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
                editUser(user);
                break;
        }
        UserRepository.updateUser(user);

    }//end of editUser

}//end of class UserUI
