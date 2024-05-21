package dolphine;

import java.time.LocalDate;
import java.time.Period;

public class Member extends User {
    private boolean isActive;
    private MemberType memberType;

    public Member(String name, LocalDate dateOfBirth, Role role, boolean isActive) {
        super(name, dateOfBirth, role);
        this.isActive = isActive;
        setMemberType(); //udregne membertype udfra alderen
    }

    public Member(User user, boolean isActive) {
        super(user.getId(), user.getName(), user.getDateOfBirth(), user.getRole());
        this.isActive = isActive;
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

    //inddeling i aldersgruppe
    public void setMemberType() {
        int age = calculateAge();
        if (age < 18) {
            this.memberType = MemberType.JUNIOR;
        } else {
            this.memberType = MemberType.SENIOR;
        }
    }

    private int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(this.getDateOfBirth(), currentDate);
        return age.getYears();
    }

    public String toString() {
        return super.toString() +
                "\nMember type (age group): " + memberType + "\nMembership status: " + (isActive ? "active" : "inactive") + "\n";
    }
}


