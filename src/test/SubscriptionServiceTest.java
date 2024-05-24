package test;


import dolphine.Member;
import dolphine.MemberType;
import dolphine.Role;
import dolphine.Subscription;
import dolphine.services.SubscriptionService;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubscriptionServiceTest {
    private SubscriptionService subscriptionService;

    @org.junit.jupiter.api.Test
    public void testCalculateSubscriptionFee_AtNextSeason_InactiveMember() {
        Member activeMember = new Member("John", subscriptionService.getNextSeasonStartDate().minusYears(35), Role.COMPETITIONMEMBER, false);
        assertEquals(subscriptionService.getInActiveMemberFee(), subscriptionService.calculateSubscriptionFeeAtNextSeason(activeMember), 0.01);
    }

    @org.junit.jupiter.api.Test
    public void testCalculateSubscriptionFee_AtNextSeason_JuniorMember() {
        // Junior member not at juniorBreakPointAge at seasonStartDate
        Member juniorMember = new Member("Alice", subscriptionService.getNextSeasonStartDate().minusYears(subscriptionService.getJuniorBreakPointAge() - 2), Role.COMPETITIONMEMBER, true);
        assertEquals(subscriptionService.getBaseMemberFee() - subscriptionService.getJuniorMemberFeeDiscount(), subscriptionService.calculateSubscriptionFeeAtNextSeason(juniorMember), 0.01);
    }

    @org.junit.jupiter.api.Test
    public void testCalculateSubscriptionFee_AtNextSeason_TurningSeniorMemberBeforeNextSeason() {
        // Junior member turning above juniorBreakPointAge before seasonStartDate
        Member juniorMember = new Member("Alice", subscriptionService.getNextSeasonStartDate().minusYears(subscriptionService.getJuniorBreakPointAge()).minusDays(1), Role.COMPETITIONMEMBER, true);
        assertEquals(subscriptionService.getBaseMemberFee(), subscriptionService.calculateSubscriptionFeeAtNextSeason(juniorMember), 0.01);
    }

    @org.junit.jupiter.api.Test
    public void testCalculateSubscriptionFee_AtNextSeason_AboveSeniorMember() {
        // Above senior member not above seniorBreakPointAge at seasonStartDate
        Member seniorMember = new Member("Bob", subscriptionService.getNextSeasonStartDate().minusYears(subscriptionService.getSeniorBreakPointAge() + 2), Role.COMPETITIONMEMBER, true);
        assertEquals(subscriptionService.getBaseMemberFee() * (100 - subscriptionService.getAboveSeniorBreakpointMemberFeeDiscountPercentage()) / 100, subscriptionService.calculateSubscriptionFeeAtNextSeason(seniorMember), 0.01);
    }

    @org.junit.jupiter.api.Test
    public void testCalculateSubscriptionFee_AtNextSeason_TurningAboveSeniorMemberBeforeNextSeason() {
        // Above senior member is turning above seniorBreakPointAge before seasonStartDate
        Member seniorMember = new Member("Bob", subscriptionService.getNextSeasonStartDate().minusYears(subscriptionService.getSeniorBreakPointAge() + 1).minusDays(1), Role.COMPETITIONMEMBER, true);
        assertEquals(subscriptionService.getBaseMemberFee() * (100 - subscriptionService.getAboveSeniorBreakpointMemberFeeDiscountPercentage()) / 100, subscriptionService.calculateSubscriptionFeeAtNextSeason(seniorMember), 0.01);
    }

    @org.junit.jupiter.api.Test
    public void testCalculateSubscriptionFee_AtNextSeason_SeniorMember() {
        Member activeMember = new Member("John", subscriptionService.getNextSeasonStartDate().minusYears(35), Role.COMPETITIONMEMBER, true);
        assertEquals(subscriptionService.getBaseMemberFee(), subscriptionService.calculateSubscriptionFeeAtNextSeason(activeMember), 0.01);
    }

    @org.junit.jupiter.api.Test
    public void testGenerateSubscriptionReport() {
        ArrayList<Member> memberList = new ArrayList<>();
        memberList.add(new Member("John", subscriptionService.getNextSeasonStartDate().minusYears(35), Role.COMPETITIONMEMBER, false));
        memberList.add(new Member("Alice", subscriptionService.getNextSeasonStartDate().minusYears(subscriptionService.getJuniorBreakPointAge() - 2), Role.COMPETITIONMEMBER, true));
        memberList.add(new Member("Alice", subscriptionService.getNextSeasonStartDate().minusYears(subscriptionService.getJuniorBreakPointAge()).minusDays(1), Role.COMPETITIONMEMBER, true));
        memberList.add(new Member("Bob", subscriptionService.getNextSeasonStartDate().minusYears(subscriptionService.getSeniorBreakPointAge() + 2), Role.COMPETITIONMEMBER, true));
        memberList.add(new Member("Bob", subscriptionService.getNextSeasonStartDate().minusYears(subscriptionService.getSeniorBreakPointAge() + 1).minusDays(1), Role.COMPETITIONMEMBER, true));
        memberList.add(new Member("John", subscriptionService.getNextSeasonStartDate().minusYears(35), Role.COMPETITIONMEMBER, true));

        String expectedReport = "Subscription Report:\n" +
                "There are currently a total of 6 members\n" +
                "Total subscription fee at next season start: 7100,00\n";

        assertEquals(expectedReport, subscriptionService.generateSubscriptionReport(memberList));
    }

    @org.junit.jupiter.api.Test
    public void testGetUnpaidSubscriptionList() {
        ArrayList<Subscription> subscriptionArrayList = new ArrayList<>();
        Member member1 = new Member("John", subscriptionService.getNextSeasonStartDate().minusYears(35), Role.COMPETITIONMEMBER, false);
        Member member2 = new Member("Alice", subscriptionService.getNextSeasonStartDate().minusYears(subscriptionService.getJuniorBreakPointAge() - 2), Role.COMPETITIONMEMBER, true);
        Member member3 = new Member("Alice", subscriptionService.getNextSeasonStartDate().minusYears(subscriptionService.getJuniorBreakPointAge()).minusDays(1), Role.COMPETITIONMEMBER, true);
        Member member4 = new Member("Bob", subscriptionService.getNextSeasonStartDate().minusYears(subscriptionService.getSeniorBreakPointAge() + 2), Role.COMPETITIONMEMBER, true);
        Member member5 = new Member("Bob", subscriptionService.getNextSeasonStartDate().minusYears(subscriptionService.getSeniorBreakPointAge() + 1).minusDays(1), Role.COMPETITIONMEMBER, true);
        Member member6 = new Member("John", subscriptionService.getNextSeasonStartDate().minusYears(35), Role.COMPETITIONMEMBER, true);

        Subscription subscription1 = new Subscription(member1, 200, LocalDate.now().minusDays(1), true);
        Subscription subscription2 = new Subscription(member2, 600, LocalDate.now().plusDays(1), false);
        Subscription subscription3 = new Subscription(member3, 800, LocalDate.now().minusDays(1), false);
        Subscription subscription4 = new Subscription(member4, 200, LocalDate.now().minusDays(100), false);
        Subscription subscription5 = new Subscription(member5, 500, LocalDate.now().plusDays(10), false);
        Subscription subscription6 = new Subscription(member6, 1000, LocalDate.now().plusDays(100), false);

        subscriptionArrayList.add(subscription1);
        subscriptionArrayList.add(subscription2);
        subscriptionArrayList.add(subscription3);
        subscriptionArrayList.add(subscription4);
        subscriptionArrayList.add(subscription5);
        subscriptionArrayList.add(subscription6);

        ArrayList<Subscription> unpaidSubscriptionList = new ArrayList<>();
        unpaidSubscriptionList.add(subscription3);
        unpaidSubscriptionList.add(subscription4);

        assertEquals(unpaidSubscriptionList, subscriptionService.getUnpaidSubscriptionList(subscriptionArrayList));
    }

    @BeforeEach
    void setUp() {
        subscriptionService = new SubscriptionService();
    }
}