package com.firstProjectDemo.first_api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CryptoController {
    private final CryptoService cryptoService;

    public CryptoController(CryptoService cryptoService){
        this.cryptoService=cryptoService;
    }

    @GetMapping("/crypto/bitcoin")
    public ResponseEntity<String> getBitcoin(){
        String price = cryptoService.getBitcoin();
        return ResponseEntity.ok(price);
    }


}
