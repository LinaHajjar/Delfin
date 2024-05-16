package dolphine.repository;

import dolphine.Subscription;

import java.io.*;
import java.util.ArrayList;

public class SubscriptionRepository {
    private static final String filePath = "src/dolphine/repository/subscriptionFile.txt";

    public static void createSubscription(Subscription subscription) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath, true));
            oos.writeObject(subscription); // Serialize each subscription object and write to the file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Subscription> getSubscriptionList() {
        ArrayList<Subscription> subscriptions = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            while (true) {
                try {
                    Subscription subscription = (Subscription) ois.readObject(); // Deserialize a Subscription object from the file
                    subscriptions.add(subscription); // Add the deserialized object to the ArrayList
                } catch (EOFException e) {
                    // End of file reached, break out of the loop
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return subscriptions;
    }

    // TODO Add update and delete subscriptions
}
