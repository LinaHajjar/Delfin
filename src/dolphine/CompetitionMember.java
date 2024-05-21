package dolphine;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompetitionMember extends Member {
    private ArrayList<SwimDiscipline> disciplineList;

    public CompetitionMember(String name, LocalDate dateOfBirth, Role role, boolean isActive, ArrayList<SwimDiscipline> disciplineList) {
        super(name, dateOfBirth, role, isActive);
        this.disciplineList = disciplineList;
    }

    public ArrayList<SwimDiscipline> getDisciplineList() {
        return disciplineList;
    }

    public void setDisciplineList(ArrayList<SwimDiscipline> disciplineList) {
        this.disciplineList = disciplineList;
    }
}
