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
            System.out.println(" 0: exit program");
            choice = UserInputUtil.getIntInput("Enter the number from the list: ", "wrong input, choose a number between 0 and 4", 0, 4);

            switch (choice) {
                case 1:
                    System.out.println("you chose option 1");
                    break;
                case 2:
                    System.out.println("you chose option 2");
                    break;
                case 3:
                    System.out.println("you chose option 3");
                    break;
                case 4:
                    SubscriptionUI.subscriptionMenu();
                    break;

            }//end switch
        }while (choice !=0);

    }//end of HovedMenu

}
