package com.arshad.stock_trading_client.service;

import com.arshad.grpc.StockRequest;
import com.arshad.grpc.StockResponse;
import com.arshad.grpc.StockTradingServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class StockClientServiceForServerStreaming {

    private StockTradingServiceGrpc.StockTradingServiceStub stockStub;

    public StockClientServiceForServerStreaming(StockTradingServiceGrpc.StockTradingServiceStub stockStub){
        this.stockStub = stockStub;
    }

    public void subscribeStockPrice(String symbol){
        StockRequest request = StockRequest.newBuilder()
                .setStockSymbol(symbol)
                .build();

        stockStub.subscribeStockPrice(request, new StreamObserver<StockResponse>() {

            @Override
            public void onNext(StockResponse response) {
                System.out.println("Stock Price Update: " + response.getStockSymbol() +
                        " Price: " + response.getStockPrice() +
                        " Time: " + response.getTimestamp());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(" Error: " + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Stock price stream live update completed !");
            }
        });
    }
}
