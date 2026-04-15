package com.example.fintech.service;

import com.example.fintech.entity.*;
import com.example.fintech.exception.CustomException;
import com.example.fintech.repository.*;
import com.example.fintech.dto.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository,
                              TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public String transfer(TransferRequest request) {

        Account sender = accountRepository.findByUserId(request.getSenderId())
                .orElseThrow(() -> new CustomException("Sender not found"));

        Account receiver = accountRepository.findByUserId(request.getReceiverId())
                .orElseThrow(() -> new CustomException("Receiver not found"));

        if (sender.getBalance() < request.getAmount()) {
            throw new CustomException("Insufficient balance");
        }

        sender.setBalance(sender.getBalance() - request.getAmount());
        receiver.setBalance(receiver.getBalance() + request.getAmount());

        accountRepository.save(sender);
        accountRepository.save(receiver);

        Transaction tx = new Transaction(null,
                request.getSenderId(),
                request.getReceiverId(),
                request.getAmount(),
                LocalDateTime.now());

        transactionRepository.save(tx);

        return "Transfer successful";
    }

    public List<Transaction> history(Long userId) {
        return transactionRepository.findBySenderIdOrReceiverId(userId, userId);
    }
}