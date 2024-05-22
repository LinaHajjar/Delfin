package dolphine.services;

import dolphine.Member;
import dolphine.MemberType;
import dolphine.Subscription;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class SubscriptionService {
    private final double inActiveMemberFee = 500;
    private final double baseMemberFee = 1600;
    private final double juniorMemberFeeDiscount = 600;
    private final double aboveSeniorBreakpointMemberFeeDiscountProcentage = 25;

    private final int seniorBreakPointAge = 60;
    private final int juniorBreakPointAge = 18;

    private final int totalDaysInYear = 365;

    public SubscriptionService() {
    }

    public double calculateSubscriptionFee(Member member) {
        if (!member.getIsActive()) {
            // Inactive subscription fee
            return inActiveMemberFee;
        } else if (member.getMemberType().equals(MemberType.JUNIOR)) {
            // Junior member fee discount
            return baseMemberFee - juniorMemberFeeDiscount;
        } else if (member.getAge() > seniorBreakPointAge) {
            // Above senior member fee discount
            return baseMemberFee * (100 - aboveSeniorBreakpointMemberFeeDiscountProcentage) / 100;
        } else {
            // Base subscription fee
            return baseMemberFee;
        }
    }

    public double calculateSubscriptionFeeForRestOfSeason(Member member) {
        double memberFee = calculateSubscriptionFee(member);
        return memberFee * calculatePercentageOfYearUntilNextSeasonStart() / 100;
    }

    // TODO Create calculate subscription left for season.

    public double calculateSubscriptionFeeAtNextSeason(Member member) {
        if (!member.getIsActive()) {
            // Inactive subscription fee
            return inActiveMemberFee;
        } else if (member.getMemberType().equals(MemberType.JUNIOR) && !(member.getAge() == juniorBreakPointAge - 1 && isBirthDayBeforeSeasonStart(member.getDateOfBirth()))) {
            // Junior member fee discount given to members that are not juniorBreakPointAge at the seasonStartDate
            return baseMemberFee - juniorMemberFeeDiscount;
        } else if (member.getAge() > seniorBreakPointAge || member.getAge() == seniorBreakPointAge && isBirthDayBeforeSeasonStart(member.getDateOfBirth())) {
            // Above senior member fee discount given to members that are not above seniorBreakPointAge at the seasonStartDate
            return baseMemberFee * (100 - aboveSeniorBreakpointMemberFeeDiscountProcentage) / 100;
        } else {
            // Base subscription fee
            return baseMemberFee;
        }
    }

    public String generateSubscriptionReport(ArrayList<Member> memberList) {
        StringBuilder subscriptionReport = new StringBuilder();
        subscriptionReport.append("Subscription Report:\n");
        subscriptionReport.append(String.format("There are currently a total of %d members\n", memberList.size()));
        double totalSubscriptionFee = 0;
        for (Member member : memberList) {
            double subscriptionFee = calculateSubscriptionFeeAtNextSeason(member);
            totalSubscriptionFee += subscriptionFee;
        }
        subscriptionReport.append(String.format("Total subscription fee at next season start: %.2f\n", totalSubscriptionFee));
        return subscriptionReport.toString();
    }

    public ArrayList<Subscription> getUnpaidSubscriptionList(ArrayList<Subscription> subscriptionList) {
        ArrayList<Subscription> unpaidSubscriptions = new ArrayList<>();
        for (Subscription subscription : subscriptionList) {
            if (!subscription.isPayed() && subscription.getDueDate().isBefore(LocalDate.now())){
                // Unpaid subscription with a past due date
                unpaidSubscriptions.add(subscription);
            }
        }
        return unpaidSubscriptions;
    }

    private LocalDate calculateNextFirstOfAugust() {
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

    public double calculatePercentageOfYearUntilNextSeasonStart(){
        LocalDate today = LocalDate.now();
        // Step 1: Calculate the number of days between today and the next season start
        long daysUntilNextSeasonStart = ChronoUnit.DAYS.between(today, getNextSeasonStartDate());
        // Step 2: Calculate the percentage of the year
        return ((double) daysUntilNextSeasonStart / totalDaysInYear) * 100;
    }

    private boolean isBirthDayBeforeSeasonStart(LocalDate birthday) {
        int birthdayMonth = birthday.getMonthValue();
        int birthdayDay = birthday.getDayOfMonth();
        int seasonStartMonth = getNextSeasonStartDate().getMonthValue();
        int seasonStartDay = getNextSeasonStartDate().getDayOfMonth();

        if (birthdayMonth < seasonStartMonth) {
            return true;  // Birthday month is earlier
        } else if (birthdayMonth > seasonStartMonth) {
            return false;  // Birthday month is later
        } else {
            // Months are the same, compare days
            return birthdayDay < seasonStartDay;
        }
    }

    public double getInActiveMemberFee() {
        return inActiveMemberFee;
    }

    public double getBaseMemberFee() {
        return baseMemberFee;
    }

    public double getJuniorMemberFeeDiscount() {
        return juniorMemberFeeDiscount;
    }

    public double getAboveSeniorBreakpointMemberFeeDiscountPercentage() {
        return aboveSeniorBreakpointMemberFeeDiscountProcentage;
    }

    public int getSeniorBreakPointAge() {
        return seniorBreakPointAge;
    }

    public int getJuniorBreakPointAge() {
        return juniorBreakPointAge;
    }

    public LocalDate getNextSeasonStartDate() {
        return calculateNextFirstOfAugust();
    }
}
