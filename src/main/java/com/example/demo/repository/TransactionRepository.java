package com.example.demo.repository;

import com.example.demo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

//    @Query("SELECT t FROM Transaction t WHERE t.account.accountNumber = :accountNumber")
//    List<Transaction> findTransactionsByAccountNumber(@Param("accountNumber") String accountNumber);

    List<Transaction> findByAccount_AccountNumber(String accountNumber);

}
