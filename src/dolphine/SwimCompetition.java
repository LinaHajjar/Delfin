package dolphine;

import java.time.LocalDate;
import java.util.ArrayList;

public class SwimCompetition {
    private LocalDate date;
    private String competitionName;
    private String location;
    private ArrayList<SwimDiscipline> swimDiscipline;
    private ArrayList<Member> swimMemberList;

    public SwimCompetition (LocalDate date, String competitionName, String location, ArrayList<SwimDiscipline> swimDiscipline, ArrayList<Member> swimMemberlist ){
        this.date=date;
        this.competitionName=competitionName;
        this.location=location;
        this.swimDiscipline=swimDiscipline;
        this.swimMemberList=swimMemberlist;
    }
}
