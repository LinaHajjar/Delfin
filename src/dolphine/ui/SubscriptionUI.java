package dolphine.ui;

import dolphine.Member;
import dolphine.Subscription;
import dolphine.repository.SubscriptionRepository;
import dolphine.services.SubscriptionService;
import dolphine.util.UserInputUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SubscriptionUI {
    public static void subscriptionMenu() {
        SubscriptionService subscriptionService = new SubscriptionService();
        int choice;
        do {
            System.out.println(" 1: Calculate subscription fee for user");
            System.out.println(" 2: Generate subscription report");
            System.out.println(" 3: Show members with unpaid subscription");
            System.out.println(" 4: Create subscription for member for rest of season");
            System.out.println(" 5: Create subscription for all members at next season start");
            choice = UserInputUtil.getIntInput("Enter the number from the list (0 to exit): ", "wrong input, choose a number between 0 and 4", 0, 4);
            switch (choice) {
                case 0:
                    System.out.println("Exiting...");
                    break;
                case 1:
                    calculateSubscriptionFee(subscriptionService);
                    break;
                case 2:
                    generateSubscriptionReport(subscriptionService);
                    break;
                case 3:
                    showMembersWithUnpaidSubscriptions(subscriptionService);
                    break;
                case 4:
                    saveAndCreateSubscriptionForMemberForRestOfSeason(subscriptionService);
                    break;
                case 5:
                    createSubscriptionForAllMembers(subscriptionService);
                    break;
                default:
                    break;

            }
        } while (choice != 0);

    }

    // Calculates the current fee for the user
    private static void calculateSubscriptionFee(SubscriptionService subscriptionService) {
        System.out.println("--Calculate subscription fee for user--");
        // TODO Select member from MemberUI
        //Member selectedMember = new Member();
        //System.out.printf("The subscription fee for the selected member is: %.2f\n",subscriptionService.calculateSubscriptionFee(selectedMember));
    }

    // Calculates subscription fee at next season start and generates report
    private static void generateSubscriptionReport(SubscriptionService subscriptionService) {
        // TODO Get members from member repository
        ArrayList<Member> members = new ArrayList<>();
        System.out.println("--Generate subscription report--");
        System.out.printf(subscriptionService.generateSubscriptionReport(members));
    }

    // Finds members with unpaid subscriptions and displays them
    private static void showMembersWithUnpaidSubscriptions(SubscriptionService subscriptionService) {
        System.out.println("--Show members with unpaid subscription--");

        ArrayList<Subscription> subscriptionList = SubscriptionRepository.getSubscriptionList();
        if (subscriptionList == null || subscriptionList.isEmpty()) {
            System.out.println("No subscriptions found, cannot show members with unpaid subscription");
            return;
        }

        ArrayList<Subscription> membersWithUnpaidSubscription = subscriptionService.getUnpaidSubscriptionList(subscriptionList);
        if (membersWithUnpaidSubscription.isEmpty()) {
            System.out.println("There are no members with unpaid subscription");
            return;
        }

        System.out.println("These members have not paid the subscription");
        for (Subscription subscription : membersWithUnpaidSubscription) {
            System.out.printf("%s has a unpaid subscription of %.2f that was due at: %s", subscription.getMember().getName(), subscription.getAmount(), subscription.getDueDate().toString());
        }
    }

    private static void saveAndCreateSubscriptionForMemberForRestOfSeason(SubscriptionService subscriptionService) {
        Subscription subscription = createSubscriptionForMemberForRestOfSeason(subscriptionService);
        SubscriptionRepository.createSubscription(subscription);
    }

    // Gets inputs from user to create a new subscription
    private static Subscription createSubscriptionForMemberForRestOfSeason(SubscriptionService subscriptionService) {
        System.out.println("--Create subscription--");
        // TODO add select member from memberUI
        Member selectedMember = new Member();
        double amount = subscriptionService.calculateSubscriptionFeeForRestOfSeason(selectedMember);
        System.out.println("Subscription fee calculated: " + amount);
        System.out.println("Enter due date for the subscription");
        LocalDate dueDate = UserInputUtil.getLocalDateInput(DateTimeFormatter.ofPattern("dd-MM-yyyy"), "dd-MM-yyyy");
        Subscription subscription = new Subscription(selectedMember, amount, dueDate, false);
        System.out.println("Subscription created: " + subscription);
        return subscription;
    }

    /**
     * Use this method to generate subscription for all members at next season start
     * @param subscriptionService service
     */
    private static void createSubscriptionForAllMembers(SubscriptionService subscriptionService) {
        System.out.println("--Create subscription for all members--");
        System.out.println("Warning! This will create a subscription for all members, with the price at next season start");
        System.out.println("Enter due date for the subscription (After next season start)");
        LocalDate dueDate = UserInputUtil.getLocalDateInput(DateTimeFormatter.ofPattern("dd-MM-yyyy"), "dd-MM-yyyy");
        ArrayList<Subscription> subscriptionList = new ArrayList<>();
        for (Member m : MemberRepository.getAllMembers()) {
            double amount = subscriptionService.calculateSubscriptionFeeAtNextSeason(m);
            subscriptionList.add(new Subscription(m, amount, dueDate, false));
        }
        SubscriptionRepository.saveSubscriptionList(subscriptionList);
        System.out.println("Subscriptions created");
    }

    private static void updateSubscription() {
        System.out.println("--Update subscription--");
        // TODO find subscription
        int choice;
        do {

            System.out.println("What do you want to update on the subscription?");
            System.out.println(" 1. Amount");
            System.out.println(" 2. Due date");
            System.out.println(" 3. Is payed");
            choice = UserInputUtil.getIntInput("Enter your choice (0 to exit): ", "wrong input, choose a number between 0 and 3", 0, 3);
            switch (choice) {
                case 0:
                    System.out.println("Exiting...");
                    break;
                case 1:
                    // TODO subscription = new amount an so on..
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Wrong choice. Try again.");
                    break;
            }
        } while (choice != 0);

        // TODO call sub repo and update
    }

//    private static Subscription findSubscription() {
//        System.out.println("--Find subscription--");
//        // TODO Find member and find subscriptions based of member
//        return;
//    }

    private static void deleteSubscription() {
        System.out.println("--Delete subscription--");
        // TODO find subscription
        // TODO call sub repo and delete
    }
}
