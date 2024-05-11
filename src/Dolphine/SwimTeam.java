package Dolphine;

import java.util.ArrayList;

public class SwimTeam {
    private ArrayList<Member> swimMemberList;
    private ArrayList<Trainer> swimTrainerList;

    public SwimTeam (ArrayList<Member> swimMemberList, ArrayList<Trainer> swimTrainerList){
        this.swimMemberList=swimMemberList;
        this.swimTrainerList=swimTrainerList;
    }

}
