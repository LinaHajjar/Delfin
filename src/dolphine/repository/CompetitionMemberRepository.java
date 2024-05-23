package dolphine.repository;

import dolphine.CompetitionMember;
import dolphine.Member;

import java.util.ArrayList;

public class CompetitionMemberRepository {
    public static void saveCompetitionMember(CompetitionMember newCompetitionMember) {
        UserRepository.createUser(newCompetitionMember);
    }

    public static ArrayList<CompetitionMember> getCompetitionMemberByName(String name){
        ArrayList<Member> memberList = MemberRepository.getMemberList();
        ArrayList<CompetitionMember> memberListByName = new ArrayList<>();
        for (Member m : memberList) {
            if (m instanceof CompetitionMember && m.getName().toLowerCase().contains(name.toLowerCase())) {
                memberListByName.add((CompetitionMember) m);
            }
        }
        return memberListByName;
    }
}
