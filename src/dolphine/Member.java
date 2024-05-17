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
    }
        public Member(int user, boolean isActive, MemberType memberType){
            super(user.getName(), user.getDateOfBirth(),user.getRole());
            this.isActive=isActive;
            this.memberType=memberType;

    }
        public boolean getIsActive() {
            return IsActive;
        }
        public MemberType getMemberType() {
            return memberType;
        }
    }

