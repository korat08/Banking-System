package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.DepositRequest;
import com.example.demo.model.TransferRequest;
import com.example.demo.model.WithDrawRequest;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("create")
    public ResponseEntity<?> createAccount(@RequestBody Account account){
        try{
            return accountService.createAccount(account);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error :- " + e.getMessage());
        }
    }

    @PutMapping("withdraw")
    public ResponseEntity<?> withDrawMoney(@RequestBody WithDrawRequest withDrawRequest){
        try {
            return accountService.withDrawMoney(withDrawRequest);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error :- "+ e.getMessage());
        }
    }

    @PutMapping("deposit")
    public ResponseEntity<?> depositMoney(@RequestBody DepositRequest depositRequest){
        try{
            return accountService.depositMoney(depositRequest);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error :- "+ e.getMessage());
        }
    }

    @PutMapping("transfer")
    public ResponseEntity<?> transferMoney(@RequestBody TransferRequest transferRequest){
        try{
            return accountService.transferMoney(transferRequest);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error :- "+ e.getMessage());
        }
    }

    @GetMapping("balance/{accountNumber}")
    public ResponseEntity<?> getBalance(@PathVariable String accountNumber){
        try{
            return accountService.getBalance(accountNumber);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error :- "+ e.getMessage());
        }
    }

    @GetMapping("accountStatement/{accountNumber}")
    public ResponseEntity<?> getAccountStatement(@PathVariable String accountNumber){
        try {
            return accountService.getAccountStatement(accountNumber);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error :- "+ e.getMessage());
        }
    }


}
