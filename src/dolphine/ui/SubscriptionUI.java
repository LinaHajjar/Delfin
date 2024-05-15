package dolphine.ui;

import dolphine.Member;
import dolphine.services.SubscriptionService;
import dolphine.util.UserInputUtil;

import java.util.ArrayList;

public class SubscriptionUI {
    public static void subscriptionMenu(){
        SubscriptionService subscriptionService = new SubscriptionService();
        int choice;
        do {
            System.out.println(" 1: Calculate subscription fee for user");
            System.out.println(" 2: Generate subscription report");
            System.out.println(" 3: Show members with unpaid subscription");
            System.out.println(" 0: exit program");
            choice = UserInputUtil.getIntInput("Enter the number from the list: ", "wrong input, choose a number between 0 and 3", 0, 3);
            // TODO Get members from member repository
            ArrayList<Member> members = new ArrayList<>();
            switch (choice) {
                    // TODO move cases to methods
                case 1:
                    // Calculates the current fee for the user
                    System.out.println("--Calculate subscription fee for user--");
                    // TODO Select user from UserUI
                    Member selectedMember = new Member();
                    System.out.printf("The subscription fee for the selected member is: %.2f\n",subscriptionService.calculateSubscriptionFee(selectedMember));
                    break;
                case 2:
                    // Calculates subscription fee at next season start and generates report
                    System.out.println("--Generate subscription report--");
                    System.out.printf(subscriptionService.generateSubscriptionReport(members));
                    break;
                case 3:
                    // Finds members with unpaid subscriptions and displays them
                    System.out.println("--Show members with unpaid subscription--");
                    ArrayList<Member> membersWithUnpaidSubscription = subscriptionService.getMembersWithUnpaidSubscription(members);
                    if (membersWithUnpaidSubscription.isEmpty()) {
                        System.out.println("There are no members with unpaid subscription");
                        break;
                    }

                    System.out.println("These members have not paid the subscription");
                    for (Member member : membersWithUnpaidSubscription) {
                        System.out.println(member); // TODO show the member name, membership status and amount owed
                    }
                    break;
                default:
                    break;

            }
        }while (choice !=0);

    }
}
