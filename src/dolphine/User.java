package dolphine;

import java.time.LocalDate;

public class User {
    private String id;
    private String name;
    private LocalDate dateOfBirth;
    private Role role;

    public User (String name, LocalDate dateOfBirth, Role role){
        this.name=name;
        this.dateOfBirth=dateOfBirth;
        this.role=role;
    }
}
