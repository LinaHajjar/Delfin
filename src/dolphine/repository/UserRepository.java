package dolphine.repository;

import dolphine.User;

import java.io.*;
import java.util.ArrayList;

public class UserRepository {

    private static final String filePath = "src/dolphine/repository/userFile.ser";

    public static void createUser(User user) { //write user to file
        ArrayList<User> userList = getUserList();
        userList.add(user);
        saveUserList(userList);
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
            File file = new File(filePath);

            // Check if the file is empty
            if (file.length() == 0) {
                return new ArrayList<>();
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<User> loadedUsers = (ArrayList<User>) objectInputStream.readObject();
            return loadedUsers;
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void saveUserList(ArrayList<User> userList) {
        try  {
            ObjectOutputStream outPutStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outPutStream.writeObject(userList);
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
