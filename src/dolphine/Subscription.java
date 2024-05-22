package dolphine;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Subscription implements Serializable {
    private String id;
    private Member member;
    private double amount;
    private LocalDate dueDate;
    private boolean isPayed;

    public Subscription(Member member, double amount, LocalDate dueDate, boolean isPayed) {
        this.id = generateId();
        this.member = member;
        this.amount = amount;
        this.dueDate = dueDate;
        this.isPayed = isPayed;
    }

    public Subscription(String id, Member member, double amount, LocalDate dueDate, boolean isPayed) {
        this.id = id;
        this.member = member;
        this.amount = amount;
        this.dueDate = dueDate;
        this.isPayed = isPayed;
    }


    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String toString() {
        String paymentStatus = isPayed ? "Paid" : "Unpaid";
        String formattedDate = dueDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        return String.format("Subscription Details:\n" +
                        "Member: %s\n" +
                        "Amount: $%.2f\n" +
                        "Due Date: %s\n" +
                        "Payment Status: %s\n",
                member.getName(), amount, formattedDate, paymentStatus);
    }

}
