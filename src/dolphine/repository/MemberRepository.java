package dolphine.repository;

import dolphine.Member;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberRepository {
    List<Member> memberList = new ArrayList<>();
    public void addMember(Member member) {
                memberList.add(member);
                saveMemberToFile(member);
            }

    private void saveMemberToFile(Member member) {
        try (FileWriter writer = new FileWriter("members.txt", true)) { // Append mode
                    writer.write("Name: " + member.getName() + "\n");
                    writer.write("Date of Birth: " + member.getDateOfBirth() + "\n");
                    writer.write("Role: " + member.getRole() + "\n");
                    writer.write("Active: " + member.isActive() + "\n");
                    writer.write("Member Type: " + member.getMemberType() + "\n");
                    writer.write("--------------------\n");
                    System.out.println("Member information written to file.");
                } catch (IOException e) {
                    System.err.println("An error occurred while writing to the file: " + e.getMessage());
                }
            }

    public void updateMember(Member member) {
        int index = memberList.indexOf(member);
        if (index != -1) {
            memberList.set(index, member);
        }
    }

    public void deleteMember(Member member) {
        memberList.remove(member);
    }

    public List<Member> getMemberListByName(String name) {
        return memberList.stream()
                .filter(member -> member.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
            }
        }