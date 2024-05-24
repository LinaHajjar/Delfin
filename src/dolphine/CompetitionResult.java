package dolphine;

import java.time.LocalDate;

public class CompetitionResult extends Result {
    private SwimCompetition swimCompetition;

    public int getPlacement() {
        return placement;
    }

    public void setPlacement(int placement) {
        this.placement = placement;
    }

    private int placement;

    public CompetitionResult(int time, int distance, LocalDate dateOfResult, SwimDiscipline swimDiscipline,CompetitionMember competitionMember ,SwimCompetition swimCompetition, int placement) {
        super (time, distance, dateOfResult, swimDiscipline, competitionMember);
        this.swimCompetition=swimCompetition;
        this.placement = placement;
    }

    public CompetitionResult(Result result, SwimCompetition swimCompetition, int placement) {
        super (result.getTime(), result.getDistance(), result.getDateOfResult(), result.getSwimDiscipline(), result.getCompetitionMember());
        this.swimCompetition=swimCompetition;
        this.placement = placement;
    }


    public SwimCompetition getSwimCompetition() {
        return swimCompetition;
    }

    public void setSwimCompetition(SwimCompetition swimCompetition) {
        this.swimCompetition = swimCompetition;
    }

    public String toString() {
        return String.format(
                "%s\n" + // Call to superclass toString method
                        "Swim Competition:  %s\n" +
                        "Placement:         %d",
                super.toString(), swimCompetition, placement
        );
    }
}
