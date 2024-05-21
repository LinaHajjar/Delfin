package dolphine;

import java.time.LocalDate;

public class Trainer extends User{
    private int seniority;

    public Trainer(String name, LocalDate dateOfBirth, Role role, int seniority){
        super (name,dateOfBirth,role);
        this.seniority=seniority;
    }

    public Trainer(User user, int seniority){
        super(user.getId(),user.getName(),user.getDateOfBirth(),user.getRole());
        this.seniority = seniority;
    }

    @Override
    public String toString(){
        return super.toString() +
                "Senority: " + getSeniority() + "\n";
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }
}

