package dolphine;

import java.time.LocalDate;

public class Member extends User{
    private boolean MemberIsActive;
    private MemberType memberType;

    public Member (String name, LocalDate dateOfBirth, Role role, boolean isActive, MemberType memberType) {
        super(name, dateOfBirth, role);
        this.MemberIsActive = MemberIsActive;
        this.memberType = memberType;
    }
        public boolean getIsActive() {
            return MemberIsActive;
        }
        public MemberType getMemberType() {
            return memberType;
        }
    }

