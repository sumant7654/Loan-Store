package dev.sumantakumar.loanstore.pojo;

import dev.sumantakumar.loanstore.entity.Loan;
import lombok.Data;
import lombok.Getter;

@Getter
public class LoanSummary {
    private double totalRemainingAmount;
    private double totalInterest;
    private double totalPenalty;

    public LoanSummary() {
        this.totalRemainingAmount = 0;
        this.totalInterest = 0;
        this.totalPenalty = 0;
    }

    public LoanSummary add(Loan loan) {
        this.totalRemainingAmount += loan.getRemainingAmount();
        this.totalInterest += loan.getInterestPerDay();
        this.totalPenalty += loan.getPenaltyPerDay();
        return this;
    }

    public LoanSummary combine(LoanSummary other) {
        this.totalRemainingAmount += other.totalRemainingAmount;
        this.totalInterest += other.totalInterest;
        this.totalPenalty += other.totalPenalty;
        return this;
    }

    // Getters for totalRemainingAmount, totalInterest, totalPenalty...
}
