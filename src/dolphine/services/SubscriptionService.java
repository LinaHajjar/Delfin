package dolphine.services;

import dolphine.Member;
import dolphine.MemberType;

import java.time.LocalDate;
import java.util.ArrayList;

public class SubscriptionService {
    private final double inActiveMemberFee = 500;
    private final double baseMemberFee = 1600;
    private final double juniorMemberFeeDiscount = 600;
    private final double aboveSeniorBreakpointMemberFeeDiscountProcentage = 25;

    private final int seniorBreakPointAge = 60;
    private final int juniorBreakPointAge = 18;

    private final LocalDate seasonStartDate;

    public SubscriptionService() {
        this.seasonStartDate = calculateNextFirstOfAugust();
    }

    public double calculateSubcriptionFee(Member member){
        if (member.isActive()){
            return inActiveMemberFee;
        } else if (member.getMemberType() == MemberType.JUNIOR) {
            return baseMemberFee - juniorMemberFeeDiscount;
        } else if (member.getMemberType().equals(MemberType.SENIOR) && member.getAge() >= seniorBreakPointAge){
            return baseMemberFee * (100 - aboveSeniorBreakpointMemberFeeDiscountProcentage);
        } else {
            return baseMemberFee;
        }
    }

    public double calculateSubcriptionFee(Member member){
        if (member.isActive()){
            return inActiveMemberFee;
        } else if (member.getMemberType() == MemberType.JUNIOR) {
            return baseMemberFee - juniorMemberFeeDiscount;
        } else if (member.getMemberType().equals(MemberType.SENIOR) && member.getAge() >= seniorBreakPointAge){
            return baseMemberFee * (100 - aboveSeniorBreakpointMemberFeeDiscountProcentage);
        } else {
            return baseMemberFee;
        }
    }

    public String generateSubscriptionReport(ArrayList<Member> memberList){
        StringBuilder subscriptionReport = new StringBuilder();
        subscriptionReport.append("Subscription Report:\n");
        subscriptionReport.append(String.format("There are currently a total of %d members\n", memberList.size()));
        for (Member member : memberList) {
            if ((member.getAge() == seniorBreakPointAge - 1 || member.getAge() == juniorBreakPointAge) && seasonStartDate.isAfter(member.getDateOfBirth())){
                //TODO find out how to calculate the fee when the age is +1
            }
        }
        return subscriptionReport.toString();
    }

    private LocalDate calculateNextFirstOfAugust(){
        LocalDate currentDate = LocalDate.now();
        LocalDate nextFirstOfAugust;

        if (currentDate.getMonthValue() > 8 || (currentDate.getMonthValue() == 8 && currentDate.getDayOfMonth() > 1)) {
            // If the current month is after August or its August but past the 1st,
            // then calculate for next year's August
            nextFirstOfAugust = currentDate.plusYears(1).withMonth(8).withDayOfMonth(1);
        } else {
            // Otherwise, calculate for the current year's August
            nextFirstOfAugust = currentDate.withMonth(8).withDayOfMonth(1);
        }
        return nextFirstOfAugust;
    }
}
