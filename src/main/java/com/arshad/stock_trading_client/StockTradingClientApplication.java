package com.arshad.stock_trading_client;

import com.arshad.grpc.StockTradingServiceGrpc;
import com.arshad.stock_trading_client.service.StockClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.grpc.client.ImportGrpcClients;

@SpringBootApplication
@ImportGrpcClients(
        target = "stock-service",
        types = StockTradingServiceGrpc.StockTradingServiceBlockingStub.class)
public class StockTradingClientApplication implements CommandLineRunner {

    private StockClientService service;

    public StockTradingClientApplication(StockClientService service){
        this.service = service;
    }

	public static void main(String[] args) {
		SpringApplication.run(StockTradingClientApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Grpc Server Response : " + service.getPrice("AAPL"));

    }
}
