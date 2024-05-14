package dolphine.ui;

import dolphine.Role;
import dolphine.Trainer;
import dolphine.User;
import dolphine.util.UserInputUtil;

import java.time.LocalDate;

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
                    break;
                case 3://delete trainer
                    break;
                case 0:
                    break;
            }
        } while (choice != 0);
    }

    //method to
    private static void registerTrainer() {
        Trainer newTrainer = createTrainer();

        System.out.println("Trainer created and saved to file:");
        System.out.println(newTrainer);
    }

    //method for creating a Trainer object via scanner input
    public static Trainer createTrainer(){
        User newUser = UserMenu.createUser();
        int seneriority = UserInputUtil.getIntInput("Input trainer's seniority (int): ","Invalid Input",0,99);
        Trainer newTrainer = new Trainer(newUser,seneriority);
        return newTrainer;
    }
}
