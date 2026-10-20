package com.arshad.stock_trading_client.service;

import com.arshad.grpc.StockRequest;
import com.arshad.grpc.StockResponse;
import com.arshad.grpc.StockTradingServiceGrpc;
import org.springframework.stereotype.Service;

@Service
public class StockClientService {

    private StockTradingServiceGrpc.StockTradingServiceBlockingStub stockStub;

    public StockClientService(StockTradingServiceGrpc.StockTradingServiceBlockingStub stockStub){
        this.stockStub = stockStub;
    }

    public StockResponse getPrice(String symbol) {
        return stockStub.getStockPrice(
                StockRequest.newBuilder().setStockSymbol(symbol).build());
    }
}
