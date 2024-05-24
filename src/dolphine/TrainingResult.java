package dolphine;

import java.time.LocalDate;

public class TrainingResult extends Result {
    private Trainer trainer;

    public TrainingResult(int time, int distance, LocalDate dateOfResult, SwimDiscipline swimDiscipline,CompetitionMember competitionMember ,Trainer trainer) {
        super (time, distance, dateOfResult, swimDiscipline, competitionMember);
        this.trainer=trainer;
    }
    public TrainingResult(Result result,Trainer trainer) {
        super (result.getTime(), result.getDistance(), result.getDateOfResult(), result.getSwimDiscipline(), result.getCompetitionMember());
        this.trainer=trainer;
    }


    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public String toString() {
        return String.format(
                "%s\n" + // Call to superclass toString method
                        "Trainer:           %s",
                super.toString(), trainer
        );
    }
}
