package dev.sumantakumar.loanstore.utility;

import dev.sumantakumar.loanstore.dto.LoanDto;
import dev.sumantakumar.loanstore.entity.Loan;
import dev.sumantakumar.loanstore.pojo.LoanResponse;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
public class Utils {
    public static LoanResponse objectMapping(Loan loan){
        log.info("Request to convert loan object to response : "+loan);
        LoanResponse loanResponse = new LoanResponse();
        loanResponse.setLoanId(loan.getLoanId());
        loanResponse.setCustomerId(loan.getCustomerId());
        loanResponse.setLenderId(loan.getLenderId());
        loanResponse.setAmount(loan.getAmount());
        loanResponse.setRemainingAmount(loan.getRemainingAmount());
        loanResponse.setPaymentDate(loan.getPaymentDate());
        loanResponse.setInterestPerDay(loan.getInterestPerDay());
        loanResponse.setDueDate(loan.getDueDate());
        loanResponse.setPenaltyPerDay(loan.getPenaltyPerDay());
        loanResponse.setCancel(loan.getCancel());
        log.info("After conversation of loan object to response : "+loanResponse);
        return loanResponse;
    }
    public static Loan objectMapping(LoanDto loanDto){
        log.info("Request to convert loan DTO object to loan entity : "+loanDto);
        Loan loan = new Loan();
        loan.setCustomerId(loanDto.getCustomerId());
        loan.setLenderId(loanDto.getLenderId());
        loan.setAmount(loanDto.getAmount());
        loan.setRemainingAmount(loanDto.getRemainingAmount());
        loan.setPaymentDate(LocalDate.parse(loanDto.getPaymentDate()));
        loan.setInterestPerDay(loanDto.getInterestPerDay());
        loan.setDueDate(LocalDate.parse(loanDto.getDueDate()));
        loan.setPenaltyPerDay(loanDto.getPenaltyPerDay());
        log.info("After conversation of loan DTO object to loan entity : "+loan);
        return loan;
    }
}
