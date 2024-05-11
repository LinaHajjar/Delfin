package Dolphine;

import java.time.LocalDate;

public class Member extends User{
    private boolean isActive;
    private MemberType memberType;

    public Member (String id, String name, LocalDate dateOfBirth, Role role, boolean isActive, MemberType memberType){
        super (id,name,dateOfBirth,role);
        this.isActive=isActive;
        this.memberType=memberType;
    }

}
