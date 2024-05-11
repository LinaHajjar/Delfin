package Dolphine;

import java.time.LocalDate;
import java.util.ArrayList;

public class SpareTimeMember extends Member{
    private String hobby;

    public SpareTimeMember (String id, String name, LocalDate dateOfBirth, Role role, boolean isActive, MemberType memberType, String hobby){
        super (id,name,dateOfBirth,role,isActive, memberType);
        this.hobby=hobby;
    }

}
