package com.example.demotransaction.domain.services;

import com.example.demotransaction.app.dtos.UserDTO;
import com.example.demotransaction.domain.entities.PointTransaction;
import com.example.demotransaction.domain.entities.TransactionType;
import com.example.demotransaction.domain.entities.User;
import com.example.demotransaction.domain.repository.TransactionRepository;
import com.example.demotransaction.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.TimeZone;

@Service
@Slf4j
public class MainService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public void save(UserDTO dto) {
        User user = new User();
        user.fromUser(dto);
        userRepository.save(user);
    }
    int count;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Retryable(maxAttempts = 10, backoff = @Backoff(delay = 100), value = {RuntimeException.class,})
    public PointTransaction addPoin(Integer userId, Integer amount, boolean isRollback) {
        User user = userRepository.getById(userId);

        if (user == null) {
            throw new RuntimeException("user null!");
        }
        user.setPoint(user.getPoint() + amount);
        userRepository.save(user);

        count++;
        log.info("===================================================retry!!!========= "+count);
        if (isRollback) {
            throw new RuntimeException("rollback");
        }

        PointTransaction pointTransaction = new PointTransaction();
        pointTransaction.setAmount(amount);
        pointTransaction.setType(TransactionType.ADD);
        pointTransaction.setUserId(userId);
        pointTransaction.setCreateAt(LocalDateTime.now(TimeZone.getTimeZone("Asia/Ho_Chi_Minh").toZoneId()));
        return transactionRepository.save(pointTransaction);
    }
}
