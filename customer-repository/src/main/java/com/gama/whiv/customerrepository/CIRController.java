package com.gama.whiv.customerrepository;

    import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cir")
public class CIRController {
//
//    @Autowired
//    private CIRService cirService;
//
//    // Bulk Insertion APIs
//    @PostMapping("/kyc/bulk")
//    public ResponseEntity<String> insertKYCInBulk(@RequestBody List<KYCDataDTO> kycDataList, @RequestParam String fiId) {
//        cirService.insertKYCInBulk(kycDataList, fiId);
//        return ResponseEntity.ok("KYC data inserted in bulk successfully.");
//    }
//
//    @PostMapping("/transactions/bulk")
//    public ResponseEntity<String> insertTransactionsInBulk(@RequestBody List<TransactionDataDTO> transactionDataList, @RequestParam String fiId) {
//        cirService.insertTransactionsInBulk(transactionDataList, fiId);
//        return ResponseEntity.ok("Transaction data inserted in bulk successfully.");
//    }
//
//    @PostMapping("/accounts/bulk")
//    public ResponseEntity<String> insertAccountsInBulk(@RequestBody List<AccountDataDTO> accountDataList, @RequestParam String fiId) {
//        cirService.insertAccountsInBulk(accountDataList, fiId);
//        return ResponseEntity.ok("Account data inserted in bulk successfully.");
//    }
//
//    // FI-Specific Data Retrieval APIs
//    @GetMapping("/fi/{fiId}/kyc")
//    public ResponseEntity<List<KYCDataDTO>> getKYCByFI(@PathVariable String fiId) {
//        return ResponseEntity.ok(cirService.getKYCByFI(fiId));
//    }
//
//    @GetMapping("/fi/{fiId}/transactions")
//    public ResponseEntity<List<TransactionDataDTO>> getTransactionsByFI(@PathVariable String fiId) {
//        return ResponseEntity.ok(cirService.getTransactionsByFI(fiId));
//    }
//
//    @GetMapping("/fi/{fiId}/accounts")
//    public ResponseEntity<List<AccountDataDTO>> getAccountsByFI(@PathVariable String fiId) {
//        return ResponseEntity.ok(cirService.getAccountsByFI(fiId));
//    }
//
//    // Time-Based Data Retrieval
//    @GetMapping("/kyc/time-range")
//    public ResponseEntity<List<KYCDataDTO>> getKYCByTimeRange(@RequestParam String startTime, @RequestParam String endTime) {
//        return ResponseEntity.ok(cirService.getKYCByTimeRange(startTime, endTime));
//    }
//
//    @GetMapping("/transactions/time-range")
//    public ResponseEntity<List<TransactionDataDTO>> getTransactionsByTimeRange(@RequestParam String startTime, @RequestParam String endTime) {
//        return ResponseEntity.ok(cirService.getTransactionsByTimeRange(startTime, endTime));
//    }
//
//    @GetMapping("/accounts/time-range")
//    public ResponseEntity<List<AccountDataDTO>> getAccountsByTimeRange(@RequestParam String startTime, @RequestParam String endTime) {
//        return ResponseEntity.ok(cirService.getAccountsByTimeRange(startTime, endTime));
//    }
//
//    // Combined Data Retrieval for Investigation
//    @GetMapping("/investigate/{customerId}")
//    public ResponseEntity<UnifiedSearchResponseDTO> investigateCustomer(@PathVariable Long customerId) {
//        return ResponseEntity.ok(cirService.investigateCustomer(customerId));
//    }
//
//    // FI Traceability API
//    @GetMapping("/trace/fi/{fiId}")
//    public ResponseEntity<List<TraceabilityDTO>> getTraceabilityByFI(@PathVariable String fiId) {
//        return ResponseEntity.ok(cirService.getTraceabilityByFI(fiId));
//    }
}
