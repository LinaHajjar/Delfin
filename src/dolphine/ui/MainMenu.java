package dolphine.ui;

import dolphine.util.UserInputUtil;

import static dolphine.ui.MemberUI.MemberMenu;

public class MainMenu {
    public static void HovedMenu(){
        int choice;
        do {
            System.out.println(" 1: User");
            System.out.println(" 2: Member");
            System.out.println(" 3: Trainer");
            System.out.println(" 4: Subscription");
            System.out.println(" 0: exit program");
            choice = UserInputUtil.getIntInput("Enter the number from the list: ", "wrong input, choose a number between 0 and 4", 0, 4);

            switch (choice) {
                case 1:
                    System.out.println("You chose option 1");
                    break;
                case 2:
                    System.out.println("You chose option 2");
                    MemberMenu();
                    break;
                case 3:
                    System.out.println("You chose option 3");
                    break;
                case 4:
                    System.out.println("You chose option 4");
                    break;

            }//end switch
        }while (choice !=0);

    }//end of HovedMenu
}
