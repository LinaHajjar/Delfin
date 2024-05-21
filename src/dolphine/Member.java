package dolphine;

import java.time.LocalDate;

public class Member extends User{
    private boolean isActive;
    private MemberType memberType;

    public Member (String name, LocalDate dateOfBirth, Role role, boolean isActive, MemberType memberType){
        super (name,dateOfBirth,role);
        this.isActive=isActive;
        this.memberType=memberType;
    }

    public Member(User user, boolean isActive, MemberType memberType){
        super(user.getName(), user.getDateOfBirth(),user.getRole());
        this.isActive=isActive;
        this.memberType=memberType;

    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }
}
