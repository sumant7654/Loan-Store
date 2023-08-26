package dev.sumantakumar.loanstore.entity;

import dev.sumantakumar.loanstore.utility.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
public class Loan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_seq")
    @GenericGenerator(name = "loan_seq", strategy = "dev.sumantakumar.loanstore.utility.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "L"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%d") })
    @Column(name = "loan_id")
    private String loanId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name="lender_id")
    private String lenderId;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "remaining_amount")
    private Integer remainingAmount;

    @Column(name="payment_date")
    private LocalDate paymentDate;

    @Column(name = "interest_per_day")
    private double interestPerDay;

    @Column(name="due_date")
    private LocalDate dueDate;

    @Column(name="penalty_per_day")
    private double penaltyPerDay;

    private String cancel;


}
