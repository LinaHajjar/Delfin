package dolphine;

import java.time.LocalDate;

public class Member extends User{
    private boolean IsActive;
    private MemberType memberType;

    public Member (String name, LocalDate dateOfBirth, Role role, boolean IsActive, MemberType memberType) {
        super(name, dateOfBirth, role);
        boolean IsActive;
        this.IsActive = IsActive;
        this.memberType = memberType;
        setMemberType(); //udregne membertype udfra alderen
    }
       /* public Member(int user, boolean isActive, MemberType memberType){
            super(user.getName(), user.getDateOfBirth(),user.getRole());
            this.isActive=isActive;
            this.memberType=memberType;*/

        public boolean getIsActive() {
            return IsActive;
        }
        public void setActive(boolean active) {
                isActive = active;
            }
        public MemberType getMemberType() {
            return memberType;
        }

        //inddeling i aldersgruppe
    public void setMemberType() {
        int age = LocalDate.now().getYear() - getDateOfBirth().getYear();
        if (age > 18 && age < 60) {
            this.memberType = MemberType.JUNIOR;
        } else if (age >= 60) {
            this.memberType = MemberType.SENIOR;
        }
    }

    public String toString() {
        return "Member information: " +
                "\nName: " + getName() + "\ndateOfBirth: " + getDateOfBirth() + "\nMember type (age group): " + memberType "\nMembership status: " + isActive + "\nRole: " + getRole()
    }
}


