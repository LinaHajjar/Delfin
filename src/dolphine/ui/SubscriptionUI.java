package dolphine.ui;

import dolphine.Member;
import dolphine.Subscription;
import dolphine.repository.SubscriptionRepository;
import dolphine.services.SubscriptionService;
import dolphine.util.UserInputUtil;

import java.text.DateFormat;
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
            System.out.println(" 4: Create subscription");
            System.out.println(" 0: exit program");
            choice = UserInputUtil.getIntInput("Enter the number from the list: ", "wrong input, choose a number between 0 and 4", 0, 4);
            switch (choice) {
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
                    saveAndCreateSubscription(subscriptionService);
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
        ArrayList<Subscription> subscriptionList = new ArrayList<>();
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

    private static void saveAndCreateSubscription(SubscriptionService subscriptionService) {
        Subscription subscription = createSubscription(subscriptionService);
        SubscriptionRepository.createSubscription(subscription);
    }

    // Gets inputs from user to create a new subscription
    private static Subscription createSubscription(SubscriptionService subscriptionService) {
        System.out.println("--Create subscription--");
        // TODO add select member from memberUI
        Member selectedMember = new Member();
        double amount = subscriptionService.calculateSubscriptionFee(selectedMember);
        System.out.println("Subscription fee calculated: " + amount);
        System.out.println("Enter due date for the subscription");
        LocalDate dueDate = UserInputUtil.getLocalDateInput(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Subscription subscription = new Subscription(selectedMember, amount, dueDate, false);
        System.out.println("Subscription created: " + subscription);
        return subscription;
    }

    // TODO add update and delete subscriptions
}
