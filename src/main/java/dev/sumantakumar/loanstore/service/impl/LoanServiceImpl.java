package dev.sumantakumar.loanstore.service.impl;

import dev.sumantakumar.loanstore.dto.LoanDto;
import dev.sumantakumar.loanstore.entity.Loan;
import dev.sumantakumar.loanstore.error.InvalidPaymentDateException;
import dev.sumantakumar.loanstore.pojo.LoanDetails;
import dev.sumantakumar.loanstore.pojo.LoanResponse;
import dev.sumantakumar.loanstore.repository.LoanRepository;
import dev.sumantakumar.loanstore.service.LoanService;
import dev.sumantakumar.loanstore.utility.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LoanServiceImpl implements LoanService {

    private LoanRepository loanRepository;

    @Autowired
    public void setLoanRepository(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public List<LoanResponse> getLoans() {
        log.info("Request to get all loan details");

        return loanRepository.findAll()
                .stream()
                .map(each -> {
                    if (each.getPaymentDate().isAfter(each.getDueDate())) {
                        log.warn("Payment is after due date " + each);
                    }
                    return Utils.objectMapping(each);
                })
                .collect(Collectors.toList());

    }

    @Override
    public LoanResponse createNewLoan(LoanDto loanDto) {
        log.info("Request create new loan : " + loanDto);
        validateRequest(loanDto);
        log.info("Request validation is success for create new loan with request : " + loanDto);
        Loan loan = Utils.objectMapping(loanDto);
        Loan save = loanRepository.save(loan);
        log.info(loanDto + " Create new loan Response is : " + save);
        return Utils.objectMapping(save);
    }

    @Override
    public Map<String, LoanDetails> getRemainingLoansByLenders() {
        log.info("Request to get remaining loan by lenders");
        Map<String, LoanDetails> lenderAggregations = loanRepository.findAll().stream()
                .collect(Collectors.toMap(
                        Loan::getLenderId,
                        LoanDetails::new,
                        LoanDetails::combine
                ));
        log.info("Response for remaining loan by lenders is : "+lenderAggregations);
        return lenderAggregations;
    }
    @Override
    public Map<Double, LoanDetails> getRemainingLoansByInterest() {
        log.info("Request to get remaining loan by interest");
        Map<Double, LoanDetails> interestAggregations = loanRepository.findAll().stream()
                .collect(Collectors.toMap(
                        Loan::getInterestPerDay,
                        LoanDetails::new,
                        LoanDetails::combine
                ));
        log.info("Response for remaining loan by interest is : "+interestAggregations);
        return interestAggregations;
    }
    @Override
    public Map<String, LoanDetails> getRemainingLoansByCustomer() {
        log.info("Request to get remaining loan by customer");
        Map<String, LoanDetails> lenderAggregations = loanRepository.findAll().stream()
                .collect(Collectors.toMap(
                        Loan::getCustomerId,
                        LoanDetails::new,
                        LoanDetails::combine
                ));
        log.info("Response for remaining loan by customer is : "+lenderAggregations);
        return lenderAggregations;

    }

    private void validateRequest(LoanDto loanDto) {
        if (!isPaymentDateBeforeDueDate.test(loanDto.getPaymentDate(), loanDto.getDueDate())) {
            log.info("Payment date cannot be greater than due date.");
            throw new InvalidPaymentDateException("Payment date cannot be greater than due date.");
        }
    }

    BiPredicate<String, String> isPaymentDateBeforeDueDate = (paymentDate, dueDate) -> {
        LocalDate pDate = LocalDate.parse(paymentDate);
        LocalDate dDate = LocalDate.parse(dueDate);
        return pDate.isBefore(dDate);
    };

}
