package dolphine;

import java.time.LocalDate;

public class Result {
    private  int time;
    private int distance;
    private LocalDate dateOfResult;
    private SwimDiscipline swimDiscipline;

    public Result(int time,int distance, LocalDate dateOfResult, SwimDiscipline swimDiscipline ){
        this.time=time;
        this.distance=distance;
        this.dateOfResult=dateOfResult;
        this.swimDiscipline=swimDiscipline;
    }

}
