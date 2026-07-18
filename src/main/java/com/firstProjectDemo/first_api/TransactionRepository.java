//package com.firstProjectDemo.first_api;
//
//import org.springframework.data.jpa.repository.EntityGraph;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface TransactionRepository extends JpaRepository<Transaction,Long> {
//
//
//    // Variant -A native lazy loading here (This is what gives N+1 problem at it accordingly)
//    List<Transaction> findByFromAccount_id(Long id);
//
//    @Query("SELECT t FROM Transaction t JOIN FETCH t.toAccount JOIN FETCH t.fromAccount WHERE t.fromAccount.id = :id")
//    List<Transaction> findByFromAccountIdWithCounterparty(@Param("id") Long id);
//
//    // Variant C — @EntityGraph fix (alternative to B, same intent)
//    @EntityGraph(attributePaths = {"toAccount", "fromAccount"})
//    List<Transaction> findWithEntityGraphByFromAccount_Id(Long id);
//
//    // Variant D — Projection
//    @Query("SELECT t.id AS transactionId, t.amount AS amount, t.timestamp AS timestamp, "
//            + "t.toAccount.holderName AS counterpartyName "
//            + "FROM Transaction t WHERE t.fromAccount.id= :id")
//    List<TransactionSummaryProjection> findSummaryByFromAccountId(@Param("id") Long id);
//
//
//
//}
