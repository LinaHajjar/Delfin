package dolphine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class SwimTeam implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ID;
    private String name;
    private MemberType memberType;
    private ArrayList<Member> swimMemberList;
    private ArrayList<Trainer> swimTrainerList;

    public SwimTeam(String name, MemberType memberType, ArrayList<Member> swimMemberList, ArrayList<Trainer> swimTrainerList) {
        this.ID = generateId();
        this.name = name;
        this.memberType = memberType;
        this.swimMemberList = swimMemberList;
        this.swimTrainerList = swimTrainerList;
    }
    private static String generateId() { // private fordi den skal kun bruges her i klassen, man kan ikke calculate id ud fra klassen
        return UUID.randomUUID().toString();
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public ArrayList<Member> getSwimMemberList() {
        return swimMemberList;
    }

    public void setSwimMemberList(ArrayList<Member> swimMemberList) {
        this.swimMemberList = swimMemberList;
    }

    public ArrayList<Trainer> getSwimTrainerList() {
        return swimTrainerList;
    }

    public void setSwimTrainerList(ArrayList<Trainer> swimTrainerList) {
        this.swimTrainerList = swimTrainerList;
    }
}
