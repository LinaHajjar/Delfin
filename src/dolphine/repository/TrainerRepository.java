package dolphine.repository;

import dolphine.Trainer;
import dolphine.User;

import java.util.ArrayList;

public class TrainerRepository {

    //TODO method to save a trainer to file
    public static void saveTrainerToUserList(Trainer newTrainer) { //TODO when userSave() is made
        try {
            ArrayList<User> userList = UserRepository.getUserList();
            userList.add(newTrainer);
            UserRepository.saveUserList(userList);
            System.out.println("Trainer saved to file");
        } catch (Exception e) {
            System.out.println("Unable to save Trainer to file");
            e.printStackTrace();
        }
    }
    //TODO check når userRepository er done
    public static void deleteTrainerFromUserList(Trainer trainer){
        try {
            ArrayList<User> userList = UserRepository.getUserList();
            userList.remove(trainer);
            UserRepository.saveUserList(userList);
            System.out.println("Trainer Succesfully deleted from file");
        } catch (Exception e) {
            System.out.println("Unable to delete trainer from file");
        }
    }
    //TODO check når userRepository er done, skal måske slettes da pretty redundant
    //Method takes an ArrayList<User> and saves to file when updating Trainer Objects
    public static void updateTrainerList(ArrayList<User> userList){
        try {
            ArrayList<User> userList = UserRepository.getUserList();
            UserRepository.saveUserList(userList);
        } catch (Exception e) {
            System.out.println("Was unable to save updated Trainer object");
            e.printStackTrace();
        }
    }


}
