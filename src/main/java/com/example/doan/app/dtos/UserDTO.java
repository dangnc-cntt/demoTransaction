package com.example.demotransaction.app.dtos;

import com.example.demotransaction.domain.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDTO {

    private String userName;
    private Integer point;

}
