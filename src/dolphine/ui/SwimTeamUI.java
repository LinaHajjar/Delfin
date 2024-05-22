package dolphine.ui;

import dolphine.*;
import dolphine.repository.MemberRepository;
import dolphine.repository.SwimTeamRepository;
import dolphine.repository.TrainerRepository;
import dolphine.repository.UserRepository;
import dolphine.util.UserInputUtil;

import java.util.ArrayList;

public class SwimTeamUI {
    public static void swimTeamMenu() {
            int choice;
            do {
                System.out.println("                 Swim Team Menu                  ");
                System.out.println("===============================================");
                System.out.println("  type 1: Register Team                     ");
                System.out.println("  type 2: Edit Team                         ");
                System.out.println("  type 3: Delete Team                       ");
                System.out.println("  type 4: View All Teams                    ");
                System.out.println("  type 0: Go back                           ");
                System.out.println();
                int[] choiceArray = new int[]{1,2,3,4,0};
                choice = UserInputUtil.getIntInput("Input a number to select an option ","Invalid Choice, try again",choiceArray);
                switch(choice){
                    case 1://register team
                        registerTeam();
                        break;
                    case 2://edit team

                        break;
                    case 3://delete team

                        break;
                    case 4://view all teams

                        break;
                    case 0:
                        return;
                }
            } while (choice != 0);
        }

    //Create Swim Team
    private static void registerTeam() {
        System.out.println("Creating new SwimTeam");
        System.out.println("Select which type of team this is :");
        MemberType swimTeamType = UserInputUtil.selectObject(MemberType.values());
        String swimTeamName = UserInputUtil.getStringInput("Input name of team: ","Invalid Input");
        ArrayList<Trainer> swimTeamTrainerArrayList = new ArrayList<>();
        ArrayList<Member> swimTeamMemberArrayList = new ArrayList<>();
        SwimTeam newSwimTeam = new SwimTeam(swimTeamName,swimTeamType,swimTeamMemberArrayList,swimTeamTrainerArrayList);
        //ask if want to add trainer below
        int trainerChoice = UserInputUtil.getIntInput("Do you wish to add Trainer to Swim Team?\n Enter 1 for yes, 0 for no","Invalid Choice",0,1);
        if(trainerChoice == 1){
            addTrainerToSwimTeam(newSwimTeam);
        } else {
            System.out.println("No Trainer was Added to the Swim Team yet, leaving Swim Team without trainer for now");
        }
        //ask if want to add members below
        int memberChoice = UserInputUtil.getIntInput("Do you wish to add Members to the list now?\n Enter 1 for yes, 0 for no","Invalid Choice",0,1);
        if (memberChoice == 1) { //if want to add member
            addMembersToSwimTeam(newSwimTeam); //loop that allows user to continously add members to the list
        } else {
            System.out.println("No Members was Added to the Swim Team yet, leaving Swim Team without Members for now");
        }
        SwimTeamRepository.saveSwimTeamToFile(newSwimTeam);

    }
    //Method to add Trainer to Swim Team, Does not save to file as that is up to the methods calling it
    public static void addTrainerToSwimTeam(SwimTeam swimTeam) {
        ArrayList<User> userArrayList = UserRepository.getUserList();
        ArrayList<Trainer> trainerArrayListFromFile = TrainerMenu.getTrainerArrayList(userArrayList);
        if (!trainerArrayListFromFile.isEmpty()) { //if Trainer exists, allow user to add 1 to SwimTeam
            System.out.println("Select a Trainer for the team ");
            Trainer swimTeamTrainer = UserInputUtil.selectObject(trainerArrayListFromFile);
            swimTeam.getSwimTrainerList().add(swimTeamTrainer);
            System.out.println("Trainer Added to Swim Team");
        } else { //if no trainer exists in files, give option to create new trainer and add that trainer to Swim Team
            System.out.println("No trainers in file exists, do you wish to create a new trainer and add to Swim Team?");
            System.out.println("Enter 1 to create new trainer, 0 to not create new trainer");
            int choice = UserInputUtil.getIntInput("Input choice","Invalid choice", 0,1);
            if(choice == 1) {
                Trainer newTrainer = TrainerMenu.createTrainer();
                swimTeam.getSwimTrainerList().add(newTrainer);
            } else {
                System.out.println("No trainer was added to Swim Team");
            }
        }
    }
    //method to add Members to Swim Team, Does not save to file as that is up to the methods calling it
    public static void addMembersToSwimTeam(SwimTeam swimTeam){
        ArrayList<Member> memberArrayListFromFile = MemberRepository.getMemberList();
        if(!memberArrayListFromFile.isEmpty()){
            int choice1 = UserInputUtil.getIntInput("Do you wish to add an existing member?\n Enter 1 for yes, 0 for no","Invalid choice",0,1);
            while(choice1 == 1){
                Member memberToAdd = UserInputUtil.selectObject(memberArrayListFromFile);
                swimTeam.getSwimMemberList().add(memberToAdd);
                System.out.println("Member added to Swim Team");
                choice1 = UserInputUtil.getIntInput("Do you wish to add an existing member?\n Enter 1 for yes, 0 for no","Invalid choice",0,1);
            }
        } else {
            System.out.println("No current Members exists in the files");
        }
        int choice2 = UserInputUtil.getIntInput("Do you wish to create new Member and add to Swim Team?\n Enter 1 for yes, 0 for no","Invalid Choice",0,1);
        while(choice2 == 1){
            Member memberToAdd = MemberUI.createMember();
            swimTeam.getSwimMemberList().add(memberToAdd);
            System.out.println("Member added to Swim Team");
            choice2 = UserInputUtil.getIntInput("Do you wish to create another new member and add to Swim Team?\n Enter 1 for yes, 0 for no","Invalid choice",0,1);
        }
    }
}

