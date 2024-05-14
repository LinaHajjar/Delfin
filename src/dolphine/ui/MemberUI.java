package dolphine.ui;
import dolphine.MemberType;
import dolphine.util.UserInputUtil;
import java.util.Scanner;
import static dolphine.ui.MainMenu.HovedMenu;

public class MemberUI {
    public static void MemberMenu(){
        int choice;
        do {
            System.out.println(" 1: Create New Member);
            System.out.println(" 2: Edit existing Member");
            System.out.println(" 3: Delete existing Member");
            System.out.println(" 0: Return to Main Menu");
            choice = UserInputUtil.getIntInput("Enter the number from the list: ", "wrong input, choose a number between 0 and 4", 0, 3);

            switch (choice) {
                case 1:
                    System.out.println("You chose option 1");
                    createMember();
                    break;
                case 2:
                    System.out.println("You chose option 2");
                    editMember();
                    break;
                case 3:
                    System.out.println("You chose option 3");
                    deleteMember();
                    break;

                case 0:
                    HovedMenu();
                    break;
            }//end switch
        }while (choice !=0);
    }//end of MemberMenu
    public static void createMember() {
        Scanner input = new Scanner(System.in);
        UserUI.createUser();
        /*System.out.println("Enter name: ");
        String name = input.nextLine();

        System.out.println("Enter age: ");
        int age = input.nextInt();
        input.nextLine(); */

        //getMemberType from MemberType enum
        if (age > 18) {
            MemberType.JUNIOR;
        } else if (age <= 18 && age >= 60) {
            MemberType.SENIOR;
        } else {
            (age < 60) {
                MemberType.SENIORDISCOUNT;
            }

            System.out.println( boolean MemberisActive +"Is member active?");
            if (MemberisActive = true);

            //enter new member into an arraylist in repository
            //print member information to a new file
        }
    } //end of createMember

        public static void editMember () {
            //method to print arraylist to console
            //method in MemberRepository to get members by searching for their name
        }

        public static void deleteMember () {
            //call method to print arraylist to console
            //call method to search for members name to delete chosen member
        }
    }
