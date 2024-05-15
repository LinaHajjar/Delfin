package dolphine;

import java.time.LocalDate;
import java.time.Period;

public class User {
    private String id;
    private String name;
    private LocalDate dateOfBirth;
    private Role role;

    public User (String id, String name, LocalDate dateOfBirth, Role role){
        this.id=id;
        this.name=name;
        this.dateOfBirth=dateOfBirth;
        this.role=role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getAge(){
        return calculateAge();
    }
    private int calculateAge(){
        LocalDate currentDate = LocalDate.now();
        // Calculate the period between the birthdate and current date
        Period period = Period.between(this.dateOfBirth, currentDate);
        // Return the difference in years
        return period.getYears();
    }
}
