package dolphine.repository;

import dolphine.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberRepository {
    public static List<Member> memberList = new ArrayList<>();
    public static void addMember(Member member) {
        memberList.add(member);
        //saveMemberToFile(member);
    }
    public static void createMember(Member newMember) {
        memberList.add(newMember);
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

    public static void updateMember(Member member) {
        int index = memberList.indexOf(member);
        if (index != -1) {
            memberList.set(index, member);
        }
    }
    public static void deleteMember(Member member) {
        memberList.remove(member);
    }
    public static List<Member> getMemberListByName(String name) {
        return memberList.stream()
                .filter(member -> member.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
            }
    public static void showArrayList(){
        for (Member member : memberList) {
            System.out.println(member);
            }
        }
}