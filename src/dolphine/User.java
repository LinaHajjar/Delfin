package dolphine;

import java.time.LocalDate;

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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Role getRole() {
        return role;
    }
}
