package dolphine;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class User {
    private String id;
    private String name;
    private LocalDate dateOfBirth;
    private Role role;

    ArrayList<User> users =new ArrayList<>();

    public User (String name, LocalDate dateOfBirth, Role role){
        this.id=CalculateID(users);
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


    private static String CalculateID(ArrayList<User> users){ // private fordi den skal kun bruges her i klassen, man kan ikke calculate id ud fra klassen

        int id = 0;
            for(User u: users){
                int idPars=Integer.parseInt(u.getId());
                if(idPars>id){
                    id=idPars;
                }
            }
            id+=1;

        String idString=String.valueOf(id); //converting the int ID to String
        return (idString);
    }

    private int calculateAge () { // private because to be used only in this class
        LocalDate currentDate=LocalDate.now();
        Period period = Period.between(dateOfBirth, currentDate);
        return (period.getYears());
    }

    public int getage(){
        return calculateAge();
    }

}
