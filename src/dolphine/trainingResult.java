package dolphine;

import java.time.LocalDate;

public class trainingResult extends Result {
    private Trainer trainer;

    public trainingResult(int time, int distance, LocalDate dateOfResult, SwimDiscipline swimDiscipline, Trainer trainer) {
        super (time, distance, dateOfResult, swimDiscipline );
        this.trainer=trainer;
    }

}
