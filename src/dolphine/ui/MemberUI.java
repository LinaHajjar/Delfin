package dolphine.ui;

import dolphine.Member;
import dolphine.Role;
import dolphine.User;
import dolphine.repository.MemberRepository;
import dolphine.util.UserInputUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static dolphine.util.UserInputUtil.getLocalDateInput;

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
                    editAndSaveMember();
                    break;
                case 3:
                    deleteAndSaveMember();
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
        MemberRepository.createMember(newMember);
        MemberRepository.addMember(newMember);
        System.out.println("Member created successfully \n" + newMember+ "\n");
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

    public static void editAndSaveMember() {
        System.out.println("Edit member");
        Member memberToEdit = findMemberByName();
        if (memberToEdit == null) {
            return;
        }
        editMember(memberToEdit);
        MemberRepository.updateMember(memberToEdit);
    }

    public static void editMember(Member member) {
        int choice;
        do {
            System.out.println(" 0: Exit.");
            System.out.println(" 1: Name");
            System.out.println(" 2: Date of birth");
            System.out.println(" 3: Role");
            System.out.println(" 4: Membership status");
            choice = UserInputUtil.getIntInput("Which information do you want to edit?", "Wrong input, choose a number between 0 and 4", 0, 4);
            switch (choice) {
                case 0:
                    break;
                case 1:
                    String name = UserInputUtil.getStringInput("What is the new name of the member?");
                    member.setName(name);
                    break;
                case 2:
                    System.out.println("What is the new date of birth?");
                    LocalDate dateOfBirth = getLocalDateInput();
                    member.setDateOfBirth(dateOfBirth);
                    member.setMemberType(); // Recalculate member type based on new date of birth
                    break;
                case 3:
                    System.out.println("What is the new role of the member?");
                    Role role = UserInputUtil.selectObject(Role.values());
                    member.setRole(role);
                    break;
                case 4:
                    boolean isActive = UserInputUtil.getBooleanInput("Is the member active? (true/false): ");
                    member.setActive(isActive);
                    break;
                default:
                    System.out.println("Wrong input. Please choose a number between 0-4.");
                    editMember(member);
                    break;
            }
            if (choice != 0) {
                System.out.println("Member was successfully edited");
            }
        } while (choice != 0);
    }

    public static void deleteAndSaveMember() {
        System.out.println("Delete member");
        Member memberToDelete = findMemberByName();
        if (memberToDelete != null) {
            MemberRepository.deleteMember(memberToDelete);
        }
    }
    public static Member findMemberByName() {
        boolean findingMember = false;
        ArrayList<Member> memberList;
        do {
            String name = UserInputUtil.getStringInput("Enter the name of the member: ");
            memberList = (ArrayList<Member>) MemberRepository.getMemberListByName(name);
            if (((ArrayList<?>) memberList).isEmpty()) {
                System.out.println("Member not found");
                findingMember = UserInputUtil.getStringInput("Try again? y/n", "Please write y or n", new String[]{"y", "n"}).equals("y");
            }
        } while (findingMember);
        if (memberList.isEmpty()) {
            return null;
        }
        System.out.println("Select member");
        return UserInputUtil.selectObject(memberList);
    }
}