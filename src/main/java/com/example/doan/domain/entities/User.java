package com.example.demotransaction.domain.entities;

import com.example.demotransaction.app.dtos.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Data
@NoArgsConstructor
@Entity
@Table(name = "accounts", schema = "player")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String userName;

    @Column(name = "point")
    private Integer point;

    @Column(name = "created_on")
    private LocalDateTime createdOn;


    public void fromUser(UserDTO user) {
        TimeZone timezone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        setUserName(user.getUserName());
        setPoint(user.getPoint());
        setCreatedOn(LocalDateTime.now(timezone.toZoneId()));
    }
}
