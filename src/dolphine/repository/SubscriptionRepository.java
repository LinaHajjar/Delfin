package dolphine.repository;

import dolphine.Member;
import dolphine.Subscription;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class SubscriptionRepository {
    private static final String filePath = "src/dolphine/repository/subscriptionFile.txt";

    public static void createSubscription(Subscription subscription) {
        ArrayList<Subscription> subscriptionList = getSubscriptionList();
        if (subscriptionList == null) {
            return;
        }
        subscriptionList.add(subscription);
        overrideSubscriptionList(subscriptionList);
    }

    public static void saveSubscriptionList(ArrayList<Subscription> subscriptionList) {
        ArrayList<Subscription> subscriptionListFromFile = getSubscriptionList();
        if (subscriptionListFromFile == null) {
            return;
        }
        subscriptionListFromFile.addAll(subscriptionList);
        overrideSubscriptionList(subscriptionListFromFile);
    }

    public static ArrayList<Subscription> getSubscriptionList() {
        try {
            File file = new File(filePath);

            // Check if the file is empty
            if (file.length() == 0) {
                return new ArrayList<>();
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Subscription> loadedSubscriptions = (ArrayList<Subscription>) objectInputStream.readObject();
            return loadedSubscriptions;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void overrideSubscriptionList(ArrayList<Subscription> subscriptionList) {
        try  {
            ObjectOutputStream outPutStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outPutStream.writeObject(subscriptionList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateSubscription(Subscription subscription) {
        ArrayList<Subscription> subscriptionList = getSubscriptionList();
        if (subscriptionList == null) {
            return;
        }
        // Find matching subscription and update all fields to make sure they are the same.
        for (Subscription sub : subscriptionList) {
            if (Objects.equals(sub.getId(), subscription.getId())) {
                sub.setMember(subscription.getMember());
                sub.setAmount(subscription.getAmount());
                sub.setPayed(subscription.isPayed());
                sub.setDueDate(subscription.getDueDate());
            }
        }
        overrideSubscriptionList(subscriptionList);
    }

    public static void deleteSubscription(Subscription subscription) {
        ArrayList<Subscription> subscriptionList = getSubscriptionList();
        if (subscriptionList == null) {
            return;
        }
        subscriptionList.removeIf(sub -> Objects.equals(sub.getId(), subscription.getId()));
        overrideSubscriptionList(subscriptionList);
    }

    public static ArrayList<Subscription> getSubscriptionListFromMember(Member member) {
        ArrayList<Subscription> subscriptionList = getSubscriptionList();
        if (subscriptionList == null) {
            return new ArrayList<>();
        }
        ArrayList<Subscription> subscriptionListMatchingMember = new ArrayList<>();
        for (Subscription sub : subscriptionList) {
            if (Objects.equals(sub.getMember().getId(), member.getId())) {
                subscriptionListMatchingMember.add(sub);
            }
        }
        return subscriptionListMatchingMember;
    }
}
