package dolphine.repository;

import dolphine.Member;
import dolphine.User;

import java.util.ArrayList;
import java.util.List;

import static dolphine.repository.UserRepository.getUserList;

public class MemberRepository {
    public static List<Member> memberList = new ArrayList<>();

    public static void addMember(Member member) {
        memberList.add(member);
        //saveMemberToFile(member);
    }

    public static void saveMember(Member newMember) {
        ArrayList<User> userList = getUserList();
        userList.add(newMember); //tilføjer til den fælles userlist
        UserRepository.saveUserList(userList);
    }
   /* public static void saveMemberToFile(Member member) {
        try (FileWriter writer = new FileWriter("members.txt", true)) { // Append mode
                    writer.write("Name: " + member.getName() + "\n");
                    writer.write("Date of Birth: " + member.getDateOfBirth() + "\n");
                    writer.write("Role: " + member.getRole() + "\n");
                    writer.write("Active: " + member.getIsActive() + "\n");
                    writer.write("Member Type: " + member.getMemberType() + "\n");
                    writer.write("--------------------\n");
                    System.out.println("\nMember information written to file.");
                } catch (IOException e) {
                    System.err.println("An error occurred while writing to the file: " + e.getMessage());
                }*/


    public static void showArrayList() {
        ArrayList<User> userList = getUserList(); //henter arraylisten
        for (User user : userList) {
            if (user instanceof Member) {
                System.out.println(user);
            }
        }
    }
}