package dev.sumantakumar.loanstore.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class LoanDto {
    @Pattern(regexp = "^(C\\d+)$", message = "Invalid Customer Id")
    @NotBlank(message = "Please provide Customer Id")
    private String customerId;
    @Pattern(regexp = "^(LEN\\d+)$", message = "Invalid Lender Id")
    @NotBlank(message = "Please provide Lender Id")
    private String lenderId;
    @NotNull(message = "Please provide amount")
    @Min(1)
    private Integer amount;
    @Min(1)
    private Integer remainingAmount;
    @NotBlank(message = "Please provide payment date")
    private String paymentDate;
    @NotNull(message = "Please provide interest per day")
    @Min(0)
    private double interestPerDay;
    @NotBlank(message = "Please provide due date")
    private String dueDate;
    @NotNull(message = "Please provide penanty per day")
    @Min(0)
    private double penaltyPerDay;
}
