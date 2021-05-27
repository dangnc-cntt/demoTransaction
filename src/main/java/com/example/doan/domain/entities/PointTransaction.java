package com.example.demotransaction.domain.entities;

import com.example.demotransaction.domain.utils.PostgreSQLEnumType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "point_transaction", schema = "player")
@TypeDef(name = "pg-enum", typeClass = PostgreSQLEnumType.class)
public class PointTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Integer userId;

    @Enumerated(value = EnumType.STRING)
    @Type(type = "pg-enum")
    @Column(name = "type")
    private TransactionType type;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "created_at")
    private LocalDateTime createAt;
}
