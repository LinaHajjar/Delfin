package dolphine.ui;

import dolphine.Role;
import dolphine.Trainer;
import dolphine.User;
import dolphine.repository.TrainerRepository;
import dolphine.util.UserInputUtil;

import java.time.LocalDate;
import java.util.ArrayList;

public class TrainerMenu {

    //trainer submenu for everything trainer related
    public static void trainerSubMenu(){
        int choice;
        do {
            System.out.println("                 Trainer Menu                  ");
            System.out.println("===============================================");
            System.out.println("  type 1: Register Trainer                     ");
            System.out.println("  type 2: Edit Trainer                         ");
            System.out.println("  type 3: Delete Trainer                       ");
            System.out.println("  type 0: Go back                              ");
            int[] choiceArray = new int[]{1,2,3,0};
            choice = UserInputUtil.getIntInput("Input a number to select an option","Invalid Choice, try again",choiceArray);
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
                case 0:
                    return;
            }
        } while (choice != 0);
    }

    //TODO check when user is done
    //method to select and delete a trainer from repository
    private static void deleteTrainer() {
        Trainer trainerChosen = selectTrainer();
        TrainerRepository.deleteTrainerFromUserList(trainerChosen);
    }


    //TODO when User menu and repository class is finished
    //method to edit a Trainer's senority
    private static void editTrainer() {
        ArrayList<User> userList = UserRepository.getUserList();
        Trainer trainerChosen = selectTrainer();
        System.out.println("You have selected Trainer: \n" + trainerChosen);
        int newSenority = UserInputUtil.getIntInput("Enter new Senority", "Invalid choice", 0,99);
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

    //TODO test when user is finished
    //method to select and return trainer from userArray via scanner input
    public static Trainer selectTrainer(){
        ArrayList<Trainer> trainerArray = getTrainerArrayList();
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

    //TODO when User menu and repository class is finished
    //method for creating a Trainer object via scanner input
    public static Trainer createTrainer(){
        try {
            User newUser = UserMenu.createUser();
            int seneriority = UserInputUtil.getIntInput("Input trainer's seniority (int): ","Invalid Input",0,99);
            Trainer newTrainer = new Trainer(newUser,seneriority);
            return newTrainer;
        } catch (Exception e) {
            System.out.println("Unable to create user");
            e.printStackTrace();
            return null;
        }
    }

    //TODO when User menu and repository class is finished
    //method for getting a Trainer ArrayList from a User ArrayList
    public static ArrayList<Trainer> getTrainerArrayList(){
        try {
            ArrayList<User> userArray = UserRepository.getUserlist();
            ArrayList<Trainer> trainerArray = new ArrayList<>();
            for(User user : userArray){
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
