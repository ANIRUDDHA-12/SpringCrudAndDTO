package com.firstProjectDemo.first_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/accounts/{id}/transactions")
public class TransactionHistoryController {

    private final TransactionHistoryService transactionHistoryService;

    public TransactionHistoryController(TransactionHistoryService transactionHistoryService){
        this.transactionHistoryService=transactionHistoryService;
    }

    @GetMapping("/naive")
    public ResponseEntity<List<Transaction>> getNaive(@PathVariable Long id){
        List<Transaction> transactions= transactionHistoryService.getHistoryNaive(id);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/join-fetch")
    public ResponseEntity<List<Transaction>> getJoinFetch(@PathVariable Long id){
        List<Transaction> transactions=transactionHistoryService.getHistoryWithJoinFetch(id);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/entity-graph")
    public ResponseEntity<List<Transaction>> getEntityGraph(@PathVariable Long id){
        List<Transaction> transactions=transactionHistoryService.getHistoryWithEntityGraph(id);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/summary")
    public ResponseEntity<List<TransactionSummaryDto>> getSummary(@PathVariable Long id){
        List<TransactionSummaryDto> transactionSummaryDtos=transactionHistoryService.getHistorySummary(id);
        return ResponseEntity.ok(transactionSummaryDtos);
    }

}
