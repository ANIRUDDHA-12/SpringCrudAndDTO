package com.firstProjectDemo.first_api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService){
        this.walletService=walletService;
    }
    @PostMapping
    public ResponseEntity<UserWallet> createWallet(@RequestBody Map<String,String> request){
        String userName=request.get("userName");
        UserWallet userWallet= walletService.createWallet(userName);
        return ResponseEntity.ok(userWallet);
    }

    @PostMapping("/{id}/funds")
    public ResponseEntity<CurrencyHolding> addFunds(@PathVariable Integer id,@RequestBody Map<String,Object> request){
        String currencyCode=(String) request.get("currencyCode");
        double amount = ((Number)request.get("amount")).doubleValue();
        return ResponseEntity.ok(walletService.addFunds(id, currencyCode, amount));
    }

    @GetMapping("/{id}/networth")
    public ResponseEntity<NetWorthResponseDT0> netWorthResponseDT0(@PathVariable Integer id){
        return ResponseEntity.ok(walletService.netWorthResponseDT0(id));
    }
}
