package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public ResponseEntity<?> createAccount(Account account) {

        if(accountRepository.existsById(account.getAccountNumber())){
            return ResponseEntity.badRequest().body("Account Number already Exist.....");
        }

        accountRepository.save(account);
        return ResponseEntity.ok("Account Created Sucessfully...");
    }

    @Transactional
    public ResponseEntity<?> withDrawMoney(WithDrawRequest withDrawRequest) {

        Optional<Account> accountOpt = accountRepository.findById(withDrawRequest.getAccountNumber());

        if(accountOpt.isEmpty()){
            return ResponseEntity.badRequest().body("Account Number not Exist....");
        }

        Account account = accountOpt.get();
        if (account.getBalance().compareTo(withDrawRequest.getAmount()) < 0) {
            return ResponseEntity.badRequest().body("Insufficient balance!");
        }

        account.setBalance(account.getBalance().subtract(withDrawRequest.getAmount()));
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(withDrawRequest.getAmount());
        transaction.setTransactionType(TransactionType.WITHDRAW);

        transactionRepository.save(transaction);

        return ResponseEntity.ok("Withdrawal successful! New balance: " + account.getBalance());
    }

    @Transactional
    public ResponseEntity<?> depositMoney(DepositRequest depositRequest) {
        Optional<Account> accountOpt = accountRepository.findById(depositRequest.getAccountNumber());

        if(accountOpt.isEmpty()){
            return ResponseEntity.badRequest().body("Account Number not Exist....");
        }

        Account account = accountOpt.get();
        if (depositRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.badRequest().body("Deposit amount must be greater than zero!");
        }

        account.setBalance(account.getBalance().add(depositRequest.getAmount()));
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setAmount(depositRequest.getAmount());
        transaction.setAccount(account);

        transactionRepository.save(transaction);

        return ResponseEntity.ok("Deposit successful! New balance: " + account.getBalance());
    }

    @Transactional
    public ResponseEntity<?> transferMoney(TransferRequest transferRequest) {
        Optional<Account> accountOpt1 = accountRepository.findById(transferRequest.getSenderAccountNumber());
        Optional<Account> accountOpt2 = accountRepository.findById(transferRequest.getReceiverAccountNumber());

        if(accountOpt1.isEmpty()){
            return ResponseEntity.badRequest().body("Sender account not found..........");
        }
        if(accountOpt2.isEmpty()){
            return ResponseEntity.badRequest().body("Receiver account not found..........");
        }

        Account senderAccount = accountOpt1.get();
        Account receiverAccount = accountOpt2.get();

        if(senderAccount.getBalance().compareTo(transferRequest.getAmount()) < 0){
            return ResponseEntity.badRequest().body("Insufficient balance!");
        }

        senderAccount.setBalance(senderAccount.getBalance().subtract(transferRequest.getAmount()));
        receiverAccount.setBalance(receiverAccount.getBalance().add(transferRequest.getAmount()));
        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

        Transaction transaction = new Transaction();

        transaction.setAccount(senderAccount);
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setAmount(transferRequest.getAmount());

        transaction.setFromAccountNum(senderAccount.getAccountNumber());
        transaction.setToAccountNum(receiverAccount.getAccountNumber());

        transactionRepository.save(transaction);

        return ResponseEntity.ok("Transfer SuccessFully............");
    }

    public ResponseEntity<?> getBalance(String accountNumber) {
        if(!accountRepository.existsById(accountNumber)){
            return ResponseEntity.badRequest().body("Account Number not Exist....");
        }
        Optional<Account> opt = accountRepository.findById(accountNumber);
        Account account = opt.get();
        return ResponseEntity.ok(" Account Balance is :  " + account.getBalance() );
    }

    public ResponseEntity<?> getAccountStatement(String accountNumber) {
        if(!accountRepository.existsById(accountNumber)){
            return ResponseEntity.badRequest().body("Account Number not Exist....");
        }

        List<Transaction> transactions = transactionRepository.findByAccount_AccountNumber(accountNumber);

        if(transactions.isEmpty()){
            return ResponseEntity.ok("No transaction found");
        }
        return ResponseEntity.ok(transactions);
    }
}
