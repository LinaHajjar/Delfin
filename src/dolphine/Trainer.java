package dolphine;

import java.time.LocalDate;

public class Trainer extends User{
    private int seniority;

    public Trainer(String id, String name, LocalDate dateOfBirth, Role role, int seniority){
        super(id,name,dateOfBirth,role);
        this.seniority=seniority;
    }

    public Trainer(User user, int seniority){
        super(user.getId(),user.getName(),user.getLocalDate,user.getRole());
        this.seniority = seniority;
    }
}

