import dolphine.Member;
import dolphine.MemberType;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MemberRepository {
    //Oprette en ny arraylist til medlemmer
    //da jeg både vil tilføje medlem til arraylisten OG printe til en fil --> metode der klarer begge dele
    public static void addMember(Member member) {
        ArrayList<Member> memberList = new ArrayList<>();
        // Add the member to the list
        memberList.add(member);
        memberList.add(new Member("Yvonne", 25, true, MemberType.JUNIOR));
        memberList.add(new Member("BølleBob", 35, true, MemberType.SENIOR));
        memberList.add(new Member("Cay", 45, false, MemberType.SENIOR));

        // medlem til member.txt fil
        try {
            FileWriter writer = new FileWriter("C:\\Users\\PC\\IdeaProjects\\Delfin\\src\\dolphine\\repository\\members.txt", true); // Append mode
            writer.write("Age: " + member.getMemberType());
            writer.write("Active: " + member.getIsActive());
            writer.write("Member Type: " + Member.getMemberType());
            writer.write();
            writer.close();

            System.out.println("Member information written to file.");
        }catch(IOException e){
                System.err.println("An error occurred while writing to the file: " + e.getMessage());
            }
    }
    public static void saveMember (Member member) {
        Arraylist<user> = UserRepository.getuserList();
        userlist.add(member);
        //saveUserList(userlist);
    }
    }
