package com.example.doan.app.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PointTransactionDTO {

    private Integer userId;
    private Integer amount;
    private Boolean isRollBack;
}
