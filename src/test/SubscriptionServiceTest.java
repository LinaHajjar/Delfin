package test;


import dolphine.Member;
import dolphine.MemberType;
import dolphine.Role;
import dolphine.services.SubscriptionService;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubscriptionServiceTest {
    private SubscriptionService subscriptionService;

    @org.junit.jupiter.api.Test
    public void testCalculateSubscriptionFee_AtNextSeason_InactiveMember() {
        Member activeMember = new Member("1", "John", subscriptionService.getSeasonStartDate().minusYears(35), Role.SWIMMEMBER, false, MemberType.SENIOR,true);
        assertEquals(subscriptionService.getInActiveMemberFee(), subscriptionService.calculateSubscriptionFeeAtNextSeason(activeMember), 0.01);
    }

    @org.junit.jupiter.api.Test
    public void testCalculateSubscriptionFee_AtNextSeason_JuniorMember() {
        // Junior member not at juniorBreakPointAge at seasonStartDate
        Member juniorMember = new Member("2", "Alice", subscriptionService.getSeasonStartDate().minusYears(subscriptionService.getJuniorBreakPointAge() - 2), Role.SWIMMEMBER, true, MemberType.JUNIOR,true);
        assertEquals(subscriptionService.getBaseMemberFee() - subscriptionService.getJuniorMemberFeeDiscount(), subscriptionService.calculateSubscriptionFeeAtNextSeason(juniorMember), 0.01);
    }

    @org.junit.jupiter.api.Test
    public void testCalculateSubscriptionFee_AtNextSeason_TurningSeniorMemberBeforeNextSeason() {
        // Junior member turning above juniorBreakPointAge before seasonStartDate
        Member juniorMember = new Member("3", "Alice", subscriptionService.getSeasonStartDate().minusYears(subscriptionService.getJuniorBreakPointAge()).minusDays(1), Role.SWIMMEMBER, true, MemberType.JUNIOR,true);
        assertEquals(subscriptionService.getBaseMemberFee(), subscriptionService.calculateSubscriptionFeeAtNextSeason(juniorMember), 0.01);
    }

    @org.junit.jupiter.api.Test
    public void testCalculateSubscriptionFee_AtNextSeason_AboveSeniorMember() {
        // Above senior member not above seniorBreakPointAge at seasonStartDate
        Member seniorMember = new Member("4", "Bob", subscriptionService.getSeasonStartDate().minusYears(subscriptionService.getSeniorBreakPointAge() + 2), Role.SWIMMEMBER, true, MemberType.SENIOR,true);
        assertEquals(subscriptionService.getBaseMemberFee() * (100 - subscriptionService.getAboveSeniorBreakpointMemberFeeDiscountProcentage()) / 100, subscriptionService.calculateSubscriptionFeeAtNextSeason(seniorMember), 0.01);
    }

    @org.junit.jupiter.api.Test
    public void testCalculateSubscriptionFee_AtNextSeason_TurningAboveSeniorMemberBeforeNextSeason() {
        // Above senior member is turning above seniorBreakPointAge before seasonStartDate
        Member seniorMember = new Member("5", "Bob", subscriptionService.getSeasonStartDate().minusYears(subscriptionService.getSeniorBreakPointAge() + 1).minusDays(1), Role.SWIMMEMBER, true, MemberType.SENIOR,true);
        assertEquals(subscriptionService.getBaseMemberFee() * (100 - subscriptionService.getAboveSeniorBreakpointMemberFeeDiscountProcentage()) / 100, subscriptionService.calculateSubscriptionFeeAtNextSeason(seniorMember), 0.01);
    }

    @org.junit.jupiter.api.Test
    public void testCalculateSubscriptionFee_AtNextSeason_SeniorMember() {
        Member activeMember = new Member("6", "John", subscriptionService.getSeasonStartDate().minusYears(35), Role.SWIMMEMBER, true, MemberType.SENIOR,true);
        assertEquals(subscriptionService.getBaseMemberFee(), subscriptionService.calculateSubscriptionFeeAtNextSeason(activeMember), 0.01);
    }

    @org.junit.jupiter.api.Test
    public void testGenerateSubscriptionReport() {
        ArrayList<Member> memberList = new ArrayList<>();
        memberList.add(new Member("1", "John", subscriptionService.getSeasonStartDate().minusYears(35), Role.SWIMMEMBER, false, MemberType.SENIOR,true));
        memberList.add(new Member("2", "Alice", subscriptionService.getSeasonStartDate().minusYears(subscriptionService.getJuniorBreakPointAge() - 2), Role.SWIMMEMBER, true, MemberType.JUNIOR,true));
        memberList.add(new Member("3", "Alice", subscriptionService.getSeasonStartDate().minusYears(subscriptionService.getJuniorBreakPointAge()).minusDays(1), Role.SWIMMEMBER, true, MemberType.JUNIOR,true));
        memberList.add(new Member("4", "Bob", subscriptionService.getSeasonStartDate().minusYears(subscriptionService.getSeniorBreakPointAge() + 2), Role.SWIMMEMBER, true, MemberType.SENIOR,true));
        memberList.add(new Member("5", "Bob", subscriptionService.getSeasonStartDate().minusYears(subscriptionService.getSeniorBreakPointAge() + 1).minusDays(1), Role.SWIMMEMBER, true, MemberType.SENIOR,true));
        memberList.add(new Member("6", "John", subscriptionService.getSeasonStartDate().minusYears(35), Role.SWIMMEMBER, true, MemberType.SENIOR,true));

        String expectedReport = "Subscription Report:\n" +
                "There are currently a total of 6 members\n" +
                "Total subscription fee at next season start: 7100,00\n";

        assertEquals(expectedReport, subscriptionService.generateSubscriptionReport(memberList));
    }

    @org.junit.jupiter.api.Test
    public void testGetMembersWithUnpaidSubscription() {
        ArrayList<Member> memberList = new ArrayList<>();
        Member member1 = new Member("1", "John", subscriptionService.getSeasonStartDate().minusYears(35), Role.SWIMMEMBER, false, MemberType.SENIOR, true);
        Member member2 = new Member("2", "Alice", subscriptionService.getSeasonStartDate().minusYears(subscriptionService.getJuniorBreakPointAge() - 2), Role.SWIMMEMBER, true, MemberType.JUNIOR, false);
        Member member3 = new Member("3", "Alice", subscriptionService.getSeasonStartDate().minusYears(subscriptionService.getJuniorBreakPointAge()).minusDays(1), Role.SWIMMEMBER, true, MemberType.JUNIOR, true);
        Member member4 = new Member("4", "Bob", subscriptionService.getSeasonStartDate().minusYears(subscriptionService.getSeniorBreakPointAge() + 2), Role.SWIMMEMBER, true, MemberType.SENIOR, false);
        Member member5 = new Member("5", "Bob", subscriptionService.getSeasonStartDate().minusYears(subscriptionService.getSeniorBreakPointAge() + 1).minusDays(1), Role.SWIMMEMBER, true, MemberType.SENIOR, false);
        Member member6 = new Member("6", "John", subscriptionService.getSeasonStartDate().minusYears(35), Role.SWIMMEMBER, true, MemberType.SENIOR, true);

        memberList.add(member1);
        memberList.add(member2);
        memberList.add(member3);
        memberList.add(member4);
        memberList.add(member5);
        memberList.add(member6);

        ArrayList<Member> memberListWithUnpaidSubscription = new ArrayList<>();
        memberListWithUnpaidSubscription.add(member2);
        memberListWithUnpaidSubscription.add(member4);
        memberListWithUnpaidSubscription.add(member5);

        assertEquals(memberListWithUnpaidSubscription, subscriptionService.getMembersWithUnpaidSubscription(memberList));
    }

    @BeforeEach
    void setUp() {
        subscriptionService = new SubscriptionService();
    }
}