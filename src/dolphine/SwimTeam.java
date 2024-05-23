package dolphine;

import java.util.ArrayList;

public class SwimTeam {
    private static ArrayList<Member> swimMemberList;
    private ArrayList<Trainer> swimTrainerList;

    public SwimTeam(ArrayList<Member> swimMemberList, ArrayList<Trainer> swimTrainerList) {
        this.swimMemberList = swimMemberList;
        this.swimTrainerList = swimTrainerList;
    }

    public static Member findMemberByName(String name) {
        for (Member member : swimMemberList) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }

    public static Member findMemberById(String id) {
        for (Member member : swimMemberList) {
            if (member.getId().equals(id)) {
                return member;
            }
        }
        return null;
    }

    public ArrayList<Member> getSwimMemberList() {
        return swimMemberList;
    }

    public ArrayList<Trainer> getSwimTrainerList() {
        return swimTrainerList;
    }
}
