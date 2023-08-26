package dev.sumantakumar.loanstore.pojo;

import dev.sumantakumar.loanstore.entity.Loan;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoanDetails {
    private double totalRemainingAmount;
    private double totalInterest;
    private double totalPenalty;

    public LoanDetails(Loan loan) {
        this.totalRemainingAmount = loan.getRemainingAmount();
        this.totalInterest = loan.getInterestPerDay();
        this.totalPenalty = loan.getPenaltyPerDay();
    }

    public LoanDetails combine(LoanDetails other) {
        this.totalRemainingAmount += other.totalRemainingAmount;
        this.totalInterest += other.totalInterest;
        this.totalPenalty += other.totalPenalty;
        return this;
    }
}
