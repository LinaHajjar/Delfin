package dolphine.ui;

import dolphine.Trainer;
import dolphine.User;
import dolphine.repository.TrainerRepository;
import dolphine.repository.UserRepository;
import dolphine.util.UserInputUtil;

import java.util.ArrayList;

public class TrainerUI {

    //trainer submenu for everything trainer related
    public static void trainerSubMenu(){
        int choice;
        do {
            System.out.println("                 Trainer Menu                  ");
            System.out.println("===============================================");
            System.out.println("  type 1: Register Trainer                     ");
            System.out.println("  type 2: Edit Trainer                         ");
            System.out.println("  type 3: Delete Trainer                       ");
            System.out.println("  type 4: View All Trainers                    ");
            System.out.println("  type 0: Go back                              ");
            choice = UserInputUtil.getIntInput("Input a number to select an option","Invalid Choice, try again",0,4);
            switch(choice){
                case 1://register trainer
                    registerTrainer();
                    break;
                case 2://edit trainer
                    editTrainer();
                    break;
                case 3://delete trainer
                    deleteTrainer();
                    break;
                case 4://view all trainers
                    viewTrainers();
                    break;
                case 0:
                    return;
            }
        } while (choice != 0);
    }

    private static void viewTrainers() {
        ArrayList<User> userlist = UserRepository.getUserList();
        for(User user : userlist){
            if(user instanceof Trainer){
                System.out.println(user);
            }
        }
    }

    //method to select and delete a trainer from repository
    private static void deleteTrainer() {
        ArrayList<User> userList = UserRepository.getUserList();
        Trainer trainerChosen = selectTrainer(userList);
        TrainerRepository.deleteTrainerFromUserList(trainerChosen);
    }

    //method to edit a Trainer's senority
    private static void editTrainer() {
        ArrayList<User> userList = UserRepository.getUserList();
        Trainer trainerChosen = selectTrainer(userList);
        System.out.println("You have selected Trainer: \n" + trainerChosen);
        int newSenority = UserInputUtil.getIntInput("Enter new Senority", "Invalid input", 0,99);
        trainerChosen.setSeniority(newSenority);
        System.out.println("Senority updated, new senority: " + trainerChosen.getSeniority());
        try {
            TrainerRepository.updateTrainerList(userList);
            System.out.println("User changes succesfully saved to file");
        } catch (Exception e) {
            System.out.println("Error: Was unable to save updated user to file");
            e.printStackTrace();
        }
    }

    //method to select and return trainer from userArray via scanner input
    public static Trainer selectTrainer(ArrayList<User> userList){
        ArrayList<Trainer> trainerArray = getTrainerArrayList(userList);
        Trainer trainerChosen = null;
        do {
            trainerChosen = UserInputUtil.selectObject(trainerArray);
        } while (trainerChosen == null);
        return trainerChosen;
    }

    //method to create and register Trainer in User repository file
    private static void registerTrainer() {
        Trainer newTrainer = createTrainer();
        System.out.println("New Trainer created: \n" + newTrainer);
        TrainerRepository.saveTrainerToUserList(newTrainer);
    }

    //method for creating a Trainer object via scanner input
    public static Trainer createTrainer(){
        try {
            User newUser = UserUI.createUser();
            int seneriority = UserInputUtil.getIntInput("Input trainer's seniority (int): ","Invalid Input",0,99);
            Trainer newTrainer = new Trainer(newUser,seneriority);
            return newTrainer;
        } catch (Exception e) {
            System.out.println("Unable to create user");
            e.printStackTrace();
            return null;
        }
    }

    //method for getting a Trainer ArrayList from a User ArrayList
    public static ArrayList<Trainer> getTrainerArrayList(ArrayList<User> userList){
        try {
            ArrayList<Trainer> trainerArray = new ArrayList<>();
            for(User user : userList){
                if(user instanceof Trainer){
                    trainerArray.add((Trainer) user);
                }
            }
            return trainerArray;
        } catch (Exception e) {
            System.out.println("Could not load ArrayList of Trainers");
            e.printStackTrace();
            return null;
        }
    }
}
