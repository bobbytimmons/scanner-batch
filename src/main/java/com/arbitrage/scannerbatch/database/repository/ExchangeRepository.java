package com.arbitrage.scannerbatch.database.repository;

import com.arbitrage.scannerbatch.database.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
}
