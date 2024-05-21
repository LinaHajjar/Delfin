package dolphine;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.UUID;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private LocalDate dateOfBirth;
    private Role role;

    public User(String name, LocalDate dateOfBirth, Role role) {
        this.id = generateId();
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    //constructor for already existing user
    public User(String id, String name, LocalDate dateOfBirth, Role role){
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
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

    public int getAge() {
        return calculateAge();
    }

    private static String generateId() { // private fordi den skal kun bruges her i klassen, man kan ikke calculate id ud fra klassen
        return UUID.randomUUID().toString();
    }

    private int calculateAge() { // private because to be used only in this class
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dateOfBirth, currentDate);
        return (period.getYears());
    }

    public String toString() {
        return String.format("  Name: %s\n" +
                        "  Date of Birth: %s\n" +
                        "  Role: %s",
                name, dateOfBirth, role);
    }
}
