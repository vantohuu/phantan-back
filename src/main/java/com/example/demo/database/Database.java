//package com.example.demo.database;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class Database {
//    private static final Logger logger = LoggerFactory.getLogger(Database.class);
//    @Bean
//    CommandLineRunner initDatabase(ProductRepositories productRepositories)
//    {
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                Product productA = new Product("MacBook Pro 16", 1010, 2400.0, "");
//                Product productB = new Product("MacBook Pro 16", 1010, 2400.0, "");
//                logger.info("insert data: " + productRepositories.save(productA));
//                logger.info("insert data: " + productRepositories.save(productB));
//            }
//        };
//    }
//}
