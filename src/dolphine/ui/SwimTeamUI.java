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
                        editTeam();
                        break;
                    case 3://delete team
                        deleteSwimTeam();
                        break;
                    case 4://view all teams
                        viewAllSwimTeams();
                        break;
                    case 0:
                        return;
                }
            } while (choice != 0);
        }

    private static void editTeam() {
        System.out.println("Enter a number to select a Swim Team to Edit");
        ArrayList<SwimTeam> swimTeamsList = SwimTeamRepository.getSwimTeamList();
        SwimTeam teamToEdit = UserInputUtil.selectObject(swimTeamsList);
        int editChoice = UserInputUtil.getIntInput("Enter 1 to edit Name for team \nEnter 2 to edit MemberType for team \nEnter 3 to edit Trainer's for team\nEnter 4 to edit Members for team \nEnter 5 to exit","Invalid Choice", 1,5);
        switch(editChoice){
            case 1:
                editSwimTeamName(teamToEdit);
                SwimTeamRepository.updateSwimTeam(teamToEdit);
                System.out.println("Name change updated succesfully");
                break;
            case 2:
                editMemberTypeForSwimTeam(teamToEdit);
                SwimTeamRepository.updateSwimTeam(teamToEdit);
                System.out.println("MemberType changes saved to file");
                break;
            case 3: //edit trainers for swim team
                editTrainersForSwimTeam(teamToEdit);
                SwimTeamRepository.updateSwimTeam(teamToEdit);
                System.out.println("changes saved to file");
                break;
            case 4: //edit members for swim team
                editMembersForSwimTeam(teamToEdit);
                SwimTeamRepository.updateSwimTeam(teamToEdit);
                System.out.println("changes saved to file");
                break;
            default://exit editTeam
                break;
        }
    }

    private static void editMemberTypeForSwimTeam(SwimTeam teamToEdit) {
        MemberType newMemberType = UserInputUtil.selectObject(MemberType.values());
        teamToEdit.setMemberType(newMemberType);
    }

    private static void editSwimTeamName(SwimTeam swimteam) {
        String newName = UserInputUtil.getStringInput("Input new name for swim team");
        swimteam.setName(newName);
    }

    private static void editMembersForSwimTeam(SwimTeam teamToEdit) {
        int firstChoice = UserInputUtil.getIntInput("Enter 1 to add Member \nEnter 2 to remove Member","Invalid Choice",1,2);
        if(firstChoice == 1){ //if want to add member
            int secondChoice = UserInputUtil.getIntInput("Enter 1 to create new member and add to Swim Team \nEnter 2 to add existing member to Swim Team","Invalid Choice", 1,2);
            if(secondChoice == 1){ //if want to create and add new member
                Member newMember = MemberUI.createMember();
                MemberRepository.saveMember(newMember); //save member to UserFile
                System.out.println("Member created and saved to file");
                teamToEdit.getSwimMemberList().add(newMember); //add member to SwimTeam object
                System.out.println("member added to Swim Team");
            } else { //if want to add existing member to Swim Team
                ArrayList<Member> memberList = MemberRepository.getMemberList();
                if (!memberList.isEmpty()) {
                    Member selectedMember = UserInputUtil.selectObject(memberList);
                    teamToEdit.getSwimMemberList().add(selectedMember);
                    System.out.println("Member added to Swim Team");
                } else {
                    System.out.println("No Members currently exists in file, so unable to add any existing member");
                }
            }
        } else { //if want to remove trainer from swim team
            ArrayList<Member> thisTeamMemberList = teamToEdit.getSwimMemberList();
            if (!thisTeamMemberList.isEmpty()) {
                System.out.println("Select Member to remove: ");
                Member chosenMember = UserInputUtil.selectObject(thisTeamMemberList);
                teamToEdit.getSwimMemberList().remove(chosenMember);
                System.out.println("Trainer succesfully removed from Swim Team");
            } else {
                System.out.println("Team Currently has no member to delete");
            }
        }
    }

    private static void editTrainersForSwimTeam(SwimTeam teamToEdit) {
        int firstChoice = UserInputUtil.getIntInput("Enter 1 to add trainer \nEnter 2 to remove trainer","Invalid Choice",1,2);
        if(firstChoice == 1){ //if want to add trainer
            int secondChoice = UserInputUtil.getIntInput("Enter 1 to create new trainer and add to Swim Team \nEnter 2 to add existing Trainer to Swim Team","Invalid Choice", 1,2);
            if(secondChoice == 1){ //if want to create and add new trainer
                Trainer newTrainer = TrainerMenu.createTrainer();
                TrainerRepository.saveTrainerToUserList(newTrainer); //save Trainer to UserFile
                System.out.println("Trainer created and saved to file");
                teamToEdit.getSwimTrainerList().add(newTrainer); //add Trainer to SwimTeam object
                SwimTeamRepository.updateSwimTeam(teamToEdit); //save changes to file
                System.out.println("Trainer added to Swim Team");
            } else { //if want to add existing trainer to Swim Team
                ArrayList<Trainer> trainerList = TrainerMenu.getTrainerArrayList(UserRepository.getUserList());
                if (!trainerList.isEmpty()) {
                    Trainer selectedTrainer = UserInputUtil.selectObject(trainerList);
                    teamToEdit.getSwimTrainerList().add(selectedTrainer);
                    System.out.println("Trainer added to Swim Team");
                } else {
                    System.out.println("No trainers currently exists in file, so unable to add any existing trainer");
                }
            }
        } else { //if want to remove trainer from swim team
            ArrayList<Trainer> thisTeamTrainerList = teamToEdit.getSwimTrainerList();
            if (!thisTeamTrainerList.isEmpty()) {
                System.out.println("Select Trainer to remove: ");
                Trainer chosenTrainer = UserInputUtil.selectObject(thisTeamTrainerList);
                teamToEdit.getSwimTrainerList().remove(chosenTrainer);
                System.out.println("Trainer succesfully removed from Swim Team");
            } else {
                System.out.println("Team Currently has no trainer to delete");
            }
        }
    }

    private static void deleteSwimTeam() {
        ArrayList<SwimTeam> swimTeams = SwimTeamRepository.getSwimTeamList();
        if(!swimTeams.isEmpty()){
            SwimTeam choice = UserInputUtil.selectObject(swimTeams);
            SwimTeamRepository.deleteSwimTeam(choice);
        } else {
            System.out.println("Currently there is no Swim Teams to delete");
        }
    }

    private static void viewAllSwimTeams() {
        ArrayList<SwimTeam> swimTeams = SwimTeamRepository.getSwimTeamList();
        if (!swimTeams.isEmpty()) {
            for(SwimTeam swimTeam : swimTeams){
                System.out.println(swimTeam);
            }
        } else {
            System.out.println("Currently there is no Swim Teams in the files");
        }
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
        System.out.println("Swim Team succesfully saved to file \n" + newSwimTeam);
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
                System.out.println("Trainer was added to Swim Team");
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

