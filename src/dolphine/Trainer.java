package dolphine;

import java.time.LocalDate;

public class Trainer extends User{
    private int seniority;

    public Trainer(String name, LocalDate dateOfBirth, Role role, int seniority){
        super (name,dateOfBirth,role);
        this.seniority=seniority;
    }
}

