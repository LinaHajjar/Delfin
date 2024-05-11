package Dolphine;

import java.time.LocalDate;

public class Trainer extends User{
    private int seniority;

    public Trainer(String id, String name, LocalDate dateOfBirth, Role role, int seniority){
        super (id,name,dateOfBirth,role);
        this.seniority=seniority;
    }
}

