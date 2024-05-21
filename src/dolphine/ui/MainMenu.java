package dolphine.ui;

import dolphine.util.UserInputUtil;

public class MainMenu {
    public static void HovedMenu(){
        int choice;
        do {
            System.out.println(" 1: User");
            System.out.println(" 2: Member");
            System.out.println(" 3: Trainer");
            System.out.println(" 4: Subscription");
            System.out.println(" 0: Close the program");
            choice = UserInputUtil.getIntInput("Enter the number from the list: ", "wrong input, choose a number between 0 and 4", 0, 4);

            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("You chose option 1");
                    UserUI.MenuUser();
                    break;
                case 2:
                    System.out.println("You chose option 2");
                    MemberUI.MenuMember();
                    break;
                case 3:
                    System.out.println("You chose option 3");
                    TrainerMenu.trainerSubMenu();
                    break;
                case 4:
                    System.out.println("You chose option 4");
                    break;
                default:
                System.out.println("Wrong input");
                break;

            }//end switch
        }while (choice !=0);

    }//end of HovedMenu

}
