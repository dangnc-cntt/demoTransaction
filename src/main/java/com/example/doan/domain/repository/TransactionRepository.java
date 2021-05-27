package com.example.demotransaction.domain.repository;

import com.example.demotransaction.domain.entities.PointTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<PointTransaction, Long> {

}
