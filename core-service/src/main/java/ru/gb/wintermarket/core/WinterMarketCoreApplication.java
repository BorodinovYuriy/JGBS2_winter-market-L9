package ru.gb.wintermarket.core;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.gb.wintermarket.core.repositories.ProductRepository;

@SpringBootApplication
public class WinterMarketCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(WinterMarketCoreApplication.class, args);

	}

}

