package com.gama.whiv.transactionrepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionRepositoryController {

    @PostMapping("/add")
    public String addTransaction(@RequestBody AddTransactionRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/get/{transactionId}")
    public String getTransaction(@PathVariable String transactionId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PostMapping("/query")
    public String queryTransactions(@RequestBody TransactionQueryRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/by-customer/{customerId}")
    public String getTransactionsByCustomer(@PathVariable String customerId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @DeleteMapping("/delete/{transactionId}")
    public String deleteTransaction(@PathVariable String transactionId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public record AddTransactionRequest(
            String transactionId,
            String customerId,
            String sourceId,
            double amount,
            String currency,
            String timestamp,
            String transactionType
    ) {}

    public record TransactionQueryRequest(
            String customerId,
            String sourceId,
            String startDate,
            String endDate,
            double minAmount,
            double maxAmount
    ) {}
}
