package Dolphine;

import java.time.LocalDate;

public class CompetitionResult extends Result {
    private SwimCompetition swimCompetition;

    public CompetitionResult(int time, int distance, LocalDate dateOfResult, SwimDiscipline swimDiscipline, SwimCompetition swimCompetition) {
        super (time, distance, dateOfResult, swimDiscipline );
        this.swimCompetition=swimCompetition;
    }

}
