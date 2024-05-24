package dolphine;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Result implements Serializable, Comparable<Result> {
    private String id;
    private int time;
    private int distance;
    private LocalDate dateOfResult;
    private SwimDiscipline swimDiscipline;
    private CompetitionMember competitionMember;

    public Result(int time, int distance, LocalDate dateOfResult, SwimDiscipline swimDiscipline, CompetitionMember competitionMember) {
        this.id = UUID.randomUUID().toString();
        this.time = time;
        this.distance = distance;
        this.dateOfResult = dateOfResult;
        this.swimDiscipline = swimDiscipline;
        this.competitionMember = competitionMember;
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public LocalDate getDateOfResult() {
        return dateOfResult;
    }

    public void setDateOfResult(LocalDate dateOfResult) {
        this.dateOfResult = dateOfResult;
    }

    public SwimDiscipline getSwimDiscipline() {
        return swimDiscipline;
    }

    public void setSwimDiscipline(SwimDiscipline swimDiscipline) {
        this.swimDiscipline = swimDiscipline;
    }

    public CompetitionMember getCompetitionMember(){
        return this.competitionMember;
    }

    public void setCompetitionMember(CompetitionMember competitionMember){
        this.competitionMember = competitionMember;
    }

    public int compareTo(Result result1){
        return this.getTime() - result1.getTime();
    }

    public String toString() {
        return String.format(
                "Result Details:\n" +
                        "---------------\n" +
                        "ID:                %s\n" +
                        "Time:              %d seconds\n" +
                        "Distance:          %d meters\n" +
                        "Date of Result:    %s\n" +
                        "Swim Discipline:   %s\n" +
                        "Competition Member:%s",
                id, time, distance, dateOfResult, swimDiscipline, competitionMember
        );
    }
}
