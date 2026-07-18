//package com.firstProjectDemo.first_api;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Min;
//import lombok.*;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class Transaction {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Min(1)
//    private BigDecimal amount;
//
//    @Column(nullable = false)
//    private LocalDateTime timestamp;
//
//    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "from_account_id", nullable = false)
//    private Account fromAccount;
//
//    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "to_account_id", nullable = false)
//    private Account toAccount;
//
//
//}
