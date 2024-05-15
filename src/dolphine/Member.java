package dolphine;

import java.time.LocalDate;

public class Member extends User {
    private boolean isActive;
    private MemberType memberType;
    private boolean hasPaidSubscription; // TODO Remake to have subscriptions saved in file instead with ID, to have a saved amount. Can be done from report or something.

    public Member(String id, String name, LocalDate dateOfBirth, Role role, boolean isActive, MemberType memberType, boolean hasPaidSubscription) {
        super(id, name, dateOfBirth, role);
        this.isActive = isActive;
        this.memberType = memberType;
        this.hasPaidSubscription = hasPaidSubscription;
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

    public boolean isHasPaidSubscription() {
        return hasPaidSubscription;
    }

    public void setHasPaidSubscription(boolean hasPaidSubscription) {
        this.hasPaidSubscription = hasPaidSubscription;
    }
}
