package dev.sumantakumar.loanstore.repository;

import dev.sumantakumar.loanstore.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, String> {
}
