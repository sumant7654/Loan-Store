package dev.sumantakumar.loanstore.service;

import dev.sumantakumar.loanstore.dto.LoanDto;
import dev.sumantakumar.loanstore.entity.Loan;
import dev.sumantakumar.loanstore.pojo.LoanDetails;
import dev.sumantakumar.loanstore.pojo.LoanResponse;

import java.util.List;
import java.util.Map;

public interface LoanService {
    List<LoanResponse> getLoans();

    LoanResponse createNewLoan(LoanDto loanDto);

    Map<String, LoanDetails> getRemainingLoansByLenders();

    Map<Double, LoanDetails> getRemainingLoansByInterest();

    Map<String, LoanDetails> getRemainingLoansByCustomer();
}
