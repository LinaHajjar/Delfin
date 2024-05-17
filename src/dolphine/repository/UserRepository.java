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
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
            ArrayList<User> loadedUsers = (ArrayList<User>) objectInputStream.readObject();
            System.out.println("Users loaded from userFile.txt:"); //TODO slettes når vi er sikrer på virker
            return loadedUsers;
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Couldn't load Users from userFile.txt"); //TODO slettes bare når vi er sikre på virker
            return null;
        }
    }

    public static void saveUserList(ArrayList<User> userList) {
        try  {
            ObjectOutputStream outPutStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outPutStream.writeObject(userList);
            System.out.println("User's saved to userFile.txt"); //TODO slet når det virker
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
