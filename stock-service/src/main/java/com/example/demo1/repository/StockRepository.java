package com.example.demo1.repository;

import com.example.demo1.model.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {
    Optional<Stock> findStockByName(String name);

    @Query(value = "SELECT * FROM stock WHERE symbol = ?1 LIMIT 1", nativeQuery = true)
    Optional<Stock> findStockBySymbol(String symbol);

}
