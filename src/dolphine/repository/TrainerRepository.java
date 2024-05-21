package dolphine.repository;

import dolphine.Trainer;
import dolphine.User;

import java.util.ArrayList;

public class TrainerRepository {

    public static void saveTrainerToUserList(Trainer newTrainer) {
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
    //method to take Trainer and delete from userFile.ser
    public static void deleteTrainerFromUserList(Trainer trainer){
        try {
            ArrayList<User> userList = UserRepository.getUserList();
            String id = trainer.getId();
            for(User user : userList){
                if(id.equalsIgnoreCase(user.getId())){
                    userList.remove(user);
                    break;
                }
            }
            UserRepository.saveUserList(userList);
            System.out.println("Trainer Succesfully deleted from file");
        } catch (Exception e) {
            System.out.println("Unable to delete trainer from file");
        }
    }

    //Method takes an ArrayList<User> and saves to file when updating Trainer Objects
    public static void updateTrainerList(ArrayList<User> userList){
        try {
            UserRepository.saveUserList(userList);
        } catch (Exception e) {
            System.out.println("Was unable to save updated Trainer object");
            e.printStackTrace();
        }
    }


}
