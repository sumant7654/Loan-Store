package dev.sumantakumar.loanstore.pojo;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class LoanResponse {
    private String loanId;
    private String customerId;
    private String lenderId;
    private Integer amount;
    private Integer remainingAmount;
    private LocalDate paymentDate;
    private double interestPerDay;
    private LocalDate dueDate;
    private double penaltyPerDay;
    private String cancel;
}
