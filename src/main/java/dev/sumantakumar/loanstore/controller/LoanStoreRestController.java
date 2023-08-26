package dev.sumantakumar.loanstore.controller;

import dev.sumantakumar.loanstore.dto.LoanDto;
import dev.sumantakumar.loanstore.pojo.LoanDetails;
import dev.sumantakumar.loanstore.pojo.LoanResponse;
import dev.sumantakumar.loanstore.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class LoanStoreRestController {
    private LoanService loanService;

    @Autowired
    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("loans")
    public ResponseEntity<List<LoanResponse>> getLoans() {
        return ResponseEntity.ok(loanService.getLoans());
    }

    @PostMapping("loan")
    public ResponseEntity<LoanResponse> createLoan(@RequestBody @Valid LoanDto loanDto) {
        return ResponseEntity.ok(loanService.createNewLoan(loanDto));
    }

    @GetMapping("remaining/lenders")
    public ResponseEntity<Map<String, LoanDetails>> getRemainingLoansByLenders() {
        return ResponseEntity.ok(loanService.getRemainingLoansByLenders());
    }

    @GetMapping("remaining/interest")
    public ResponseEntity<Map<Double, LoanDetails>> getRemainingLoansByInterest() {
        return ResponseEntity.ok(loanService.getRemainingLoansByInterest());
    }

    @GetMapping("remaining/customer")
    public ResponseEntity<Map<String, LoanDetails>> getRemainingLoansByCustomer() {
        return ResponseEntity.ok(loanService.getRemainingLoansByCustomer());
    }



}
