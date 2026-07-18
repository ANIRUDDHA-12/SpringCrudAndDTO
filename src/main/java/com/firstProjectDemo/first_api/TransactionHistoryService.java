//package com.firstProjectDemo.first_api;
//
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class TransactionHistoryService {
//    private TransactionRepository transactionRepository;
//
//    public TransactionHistoryService(TransactionRepository transactionRepository){
//        this.transactionRepository=transactionRepository;
//    }
//
//    public List<Transaction> getHistoryNaive(Long id){
//       List<Transaction> transactions= transactionRepository.findByFromAccount_id(id);
//        for(Transaction t:transactions){
//            if(t.getToAccount()!=null){
//                t.getToAccount().getHolderName();
//            }
//        }
//        return transactions;
//    }
//
//    public List<Transaction> getHistoryWithJoinFetch(Long id){
//        return transactionRepository.findByFromAccountIdWithCounterparty(id);
//    }
//
//    public List<Transaction> getHistoryWithEntityGraph(Long id){
//        return transactionRepository.findWithEntityGraphByFromAccount_Id(id);
//    }
//
//   public  List<TransactionSummaryDto> getHistorySummary( @Param("id") Long id){
//        List<TransactionSummaryProjection> summaryProjections=transactionRepository.findSummaryByFromAccountId(id);
//
//        return summaryProjections.stream()
//                .map(p->new TransactionSummaryDto(
//                        p.getTransactionId(),
//                        p.getAmount(),
//                        p.getTimeStamp(),
//                        p.getCounterPartyName()
//                )).collect(Collectors.toList());
//   }
//
//}
