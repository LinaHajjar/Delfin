package Dolphine;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompetitionMember extends Member{
    private ArrayList<SwimDiscipline> disciplineList;
    private ArrayList<trainingResult> trainingResultList;
    private ArrayList<CompetitionResult> competitionResultList;

    public CompetitionMember (String id, String name, LocalDate dateOfBirth, Role role, boolean isActive, MemberType memberType, ArrayList<SwimDiscipline> disciplineList, ArrayList<trainingResult> trainingResultList, ArrayList<CompetitionResult> competitionResultList){
        super (id,name,dateOfBirth,role,isActive, memberType);
        this.disciplineList=disciplineList;
        this.trainingResultList=trainingResultList;
        this.competitionResultList=competitionResultList;
    }

}
