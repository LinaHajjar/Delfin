package dolphine;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompetitionMember extends Member{
    private ArrayList<SwimDiscipline> disciplineList;

    public CompetitionMember (String id, String name, LocalDate dateOfBirth, Role role, boolean isActive, MemberType memberType, ArrayList<SwimDiscipline> disciplineList){
        super (id,name,dateOfBirth,role,isActive, memberType);
        this.disciplineList=disciplineList;
    }

    public ArrayList<SwimDiscipline> getDisciplineList() {
        return disciplineList;
    }

    public void setDisciplineList(ArrayList<SwimDiscipline> disciplineList) {
        this.disciplineList = disciplineList;
    }
}
