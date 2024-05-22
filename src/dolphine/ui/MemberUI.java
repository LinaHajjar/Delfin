package dolphine.ui;

import dolphine.Member;
import dolphine.User;
import dolphine.repository.MemberRepository;
import dolphine.repository.UserRepository;
import dolphine.util.UserInputUtil;

import java.util.ArrayList;
import java.util.Scanner;

import static dolphine.repository.UserRepository.getUserList;

public class MemberUI {
    public static void MenuMember() {
        int choice;
        do {
            System.out.println(" 1: Create new member");
            System.out.println(" 2: Edit member");
            System.out.println(" 3: Delete member");
            System.out.println(" 4: Show list of members");
            System.out.println(" 0: Return to Main Menu");
            choice = UserInputUtil.getIntInput("Enter the number from the list: ", "Wrong input, choose a number between 0 and 4", 0, 4);

            switch (choice) {
                case 0:
                    System.out.println("You chose the option exit \nReturning to Main Menu");
                    break;
                case 1:
                    saveAndCreateMember();
                    break;
                case 2:
                    editMember();
                    break;
                case 3:
                    deleteMember();
                    break;
                case 4:
                    MemberRepository.showArrayList();
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        } while (choice != 0);
    }

    //gemme nye member objekt til arraylist i repository
    public static void saveAndCreateMember() {
        Member newMember = createMember();
        MemberRepository.saveMember(newMember);
        MemberRepository.addMember(newMember);
        System.out.println("Member created successfully \n" + newMember + "\n");
    }

    //nyt member objekt,
    public static Member createMember() {
        User user = UserUI.createUser(); //new member objekt ved brug af createUser metoden for at undgå redundans

        Scanner scanner = new Scanner(System.in);
        System.out.print("Is the member active? (true/false): ");
        boolean isActive = scanner.nextBoolean();

        Member newMember = new Member(user, isActive); //nyt member objekt oprettet med tilhørende informationer
        newMember.setMemberType();
        newMember.setActive(isActive); //medlemstatus aktiv/passiv
        return newMember;
    }

    public static void editMember() {
        System.out.println("Edit member");
        ArrayList<User> userList = getUserList(); //henter arraylisten
        Member memberToEdit = selectMember(userList);
        System.out.println("Membership status is " + (memberToEdit.getIsActive() ? "active" : "inactive"));
        int choice = UserInputUtil.getIntInput("Enter 1 to set to active, or 2 to set inactive", " Invalid choice", 1, 2);
        if (choice == 1) {
            memberToEdit.setActive(true);
        }
        if (choice == 2) {
            memberToEdit.setActive(false);
        }
        System.out.println("Membership status is now " + (memberToEdit.getIsActive() ? "active" : "inactive"));
        UserRepository.saveUserList(userList);
    }

    private static Member selectMember(ArrayList<User> userList) {
        ArrayList<Member> memberList = getMemberArrayList(userList);
        Member memberChosen = null;
        do {
            memberChosen = UserInputUtil.selectObject(memberList); //vælge objekt fra listen, som kun er member
        }
        while (memberChosen == null);
        return memberChosen;
    }

    //Liste KUN af members
    private static ArrayList<Member> getMemberArrayList(ArrayList<User> userList) {
        ArrayList<Member> memberList = new ArrayList();
        for (User user : userList) {
            if (user instanceof Member) {
                memberList.add((Member) user); //caster user med (member)
            }
        }
        return memberList;
    }

    public static void deleteMember() {
        System.out.println("Delete member");
        ArrayList<User> userList = getUserList(); //henter arraylisten
        Member memberToDelete = selectMember(userList);
        userList.remove(memberToDelete);
        UserRepository.saveUserList(userList);
        System.out.println("Member successfully deleted.");
    }

    public static Member findMemberByName(){
        boolean findingMember = false;
        ArrayList<Member> memberArrayList;
        do {
            String name = UserInputUtil.getStringInput("Name of the member: ");
            memberArrayList = MemberRepository.getMemberListByName(name);
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