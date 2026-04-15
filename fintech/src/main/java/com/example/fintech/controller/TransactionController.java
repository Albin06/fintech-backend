package com.example.fintech.controller;

import com.example.fintech.dto.TransferRequest;
import com.example.fintech.service.TransactionService;
import com.example.fintech.entity.Transaction;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferRequest request) {
        return transactionService.transfer(request);
    }

    @GetMapping("/transactions/{userId}")
    public List<Transaction> history(@PathVariable Long userId) {
        return transactionService.history(userId);
    }
}