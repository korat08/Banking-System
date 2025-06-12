package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.DepositRequest;
import com.example.demo.model.TransferRequest;
import com.example.demo.model.WithDrawRequest;
import com.example.demo.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("create")
    public ResponseEntity<?> createAccount(@Valid @RequestBody Account account){
        return accountService.createAccount(account);
    }

    @PutMapping("withdraw")
    public ResponseEntity<?> withDrawMoney(@Valid @RequestBody WithDrawRequest withDrawRequest){
        return accountService.withDrawMoney(withDrawRequest);
    }

    @PutMapping("deposit")
    public ResponseEntity<?> depositMoney(@Valid @RequestBody DepositRequest depositRequest){
        return accountService.depositMoney(depositRequest);
    }

    @PutMapping("transfer")
    public ResponseEntity<?> transferMoney(@RequestBody TransferRequest transferRequest){
        return accountService.transferMoney(transferRequest);
    }

    @GetMapping("balance/{accountNumber}")
    public ResponseEntity<?> getBalance(@PathVariable String accountNumber){
        if(accountNumber == null || !accountNumber.matches("\\d{10}")){
            return ResponseEntity.badRequest().body("Account number must be exactly 10 digits");
        }
        return accountService.getBalance(accountNumber);
    }

    @GetMapping("accountStatement/{accountNumber}")
    public ResponseEntity<?> getAccountStatement(@PathVariable String accountNumber){
        if(accountNumber == null || !accountNumber.matches("\\d{10}")){
            return ResponseEntity.badRequest().body("Account number must be exactly 10 digits");
        }
        return accountService.getAccountStatement(accountNumber);
    }
}
