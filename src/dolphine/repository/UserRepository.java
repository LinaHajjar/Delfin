package dolphine.repository;

import dolphine.User;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserRepository {

    private static final String filePath = "src/dolphine/repository/userFile.txt";

    public static void createUser(User user) { //write user to file
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath, true));
            oos.writeObject(user); // Serialize each Member object and write to the file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(User updatedUser) {
        ArrayList<User> userList = getUserList();
        for (User u : userList) {
            if (u.getId().equals(updatedUser.getId())) {
                u.setName(updatedUser.getName());
                u.setDateOfBirth(updatedUser.getDateOfBirth());
                u.setRole(updatedUser.getRole());
            }
        }
        saveUserList(userList);
    }

    public static void deleteUser(User userToDelete) {
        ArrayList<User> userList = getUserList();
        userList.removeIf(u -> u.getId().equals(userToDelete.getId()));
        saveUserList(userList);
    }

    public static ArrayList<User> getUserList() {
        ArrayList<User> userList = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            while (true) {
                try {
                    User user = (User) ois.readObject(); // Deserialize a Member object from the file
                    userList.add(user); // Add the deserialized object to the ArrayList
                } catch (EOFException e) {
                    // End of file reached, break out of the loop
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList; // Return the list of deserialized Member objects
    }

    public static void saveUserList(ArrayList<User> userList) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath, true));
            for (User u : userList) {
                oos.writeObject(u); // Serialize each Member object and write to the file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> getUserListByName(String name) {
        ArrayList<User> userList = getUserList();
        ArrayList<User> userListByName = new ArrayList<>();
        for (User u : userList) {
            if (u.getName().toLowerCase().contains(name.toLowerCase())) {
                userListByName.add(u);
            }
        }
        return userListByName;
    }

}
