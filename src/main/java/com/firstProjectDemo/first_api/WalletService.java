package com.firstProjectDemo.first_api;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    private final UserWalletRepository userWalletRepository;
    private final CurrencyHoldingRepository currencyHoldingRepository;
    private final MarketDataService marketDataService;
    private final AlertService alertService;

    public WalletService(UserWalletRepository userWalletRepository,
                         CurrencyHoldingRepository currencyHoldingRepository,
                         MarketDataService marketDataService,
                         AlertService alertService
    ){
        this.currencyHoldingRepository=currencyHoldingRepository;
        this.userWalletRepository=userWalletRepository;
        this.alertService=alertService;
        this.marketDataService=marketDataService;
    }

    public UserWallet createWallet(String userName){
        UserWallet userWallet= userWalletRepository.save(new UserWallet(userName));
        alertService.sendWelcomeAlert(userName);
        return userWallet;
    }
    @Transactional
    public CurrencyHolding addFunds(Integer id,String currencyCode,double amount){
        UserWallet wallet=userWalletRepository.findById(id)
                .orElseThrow(()->new WalletNotFoundException("wallet not found for the said"));

        CurrencyHolding currencyHolding=new CurrencyHolding(currencyCode.toUpperCase(),amount,wallet);
                wallet.getCurrencyHoldingList().add(currencyHolding);

                return currencyHoldingRepository.save(currencyHolding);
    }

    public NetWorthResponseDT0 netWorthResponseDT0(Integer id){
        UserWallet wallet=userWalletRepository.findById(id)
                .orElseThrow(()->new WalletNotFoundException("wallet not found"));

        ExchangeRateResponse exchangeRates= marketDataService.exchangeRates();

        double netWorthToUsd = wallet.getCurrencyHoldingList().stream()
                .mapToDouble(holding->{
                    String code= holding.getCurrencyCode();
                    Double ratesToUsd = exchangeRates.rates().get(code);
                    if (ratesToUsd==null){
                        throw new IllegalArgumentException("Unsupported currency"+code);
                    }
                    return holding.getAmount()/ratesToUsd;
                })
                .sum();
        return  new NetWorthResponseDT0(wallet.getUserName(),netWorthToUsd);
    }
}
