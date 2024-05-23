package dolphine.ui;

import dolphine.CompetitionMember;
import dolphine.Member;
import dolphine.SwimDiscipline;
import dolphine.repository.CompetitionMemberRepository;
import dolphine.util.UserInputUtil;

import java.util.ArrayList;

public class CompetitionMemberUI {
    public static void MenuCompetitionMember() {
        int choice;
        do {
            System.out.println(" 1: Create new competition member");
            System.out.println(" 0: Return to Main Menu");
            choice = UserInputUtil.getIntInput("Enter the number from the list: ", "Wrong input, choose a number between 0 and 1", 0, 1);

            switch (choice) {
                case 0:
                    System.out.println("You chose the option exit \nReturning to Main Menu");
                    break;
                case 1:
                    saveAndCreateMember();
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        } while (choice != 0);
    }

    //gemme nye member objekt til arraylist i repository
    public static void saveAndCreateMember() {
        CompetitionMember newMember = createCompetitionMember();
        CompetitionMemberRepository.saveCompetitionMember(newMember);
        System.out.println("Member created successfully \n" + newMember + "\n");
    }

    //nyt member objekt,
    public static CompetitionMember createCompetitionMember() {
        Member member = MemberUI.createMember(); //new member objekt ved brug af createUser metoden for at undgå redundans

        boolean selectingDiscipline;
        ArrayList<SwimDiscipline> disciplines = new ArrayList<>();
        do {
            disciplines.add(UserInputUtil.selectObject(SwimDiscipline.values()));
            selectingDiscipline = UserInputUtil.getStringInput("Select more disciplines? y/n :", "Select y/n please", new String[]{"y","n"}).equalsIgnoreCase("y");
        } while (selectingDiscipline);

        return new CompetitionMember(member, disciplines);
    }

    public static CompetitionMember findCompetitionMemberByName(){
        boolean findingMember = false;
        ArrayList<CompetitionMember> memberArrayList;
        do {
            String name = UserInputUtil.getStringInput("Name of the member: ");
            memberArrayList = CompetitionMemberRepository.getCompetitionMemberByName(name);
            if (memberArrayList.isEmpty()) {
                System.out.println("Member not found");
                findingMember = UserInputUtil.getStringInput("Try again? y/n", "Please write y or n", new String[]{"y", "n"}).equals("y");
            }
        } while (findingMember);
        if (memberArrayList.isEmpty()) {
            return null;
        }
        System.out.println("Select Member");
        return UserInputUtil.selectObject(memberArrayList);
    }
}
