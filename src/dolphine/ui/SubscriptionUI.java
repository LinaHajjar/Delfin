package dolphine.ui;

import dolphine.Member;
import dolphine.Subscription;
import dolphine.repository.MemberRepository;
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
            System.out.println(" 6: Delete subscription");
            System.out.println(" 7: Update subscription");
            System.out.println(" 8: Show all subscriptions");
            choice = UserInputUtil.getIntInput("Enter the number from the list (0 to exit): ", "wrong input, choose a number between 0 and 8", 0, 8);
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
                case 6:
                    deleteSubscription();
                    break;
                case 7:
                    updateSubscription();
                    break;
                case 8:
                    showAllSubscriptions();
                    break;
                default:
                    break;

            }
        } while (choice != 0);

    }

    private static void showAllSubscriptions() {
        System.out.println("--Show all subscriptions--");
        ArrayList<Subscription> subscriptionList = SubscriptionRepository.getSubscriptionList();
        if (subscriptionList == null || subscriptionList.isEmpty()) {
            System.out.println("No subscriptions");
            return;
        }

        for (Subscription subscription : subscriptionList) {
            System.out.println(subscription);
        }
    }

    // Calculates the current fee for the user
    private static void calculateSubscriptionFee(SubscriptionService subscriptionService) {
        System.out.println("--Calculate subscription fee for user--");
        Member selectedMember = MemberUI.findMemberByName();
        if (selectedMember == null) {
            System.out.println("Member not found");
            return;
        }
        System.out.printf("The subscription fee for the selected member is: %.2f DKK\n", subscriptionService.calculateSubscriptionFee(selectedMember));
    }

    // Calculates subscription fee at next season start and generates report
    private static void generateSubscriptionReport(SubscriptionService subscriptionService) {
        ArrayList<Member> members = MemberRepository.getMemberList();
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
            System.out.printf("%s has a unpaid subscription of %.2f DKK that was due at: %s\n", subscription.getMember().getName(), subscription.getAmount(), subscription.getDueDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }
    }

    private static void saveAndCreateSubscriptionForMemberForRestOfSeason(SubscriptionService subscriptionService) {
        Subscription subscription = createSubscriptionForMemberForRestOfSeason(subscriptionService);
        if (subscription == null) {
            return;
        }
        SubscriptionRepository.createSubscription(subscription);
    }

    // Gets inputs from user to create a new subscription
    private static Subscription createSubscriptionForMemberForRestOfSeason(SubscriptionService subscriptionService) {
        System.out.println("--Create subscription--");
        Member selectedMember = MemberUI.findMemberByName();
        if (selectedMember == null) {
            System.out.println("Member not found");
            System.out.println("Subscription not created");
            return null;
        }
        double amount = subscriptionService.calculateSubscriptionFeeForRestOfSeason(selectedMember);
        System.out.printf("Subscription fee calculated for rest of season: %.2f DKK\n", amount);
        System.out.println("Enter due date for the subscription");
        LocalDate dueDate = UserInputUtil.getLocalDateInput(DateTimeFormatter.ofPattern("dd-MM-yyyy"), "dd-MM-yyyy");
        Subscription subscription = new Subscription(selectedMember, amount, dueDate, false);
        System.out.println("Subscription created: " + subscription);
        return subscription;
    }

    /**
     * Use this method to generate subscription for all members at next season starts
     *
     * @param subscriptionService service
     */
    private static void createSubscriptionForAllMembers(SubscriptionService subscriptionService) {
        System.out.println("--Create subscription for all members--");
        System.out.println("Warning! This will create a subscription for all members, with the price at next season start");
        System.out.printf("Enter due date for the subscription (After next season start %s)\n", subscriptionService.getNextSeasonStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        LocalDate dueDate = UserInputUtil.getLocalDateInput(DateTimeFormatter.ofPattern("dd-MM-yyyy"), "dd-MM-yyyy");
        ArrayList<Subscription> subscriptionList = new ArrayList<>();
        for (Member m : MemberRepository.getMemberList()) {
            double amount = subscriptionService.calculateSubscriptionFeeAtNextSeason(m);
            subscriptionList.add(new Subscription(m, amount, dueDate, false));
        }
        SubscriptionRepository.saveSubscriptionList(subscriptionList);
        System.out.println("Subscriptions created");
    }

    private static void updateSubscription() {
        System.out.println("--Update subscription--");
        System.out.println("Select subscription to update");
        Subscription subscriptionToUpdate = findSubscription();
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
                    double newAmount = UserInputUtil.getDoubleInput("Enter the new amount (DKK): ");
                    subscriptionToUpdate.setAmount(newAmount);
                    break;
                case 2:
                    System.out.println("Enter new due date");
                    LocalDate newDueDate = UserInputUtil.getLocalDateInput(DateTimeFormatter.ofPattern("dd-MM-yyyy"), "dd-MM-yyyy");
                    subscriptionToUpdate.setDueDate(newDueDate);
                    break;
                case 3:
                    boolean payed = UserInputUtil.getStringInput("Is the subscription payed? y/n: ", "Please type y/n", new String[]{"y", "n"}).equals("y");
                    subscriptionToUpdate.setPayed(payed);
                    break;
                default:
                    System.out.println("Wrong choice. Try again.");
                    break;
            }
        } while (choice != 0);

        SubscriptionRepository.updateSubscription(subscriptionToUpdate);
        System.out.println("Subscription updated.");
    }

    private static Subscription findSubscription() {
        Member member = MemberUI.findMemberByName();
        ArrayList<Subscription> subscriptionList = SubscriptionRepository.getSubscriptionListFromMember(member);
        return UserInputUtil.selectObject(subscriptionList);
    }

    private static void deleteSubscription() {
        System.out.println("--Delete subscription--");
        System.out.println("Select subscription to delete");
        Subscription subscriptionToDelete = findSubscription();
        SubscriptionRepository.deleteSubscription(subscriptionToDelete);
        System.out.println("Subscription deleted.");
    }
}
